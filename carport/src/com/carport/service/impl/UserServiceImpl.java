/**
 * 
 */
package com.carport.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carport.bean.Carport;
import com.carport.bean.CarportHistory;
import com.carport.bean.UserInfo;
import com.carport.dao.UserInfoMapper;
import com.carport.model.UserInfoModel;
import com.carport.service.CarportHistoryService;
import com.carport.service.CarportService;
import com.carport.service.UserService;
import com.carport.util.Constants;
import com.carport.util.MD5Utils;
import com.carport.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 描述：用户功能实现类
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午9:07:01
 * @since
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	//@Autowired
	//private CarportMapper carportMapper;
	
	private CarportHistoryService chService = new CarportHistoryServiceImpl();

	private CarportService carService = new CarportServiceImpl();
	
	@Override
	public int register(UserInfo user) {
		logger.info("调用开始！");
		List<UserInfo> userInfos = userInfoMapper.selectUserInfoByCarNo(user
				.getCar_no());
		if (userInfos != null && userInfos.size() > 0) {
			// 表示该用户 该车牌号已经存在
			return -10002;
		}
		List<UserInfo> userInfos2 = userInfoMapper.selectUserInfoByJobNumber(user.getJob_number());
		if (userInfos2 != null && userInfos2.size() > 0) {
			// 表示该用户 该工号已经存在
			return -10003;
		}
		String user_pwd_md5 = MD5Utils.convertStringToMD5(user.getUser_pwd());
		user.setUser_pwd(user_pwd_md5);
		user.setIs_system("0");
		int result = userInfoMapper.insertUserInfo(user);
		if (result > 0) {
			return 0;
		}
		// 返回1 则表示
		logger.info("调用开始！");
		return -10001;
	}

	@Override
	public int login(String user_id, String user_pwd) {

		UserInfo userInfo = userInfoMapper.selectUserInfoById(user_id);

		if (userInfo != null) {
			// 比较密码的Md5值
			String user_pwd_md5 = MD5Utils.convertStringToMD5(user_pwd);
			String user_pwd_db = userInfo.getUser_pwd();
			if (user_pwd_md5.equals(user_pwd_db)) {
				return 0;
			}
		} else {
			// 表示该用户不存在
			return -10006;
		}
		return -10005;
	}

	@Override
	public boolean isExist(String car_no) {
		// 使用car_no 查询是否已经存在该车牌号
		List<UserInfo> userInfos = userInfoMapper.selectUserInfoByCarNo(car_no);
		if (userInfos != null && userInfos.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int isUsable(UserInfo userInfo) {
		String user_id = userInfo.getUser_id();
		String org_id = userInfo.getOrg_id();
		String datetime = userInfo.getReg_time();
		String apartment_id = userInfo.getApartment_id();
		// 查询userinfo该用户能不能抢车位 status = 1表示可以
		UserInfo userquery1 = userInfoMapper.selectUserInfoById(user_id);
		if (userquery1 == null) {
			return -10001;
		}
		
		String isStatus = userquery1.getStatus();
		if ("1".equals(isStatus)) {
			CarportHistory requestCh = new CarportHistory();
			requestCh.setOrg_id(org_id);
			requestCh.setDatetime(datetime);
			requestCh.setUser_id(user_id);
			// 查询 carporthistory 判断是否已经抢过
			CarportHistory carportHistory = chService.queryCarportHistoryByUserIdAndDate(requestCh);
			if (carportHistory == null) {
				Carport requestCar = new Carport();
				requestCar.setOrg_id(org_id);
				requestCar.setDatetime(datetime);
				requestCar.setApartment_id(apartment_id);
				List<Carport> carports = carService.getCarportByOrgIdAndStatus1(requestCar);
				if (carports != null && carports.size() > 0) {
					// 表示该用户可抢车位
					return 0;
				} else {
					// 表示该部门下已经不存在可抢车位
					return -10003;
				}
			} else {
				// 表示该用户已经在当天成功抢过了车位
				return -10002;
			}
		} else {
			// 表示该用户不可用
			return -10001;
		}
	}

	@Override
	public List<UserInfo> findAllUser() {
		List<UserInfo> userInfos = userInfoMapper.selectAllUser();
		return userInfos;
	}

	@Override
	public List<UserInfo> getUserInfoByOrgId(String org_id) {
		if (StringUtils.isEmpty(org_id)) {
			return null;
		}
		List<UserInfo> userInfos = userInfoMapper.selectUserInfoByOrgId(org_id);
		return userInfos;
	}

	@Override
	public boolean modifyUserStatusTo1(String user_id) {
		if (StringUtils.isEmpty(user_id)) {
			return false;
		}
		int result = userInfoMapper.updateUserStatusTo1ById(user_id);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyUserStatusTo0(String user_id) {
		if (StringUtils.isEmpty(user_id)) {
			return false;
		}
		int result = userInfoMapper.updateUserStatusTo0ById(user_id);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyUserPassword(UserInfo userInfo) {
		
		if (userInfo == null) {
			return false;
		}
		
		String user_pwd_md5 = MD5Utils.convertStringToMD5(userInfo.getUser_pwd());
		userInfo.setUser_pwd(user_pwd_md5);

		int result = userInfoMapper.udpateUserPasswordById(userInfo);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserInfo> getUserInfoByApId(String apartment_id) {
		if (StringUtils.isEmpty(apartment_id)) {
			return null;
		}
		List<UserInfo> userInfos = userInfoMapper
				.selectUserInfoByApId(apartment_id);

		return userInfos;
	}

	@Override
	public boolean deleteUserInfoByUserId(String user_id) {
		if (StringUtils.isEmpty(user_id)) {
			return false;
		}
		int result = userInfoMapper.deleteUserInfoByUserId(user_id);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserInfo modifyUserInfoByUserId(UserInfo userInfo) {
		if (userInfo == null) {
			return null;
		}
		if (StringUtils.isEmpty(userInfo.getIs_system())) {
			userInfo.setIs_system("0");
		}
		int result = userInfoMapper.udpateUserInfoById(userInfo);
		if (result > 0) {
			return userInfoMapper.selectUserInfoById(userInfo.getUser_id());
		}
		return null;
	}

	@Override
	public UserInfo adminLogin(String user_name, String user_pwd) {
		if (StringUtils.isEmpty(user_name) && StringUtils.isEmpty(user_pwd)) {
			return null;
		}
		List<UserInfo> userInfos = userInfoMapper.selelctUserInfoByUserName(user_name);
		if (userInfos != null && userInfos.size() > 0) {
			UserInfo userInfo = userInfos.get(0);
			String user_pwd_md5 = MD5Utils.convertStringToMD5(user_pwd);
			if (user_pwd_md5.equals(userInfo.getUser_pwd())) {
				// 表示管理员登陆成功
				return userInfo;
			}
		}
		return null;
	}

	@Override
	public PageInfo<UserInfoModel> queryUserInfoByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? Constants.PAGE_NO : pageNo;
		pageSize = pageSize == null ? Constants.PAGE_SIZE : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<UserInfoModel> userInfoModels = userInfoMapper.selectUserInfoByPage();
		PageInfo<UserInfoModel> pages = new PageInfo<UserInfoModel>(userInfoModels);
		return pages;
	}
}

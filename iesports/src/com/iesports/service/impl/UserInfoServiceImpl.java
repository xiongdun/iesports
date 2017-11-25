/**
 * 
 */
package com.iesports.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iesports.dao.bean.UserInfo;
import com.iesports.dao.mapper.UserInfoMapper;
import com.iesports.service.UserInfoService;
import com.iesports.util.DataList;
import com.iesports.util.security.MD5Util;

/**
 * 描述：
 * 
 * @author xiongdun
 * @created 2016年9月1日 上午10:00:20
 * @since
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);
	
	@Resource
	private UserInfoMapper userInfoDao;
	@Override
	public List<UserInfo> queryAllUserInfo() {
		logger.info("查询开始！");
		List<UserInfo> list = this.userInfoDao.selectAll();
		if (list == null) {
			return null;
		}
		logger.info("查询结束！");
		return list;
	}

	@Override
	public UserInfo queryUserInfoById(String id) {
		return null;
	}

	@Override
	public UserInfo queryUserInfoByName(String name) {
		
		return null;
	}

	@Override
	public boolean modifyUserInfoById(String id) {
		return false;
	}

	@Override
	public boolean deleteUserInfoById(String id) {
		return false;
	}

	@Override
	public boolean addUserInfo(DataList data) {
		if (data == null) {
			return false;
		}
		
		String password = data.getString("password");
		String password2md5 = MD5Util.convertStringToMD5(password);
		data.set("password", password2md5);
		int result = this.userInfoDao.insertUserInfo(new UserInfo());
		if (result > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean login(String name, String password) {
		if (name == null || "".equals(name)) {
			return false;
		}
		if (password == null || "".equals(password)) {
			return false;
		}
		List<UserInfo> list = this.userInfoDao.selectByName(name);
		
		String pwd = null;
		if (list.size() == 1) {
			UserInfo users = list.get(0);
			pwd = users.getPassword();
		}
		String md5Pwd = MD5Util.convertStringToMD5(password);
		boolean isEqual = md5Pwd.equals(pwd);
		
		return isEqual;
	}

}

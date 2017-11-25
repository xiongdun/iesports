/**
 * 
 */
package com.carport.service;

import java.util.List;

import com.carport.bean.UserInfo;
import com.carport.model.UserInfoModel;
import com.github.pagehelper.PageInfo;

/**
 * 描述：用户功能
 * 
 * @author zhangyijie
 * @created 2016年12月2日 上午8:58:11
 * @since
 */
public interface UserService {

	/**
	 * 描述：用户注册
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 上午9:16:42
	 * @since
	 * @param user
	 * @return
	 */
	public int register(UserInfo user);

	/**
	 * 描述：登录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午1:42:00
	 * @since
	 * @param user_id
	 * @param user_pwd
	 * @return
	 */
	public int login(String user_id, String user_pwd);

	/**
	 * 描述：判断用户是否已经存在
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 上午9:16:52
	 * @since
	 * @param car_no
	 * @return
	 */
	public boolean isExist(String car_no);

	/**
	 * 描述：判断用户是否可以抢车位
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 上午9:17:04
	 * @since
	 * @param user_id
	 * @param org_no
	 * @return
	 */
	public int isUsable(UserInfo userInfo);

	/**
	 * 描述：查询所有用户信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午12:47:38
	 * @since
	 * @return
	 */
	public List<UserInfo> findAllUser();

	/**
	 * 描述：按组织机构Id查询用户信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午7:28:57
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<UserInfo> getUserInfoByOrgId(String org_id);

	/**
	 * 描述：按apartment_id查询用户信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月6日 上午9:46:49
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<UserInfo> getUserInfoByApId(String apartment_id);

	/**
	 * 描述：按user_id修改用户状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午2:13:27
	 * @since
	 * @param user_id
	 * @return
	 */
	public boolean modifyUserStatusTo1(String user_id);

	/**
	 * 描述：按user_id修改用户状态0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午2:13:50
	 * @since
	 * @param user_id
	 * @return
	 */
	public boolean modifyUserStatusTo0(String user_id);

	/**
	 * 描述：修改密码
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午2:20:00
	 * @since
	 * @param user_id
	 * @param user_pwd
	 * @return
	 */
	public boolean modifyUserPassword(UserInfo userInfo);

	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo modifyUserInfoByUserId(UserInfo userInfo);
	
	/**
	 * 描述：按user_id删除
	 * 
	 * @author zhangyijie
	 * @created 2016年12月6日 下午2:06:03
	 * @since
	 * @param user_id
	 * @return
	 */
	public boolean deleteUserInfoByUserId(String user_id);
	
	/**
	 * 管理员的登录
	 * @param user_name
	 * @param user_pwd
	 * @return
	 */
	public UserInfo adminLogin(String user_name, String user_pwd);
	
	/**
	 * 描述：分页查询用户信息
	 * @author xiongdun
	 * @created 2016年12月15日 下午8:25:12
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<UserInfoModel> queryUserInfoByPage(Integer pageNo, Integer pageSize);
	
}

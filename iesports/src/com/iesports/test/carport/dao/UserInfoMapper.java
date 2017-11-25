/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.UserInfo;

/**
 * 描述：抢车位历史映射
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午5:47:09
 * @since
 */
public interface UserInfoMapper {

	/**
	 * 描述：插入用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:42:07
	 * @since
	 * @param user
	 * @return
	 */
	public int insertUserInfo(UserInfo user);

	/**
	 * 描述：查询所有用户
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:42:18
	 * @since
	 * @return
	 */
	public List<UserInfo> selectAllUser();

	/**
	 * 描述：按user_id 查询用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:42:25
	 * @since
	 * @param user_id
	 * @return
	 */
	public UserInfo selectUserInfoById(String user_id);

	/**
	 * 描述：按car_no 查询用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:42:45
	 * @since
	 * @param car_no
	 * @return
	 */
	public List<UserInfo> selectUserInfoByCarNo(String car_no);

	/**
	 * 描述：按job_number查询用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:42:59
	 * @since
	 * @param job_number
	 * @return
	 */
	public List<UserInfo> selectUserInfoByJobNumber(String job_number);

	/**
	 * 描述：按用户名查询用户记录
	 * @param user_name
	 * @return
	 */
	public List<UserInfo> selelctUserInfoByUserName(String user_name);
	/**
	 * 描述：按org_id 查询用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:43:15
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<UserInfo> selectUserInfoByOrgId(String org_id);
	
	/**
	 * 按org_id 和 user_id查询用户信息
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:43:15
	 * @param userInfo
	 * @return
	 */
	public UserInfo selectUserInfoByOrgIdAndUserIdAndApId(UserInfo userInfo);

	/**
	 * 描述：按apartment_id查询用户信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月6日 上午9:50:03
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<UserInfo> selectUserInfoByApId(String apartment_id);

	/**
	 * 描述：修改用户状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:43:34
	 * @since
	 * @param user_id
	 * @return
	 */
	public int updateUserStatusTo1ById(String user_id);

	/**
	 * 描述：修改用户状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午2:15:51
	 * @since
	 * @param user_id
	 * @return
	 */
	public int updateUserStatusTo0ById(String user_id);

	/**
	 * 描述：修改用户密码
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:43:50
	 * @since
	 * @param userInfo
	 * @return
	 */
	public int udpateUserPasswordById(UserInfo userInfo);

	/**
	 * 描述：修改用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:44:34
	 * @since
	 * @param userInfo
	 * @return
	 */
	public int udpateUserInfoById(UserInfo userInfo);

	/**
	 * 描述：删除用户记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午8:44:01
	 * @since
	 * @param user_id
	 * @return
	 */
	public int deleteUserInfoByUserId(String user_id);

}

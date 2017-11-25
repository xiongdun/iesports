/**
 * 
 */
package com.iesports.dao.jdbc;

import java.util.List;

import com.iesports.dao.bean.UserInfo;

/**
 * 描述：用户信息类
 * @author xiongdun
 * @created 2016年9月1日 上午10:02:24
 * @since 
 */
public interface UserInfoDao {
	/**
	 * 
	 * 描述：查询所有的用户信息
	 * @author xiongdun
	 * @created 2016年9月1日 下午2:22:24
	 * @since 
	 * @return
	 */
	public List<UserInfo> selectAllUserInfo();
	
	/**
	 * 按用户Id查询用户信息
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 下午4:53:31
	 * @since 
	 * @return
	 */
	public UserInfo selectUserInfoById(String id);
	
}

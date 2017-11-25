/**
 * 
 */
package com.iesports.service;

import java.util.List;

import com.iesports.dao.bean.UserInfo;
import com.iesports.util.DataList;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年9月1日 上午10:01:33
 * @since 
 */
public interface UserInfoService {
	/**
	 * 
	 * 描述：查询所有用户的信息
	 * @author xiongdun
	 * @created 2016年9月1日 下午2:57:30
	 * @since 
	 * @return
	 */
	public List<UserInfo> queryAllUserInfo();
	
	/**
	 * 描述：按Id查询
	 * @author xiongdun
	 * @created 2016年9月19日 上午8:27:53
	 * @since 
	 * @param id
	 * @return
	 */
	public UserInfo queryUserInfoById(String id);
	
	/**
	 * 描述：按名称查询
	 * @author xiongdun
	 * @created 2016年9月19日 上午8:27:28
	 * @since 
	 * @param name
	 * @return
	 */
	public UserInfo queryUserInfoByName(String name);
	
	/**
	 * 描述：按Id修改
	 * @author xiongdun
	 * @created 2016年9月19日 上午8:27:13
	 * @since 
	 * @param id
	 * @return
	 */
	public boolean modifyUserInfoById(String id);
	
	/**
	 * 描述：按id刪除
	 * @author xiongdun
	 * @created 2016年9月19日 上午8:25:41
	 * @since 
	 * @param id
	 * @return
	 */
	public boolean deleteUserInfoById(String id);
	
	/**
	 * 描述：插入新数据
	 * @author xiongdun
	 * @created 2016年9月19日 上午8:28:04
	 * @since 
	 * @param data
	 * @return
	 */
	public boolean addUserInfo(DataList data);
	
	/**
	 * 描述：登录
	 * @author xiongdun
	 * @created 2016年9月19日 上午8:46:27
	 * @since 
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean login(String name, String password);
}

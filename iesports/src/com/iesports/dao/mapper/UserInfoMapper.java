/**
 * 
 */
package com.iesports.dao.mapper;

import java.util.List;

import com.iesports.dao.bean.UserInfo;

/**
 * 描述：user表映射文件
 * @author xiongdun
 * @created 2016年10月11日 上午8:45:58
 * @since 
 */
public interface UserInfoMapper {
	
	/**
	 * 描述：插入用户数据
	 * @author xiongdun
	 * @created 2016年10月24日 下午8:57:24
	 * @since 
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo);
	
	/**
	 * 描述：查询所有数据
	 * @author xiongdun
	 * @created 2016年10月24日 下午8:57:37
	 * @since 
	 * @return
	 */
	public List<UserInfo> selectAll();
	
	/**
	 * 描述：
	 * @author xiongdun
	 * @created 2016年10月24日 下午8:57:53
	 * @since 
	 * @param id
	 * @return
	 */
	public UserInfo selectById(String id);
	
	public List<UserInfo> selectByName(String name);
	
	public int updateUserInfo(UserInfo userInfo);
	
	public int updatePassword(UserInfo userInfo);
	
	public int deleteById(String id);
	
}

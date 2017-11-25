/**
 * 
 */
package com.iesports.dao.mapper;

import java.util.List;

import com.iesports.dao.bean.UserInfo;

/**
 * ������user��ӳ���ļ�
 * @author xiongdun
 * @created 2016��10��11�� ����8:45:58
 * @since 
 */
public interface UserInfoMapper {
	
	/**
	 * �����������û�����
	 * @author xiongdun
	 * @created 2016��10��24�� ����8:57:24
	 * @since 
	 * @param userInfo
	 * @return
	 */
	public int insertUserInfo(UserInfo userInfo);
	
	/**
	 * ��������ѯ��������
	 * @author xiongdun
	 * @created 2016��10��24�� ����8:57:37
	 * @since 
	 * @return
	 */
	public List<UserInfo> selectAll();
	
	/**
	 * ������
	 * @author xiongdun
	 * @created 2016��10��24�� ����8:57:53
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

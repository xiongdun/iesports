/**
 * 
 */
package com.iesports.dao.jdbc;

import java.util.List;

import com.iesports.dao.bean.UserInfo;

/**
 * �������û���Ϣ��
 * @author xiongdun
 * @created 2016��9��1�� ����10:02:24
 * @since 
 */
public interface UserInfoDao {
	/**
	 * 
	 * ��������ѯ���е��û���Ϣ
	 * @author xiongdun
	 * @created 2016��9��1�� ����2:22:24
	 * @since 
	 * @return
	 */
	public List<UserInfo> selectAllUserInfo();
	
	/**
	 * ���û�Id��ѯ�û���Ϣ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����4:53:31
	 * @since 
	 * @return
	 */
	public UserInfo selectUserInfoById(String id);
	
}

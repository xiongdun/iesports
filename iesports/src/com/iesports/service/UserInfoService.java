/**
 * 
 */
package com.iesports.service;

import java.util.List;

import com.iesports.dao.bean.UserInfo;
import com.iesports.util.DataList;

/**
 * ������
 * @author xiongdun
 * @created 2016��9��1�� ����10:01:33
 * @since 
 */
public interface UserInfoService {
	/**
	 * 
	 * ��������ѯ�����û�����Ϣ
	 * @author xiongdun
	 * @created 2016��9��1�� ����2:57:30
	 * @since 
	 * @return
	 */
	public List<UserInfo> queryAllUserInfo();
	
	/**
	 * ��������Id��ѯ
	 * @author xiongdun
	 * @created 2016��9��19�� ����8:27:53
	 * @since 
	 * @param id
	 * @return
	 */
	public UserInfo queryUserInfoById(String id);
	
	/**
	 * �����������Ʋ�ѯ
	 * @author xiongdun
	 * @created 2016��9��19�� ����8:27:28
	 * @since 
	 * @param name
	 * @return
	 */
	public UserInfo queryUserInfoByName(String name);
	
	/**
	 * ��������Id�޸�
	 * @author xiongdun
	 * @created 2016��9��19�� ����8:27:13
	 * @since 
	 * @param id
	 * @return
	 */
	public boolean modifyUserInfoById(String id);
	
	/**
	 * ��������id�h��
	 * @author xiongdun
	 * @created 2016��9��19�� ����8:25:41
	 * @since 
	 * @param id
	 * @return
	 */
	public boolean deleteUserInfoById(String id);
	
	/**
	 * ����������������
	 * @author xiongdun
	 * @created 2016��9��19�� ����8:28:04
	 * @since 
	 * @param data
	 * @return
	 */
	public boolean addUserInfo(DataList data);
	
	/**
	 * ��������¼
	 * @author xiongdun
	 * @created 2016��9��19�� ����8:46:27
	 * @since 
	 * @param name
	 * @param password
	 * @return
	 */
	public boolean login(String name, String password);
}

/**
 * 
 */
package com.carport.service;

import java.util.List;

import com.carport.bean.UserInfo;
import com.carport.model.UserInfoModel;
import com.github.pagehelper.PageInfo;

/**
 * �������û�����
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����8:58:11
 * @since
 */
public interface UserService {

	/**
	 * �������û�ע��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����9:16:42
	 * @since
	 * @param user
	 * @return
	 */
	public int register(UserInfo user);

	/**
	 * ��������¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����1:42:00
	 * @since
	 * @param user_id
	 * @param user_pwd
	 * @return
	 */
	public int login(String user_id, String user_pwd);

	/**
	 * �������ж��û��Ƿ��Ѿ�����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����9:16:52
	 * @since
	 * @param car_no
	 * @return
	 */
	public boolean isExist(String car_no);

	/**
	 * �������ж��û��Ƿ��������λ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����9:17:04
	 * @since
	 * @param user_id
	 * @param org_no
	 * @return
	 */
	public int isUsable(UserInfo userInfo);

	/**
	 * ��������ѯ�����û���Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����12:47:38
	 * @since
	 * @return
	 */
	public List<UserInfo> findAllUser();

	/**
	 * ����������֯����Id��ѯ�û���Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����7:28:57
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<UserInfo> getUserInfoByOrgId(String org_id);

	/**
	 * ��������apartment_id��ѯ�û���Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��6�� ����9:46:49
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<UserInfo> getUserInfoByApId(String apartment_id);

	/**
	 * ��������user_id�޸��û�״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����2:13:27
	 * @since
	 * @param user_id
	 * @return
	 */
	public boolean modifyUserStatusTo1(String user_id);

	/**
	 * ��������user_id�޸��û�״̬0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����2:13:50
	 * @since
	 * @param user_id
	 * @return
	 */
	public boolean modifyUserStatusTo0(String user_id);

	/**
	 * �������޸�����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����2:20:00
	 * @since
	 * @param user_id
	 * @param user_pwd
	 * @return
	 */
	public boolean modifyUserPassword(UserInfo userInfo);

	/**
	 * �޸��û���Ϣ
	 * @param userInfo
	 * @return
	 */
	public UserInfo modifyUserInfoByUserId(UserInfo userInfo);
	
	/**
	 * ��������user_idɾ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��6�� ����2:06:03
	 * @since
	 * @param user_id
	 * @return
	 */
	public boolean deleteUserInfoByUserId(String user_id);
	
	/**
	 * ����Ա�ĵ�¼
	 * @param user_name
	 * @param user_pwd
	 * @return
	 */
	public UserInfo adminLogin(String user_name, String user_pwd);
	
	/**
	 * ��������ҳ��ѯ�û���Ϣ
	 * @author xiongdun
	 * @created 2016��12��15�� ����8:25:12
	 * @since 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<UserInfoModel> queryUserInfoByPage(Integer pageNo, Integer pageSize);
	
}

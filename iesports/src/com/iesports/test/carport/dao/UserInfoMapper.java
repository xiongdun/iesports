/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.UserInfo;

/**
 * ����������λ��ʷӳ��
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����5:47:09
 * @since
 */
public interface UserInfoMapper {

	/**
	 * �����������û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:42:07
	 * @since
	 * @param user
	 * @return
	 */
	public int insertUserInfo(UserInfo user);

	/**
	 * ��������ѯ�����û�
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:42:18
	 * @since
	 * @return
	 */
	public List<UserInfo> selectAllUser();

	/**
	 * ��������user_id ��ѯ�û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:42:25
	 * @since
	 * @param user_id
	 * @return
	 */
	public UserInfo selectUserInfoById(String user_id);

	/**
	 * ��������car_no ��ѯ�û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:42:45
	 * @since
	 * @param car_no
	 * @return
	 */
	public List<UserInfo> selectUserInfoByCarNo(String car_no);

	/**
	 * ��������job_number��ѯ�û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:42:59
	 * @since
	 * @param job_number
	 * @return
	 */
	public List<UserInfo> selectUserInfoByJobNumber(String job_number);

	/**
	 * ���������û�����ѯ�û���¼
	 * @param user_name
	 * @return
	 */
	public List<UserInfo> selelctUserInfoByUserName(String user_name);
	/**
	 * ��������org_id ��ѯ�û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:43:15
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<UserInfo> selectUserInfoByOrgId(String org_id);
	
	/**
	 * ��org_id �� user_id��ѯ�û���Ϣ
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:43:15
	 * @param userInfo
	 * @return
	 */
	public UserInfo selectUserInfoByOrgIdAndUserIdAndApId(UserInfo userInfo);

	/**
	 * ��������apartment_id��ѯ�û���Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��6�� ����9:50:03
	 * @since
	 * @param apartment_id
	 * @return
	 */
	public List<UserInfo> selectUserInfoByApId(String apartment_id);

	/**
	 * �������޸��û�״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:43:34
	 * @since
	 * @param user_id
	 * @return
	 */
	public int updateUserStatusTo1ById(String user_id);

	/**
	 * �������޸��û�״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����2:15:51
	 * @since
	 * @param user_id
	 * @return
	 */
	public int updateUserStatusTo0ById(String user_id);

	/**
	 * �������޸��û�����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:43:50
	 * @since
	 * @param userInfo
	 * @return
	 */
	public int udpateUserPasswordById(UserInfo userInfo);

	/**
	 * �������޸��û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:44:34
	 * @since
	 * @param userInfo
	 * @return
	 */
	public int udpateUserInfoById(UserInfo userInfo);

	/**
	 * ������ɾ���û���¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����8:44:01
	 * @since
	 * @param user_id
	 * @return
	 */
	public int deleteUserInfoByUserId(String user_id);

}

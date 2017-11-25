/**
 * 
 */
package com.iesports.test.carport.service;

import java.util.List;

import com.iesports.test.carport.bean.Organization;

/**
 * ��������֯��������
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����8:58:35
 * @since
 */
public interface OrganizationService {

	/**
	 * ��������ѯ������֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����1:49:57
	 * @since
	 * @return
	 */
	public List<Organization> queryAllOrganization();

	/**
	 * �����������ű�Ų�ѯ��֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����1:50:48
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public List<Organization> getOrganizationByApartmentNo(String apartment_no);

	/**
	 * ����������֯����Id��ѯ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����3:23:15
	 * @since
	 * @param org_id
	 * @return
	 */
	public Organization getOrganizationByOrgId(String org_id);

	/**
	 * ����������֯����Id��statusΪ1��ѯ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����3:23:40
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Organization> getOrganizationByOrgIdAndStatus1(String org_id);

	/**
	 * ������������֯������¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����7:37:10
	 * @since
	 * @param organization
	 * @return
	 */
	public boolean addOrganizationInfo(Organization organization);

	/**
	 * ����������������֯������¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����7:38:39
	 * @since
	 * @param organizations
	 * @return
	 */
	public List<Boolean> batchAddOrganizationInfo(
			List<Organization> organizations);

	/**
	 * �������޸���֯������¼
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����7:39:40
	 * @since
	 * @param organization
	 * @return
	 */
	public boolean modifyOrganizationByOrgId(Organization organization);

	/**
	 * �������޸���֯����״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:02:00
	 * @since
	 * @param org_id
	 * @return
	 */
	public boolean modifyOrganizationStatusTo1ByOrgId(String org_id);

	/**
	 * �������޸���֯����״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:02:15
	 * @since
	 * @param org_id
	 * @return
	 */
	public boolean modifyOrganizationStatusTo0ByOrgId(String org_id);

	/**
	 * ��������org_id ɾ����֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:24:11
	 * @since
	 * @param org_id
	 * @return
	 */
	public boolean deleteOrganizationByOrgId(String org_id);

}

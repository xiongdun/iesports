/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.Organization;

/**
 * ����������λ��ʷӳ��
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����5:47:09
 * @since
 */
public interface OrganizationMapper {

	/**
	 * ������������֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:35:25
	 * @since
	 * @param organization
	 * @return
	 */
	public int insertOrganization(Organization organization);

	/**
	 * ��������ѯ������֯����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:35:45
	 * @since
	 * @return
	 */
	public List<Organization> selectAllOrganizations();

	/**
	 * �����������ű�Ų�ѯ��֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����1:52:22
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public List<Organization> selectOrganizationByApartmentNo(
			String apartment_no);

	/**
	 * ��������Id��ѯ��֯����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:35:59
	 * @since
	 * @param org_id
	 * @return
	 */
	public Organization selectOrganizationById(String org_id);

	/**
	 * ����������֯����Id��statusΪ1��ѯ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����3:34:07
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Organization> selectOrganizationByOrgIdAndStatus1(String org_id);

	/**
	 * ��������Id�޸���֯����״̬Ϊ1
	 * 
	 * @author zhangyijie
	 * @created 2016��12��1�� ����8:36:13
	 * @since
	 * @param org_id
	 * @return
	 */
	public int updateOrgStatusTo1(String org_id);

	/**
	 * ��������Id�޸���֯����״̬Ϊ0
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����1:30:02
	 * @since
	 * @param org_id
	 * @return
	 */
	public int updateOrgStatusTo0(String org_id);

	/**
	 * ��������org_id�޸���֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����7:51:25
	 * @since
	 * @param organization
	 * @return
	 */
	public int updateOrganizationByOrgId(Organization organization);

	/**
	 * ��������org_id ɾ����֯������Ϣ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����8:23:23
	 * @since
	 * @param org_id
	 * @return
	 */
	public int deleteOrganizationByOrgId(String org_id);

}

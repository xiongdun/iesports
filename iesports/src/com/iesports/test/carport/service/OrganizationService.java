/**
 * 
 */
package com.iesports.test.carport.service;

import java.util.List;

import com.iesports.test.carport.bean.Organization;

/**
 * 描述：组织机构管理
 * 
 * @author zhangyijie
 * @created 2016年12月2日 上午8:58:35
 * @since
 */
public interface OrganizationService {

	/**
	 * 描述：查询所有组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午1:49:57
	 * @since
	 * @return
	 */
	public List<Organization> queryAllOrganization();

	/**
	 * 描述：按部门编号查询组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午1:50:48
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public List<Organization> getOrganizationByApartmentNo(String apartment_no);

	/**
	 * 描述：按组织机构Id查询
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午3:23:15
	 * @since
	 * @param org_id
	 * @return
	 */
	public Organization getOrganizationByOrgId(String org_id);

	/**
	 * 描述：按组织机构Id和status为1查询
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午3:23:40
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Organization> getOrganizationByOrgIdAndStatus1(String org_id);

	/**
	 * 描述：新增组织机构记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午7:37:10
	 * @since
	 * @param organization
	 * @return
	 */
	public boolean addOrganizationInfo(Organization organization);

	/**
	 * 描述：批量新增组织机构记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午7:38:39
	 * @since
	 * @param organizations
	 * @return
	 */
	public List<Boolean> batchAddOrganizationInfo(
			List<Organization> organizations);

	/**
	 * 描述：修改组织机构记录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午7:39:40
	 * @since
	 * @param organization
	 * @return
	 */
	public boolean modifyOrganizationByOrgId(Organization organization);

	/**
	 * 描述：修改组织机构状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:02:00
	 * @since
	 * @param org_id
	 * @return
	 */
	public boolean modifyOrganizationStatusTo1ByOrgId(String org_id);

	/**
	 * 描述：修改组织机构状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:02:15
	 * @since
	 * @param org_id
	 * @return
	 */
	public boolean modifyOrganizationStatusTo0ByOrgId(String org_id);

	/**
	 * 描述：按org_id 删除组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:24:11
	 * @since
	 * @param org_id
	 * @return
	 */
	public boolean deleteOrganizationByOrgId(String org_id);

}

/**
 * 
 */
package com.iesports.test.carport.dao;

import java.util.List;

import com.iesports.test.carport.bean.Organization;

/**
 * 描述：抢车位历史映射
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午5:47:09
 * @since
 */
public interface OrganizationMapper {

	/**
	 * 描述：插入组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:35:25
	 * @since
	 * @param organization
	 * @return
	 */
	public int insertOrganization(Organization organization);

	/**
	 * 描述：查询所有组织机构
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:35:45
	 * @since
	 * @return
	 */
	public List<Organization> selectAllOrganizations();

	/**
	 * 描述：按部门编号查询组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午1:52:22
	 * @since
	 * @param apartment_no
	 * @return
	 */
	public List<Organization> selectOrganizationByApartmentNo(
			String apartment_no);

	/**
	 * 描述：按Id查询组织机构
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:35:59
	 * @since
	 * @param org_id
	 * @return
	 */
	public Organization selectOrganizationById(String org_id);

	/**
	 * 描述：按组织机构Id和status为1查询
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午3:34:07
	 * @since
	 * @param org_id
	 * @return
	 */
	public List<Organization> selectOrganizationByOrgIdAndStatus1(String org_id);

	/**
	 * 描述：按Id修改组织机构状态为1
	 * 
	 * @author zhangyijie
	 * @created 2016年12月1日 下午8:36:13
	 * @since
	 * @param org_id
	 * @return
	 */
	public int updateOrgStatusTo1(String org_id);

	/**
	 * 描述：按Id修改组织机构状态为0
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:30:02
	 * @since
	 * @param org_id
	 * @return
	 */
	public int updateOrgStatusTo0(String org_id);

	/**
	 * 描述：按org_id修改组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午7:51:25
	 * @since
	 * @param organization
	 * @return
	 */
	public int updateOrganizationByOrgId(Organization organization);

	/**
	 * 描述：按org_id 删除组织机构信息
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 下午8:23:23
	 * @since
	 * @param org_id
	 * @return
	 */
	public int deleteOrganizationByOrgId(String org_id);

}

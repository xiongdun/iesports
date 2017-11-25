/**
 * 
 */
package com.iesports.test.carport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.iesports.test.carport.bean.Organization;
import com.iesports.test.carport.dao.OrganizationMapper;
import com.iesports.test.carport.service.OrganizationService;
import com.iesports.test.carport.util.MybatisUtils;
import com.iesports.test.carport.util.StringUtils;

/**
 * 描述：组织机构功能实现
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午9:04:51
 * @since
 */
public class OrganizationServiceImpl implements OrganizationService {

	private static Logger logger = Logger.getLogger(CarportServiceImpl.class);
	private SqlSessionFactory sessionFactory = MybatisUtils.getInstance();
	SqlSession session = sessionFactory.openSession();
	OrganizationMapper organizationMapper = session
			.getMapper(OrganizationMapper.class);

	@Override
	public List<Organization> queryAllOrganization() {
		logger.info("查询所有组织机构信息开始");
		List<Organization> organizations = organizationMapper
				.selectAllOrganizations();
		logger.info("查询所有组织机构信息结束");
		return organizations;
	}

	@Override
	public List<Organization> getOrganizationByApartmentNo(String apartment_no) {
		logger.info("按部门编号查询组织机构信息开始");
		if (StringUtils.isEmpty(apartment_no)) {
			return null;
		}
		List<Organization> organizations = organizationMapper
				.selectOrganizationByApartmentNo(apartment_no);
		logger.info("按部门编号查询组织机构信息结束");
		return organizations;
	}

	@Override
	public Organization getOrganizationByOrgId(String org_id) {
		logger.info("按组织机构Id查询组织机构信息开始");
		if (StringUtils.isEmpty(org_id)) {
			return null;
		}
		Organization organization = organizationMapper
				.selectOrganizationById(org_id);
		logger.info("按组织机构Id查询组织机构信息结束");
		return organization;
	}

	@Override
	public List<Organization> getOrganizationByOrgIdAndStatus1(String org_id) {
		logger.info("按组织机构Id和status为1查询开始");
		if (StringUtils.isEmpty(org_id)) {
			return null;
		}
		List<Organization> organizations = organizationMapper
				.selectOrganizationByOrgIdAndStatus1(org_id);
		logger.info("按组织机构Id和status为1查询结束");
		return organizations;
	}

	@Override
	public boolean addOrganizationInfo(Organization organization) {
		if (organization == null) {
			return false;
		}
		int result = organizationMapper.insertOrganization(organization);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Boolean> batchAddOrganizationInfo(
			List<Organization> organizations) {
		if (organizations == null) {
			return null;
		}
		List<Boolean> booleans = new ArrayList<Boolean>();
		for (Organization organization : organizations) {
			boolean result = addOrganizationInfo(organization);
			booleans.add(result);
		}
		return booleans;
	}

	@Override
	public boolean modifyOrganizationByOrgId(Organization organization) {
		if (organization == null) {
			return false;
		}
		int result = organizationMapper.updateOrganizationByOrgId(organization);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyOrganizationStatusTo1ByOrgId(String org_id) {
		if (StringUtils.isEmpty(org_id)) {
			return false;
		}
		int result = organizationMapper.updateOrgStatusTo1(org_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyOrganizationStatusTo0ByOrgId(String org_id) {
		if (StringUtils.isEmpty(org_id)) {
			return false;
		}
		int result = organizationMapper.updateOrgStatusTo0(org_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteOrganizationByOrgId(String org_id) {
		if (StringUtils.isEmpty(org_id)) {
			return false;
		}
		int result = organizationMapper.deleteOrganizationByOrgId(org_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

}

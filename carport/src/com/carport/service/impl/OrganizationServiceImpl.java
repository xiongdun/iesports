/**
 * 
 */
package com.carport.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carport.bean.Organization;
import com.carport.dao.OrganizationMapper;
import com.carport.model.OrganizationModel;
import com.carport.service.OrganizationService;
import com.carport.util.Constants;
import com.carport.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 描述：组织机构功能实现
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午9:04:51
 * @since
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	private static Logger logger = Logger.getLogger(CarportServiceImpl.class);

	@Resource
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@Override
	public List<Organization> queryAllOrganization() {
		logger.info("查询所有组织机构信息开始");
		List<Organization> organizations = organizationMapper.selectAllOrganizations();
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
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageInfo<OrganizationModel> queryOrganizationByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? Constants.PAGE_NO : pageNo;
		pageSize = pageSize == null ? Constants.PAGE_SIZE : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<OrganizationModel> models = organizationMapper.selectOrganizationByPage();
		PageInfo<OrganizationModel> infos = new PageInfo<>(models);
		return infos;
	}

}

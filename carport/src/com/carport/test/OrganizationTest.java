/**
 * 
 */
package com.carport.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.carport.model.OrganizationModel;
import com.carport.service.OrganizationService;
import com.carport.util.BaseSpringTest;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月20日 上午8:48:07
 * @since 
 */
public class OrganizationTest extends BaseSpringTest {

	@Autowired
	private OrganizationService service;
	
	@Test
	public void testOrgAll() {
		PageInfo<OrganizationModel> infos = service.queryOrganizationByPage(1, 10);
		List<OrganizationModel> models = infos.getList();
		for (OrganizationModel organizationModel : models) {
			System.out.println(organizationModel.getOrg_name() + organizationModel.getApartment_name());
		}
	}
}

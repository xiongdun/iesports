/**
 * 
 */
package com.carport.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.carport.model.CarportModel;
import com.carport.service.CarportService;
import com.carport.util.BaseSpringTest;
import com.github.pagehelper.PageInfo;

/**
 * 描述：测试车位
 * @author xiongdun
 * @created 2016年12月19日 下午8:09:55
 * @since 
 */
public class CarportTest extends BaseSpringTest {

	@Resource
	public CarportService service;
	
	@Test
	public void testCarportModel() {
		PageInfo<CarportModel> pages = service.queryCarportByPage(1, 10); 
		List<CarportModel> models = pages.getList();
		for (CarportModel carportModel : models) {
			System.out.println(carportModel.getOrg_name() + carportModel.getApartment_name());
		}
	}
}

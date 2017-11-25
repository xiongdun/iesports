/**
 * 
 */
package com.carport.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.carport.model.CarportHistoryModel;
import com.carport.service.CarportHistoryService;
import com.carport.util.BaseSpringTest;
import com.github.pagehelper.PageInfo;

/**
 * 描述：测试强势车位历史
 * @author xiongdun
 * @created 2016年12月19日 下午8:28:58
 * @since 
 */
public class CarportHistoryTest extends BaseSpringTest {

	@Autowired
	private CarportHistoryService service;
	
	@Test
	public void testQuery() {
		PageInfo<CarportHistoryModel> pages = service.queryCarportHisByPage(1, 10);
		List<CarportHistoryModel> models = pages.getList();
		for (CarportHistoryModel carportHistoryModel : models) {
			System.out.println(carportHistoryModel.getApartment_name() + carportHistoryModel.getOrg_name() 
			+ carportHistoryModel.getUser_name());
		}
	}
}

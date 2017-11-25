/**
 * 
 */
package com.carport.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.carport.bean.Apartment;
import com.carport.service.ApartmentService;
import com.carport.util.BaseSpringTest;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月19日 下午7:19:50
 * @since 
 */
public class ApartmentTest extends BaseSpringTest {

	@Autowired
	private ApartmentService service;
	
	//@Test
	public void testApartmentAll() {
		List<Apartment> list = service.queryAllApartment();
		for (Apartment apartment : list) {
			System.out.println(apartment.getApartment_name());
		}
	}
	
	@Test
	public void testApartmentByPage() {
		PageInfo<Apartment> infos = service.queryApartmentByPage(3, 10);
		List<Apartment> apartments = infos.getList();
		for (Apartment apartment : apartments) {
			System.out.println(apartment.getApartment_name() + apartment.getApartment_no());
		}
	}
}

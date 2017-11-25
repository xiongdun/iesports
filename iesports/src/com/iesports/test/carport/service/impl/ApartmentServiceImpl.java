/**
 * 
 */
package com.iesports.test.carport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.iesports.test.carport.bean.Apartment;
import com.iesports.test.carport.dao.ApartmentMapper;
import com.iesports.test.carport.service.ApartmentService;
import com.iesports.test.carport.util.MybatisUtils;
import com.iesports.test.carport.util.StringUtils;

/**
 * 描述：部门管理实现类
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午9:01:42
 * @since
 */
public class ApartmentServiceImpl implements ApartmentService {

	private static Logger logger = Logger.getLogger(ApartmentServiceImpl.class);

	private SqlSessionFactory sessionFactory = MybatisUtils.getInstance();
	SqlSession session = sessionFactory.openSession();
	ApartmentMapper apartmentMapper = session.getMapper(ApartmentMapper.class);

	@Override
	public List<Apartment> queryAllApartment() {
		logger.info("查询所有部门信息开始");
		List<Apartment> apartments = apartmentMapper.selectAllApartment();
		logger.info("查询所有部门信息结束");
		return apartments;
	}

	@Override
	public Map<String, Integer[]> getAllApartmentCarport() {
		Map<String, Integer[]> resultMap = new HashMap<String, Integer[]>();
		List<Apartment> apartments = this.queryAllApartment();
		for (Apartment apartment : apartments) {
			Integer[] intArr = StringUtils.splitStrToInt(
					apartment.getCarport_nums(), ",");
			resultMap.put(apartment.getApartment_id(), intArr);
		}
		return resultMap;
	}

	@Test
	public void test() {
		this.queryAllApartment();
	}
	
	@Override
	public String[] getApartmentCarportByApNo(String apartment_no) {
		List<Apartment> apartments = apartmentMapper.selectApartmentByApNo(apartment_no);
		Apartment apartment = new Apartment();
		if (apartments != null && apartments.size() >= 0) {
			String apartment_id = apartments.get(0).getApartment_id();
			apartment = apartmentMapper.selectApartmentById(apartment_id);
		}
		String[] strArr = null;
		if (apartment != null) {
			strArr = StringUtils.splitStr(apartment.getCarport_nums(), ",");
		}

		return strArr;
	}

	@Override
	public String[] getApartmentCarportByApId(String apartment_id) {

		Apartment apartment = apartmentMapper.selectApartmentById(apartment_id);
		String[] strArr = null;
		if (apartment != null) {
			strArr = StringUtils.splitStr(apartment.getCarport_nums(), ",");
		}

		return strArr;
	}

	@Override
	public Apartment getApartmentInfoByApNo(String apartment_no) {
		List<Apartment> apartments = apartmentMapper
				.selectApartmentByApNo(apartment_no);
		Apartment apartment = new Apartment();
		if (apartments != null && apartments.size() > 0) {
			apartment = apartments.get(0);
		}
		return apartment;
	}

	@Override
	public Apartment getApartmentInfoById(String apartment_id) {
		Apartment apartment = apartmentMapper.selectApartmentById(apartment_id);
		return apartment;
	}

	@Override
	public boolean addApartmentInfo(Apartment apartment) {

		if (apartment == null) {
			return false;
		}
		List<Apartment> apartments = apartmentMapper
				.selectApartmentByApNo(apartment.getApartment_no());
		// 部门编号不能相同
		if (apartments != null && apartments.size() > 0) {
			return false;
		}
		int result = apartmentMapper.insertApartment(apartment);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyApartmentInfoByApId(Apartment apartment) {
		if (apartment == null) {
			return false;
		}
		List<Apartment> apartments = apartmentMapper
				.selectApartmentByApNo(apartment.getApartment_no());
		// 部门编号不能相同
		if (apartments != null && apartments.size() > 0) {
			return false;
		}
		int result = apartmentMapper.updateApartmentInfoByApId(apartment);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteApartmentInfoByApId(String apartment_id) {
		if (StringUtils.isEmpty(apartment_id)) {
			return false;
		}
		int result = apartmentMapper.deleteApartmentInfoByApId(apartment_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modfiyApartmentStatusTo1ByApId(String apartment_id) {

		if (StringUtils.isEmpty(apartment_id)) {
			return false;
		}
		int result = apartmentMapper.updateApartmentStatusTo1ById(apartment_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modfiyApartmentStatusTo0ByApId(String apartment_id) {
		if (StringUtils.isEmpty(apartment_id)) {
			return false;
		}
		int result = apartmentMapper.updateApartmentStatusTo0ById(apartment_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

}

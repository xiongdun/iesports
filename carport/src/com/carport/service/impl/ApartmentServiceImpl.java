/**
 * 
 */
package com.carport.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carport.bean.Apartment;
import com.carport.dao.ApartmentMapper;
import com.carport.service.ApartmentService;
import com.carport.util.Constants;
import com.carport.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 描述：部门管理实现类
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午9:01:42
 * @since
 */
@Service("apartmentService")
public class ApartmentServiceImpl implements ApartmentService {

	private static Logger logger = Logger.getLogger(ApartmentServiceImpl.class);

	@Resource
	@Autowired
	private ApartmentMapper apartmentMapper;
	
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

	@Override
	public String[] getApartmentCarportByApNo(String apartment_no) {
		if (StringUtils.isEmpty(apartment_no)) {
			return null;
		}
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
		if (StringUtils.isEmpty(apartment_id)) {
			return null;
		}
		Apartment apartment = apartmentMapper.selectApartmentById(apartment_id);
		String[] strArr = null;
		if (apartment != null) {
			strArr = StringUtils.splitStr(apartment.getCarport_nums(), ",");
		}

		return strArr;
	}

	@Override
	public Apartment getApartmentInfoByApNo(String apartment_no) {
		if (StringUtils.isEmpty(apartment_no)) {
			return null;
		}
		List<Apartment> apartments = apartmentMapper.selectApartmentByApNo(apartment_no);
		Apartment apartment = new Apartment();
		if (apartments != null && apartments.size() > 0) {
			apartment = apartments.get(0);
		}
		return apartment;
	}

	@Override
	public Apartment getApartmentInfoById(String apartment_id) {
		if (StringUtils.isEmpty(apartment_id)) {
			return null;
		}
		Apartment apartment = apartmentMapper.selectApartmentById(apartment_id);
		return apartment;
	}

	@Override
	public boolean addApartmentInfo(Apartment apartment) {
		if (apartment == null) {
			return false;
		}
		List<Apartment> apartments = apartmentMapper.selectApartmentByApNo(apartment.getApartment_no());
		// 部门编号不能相同
		if (apartments != null && apartments.size() > 0) {
			return false;
		}
		int result = apartmentMapper.insertApartment(apartment);
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
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Apartment> queryApartmentStatus1() {
		List<Apartment> apartments = apartmentMapper.selectAllApartmentStatus1();
		return apartments;
	}

	@Override
	public PageInfo<Apartment> queryApartmentByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? Constants.PAGE_NO : pageNo;
		pageSize = pageSize == null ? Constants.PAGE_SIZE : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Apartment> apartments = apartmentMapper.selectAllApartment();
		PageInfo<Apartment> pages = new PageInfo<Apartment>(apartments);
		return pages;
	}
}

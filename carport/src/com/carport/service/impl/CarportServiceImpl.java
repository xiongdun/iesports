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

import com.carport.bean.Carport;
import com.carport.dao.CarportMapper;
import com.carport.model.CarportModel;
import com.carport.service.CarportService;
import com.carport.util.Constants;
import com.carport.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 描述：车位控制实现类
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午9:11:25
 * @since
 */
@Service("carportService")
public class CarportServiceImpl implements CarportService {

	private static Logger logger = Logger.getLogger(CarportServiceImpl.class);

	@Resource
	@Autowired
	private CarportMapper carportMapper;
	
	@Override
	public List<Carport> queryAllCarport() {
		logger.info("查询所有车位开始");
		List<Carport> carports = carportMapper.selectAllCarport();
		logger.info("查询所有车位结束");
		return carports;
	}

	@Override
	public List<Carport> getCarportByApartmentId(String apartment_id) {
		logger.info("按部门Id查询车位信息开始");
		List<Carport> carports = carportMapper.selectCarportByApartmentId(apartment_id);
		logger.info("按部门Id查询车位信息结束");
		return carports;
	}

	@Override
	public List<Carport> getCarportByOrgId(String org_id) {
		logger.info("按组织机构查询车位开始");
		List<Carport> carports = carportMapper.selectCarportByOrgId(org_id);
		logger.info("按组织机构查询车位结束");
		return carports;
	}

	@Override
	public List<Carport> getCarportByOrgIdAndStatus1(Carport carport) {
		logger.info("按组织机构和status为1查询车位开始");
		if (carport == null) {
			return null;
		}
		List<Carport> carports = carportMapper.selectCarportByOrgIdAndStatus1(carport);
		logger.info("按组织机构和status为1查询车位结束");
		return carports;
	}

	@Override
	public boolean modifyCarportStatus1ByCarportId(String carport_id) {
		logger.info("修改车位状态为1开始");
		int result = carportMapper.updateCarportStatusTo1(carport_id);
		if (result > 0) {
			return true;
		}
		logger.info("修改车位状态为1结束");
		return false;
	}

	@Override
	public boolean modifyCarportStatus0ByCarportId(String carport_id) {
		logger.info("修改车位状态为0开始");
		int result = carportMapper.updateCarportStatusTo0(carport_id);
		if (result > 0) {
			return true;
		}
		logger.info("修改车位状态为0结束");
		return false;
	}

	@Override
	public boolean addCarportInfo(Carport carport) {
		logger.info("添加车位信息开始");
		int result = carportMapper.insertCarport(carport);
		if (result > 0) {
			return true;
		}
		logger.info("添加车位信息开始结束");
		return false;
	}

	@Override
	public List<Boolean> batchInsertCarports(List<Carport> carports) {
		logger.info("批量添加车位信息开始");
		List<Boolean> booleans = new ArrayList<Boolean>();

		for (Carport carport : carports) {
			boolean result = this.addCarportInfo(carport);
			booleans.add(result);
		}
		logger.info("批量添加车位信息结束");
		return booleans;
	}

	@Override
	public List<Carport> getCarportByCarportNo(String carport_no) {
		if (StringUtils.isEmpty(carport_no)) {
			return null;
		}
		List<Carport> carports = carportMapper
				.selectCarportByCarportNo(carport_no);

		return carports;
	}

	@Override
	public boolean deleteCarportByCarportId(String carport_id) {

		if (StringUtils.isEmpty(carport_id)) {
			return false;
		}
		int result = carportMapper.deleteCarportByCarportId(carport_id);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Carport> getCarportByNowDate(Carport carport) {
		if (carport == null) {
			return null;
		}
		List<Carport> carports = carportMapper.selectCarportByNowDate(carport);
		return carports;
	}

	@Override
	public PageInfo<CarportModel> queryCarportByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? Constants.PAGE_NO : pageNo;
		pageSize = pageSize == null ? Constants.PAGE_SIZE : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<CarportModel> models = carportMapper.selectCarportByPage();
		PageInfo<CarportModel> pages = new PageInfo<>(models);
		return pages;
	}

}

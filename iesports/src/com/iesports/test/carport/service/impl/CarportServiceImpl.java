/**
 * 
 */
package com.iesports.test.carport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.iesports.test.carport.bean.Carport;
import com.iesports.test.carport.dao.CarportMapper;
import com.iesports.test.carport.service.CarportService;
import com.iesports.test.carport.util.MybatisUtils;
import com.iesports.test.carport.util.StringUtils;

/**
 * 描述：车位控制实现类
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午9:11:25
 * @since
 */
public class CarportServiceImpl implements CarportService {

	private static Logger logger = Logger.getLogger(CarportServiceImpl.class);
	private SqlSessionFactory sessionFactory = MybatisUtils.getInstance();
	SqlSession session = sessionFactory.openSession();
	CarportMapper carportMapper = session.getMapper(CarportMapper.class);

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
		List<Carport> carports = carportMapper
				.selectCarportByApartmentId(apartment_id);
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
		session.commit();
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
		session.commit();
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
		session.commit();
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

}

/**
 * 
 */
package com.iesports.test.carport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.iesports.test.carport.bean.CarportHistory;
import com.iesports.test.carport.dao.CarportHistoryMapper;
import com.iesports.test.carport.service.CarportHistoryService;
import com.iesports.test.carport.util.MybatisUtils;
import com.iesports.test.carport.util.StringUtils;

/**
 * 描述：
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午9:03:11
 * @since
 */
public class CarportHistoryServiceImpl implements CarportHistoryService {

	private static Logger logger = Logger.getLogger(ApartmentServiceImpl.class);

	private SqlSessionFactory sessionFactory = MybatisUtils.getInstance();
	SqlSession session = sessionFactory.openSession();
	CarportHistoryMapper carportHistoryMapper = session
			.getMapper(CarportHistoryMapper.class);

	@Override
	public List<CarportHistory> queryAllCarportHistory() {
		logger.info("查询所有抢车位记录开始！");
		List<CarportHistory> carportHistories = carportHistoryMapper
				.selectAllCarportHistory();
		logger.info("查询所有抢车位记录开始！");
		return carportHistories;
	}

	@Override
	public List<CarportHistory> queryCarportHistoryByUserId(String user_id) {
		if (StringUtils.isEmpty(user_id)) {
			return null;
		}
		List<CarportHistory> carportHistories = carportHistoryMapper
				.selectCarportHistoryByUserId(user_id);

		return carportHistories;
	}

	@Override
	public List<CarportHistory> queryCarportHistoryByOrgId(String org_id) {
		if (StringUtils.isEmpty(org_id)) {
			return null;
		}
		List<CarportHistory> carportHistories = carportHistoryMapper
				.selectCaportHistoryByOrgId(org_id);

		return carportHistories;
	}

	@Override
	public List<CarportHistory> queryCarportHistoryByDate(String datetime) {
		if (StringUtils.isEmpty(datetime)) {
			return null;
		}
		List<CarportHistory> carportHistories = carportHistoryMapper
				.selectCarportHistoryByDate(datetime);

		return carportHistories;
	}

	@Override
	public CarportHistory queryCarportHistoryByChId(String carportHistory_id) {
		if (StringUtils.isEmpty(carportHistory_id)) {
			return null;
		}
		CarportHistory carportHistory = carportHistoryMapper
				.selectCarportHistoryById(carportHistory_id);
		return carportHistory;
	}

	@Override
	public boolean addCarportHistoryInfo(CarportHistory carportHistory) {
		if (carportHistory == null) {
			return false;
		}

		int result = carportHistoryMapper.insertCarportHistory(carportHistory);
		session.commit();
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Boolean> batchAddCarportHistoryInfo(
			List<CarportHistory> carportHistories) {

		List<Boolean> booleans = new ArrayList<Boolean>();
		for (CarportHistory carportHistory : carportHistories) {
			boolean flag = this.addCarportHistoryInfo(carportHistory);
			booleans.add(flag);
		}
		return booleans;
	}

	@Override
	public CarportHistory queryCarportHistoryByUserIdAndDate(
			CarportHistory carportHistory) {

		if (carportHistory == null) {
			return null;
		}

		List<CarportHistory> carportHistories = carportHistoryMapper
				.selectCarportHistoryByUserIdAndDate(carportHistory);
		if (carportHistories != null && carportHistories.size() > 0) {
			return carportHistories.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteCarportHistoryByCphisId(String cphis_id) {
		if (StringUtils.isEmpty(cphis_id)) {
			return false;
		}
		int result = carportHistoryMapper
				.deleteCarportHistoryByCphisId(cphis_id);
		if (result > 0) {
			return true;
		}
		return false;
	}
}

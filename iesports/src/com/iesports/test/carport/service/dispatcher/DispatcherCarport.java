/**
 * 
 */
package com.iesports.test.carport.service.dispatcher;

import java.util.List;

import org.apache.log4j.Logger;

import com.iesports.test.carport.bean.Carport;
import com.iesports.test.carport.bean.CarportHistory;
import com.iesports.test.carport.bean.UserInfo;
import com.iesports.test.carport.service.CarportHistoryService;
import com.iesports.test.carport.service.CarportService;
import com.iesports.test.carport.service.impl.CarportHistoryServiceImpl;
import com.iesports.test.carport.service.impl.CarportServiceImpl;
import com.iesports.test.carport.util.DateUtils;
import com.iesports.test.carport.util.TimerConfig;

/**
 * 描述：抢车位
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午9:07:38
 * @since
 */
public class DispatcherCarport {

	private static Logger logger = Logger.getLogger(DispatcherCarport.class);

	private static String strDateBegin = TimerConfig.getString("carport.dispacher.begin");
	private static String strDateEnd = TimerConfig.getString("carport.dispacher.end");

	public DispatcherCarport() {

	}

	public String getCarport(UserInfo userInfo) {
		logger.info("抢车位开始！");
		String result = "";
		try {
			
			// 抢车位分为两种情况
			String strDateNow = DateUtils.getDatetime();
			boolean flag = false;
			// 判断时间是否在指定的时间段内
			flag = DateUtils.isInDate(strDateNow, strDateBegin, strDateEnd);
			String datetime = DateUtils.getDate();
			String org_id = userInfo.getOrg_id();
			String apartment_id = userInfo.getApartment_id();
			List<Carport> existCarports = getCarportInfoByNowDate(datetime, apartment_id);
			
			if (existCarports == null) {
				result = "今天车位已经抢完！明天再来吧！";
				return result;
			}
			if (flag) {
				// 1.在指定时间内
				// 先查询carport表中是否还存在可以抢的车位，判断条件，当天时间，status=1,org_id,apartment_id
				List<Carport> carports = getCarportInfoInTime(datetime, org_id, apartment_id);
				if (carports != null && carports.size() > 0) {
					// 如果存在则修改车位状态 为0 表示该车位被抢了
					Carport respCarport = carports.get(0);
					String carport_id = respCarport.getCarport_id();
					flag = modifyCaportStatusTo0(carport_id);
					if (flag) {
						result = respCarport.getCarport_no();
						// 如果修改车位状态成功，则将这条记录存入数据库，作为抢车位历史记录
						CarportHistory reqCh = new CarportHistory();
						reqCh.setApartment_id(apartment_id);
						reqCh.setCarport_no(result);
						reqCh.setDatetime(DateUtils.getDatetime());
						reqCh.setOrg_id(org_id);
						reqCh.setStatus("1");
						reqCh.setUser_id(userInfo.getUser_id());
						flag = addCarportHistoryInfo(reqCh);
						if (flag) {
							logger.info("工号：" + userInfo.getJob_number() + "成功抢到" + result + "车位!");
						} else {
							result = "抢车位失败！";
						}
					}
				}
			} else {
				// 2.超过指定时间
				// 先查询carport表中是否还存在可以抢的车位，判断条件，当天时间，status=1,apartment_id
				List<Carport> carports = getCarportInfoNotInTime(datetime, apartment_id);
				if (carports != null && carports.size() > 0) {
					// 如果存在则修改车位状态 为0 表示该车位被抢了
					Carport respCarport = carports.get(0);
					String carport_id = respCarport.getCarport_id();
					flag = modifyCaportStatusTo0(carport_id);
					if (flag) {
						result = respCarport.getCarport_no();
						// 如果修改车位状态成功，则将这条记录存入数据库，作为抢车位历史记录
						CarportHistory reqCh = new CarportHistory();
						reqCh.setApartment_id(apartment_id);
						reqCh.setCarport_no(result);
						reqCh.setDatetime(DateUtils.getDatetime());
						reqCh.setOrg_id(org_id);
						reqCh.setStatus("1");
						reqCh.setUser_id(userInfo.getUser_id());
						flag = addCarportHistoryInfo(reqCh);
						if (flag) {
							logger.info("工号：" + userInfo.getJob_number() + "成功抢到" + result + "车位!");
						} else {
							result = "抢车位失败！";
						}
					}
				}
			}
			logger.info(result);
		} catch (Exception e) {
			logger.error("抢车位失败！失败原因：" + e.getMessage());
		}
		logger.info("抢车位结束！");
		return result;
	}
	
	/**
	 * 按datetime 和apartment_id查询车位信息
	 * @param datetime
	 * @param apartment_id
	 * @return
	 */
	private List<Carport> getCarportInfoByNowDate(String datetime, String apartment_id) {
		CarportService carportService = new CarportServiceImpl();
		Carport carport = new Carport();
		carport.setDatetime(datetime);
		carport.setApartment_id(apartment_id);
		List<Carport> carports = carportService.getCarportByNowDate(carport);
		return carports;
	}

	/**
	 * 在指定时间内
	 * @param datetime
	 * @param org_id
	 * @param apartment_id
	 * @return
	 */
	private List<Carport> getCarportInfoInTime(String datetime, String org_id, String apartment_id) {
		CarportService carportService = new CarportServiceImpl();
		Carport requestCarport = new Carport();
		requestCarport.setApartment_id(apartment_id);
		requestCarport.setOrg_id(org_id);
		requestCarport.setDatetime(datetime);
		List<Carport> carports = carportService.getCarportByOrgIdAndStatus1(requestCarport);
		return carports;
	}
	
	/**
	 * 不在指定时间内
	 * @param datetime
	 * @param apartment_id
	 * @return
	 */
	private List<Carport> getCarportInfoNotInTime(String datetime, String apartment_id) {
		CarportService carportService = new CarportServiceImpl();
		Carport requestCarport = new Carport();
		requestCarport.setApartment_id(apartment_id);
		requestCarport.setDatetime(datetime);
		List<Carport> carports = carportService.getCarportByOrgIdAndStatus1(requestCarport);
		return carports;
	}
	
	/**
	 * 修改车位状态
	 * @param carport_id
	 * @return
	 */
	private boolean modifyCaportStatusTo0(String carport_id) {
		CarportService carportService = new CarportServiceImpl();
		boolean flag = false;
		flag = carportService.modifyCarportStatus0ByCarportId(carport_id);
		if (flag) {
			return true;
		}
		return flag;
	}
	
	/**
	 * 新增抢车位历史记录
	 * @param carportHistory
	 * @return
	 */
	private boolean addCarportHistoryInfo(CarportHistory carportHistory) {
		CarportHistoryService carportHistoryService = new CarportHistoryServiceImpl();
		boolean flag = false;
		flag = carportHistoryService.addCarportHistoryInfo(carportHistory);
		if (flag) {
			return true;
		}
		return flag;
	}
	
}

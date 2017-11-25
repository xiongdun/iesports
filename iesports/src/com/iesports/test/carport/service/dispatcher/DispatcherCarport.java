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
 * ����������λ
 * 
 * @author zhangyijie
 * @created 2016��12��3�� ����9:07:38
 * @since
 */
public class DispatcherCarport {

	private static Logger logger = Logger.getLogger(DispatcherCarport.class);

	private static String strDateBegin = TimerConfig.getString("carport.dispacher.begin");
	private static String strDateEnd = TimerConfig.getString("carport.dispacher.end");

	public DispatcherCarport() {

	}

	public String getCarport(UserInfo userInfo) {
		logger.info("����λ��ʼ��");
		String result = "";
		try {
			
			// ����λ��Ϊ�������
			String strDateNow = DateUtils.getDatetime();
			boolean flag = false;
			// �ж�ʱ���Ƿ���ָ����ʱ�����
			flag = DateUtils.isInDate(strDateNow, strDateBegin, strDateEnd);
			String datetime = DateUtils.getDate();
			String org_id = userInfo.getOrg_id();
			String apartment_id = userInfo.getApartment_id();
			List<Carport> existCarports = getCarportInfoByNowDate(datetime, apartment_id);
			
			if (existCarports == null) {
				result = "���쳵λ�Ѿ����꣡���������ɣ�";
				return result;
			}
			if (flag) {
				// 1.��ָ��ʱ����
				// �Ȳ�ѯcarport�����Ƿ񻹴��ڿ������ĳ�λ���ж�����������ʱ�䣬status=1,org_id,apartment_id
				List<Carport> carports = getCarportInfoInTime(datetime, org_id, apartment_id);
				if (carports != null && carports.size() > 0) {
					// ����������޸ĳ�λ״̬ Ϊ0 ��ʾ�ó�λ������
					Carport respCarport = carports.get(0);
					String carport_id = respCarport.getCarport_id();
					flag = modifyCaportStatusTo0(carport_id);
					if (flag) {
						result = respCarport.getCarport_no();
						// ����޸ĳ�λ״̬�ɹ�����������¼�������ݿ⣬��Ϊ����λ��ʷ��¼
						CarportHistory reqCh = new CarportHistory();
						reqCh.setApartment_id(apartment_id);
						reqCh.setCarport_no(result);
						reqCh.setDatetime(DateUtils.getDatetime());
						reqCh.setOrg_id(org_id);
						reqCh.setStatus("1");
						reqCh.setUser_id(userInfo.getUser_id());
						flag = addCarportHistoryInfo(reqCh);
						if (flag) {
							logger.info("���ţ�" + userInfo.getJob_number() + "�ɹ�����" + result + "��λ!");
						} else {
							result = "����λʧ�ܣ�";
						}
					}
				}
			} else {
				// 2.����ָ��ʱ��
				// �Ȳ�ѯcarport�����Ƿ񻹴��ڿ������ĳ�λ���ж�����������ʱ�䣬status=1,apartment_id
				List<Carport> carports = getCarportInfoNotInTime(datetime, apartment_id);
				if (carports != null && carports.size() > 0) {
					// ����������޸ĳ�λ״̬ Ϊ0 ��ʾ�ó�λ������
					Carport respCarport = carports.get(0);
					String carport_id = respCarport.getCarport_id();
					flag = modifyCaportStatusTo0(carport_id);
					if (flag) {
						result = respCarport.getCarport_no();
						// ����޸ĳ�λ״̬�ɹ�����������¼�������ݿ⣬��Ϊ����λ��ʷ��¼
						CarportHistory reqCh = new CarportHistory();
						reqCh.setApartment_id(apartment_id);
						reqCh.setCarport_no(result);
						reqCh.setDatetime(DateUtils.getDatetime());
						reqCh.setOrg_id(org_id);
						reqCh.setStatus("1");
						reqCh.setUser_id(userInfo.getUser_id());
						flag = addCarportHistoryInfo(reqCh);
						if (flag) {
							logger.info("���ţ�" + userInfo.getJob_number() + "�ɹ�����" + result + "��λ!");
						} else {
							result = "����λʧ�ܣ�";
						}
					}
				}
			}
			logger.info(result);
		} catch (Exception e) {
			logger.error("����λʧ�ܣ�ʧ��ԭ��" + e.getMessage());
		}
		logger.info("����λ������");
		return result;
	}
	
	/**
	 * ��datetime ��apartment_id��ѯ��λ��Ϣ
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
	 * ��ָ��ʱ����
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
	 * ����ָ��ʱ����
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
	 * �޸ĳ�λ״̬
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
	 * ��������λ��ʷ��¼
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

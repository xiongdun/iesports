/**
 * 
 */
package com.carport.service.dispatcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.carport.bean.Apartment;
import com.carport.bean.Carport;
import com.carport.bean.CarportHistory;
import com.carport.bean.Organization;
import com.carport.bean.UserInfo;
import com.carport.dao.ApartmentMapper;
import com.carport.dao.CarportHistoryMapper;
import com.carport.dao.CarportMapper;
import com.carport.dao.OrganizationMapper;
import com.carport.dao.UserInfoMapper;
import com.carport.util.MybatisUtils;
import com.carport.util.StringUtils;

/**
 * ������
 * @author zhangyijie
 * @created 2016��12��20�� ����11:28:27
 * @since 
 */
public class InitDB {
	
	private static Logger logger = Logger.getLogger(InitDB.class);
	
	private static SqlSessionFactory sessionFactory = MybatisUtils.getInstance();
	private static SqlSession session = sessionFactory.openSession();
	
	private ApartmentMapper apartmentMapper = session.getMapper(ApartmentMapper.class);
	private CarportHistoryMapper carportHistoryMapper = session.getMapper(CarportHistoryMapper.class);
	private CarportMapper carportMapper = session.getMapper(CarportMapper.class);
	private UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);
	private OrganizationMapper organizationMapper = session.getMapper(OrganizationMapper.class);
	
	public InitDB() {
		logger.info("*********chushihua*");
	}
	
	/**
	 * ����������������λ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:47:50
	 * @since 
	 * @param carports
	 * @return
	 */
	public List<Boolean> batchInsertCarports(List<Carport> carports) {
		logger.info("������ӳ�λ��Ϣ��ʼ");
		List<Boolean> booleans = new ArrayList<Boolean>();

		for (Carport carport : carports) {
			boolean result = this.addCarportInfo(carport);
			booleans.add(result);
		}
		logger.info("������ӳ�λ��Ϣ����");
		return booleans;
	}
	
	/**
	 * ������������λ��¼
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:47:40
	 * @since 
	 * @param carport
	 * @return
	 */
	public boolean addCarportInfo(Carport carport) {
		logger.info("��ӳ�λ��Ϣ��ʼ");
		int result = carportMapper.insertCarport(carport);
		session.commit();
		if (result > 0) {
			return true;
		}
		logger.info("��ӳ�λ��Ϣ��ʼ����");
		return false;
	}
	
	/**
	 * ���������ű�Ų�ѯ���ų�λ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����2:41:27
	 * @since 
	 * @param apartment_no
	 * @return
	 */
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
	
	/**
	 * ���������ű�Ų�ѯ������Ϣ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:45:49
	 * @since 
	 * @param apartment_no
	 * @return
	 */
	public Apartment getApartmentInfoByApNo(String apartment_no) {
		List<Apartment> apartments = apartmentMapper.selectApartmentByApNo(apartment_no);
		Apartment apartment = new Apartment();
		if (apartments != null && apartments.size() > 0) {
			apartment = apartments.get(0);
		}
		return apartment;
	}
	
	/**
	 * ���������ű�Ų�ѯ��֯����
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:46:07
	 * @since 
	 * @param apartment_no
	 * @return
	 */
	public List<Organization> getOrganizationByApartmentNo(String apartment_no) {
		logger.info("�����ű�Ų�ѯ��֯������Ϣ��ʼ");
		if (StringUtils.isEmpty(apartment_no)) {
			return null;
		}
		List<Organization> organizations = organizationMapper.selectOrganizationByApartmentNo(apartment_no);
		logger.info("�����ű�Ų�ѯ��֯������Ϣ����");
		return organizations;
	}
	
	/**
	 * ����������ID��ѯ�û���Ϣ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:46:25
	 * @since 
	 * @param apartment_id
	 * @return
	 */
	public List<UserInfo> getUserInfoByApId(String apartment_id) {

		if (StringUtils.isEmpty(apartment_id)) {
			return null;
		}
		List<UserInfo> userInfos = userInfoMapper.selectUserInfoByApId(apartment_id);
		return userInfos;
	}
	
	/**
	 * ��������֯������ѯ�û���Ϣ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:46:38
	 * @since 
	 * @param org_id
	 * @return
	 */
	public List<UserInfo> getUserInfoByOrgId(String org_id) {
		if (StringUtils.isEmpty(org_id)) {
			return null;
		}
		List<UserInfo> userInfos = userInfoMapper.selectUserInfoByOrgId(org_id);
		return userInfos;
	}
	
	/**
	 * ��������ѯ���쳵λ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:52:29
	 * @since 
	 * @param carport
	 * @return
	 */
	public List<Carport> getCarportByNowDate(Carport carport) {
		if (carport == null) {
			return null;
		}
		List<Carport> carports = carportMapper.selectCarportByNowDate(carport);
		return carports;
	}
	
	/**
	 * ��������������λ��¼
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:52:47
	 * @since 
	 * @param carportHistory
	 * @return
	 */
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
	
	/**
	 * ��������ѯ���ó�λ
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:53:04
	 * @since 
	 * @param carport
	 * @return
	 */
	public List<Carport> getCarportByOrgIdAndStatus1(Carport carport) {
		logger.info("����֯������statusΪ1��ѯ��λ��ʼ");
		if (carport == null) {
			return null;
		}
		List<Carport> carports = carportMapper.selectCarportByOrgIdAndStatus1(carport);
		logger.info("����֯������statusΪ1��ѯ��λ����");
		return carports;
	}
	
	/**
	 * �������޸ĳ�λ״̬
	 * @author zhangyijie
	 * @created 2016��12��20�� ����1:50:57
	 * @since 
	 * @param carport_id
	 * @return
	 */
	public boolean modifyCarportStatus0ByCarportId(String carport_id) {
		logger.info("�޸ĳ�λ״̬Ϊ0��ʼ");
		int result = carportMapper.updateCarportStatusTo0(carport_id);
		session.commit();
		if (result > 0) {
			return true;
		}
		logger.info("�޸ĳ�λ״̬Ϊ0����");
		return false;
	}
	
}

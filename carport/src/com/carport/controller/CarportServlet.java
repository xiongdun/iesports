/**
 * 
 */
package com.carport.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.carport.bean.UserInfo;
import com.carport.service.UserService;
import com.carport.service.dispatcher.DispatcherCarport;
import com.carport.service.impl.UserServiceImpl;
import com.carport.util.DateUtils;
import com.carport.util.StringUtils;

/**
 * ������ǰ�������servlet,���ڻ�ȡ�ַ���λ
 * 
 * @author zhangyijie
 * @created 2016��11��29�� ����8:38:22
 * @since
 */
@WebServlet(urlPatterns = {"/getcarport"}, name="CarportServlet", 
asyncSupported = true, loadOnStartup = 3)
public class CarportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CarportServlet.class);

	private static ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<String, Boolean>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	// �����������Ҫ�õ��߳���
	// �����ܲ��þͲ��ã�Ӱ������
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user_id = req.getParameter("user_id");
		String org_id = req.getParameter("org_id");
		String car_no = req.getParameter("car_no");
		String job_number = req.getParameter("job_number");
		String apartment_id = req.getParameter("apartment_id");
		String user_name = req.getParameter("user_name");
		String uniqueKey = getUniqueKey(user_id, org_id, car_no);
		try {
			if (StringUtils.isEmpty(user_id)) {
				logger.error("user_id����Ϊ�գ�");
			}
			if (StringUtils.isEmpty(org_id)) {
				logger.error("org_id����Ϊ�գ�");
			}
			if (StringUtils.isEmpty(car_no)) {
				logger.error("car_no����Ϊ�գ�");
			}
			if (StringUtils.isEmpty(job_number)) {
				logger.error("job_number����Ϊ�գ�");
			}
			if (StringUtils.isEmpty(apartment_id)) {
				logger.error("apartment_id����Ϊ�գ�");
			}
			boolean flag = map.get(uniqueKey) == null ? false : true;
			if (flag) {
				logger.info("�벻Ҫ�ظ�����");
			} else {
				map.put(uniqueKey, true);
			}
			String datetime = DateUtils.getDate();
			// ��װ�������
			UserInfo userInfo = new UserInfo();
			userInfo.setCar_no(car_no);
			userInfo.setJob_number(job_number);
			userInfo.setOrg_id(org_id);
			userInfo.setUser_id(user_id);
			userInfo.setApartment_id(apartment_id);
			userInfo.setReg_time(datetime);
			userInfo.setUser_name(user_name);
			UserService userService = new UserServiceImpl();
			
			int isUsed = userService.isUsable(userInfo);
			if (isUsed == 0) {
				logger.info("���û���������λ");
				
				DispatcherCarport dc = new DispatcherCarport();
				String result = dc.getCarport(userInfo);
				logger.info("����λ�����" + result);
			} else if (isUsed == -10001) {
				
				logger.info("���û������ڻ򲻿���");
			} else if (isUsed == -10002) {

				logger.info("���û��Ѿ��ڵ���ɹ������˳�λ");
			} else if (isUsed == -10004) {
				logger.info("����֯������û�и��û���Ϣ");
			} else {

				logger.info("�ò�����Ա��û�п������ĳ�λ");
			}
		} catch (Exception e) {
			logger.error("�����쳣��", e);
		} finally {
			map.remove(uniqueKey);
		}
	}

	/**
	 * ��������ȡΨһ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����10:19:19
	 * @since
	 * @param user_id
	 * @param org_id
	 * @param car_no
	 * @return
	 */
	private String getUniqueKey(String user_id, String org_id, String car_no) {
		StringBuffer uniquekey = new StringBuffer();
		uniquekey.append(user_id);
		uniquekey.append(org_id);
		uniquekey.append(car_no);
		return uniquekey.toString();
	}
}

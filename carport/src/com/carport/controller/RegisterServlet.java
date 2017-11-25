/**
 * 
 */
package com.carport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.carport.bean.UserInfo;
import com.carport.service.UserService;
import com.carport.service.impl.UserServiceImpl;
import com.carport.util.DateUtils;

/**
 * 描述：用户注册的servlet
 * 
 * @author zhangyijie
 * @created 2016年11月29日 下午8:38:22
 * @since
 */
@WebServlet(urlPatterns = {"/register"}, name="RegisterServlet", 
	asyncSupported = true, loadOnStartup = 1)
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(RegisterServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String user_name = req.getParameter("user_name");
		String org_id = req.getParameter("org_id");
		String user_pwd = req.getParameter("user_pwd");
		String car_no = req.getParameter("car_no");
		String job_number = req.getParameter("job_number");
		String apartment_id = req.getParameter("apartment_id");
		String reg_time = DateUtils.getDatetime();
		UserInfo user = new UserInfo();
		user.setUser_name(user_name);
		user.setUser_pwd(user_pwd);
		user.setStatus("1");
		user.setCar_no(car_no);
		user.setOrg_id(org_id);
		user.setReg_time(reg_time);
		user.setApartment_id(apartment_id);
		user.setJob_number(job_number);
		UserService us = new UserServiceImpl();
		boolean flag = false;
		flag = us.isExist(car_no);
		if (!flag) {
			int reg_no = us.register(user);
			if (reg_no == 0) {
				// 用户注册成功
				
				logger.info("用户注册成功");
			} else if (reg_no == -10002) {
				// 用户信息已经存在
				
				logger.info("该车牌号已经存在");
			} else if (reg_no == -10003) {
				
				logger.info("该工号已经存在");
			} else {
				// reg_no = 0;
				// 注册失败
				logger.info("注册失败");
			}
		} else {
			// 该用户信息已经存在
		}

	}
}

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
import com.carport.util.StringUtils;

/**
 * ����Ա��½
 * @author xiong
 *
 */
@WebServlet(urlPatterns = {"/adminlogin"}, name="AdminLoginServlet", 
asyncSupported = true, loadOnStartup = 2)
public class AdminLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AdminLoginServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String user_name = req.getParameter("admin_name");
			String user_pwd = req.getParameter("admin_pwd");
			if (StringUtils.isEmpty(user_name) && StringUtils.isEmpty(user_pwd)) {
				logger.info("�û��������벻��Ϊ�գ�");
			} else {
				UserService userService = new UserServiceImpl();
				UserInfo userInfo = userService.adminLogin(user_name, user_pwd);
				if (userInfo != null) {
					logger.info("��¼�ɹ���");
					resp.sendRedirect("jsp/index.jsp");
				} else {
					logger.info("��¼ʧ�ܣ�������");
					resp.sendRedirect("jsp/adminlogin.jsp");
				}
			}
		} catch (Exception e) {
			logger.error("��¼ʧ�ܣ�ʧ��ԭ��" + e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

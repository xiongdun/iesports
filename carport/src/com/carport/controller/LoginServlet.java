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

import com.carport.service.UserService;
import com.carport.service.impl.UserServiceImpl;
import com.carport.util.StringUtils;

/**
 * 描述：登录
 * 
 * @author zhangyijie
 * @created 2016年12月5日 下午1:53:30
 * @since
 */
@WebServlet(urlPatterns = {"/login"}, name="LoginServlet", 
asyncSupported = true, loadOnStartup = 2)
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String user_id = req.getParameter("user_id");
		String user_pwd = req.getParameter("user_pwd");

		if (StringUtils.isEmpty(user_id)) {
			logger.info("用户Id不能为空！");
		}
		if (StringUtils.isEmpty(user_pwd)) {
			logger.info("用户密码不能为空！");
		}
		UserService userService = new UserServiceImpl();
		int isLogin = userService.login(user_id, user_pwd);
		if (isLogin == 0) {
			logger.info("登录成功！");
		} else if (isLogin == -10006) {
			logger.info("该用户不存在，先注册");
		} else {
			logger.info("该用户信息有误，请检查");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}

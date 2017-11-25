/**
 * 
 */
package com.iesports.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.iesports.dao.bean.UserInfo;
import com.iesports.service.UserInfoService;

/**
 * 描述：用户登录的Controller
 * @author xiongdun
 * @created 2016年9月1日 上午11:45:10
 * @since 
 */
//@Controller
//@RequestMapping("/user")
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	//@Resource
	private UserInfoService userInfoService;
	
	//@RequestMapping("/index")
	public String hello() {
		return "index";
	}
	
	//@RequestMapping("/login")
	public String login(String name, String password) {
		logger.info("------------login------------name:" + name + " and password:" + password);
		boolean result = this.userInfoService.login(name, password);
		System.out.println(result);
		return "login";
	}
	public static UserInfo getLoginUserInfo(HttpServletRequest request) {
		return null;
	}
}

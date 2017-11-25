/**
 * 
 */
package com.carport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carport.bean.UserInfo;
import com.carport.model.UserInfoModel;
import com.carport.service.UserService;
import com.carport.util.StringUtils;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月13日 下午7:27:17
 * @since 
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="login")
	public ModelAndView login(String admin_name, String admin_pwd, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(admin_name) && StringUtils.isNotBlank(admin_pwd)) {
			UserInfo userInfo = service.adminLogin(admin_name, admin_pwd);
			if (userInfo != null) {
				logger.info("登录成功！");
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", userInfo);
				//mav.addObject("userInfo", userInfo);
				mav.setViewName("redirect:/plat/index");
			} else {
				mav.setViewName("redirect:tologin");
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/tologin")
	public String toLogin() {
		return "adminlogin";
	}
	
	@RequestMapping(value="/all")
	public ModelAndView getAllUser(@RequestParam(value="pageNo", defaultValue="1") Integer pageNo,
			@RequestParam(value="pageSize", defaultValue="10") Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		PageInfo<UserInfoModel> pages = service.queryUserInfoByPage(pageNo, pageSize);
		mav.addObject("users", pages.getList());
		mav.setViewName("users");
		return mav;
	}
	
	@RequestMapping(value="/toadd")
	public ModelAndView toAddUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("new-user");
		return mav;
	}
	
	@RequestMapping(value="/add")
	public ModelAndView addUser(UserInfo userInfo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:all");
		return mav;
	}
	
	@RequestMapping(value="/detail")
	public String detail(String id) {
		return "my-profile";
	}
	
	@RequestMapping(value="/modify")
	public ModelAndView modify(UserInfo userInfo) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isEmpty(userInfo.getUser_id())) {
			UserInfo modifiedUser = service.modifyUserInfoByUserId(userInfo);
			mav.addObject("userInfo", modifiedUser);
			mav.setViewName("redirect:detail");
		}
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(ModelAndView mav,HttpServletRequest request) {
		mav.clear();
		mav.setViewName("redirect:tologin");
		return mav;
	}
}

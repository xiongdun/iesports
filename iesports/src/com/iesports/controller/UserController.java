/**
 * 
 */
package com.iesports.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iesports.dao.bean.TestInfo;
import com.iesports.dao.bean.UserInfo;
import com.iesports.service.TestInfoService;


/**
 * 描述：测试用的user controller
 * @author xiongdun
 * @created 2016年10月12日 上午10:18:46
 * @since 
 */
@Controller
@RequestMapping("/test")
public class UserController {
	/**
	 * 使用controller标记控制器
	 * @RequestMapping 实现对url的相对映射
	 */
	@Autowired
	private TestInfoService testInfoService;
	
	@RequestMapping(value="/list")
	public String listAllUserInfo(Model model) {
		List<TestInfo> list = this.testInfoService.queryAll();
		model.addAttribute("list", list);
		return "user_list";
	}
	@RequestMapping(value="/modify")
	public String modifyUser(UserInfo userInfo) {
		return "user_modify";
	}
	
	@RequestMapping(value="/detail")
	public ModelAndView readUserDetail(String id) {
		TestInfo test = this.testInfoService.queryTestById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("testInfo", test);
		mav.setViewName("user_modify");
		return mav;
	}
	@RequestMapping(value="/toadd")
	public String toAdd() {
		return "user_add";
	}
	
	@RequestMapping(value="/add")
	public String addUserInfo(TestInfo test) {
		return "user_list";
	}
	@RequestMapping(value="/delete")
	public String deleteUserInfoById(String id) {
		return "user_list";
	}
	@InitBinder
	public void initBinder(ServletRequestDataBinder bin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor cust = new CustomDateEditor(sdf, true);
		bin.registerCustomEditor(Date.class, cust);
	}
	@RequestMapping(value="/ajaxTest")
	public @ResponseBody TestInfo ajaxTest(Integer id) {
		System.out.println("编号：" + id);
		TestInfo test = null;
		return test;
	}
	
}

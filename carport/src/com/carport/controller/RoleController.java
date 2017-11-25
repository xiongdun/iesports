/**
 * 
 */
package com.carport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：统计
 * @author xiongdun
 * @created 2016年12月13日 下午7:27:17
 * @since 
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	
	
	@RequestMapping(value="/all")
	public ModelAndView getAllRole(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("roles");
		return mav;
	}
	
	@RequestMapping(value="/toadd")
	public String toAddRole() {
		return "new-role";
	}
	
	@RequestMapping(value="/add")
	public ModelAndView addRole() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:all");
		return mav;
	}
}

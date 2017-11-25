/**
 * 
 */
package com.carport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carport.bean.Organization;
import com.carport.model.OrganizationModel;
import com.carport.service.OrganizationService;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月13日 下午7:27:17
 * @since 
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView getAllOrg(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		PageInfo<OrganizationModel> pages = service.queryOrganizationByPage(pageNo, pageSize);
		mav.addObject("orgs", pages.getList());
		mav.setViewName("orgs");
		return mav;
	}
	@RequestMapping(value = "/toadd", method = RequestMethod.GET)
	public ModelAndView toAddOrg() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("new-org");
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addOrg() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:all");
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value = "id") String id) {
		ModelAndView mav = new ModelAndView();
		Organization organization = service.getOrganizationByOrgId(id);
		mav.addObject("org", organization);
		mav.setViewName("orgs");
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id") String id) {
		boolean flag = service.deleteOrganizationByOrgId(id);
		if (flag) {
			return "redirect:all";
		}
		return "redirect:all";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify(Organization organization) {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}

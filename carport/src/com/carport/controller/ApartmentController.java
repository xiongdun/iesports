/**
 * 
 */
package com.carport.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carport.bean.Apartment;
import com.carport.service.ApartmentService;
import com.carport.util.StringUtils;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月13日 下午7:27:17
 * @since 
 */
@Controller
@RequestMapping("/apartment")
public class ApartmentController {
	
	private static Logger logger = Logger.getLogger(ApartmentController.class);
	
	@Autowired
	private ApartmentService service;
	
	@RequestMapping(value="/add")
	public ModelAndView addApartment(Apartment apartment) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(apartment.getApartment_name()) && StringUtils.isNotBlank(apartment.getApartment_no()) ) {
			if (StringUtils.isBlank(apartment.getStatus()) || !"1".equals(apartment.getStatus())) {
				apartment.setStatus("0");
			}
			boolean flag = service.addApartmentInfo(apartment);
			if (flag) {
				mav.setViewName("redirect:all");
			}
			mav.setViewName("new-apartment");
			logger.info("新增部门成功！");
		} else {
			mav.setViewName("new-apartment");
		}
		return mav;
	}
	
	@RequestMapping(value="/all")
	public ModelAndView getAllApartment() {
		try {
			ModelAndView mav = new ModelAndView();
			PageInfo<Apartment> apartments = service.queryApartmentByPage(1, 10);
			mav.addObject("apartments", apartments.getList());
			mav.setViewName("apartments");
			return mav;
		} catch (Exception e) {
			logger.error("查询出错！" + e);
		}
		return null;
	}
	
	@RequestMapping(value="/delete")
	public ModelAndView deleteApartment(@RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			boolean flag = service.deleteApartmentInfoByApId(id);
			if (flag) {
				logger.info("删除部门成功！");
			}
		} 
		mav.setViewName("redirect:all");
		return mav;
	}
	
	@RequestMapping(value="/detail")
	public ModelAndView showDetail(@RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		if (StringUtils.isNotBlank(id)) {
			Apartment apartment = service.getApartmentInfoById(id);
			mav.addObject("apartment", apartment);
		}
		mav.setViewName("modify-apartment");
		return mav;
	}
	
	@RequestMapping(value="/toapartment")
	public String toApartment() {
		return "apartments";
	}
	
	@RequestMapping(value="/toadd")
	public String toadd() {
		return "new-apartment";
	}
	
}

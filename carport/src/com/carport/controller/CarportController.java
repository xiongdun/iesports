/**
 * 
 */
package com.carport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carport.bean.Carport;
import com.carport.model.CarportModel;
import com.carport.service.CarportService;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月13日 下午7:27:17
 * @since 
 */
@Controller
@RequestMapping("/carset")
public class CarportController {
	
	@Autowired
	private CarportService service;
	
	@RequestMapping(value="/add")
	public ModelAndView addCarport(Carport carport) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:all");
		return mav;
	}
	
	@RequestMapping(value="/all")
	public ModelAndView getAllCarport(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		PageInfo<CarportModel> pages = service.queryCarportByPage(pageNo, pageSize);
		mav.addObject("carports", pages.getList());
		mav.setViewName("carports");
		return mav;
	}
	
	@RequestMapping(value="/toadd", method = RequestMethod.GET)
	public String toCarport(Model model) {
		return "new-caport";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id", required = true) String id) {
		service.deleteCarportByCarportId(id);
		return "redirect:all";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam(value = "id", required = true) String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("");
		return mav;
	}
}

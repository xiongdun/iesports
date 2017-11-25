/**
 * 
 */
package com.carport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月13日 下午7:27:17
 * @since 
 */
@Controller
@RequestMapping("/stats")
public class StatsController {
	
	@RequestMapping(value="/index")
	public ModelAndView toStatsIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stats");
		return mav;
	}
	
	@RequestMapping(value="/user")
	public ModelAndView toStatsUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user-stats");
		return mav;
	}
	
	@RequestMapping(value="/visitor")
	public ModelAndView toStatsVisitor() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("visitor-stats");
		return mav;
	}
	
	@RequestMapping(value="/carhistory")
	public ModelAndView toStatsCarHistory() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("carport-stats");
		return mav;
	}
}

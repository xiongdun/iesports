/**
 * 
 */
package com.carport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ������
 * @author xiongdun
 * @created 2016��12��13�� ����7:27:17
 * @since 
 */
@Controller
@RequestMapping("/plat")
public class PlatController {
	
	@RequestMapping(value="/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/tologin")
	public String toLogin() {
		return "adminlogin";
	}
	
	@RequestMapping(value="/tosettings")
	public String toSettings() {
		return "settings";
	}
}

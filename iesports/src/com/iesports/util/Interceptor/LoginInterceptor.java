/**
 * 
 */
package com.iesports.util.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iesports.controller.LoginController;
import com.iesports.dao.bean.UserInfo;

/**
 * 描述：登录用拦截器
 * @author xiongdun
 * @created 2016年9月19日 下午5:22:57
 * @since 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String[] IGNORE_URI = { "/login.jsp", "/login.html", "/login/", "backui", "frontui", "/login.do" };
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		boolean flag = false;
		String url = request.getRequestURI().toString();
		logger.info(">>>请求地址：>>>>>>>>>>>" + url);
		for (String str : IGNORE_URI) {
			if (url.contains(str)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			UserInfo user = LoginController.getLoginUserInfo(request);
			if (user != null) {
				flag = true;
			}
		}
		return flag;
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}


	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception ex)
			throws Exception {
		
	}
	
}

/**
 * 
 */
package com.iesports.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：测试用servlet
 * @author xiongdun
 * @created 2016年9月19日 下午5:35:33
 * @since 
 */
public class MyTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String configName = getServletConfig().getInitParameter("servlet");
		System.out.println(configName);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

}

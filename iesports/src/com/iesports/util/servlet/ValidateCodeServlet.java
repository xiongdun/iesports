/**
 * 
 */
package com.iesports.util.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iesports.util.functions.ValidateCode;

/**
 * 描述：获取生成的验证图片servlet
 * @author xiongdun
 * @created 2016年9月19日 下午5:35:33
 * @since 
 */
public class ValidateCodeServlet extends HttpServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		super.service(arg0, arg1);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		String verifyCode = ValidateCode.genernateVerifyCode(4);
		HttpSession session = request.getSession(true);
		session.setAttribute("validate", verifyCode.toLowerCase());
		int width = 800, height = 300;
		ValidateCode.outputImage(width, height, response.getOutputStream(), verifyCode);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}

}

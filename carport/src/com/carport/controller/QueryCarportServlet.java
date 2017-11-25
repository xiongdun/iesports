/**
 * 
 */
package com.carport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.carport.bean.Carport;
import com.carport.service.CarportService;
import com.carport.service.impl.CarportServiceImpl;
import com.carport.util.StringUtils;

/**
 * 描述：车位管理
 * @author xiongdun
 * @created 2016年12月13日 下午2:32:51
 * @since 
 */
@WebServlet(urlPatterns = {"/querycarport"}, name="QueryCarportServlet", 
asyncSupported = true, loadOnStartup = 2)
public class QueryCarportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(QueryCarportServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String carport_id = req.getParameter("carportId");
		if (StringUtils.isEmpty(carport_id)) {
			
		}
		logger.info("查询车位信息！");
		List<Carport> carports = new ArrayList<Carport>();
		CarportService service = new CarportServiceImpl();
		carports = service.queryAllCarport();
		HttpSession session = req.getSession();
		session.setAttribute("carports", carports);
		
	}

}

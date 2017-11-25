/**
 * 
 */
package com.carport.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.carport.bean.UserInfo;
import com.carport.service.UserService;
import com.carport.service.dispatcher.DispatcherCarport;
import com.carport.service.impl.UserServiceImpl;
import com.carport.util.DateUtils;
import com.carport.util.StringUtils;

/**
 * 描述：前端请求的servlet,用于获取分发车位
 * 
 * @author zhangyijie
 * @created 2016年11月29日 下午8:38:22
 * @since
 */
@WebServlet(urlPatterns = {"/getcarport"}, name="CarportServlet", 
asyncSupported = true, loadOnStartup = 3)
public class CarportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CarportServlet.class);

	private static ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<String, Boolean>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	// 在这里可能需要用到线程锁
	// 但是能不用就不用，影响性能
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user_id = req.getParameter("user_id");
		String org_id = req.getParameter("org_id");
		String car_no = req.getParameter("car_no");
		String job_number = req.getParameter("job_number");
		String apartment_id = req.getParameter("apartment_id");
		String user_name = req.getParameter("user_name");
		String uniqueKey = getUniqueKey(user_id, org_id, car_no);
		try {
			if (StringUtils.isEmpty(user_id)) {
				logger.error("user_id不能为空！");
			}
			if (StringUtils.isEmpty(org_id)) {
				logger.error("org_id不能为空！");
			}
			if (StringUtils.isEmpty(car_no)) {
				logger.error("car_no不能为空！");
			}
			if (StringUtils.isEmpty(job_number)) {
				logger.error("job_number不能为空！");
			}
			if (StringUtils.isEmpty(apartment_id)) {
				logger.error("apartment_id不能为空！");
			}
			boolean flag = map.get(uniqueKey) == null ? false : true;
			if (flag) {
				logger.info("请不要重复请求！");
			} else {
				map.put(uniqueKey, true);
			}
			String datetime = DateUtils.getDate();
			// 封装请求入参
			UserInfo userInfo = new UserInfo();
			userInfo.setCar_no(car_no);
			userInfo.setJob_number(job_number);
			userInfo.setOrg_id(org_id);
			userInfo.setUser_id(user_id);
			userInfo.setApartment_id(apartment_id);
			userInfo.setReg_time(datetime);
			userInfo.setUser_name(user_name);
			UserService userService = new UserServiceImpl();
			
			int isUsed = userService.isUsable(userInfo);
			if (isUsed == 0) {
				logger.info("该用户可以抢车位");
				
				DispatcherCarport dc = new DispatcherCarport();
				String result = dc.getCarport(userInfo);
				logger.info("抢车位结果：" + result);
			} else if (isUsed == -10001) {
				
				logger.info("该用户不存在或不可用");
			} else if (isUsed == -10002) {

				logger.info("该用户已经在当天成功抢过了车位");
			} else if (isUsed == -10004) {
				logger.info("该组织机构中没有该用户信息");
			} else {

				logger.info("该部门下员工没有可以抢的车位");
			}
		} catch (Exception e) {
			logger.error("出现异常！", e);
		} finally {
			map.remove(uniqueKey);
		}
	}

	/**
	 * 描述：获取唯一键
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午10:19:19
	 * @since
	 * @param user_id
	 * @param org_id
	 * @param car_no
	 * @return
	 */
	private String getUniqueKey(String user_id, String org_id, String car_no) {
		StringBuffer uniquekey = new StringBuffer();
		uniquekey.append(user_id);
		uniquekey.append(org_id);
		uniquekey.append(car_no);
		return uniquekey.toString();
	}
}

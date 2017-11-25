/**
 * 
 */
package com.iesports.test.carport.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.iesports.test.carport.service.dispatcher.StartTimer;

/**
 * @描述： 容器定时器启动
 * @author zhangyijie
 *
 */
public class TimerContextListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(TimerContextListener.class);
	private Timer timer = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		logger.info("*************定时器任务销毁*****************");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("*************定时器任务启动*****************");
		timer = new Timer(true);
		// 设置执行时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// 设置每天17点开始初始化车位表数据
		int hour = TimerConfig.getInt("start.timer.hour");
		int minute = TimerConfig.getInt("start.timer.minute");
		int second = TimerConfig.getInt("start.timer.second");
		calendar.set(year, month, day, hour, minute, second);

		Date date = calendar.getTime();
		Timer timer = new Timer();

		// 每天的执行的定时任务，每天只执行一次
		timer.schedule(new StartTimer(event.getServletContext()), date);
		//timer.schedule(new StartTimer(event.getServletContext()), 0, 10 * 1000);
		logger.info("*************定时器任务正在添加**************");
	}
}

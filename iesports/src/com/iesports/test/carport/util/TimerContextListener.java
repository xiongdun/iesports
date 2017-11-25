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
 * @������ ������ʱ������
 * @author zhangyijie
 *
 */
public class TimerContextListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(TimerContextListener.class);
	private Timer timer = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		logger.info("*************��ʱ����������*****************");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("*************��ʱ����������*****************");
		timer = new Timer(true);
		// ����ִ��ʱ��
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// ����ÿ��17�㿪ʼ��ʼ����λ������
		int hour = TimerConfig.getInt("start.timer.hour");
		int minute = TimerConfig.getInt("start.timer.minute");
		int second = TimerConfig.getInt("start.timer.second");
		calendar.set(year, month, day, hour, minute, second);

		Date date = calendar.getTime();
		Timer timer = new Timer();

		// ÿ���ִ�еĶ�ʱ����ÿ��ִֻ��һ��
		timer.schedule(new StartTimer(event.getServletContext()), date);
		//timer.schedule(new StartTimer(event.getServletContext()), 0, 10 * 1000);
		logger.info("*************��ʱ�������������**************");
	}
}

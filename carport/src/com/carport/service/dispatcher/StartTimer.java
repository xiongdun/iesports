/**
 * 
 */
package com.carport.service.dispatcher;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.carport.util.DateUtils;

/**
 * 描述：定时器启动
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午9:07:01
 * @since
 */
public class StartTimer extends TimerTask {

	private static Logger logger = Logger.getLogger(StartTimer.class);

	// 任务启动此时计数器
	private static int count = 0;
	
	private static boolean isRunning = true;
	private ServletContext Context = null;
	
	public StartTimer(ServletContext context) {
		this.Context = context;
	}

	/**
	 * 描述：设置定时任务，用于初始化
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午4:41:37
	 * @since
	 */
	@Override
	public void run() {
		if (isRunning) {
			Context.log("定时任务开始执行！");
			count++;
			InitCarport initCarport = new InitCarport();
			initCarport.initCarportData();
			logger.info("*****时间=" + DateUtils.getDatetime() + "执行了" + count + "次！******");
			Context.log("定时任务执行完成！");
		}
		
	}
}

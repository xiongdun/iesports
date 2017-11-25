/**
 * 
 */
package com.carport.service.dispatcher;

import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.carport.util.DateUtils;

/**
 * ��������ʱ������
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����9:07:01
 * @since
 */
public class StartTimer extends TimerTask {

	private static Logger logger = Logger.getLogger(StartTimer.class);

	// ����������ʱ������
	private static int count = 0;
	
	private static boolean isRunning = true;
	private ServletContext Context = null;
	
	public StartTimer(ServletContext context) {
		this.Context = context;
	}

	/**
	 * ���������ö�ʱ�������ڳ�ʼ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����4:41:37
	 * @since
	 */
	@Override
	public void run() {
		if (isRunning) {
			Context.log("��ʱ����ʼִ�У�");
			count++;
			InitCarport initCarport = new InitCarport();
			initCarport.initCarportData();
			logger.info("*****ʱ��=" + DateUtils.getDatetime() + "ִ����" + count + "�Σ�******");
			Context.log("��ʱ����ִ����ɣ�");
		}
		
	}
}

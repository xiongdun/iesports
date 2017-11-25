/**
 * 
 */
package com.carport.util;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.carport.service.dispatcher.InitCarport;

/**
 * ������spring mvc ��ʱ����
 * @author xiongdun
 * @created 2016��12��20�� ����3:43:53
 * @since 
 */
@Component
public class InitTaskJob {
	private static Logger logger = Logger.getLogger(InitTaskJob.class);
	private static int count = 1;
	//"0 15 10 * * ? *"
	@Scheduled(cron="0 10 16 * * ?")
	public void execute() {
		
		logger.info("****ʱ�䣺" + DateUtils.getDatetime() + "ִ�ж�ʱ����" + count++ + "�Σ�****");
		InitCarport initCarport = new InitCarport();
		initCarport.initCarportData();
		
	}
	
	@Test
	public void test() {
		InitCarport initCarport = new InitCarport();
		initCarport.initCarportData();
	}
}

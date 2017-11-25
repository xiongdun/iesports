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
 * 描述：spring mvc 定时任务
 * @author xiongdun
 * @created 2016年12月20日 下午3:43:53
 * @since 
 */
@Component
public class InitTaskJob {
	private static Logger logger = Logger.getLogger(InitTaskJob.class);
	private static int count = 1;
	//"0 15 10 * * ? *"
	@Scheduled(cron="0 10 16 * * ?")
	public void execute() {
		
		logger.info("****时间：" + DateUtils.getDatetime() + "执行定时任务" + count++ + "次！****");
		InitCarport initCarport = new InitCarport();
		initCarport.initCarportData();
		
	}
	
	@Test
	public void test() {
		InitCarport initCarport = new InitCarport();
		initCarport.initCarportData();
	}
}

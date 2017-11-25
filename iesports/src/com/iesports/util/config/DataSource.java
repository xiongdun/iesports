/**
 * 
 */
package com.iesports.util.config;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.iesports.util.jdbc.JDBCProperties;

/**
 * ��������ȡ����Դ�����ݿ����ӳ�������Ϣ��
 * @author xiongdun
 * @created 2016��9��2�� ����3:41:10
 * @since 
 */
public class DataSource {
	/**
	 * ���ݿ��û���
	 */
	private static final String  USER_NAME 					  = JDBCProperties.getString("datasource.username");
	/**
	 * ���ݿ�����
	 */
	private static final String  PASSWORD  					  = JDBCProperties.getString("datasource.password");
	/**
	 * ���ݿ����ӵ�ַ
	 */
	private static final String  URL       					  = JDBCProperties.getString("datasource.url");
	/**
	 * ������������
	 */
	private static final String  DRIVER    					  = JDBCProperties.getString("datasource.driver");
	/**
	 * ��ʼ���ӳش�С
	 */
	private static final Integer INITIAL_POOL_SIZE			  = JDBCProperties.getInt("datasource.initialPoolSize", 10);
	/**
	 * ��С���ӳش�С
	 */
	private static final Integer MIN_POOL_SIZE 			  	  = JDBCProperties.getInt("datasource.minPoolSize", 10);
	/**
	 * ������ӳش�С
	 */
	private static final Integer MAX_POOL_SIZE 			  	  = JDBCProperties.getInt("datasource.maxPoolSize", 100);
	/**
	 * ���ӳ�ʱʱ��
	 */
	private static final Integer CHECKOUT_TIMEOUT             = JDBCProperties.getInt("datasource.checkoutTimeout", 100000);
	/**
	 * ���״̬˵��
	 */
	private static final Integer MAX_STATEMENTS 			  = JDBCProperties.getInt("datasource.maxStatements", 100);
	/**
	 * �������Ӳ�������
	 */
	private static final Integer IDLE_CONNECTION_TEST_PERIOD  = JDBCProperties.getInt("datasource.idleConnectionTestPeriod", 3000);
	/**
	 * �������ֵ
	 */
	private static final Integer ACQUIRE_INCREMENT 		      = JDBCProperties.getInt("datasource.acquireIncrement", 2);
	/**
	 * ����ֵ(������)
	 */
	private static final Integer ENCRYPT 					  = JDBCProperties.getInt("datasource.encrypt", 0);
	
	/**
	 * ���췽��
	 */
	public DataSource() {
		
	}
	
	public static HashMap dataMap = new HashMap();
	
	private static Logger logger = Logger.getLogger(DataSource.class);
	
	/**
	 * ��̬�����,ֻҪ���ʸ��࣬�ͻ���ִ�о�̬����飬�Ӷ��������ݿ����ӳز���
	 */
	static {
		try {
			dataMap.put("driver", DRIVER);
			dataMap.put("url", URL);
			dataMap.put("username", USER_NAME);
			dataMap.put("password", PASSWORD);
			dataMap.put("maxPoolSize", MAX_POOL_SIZE);
			dataMap.put("minPoolSize", MIN_POOL_SIZE);
			dataMap.put("maxStatements", MAX_STATEMENTS);
			dataMap.put("acquireIncrement", ACQUIRE_INCREMENT);
			dataMap.put("idleConnectionTestPeriod", IDLE_CONNECTION_TEST_PERIOD);
			dataMap.put("initialPoolSize", INITIAL_POOL_SIZE);
			dataMap.put("checkoutTimeout", CHECKOUT_TIMEOUT);
		} catch (Exception e) {
			logger.error("����Դ���ݷ�װʧ�ܣ�", e);
		}
	}
}

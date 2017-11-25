/**
 * 
 */
package com.iesports.util.config;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.iesports.util.jdbc.JDBCProperties;

/**
 * 描述：获取数据源和数据库连接池配置信息类
 * @author xiongdun
 * @created 2016年9月2日 下午3:41:10
 * @since 
 */
public class DataSource {
	/**
	 * 数据库用户名
	 */
	private static final String  USER_NAME 					  = JDBCProperties.getString("datasource.username");
	/**
	 * 数据库密码
	 */
	private static final String  PASSWORD  					  = JDBCProperties.getString("datasource.password");
	/**
	 * 数据库连接地址
	 */
	private static final String  URL       					  = JDBCProperties.getString("datasource.url");
	/**
	 * 数据连接驱动
	 */
	private static final String  DRIVER    					  = JDBCProperties.getString("datasource.driver");
	/**
	 * 初始连接池大小
	 */
	private static final Integer INITIAL_POOL_SIZE			  = JDBCProperties.getInt("datasource.initialPoolSize", 10);
	/**
	 * 最小连接池大小
	 */
	private static final Integer MIN_POOL_SIZE 			  	  = JDBCProperties.getInt("datasource.minPoolSize", 10);
	/**
	 * 最大连接池大小
	 */
	private static final Integer MAX_POOL_SIZE 			  	  = JDBCProperties.getInt("datasource.maxPoolSize", 100);
	/**
	 * 连接超时时间
	 */
	private static final Integer CHECKOUT_TIMEOUT             = JDBCProperties.getInt("datasource.checkoutTimeout", 100000);
	/**
	 * 最大状态说明
	 */
	private static final Integer MAX_STATEMENTS 			  = JDBCProperties.getInt("datasource.maxStatements", 100);
	/**
	 * 闲置连接测试周期
	 */
	private static final Integer IDLE_CONNECTION_TEST_PERIOD  = JDBCProperties.getInt("datasource.idleConnectionTestPeriod", 3000);
	/**
	 * 获得增量值
	 */
	private static final Integer ACQUIRE_INCREMENT 		      = JDBCProperties.getInt("datasource.acquireIncrement", 2);
	/**
	 * 加密值(暂无用)
	 */
	private static final Integer ENCRYPT 					  = JDBCProperties.getInt("datasource.encrypt", 0);
	
	/**
	 * 构造方法
	 */
	public DataSource() {
		
	}
	
	public static HashMap dataMap = new HashMap();
	
	private static Logger logger = Logger.getLogger(DataSource.class);
	
	/**
	 * 静态代码块,只要访问该类，就会新执行静态代码块，从而加载数据库连接池参数
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
			logger.error("数据源数据封装失败！", e);
		}
	}
}

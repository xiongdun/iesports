/**
 * 
 */
package com.iesports.util;

/**
 * 描述：项目内常量工具类
 * 放置一些
 * @author xiongdun
 * @created 2016年9月1日 上午10:09:19
 * @since 
 */
public class Constants {
	/**
	 * 数据连接驱动
	 */
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * 数据库连接地址
	 */
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/iesports?characterEncoding=utf8";
	/**
	 * 数据库用户名
	 */
	public static final String USERNAME = "root";
	/**
	 * 数据库密码
	 */
	public static final String PASSWORD = "123456";
	/**
	 * 初始连接池大小
	 */
	public static final Integer INITIAL_POOL_SIZE = 10;
	/**
	 * 最小连接池大小
	 */
	public static final Integer MIN_POOL_SIZE = 10;
	/**
	 * 最大连接池大小
	 */
	public static final Integer MAX_POOL_SIZE = 100;
	/**
	 * 连接超时时间
	 */
	public static final Integer CHECKOUT_TIMEOUT = 100000;
	/**
	 * 最大状态说明
	 */
	public static final Integer MAX_STATEMENTS = 100;
	/**
	 * 闲置连接测试周期
	 */
	public static final Integer IDLE_CONNECTION_TEST_PERIOD = 3000;
	/**
	 * 获得增量值
	 */
	public static final Integer ACQUIRE_INCREMENT = 3;
	
	/**
	 * 系统初始化配置
	 */
	public static final String SYSTEM_CONFIG_SQL = "select * from ";
	
	public static final String county = "";
}

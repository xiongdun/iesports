/**
 * 
 */
package com.iesports.util.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 描述：数据库连接管理
 * @author xiongdun
 * @created 2016年9月7日 上午10:31:19
 * @since 
 */
public class ConnManager {
	private static Logger logger = Logger.getLogger(ConnManager.class);
	
	/**
	 * 创建实例
	 */
	private static Configure configure = Configure.getInstance();
	
	public ConnManager() {
		
	}
	
	/**
	 * 
	 * 描述：获取数据源连接对象
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:07:31
	 * @since 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			DataSource dataSource = configure.getDataSource();
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("数据库连接失败！", e);
		}
		return null;
	}
	
	
	/**
	 * 
	 * 描述：获取指定id的数据源连接对象
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:10:23
	 * @since 
	 * @param id
	 * @return
	 */
	public static Connection getConnection(String id) {
		try {
			DataSource dataSource = configure.getDataSource(id);
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			logger.error("数据库连接失败！", e);
		}
		return null;
	}
	
	/**
	 * 描述：销毁数据源连接
	 * @author xiongdun
	 * @created 2016年9月20日 下午3:15:38
	 * @since
	 */
	public static void destoryConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	/**
	 * 
	 * 描述：开始数据库事务管理
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:13:54
	 * @since 
	 * @param conn
	 */
	public static void begin(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(false);
			}
		} catch (Exception e) {
			logger.error("事务开始失败！", e);
		}
	}
	
	/**
	 * 
	 * 描述：提交数据库事务管理
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:14:50
	 * @since 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			logger.error("事务提交失败！", e);
		}
	}
	
	/**
	 * 
	 * 描述：回滚数据库事务管理
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:15:33
	 * @since 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			logger.error("事务回滚失败！", e);
		}
	}
	
	/**
	 * 描述：关闭数据库事务管理
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:16:11
	 * @since 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("事务关闭失败！", e);
		}
	}
	
	/**
	 * 描述：获取数据库单连接对象
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:17:49
	 * @since 
	 * @return
	 */
	public static Connection getSingleConnection(String id) {
		try {
			HashMap xmlMap = configure.getDbConnXmlMap(id);
			String driver = (String) xmlMap.get("driver");
            String url = (String) xmlMap.get("url");
            String username = (String) xmlMap.get("username");
            String password = (String) xmlMap.get("password");
        	Class.forName(driver).newInstance();
        	Connection conn = DriverManager.getConnection(url, username, password);
        	return conn;
		} catch (Exception e) {
			logger.error("创建数据库单连接失败！", e);
		}
		return null;
	}
}

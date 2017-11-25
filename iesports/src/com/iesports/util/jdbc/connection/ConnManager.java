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
 * ���������ݿ����ӹ���
 * @author xiongdun
 * @created 2016��9��7�� ����10:31:19
 * @since 
 */
public class ConnManager {
	private static Logger logger = Logger.getLogger(ConnManager.class);
	
	/**
	 * ����ʵ��
	 */
	private static Configure configure = Configure.getInstance();
	
	public ConnManager() {
		
	}
	
	/**
	 * 
	 * ��������ȡ����Դ���Ӷ���
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:07:31
	 * @since 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			DataSource dataSource = configure.getDataSource();
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("���ݿ�����ʧ�ܣ�", e);
		}
		return null;
	}
	
	
	/**
	 * 
	 * ��������ȡָ��id������Դ���Ӷ���
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:10:23
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
			logger.error("���ݿ�����ʧ�ܣ�", e);
		}
		return null;
	}
	
	/**
	 * ��������������Դ����
	 * @author xiongdun
	 * @created 2016��9��20�� ����3:15:38
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
	 * ��������ʼ���ݿ��������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:13:54
	 * @since 
	 * @param conn
	 */
	public static void begin(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(false);
			}
		} catch (Exception e) {
			logger.error("����ʼʧ�ܣ�", e);
		}
	}
	
	/**
	 * 
	 * �������ύ���ݿ��������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:14:50
	 * @since 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			logger.error("�����ύʧ�ܣ�", e);
		}
	}
	
	/**
	 * 
	 * �������ع����ݿ��������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:15:33
	 * @since 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			logger.error("����ع�ʧ�ܣ�", e);
		}
	}
	
	/**
	 * �������ر����ݿ��������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:16:11
	 * @since 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("����ر�ʧ�ܣ�", e);
		}
	}
	
	/**
	 * ��������ȡ���ݿⵥ���Ӷ���
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:17:49
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
			logger.error("�������ݿⵥ����ʧ�ܣ�", e);
		}
		return null;
	}
}

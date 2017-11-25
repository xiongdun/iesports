/**
 * 
 */
package com.iesports.util.jdbc.connection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.iesports.util.StringUtil;
import com.iesports.util.config.Configuration;
import com.mchange.v2.c3p0.DataSources;

/**
 * ��������ȡ����Դ������Ϣ
 * @author xiongdun
 * @created 2016��9��7�� ����10:38:39
 * @since 
 */
public final class Configure {
	
	private static Logger logger = Logger.getLogger(Configure.class);
	
	/**
	 * ��DataSource.class �л�ȡdataMap��ֵ�Լ���
	 */
	private static HashMap dataMap = com.iesports.util.config.DataSource.dataMap;
	
	private static Configure instance = new Configure();
	
	private static HashMap dbConnXmlMap = new HashMap();
	
	/**
	 * ����Դ���󼯺�
	 */
	public static HashMap datasourceMap = new HashMap();
	
	/**
	 * Ĭ�ϵ�����Դid
	 */
	private static String _defaultDB = Configuration.getString("datasource.dbName", "db");
	
	static {
		loadConfig();
		logger.info("this is Configure.class,hello everybody" + _defaultDB);
	}
	
	/**
	 * ������������Դ���д���
	 * @author xiongdun
	 * @created 2016��9��7�� ����11:44:20
	 * @since
	 */
	private static void loadConfig() {
		try {
			 DataSource dataSource = buildDataSource(dataMap);
			 if (dataSource != null) {
				 datasourceMap.put(_defaultDB, dataSource);
			 }
		} catch (Exception e) {
			logger.error("����Դ��Ϣ��ȡ�쳣��", e);
		}
	}
	
	/**
	 * ˽�л����췽��
	 */
	private Configure() {
		
	}
	
	/**
	 * 
	 * ��������������Դʵ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:43:54
	 * @since 
	 * @return
	 */
	public static Configure getInstance() {
		return instance;
	}
	
	/**
	 * ��������Դ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����10:51:00
	 * @since
	 */
	public static void destoryDataSource() {
		
		try {
			for (Iterator iterator = datasourceMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				DataSource dataSource = (DataSource) datasourceMap.get(key);
				// c3p0���ӳ�����
				DataSources.destroy(dataSource);
			}
		} catch (Exception e) {
			logger.error("��������Դ����", e);
		}
	}
	
	/**
	 * ����������c3p0����Դ���ӳ�
	 * @author xiongdun
	 * @created 2016��9��7�� ����12:00:12
	 * @since 
	 * @param dataMap
	 * @return
	 */
	private static DataSource buildDataSource(HashMap dataMap) {
		// ��ȡ���ݿ�����ֵ
		String driver  = (String) dataMap.get("driver");
		String url = (String) dataMap.get("url");
		String username = (String) dataMap.get("username");
		String password = (String) dataMap.get("password");
		// ���Ѿ���ȡ���ļ�ֵ�Ƴ�
		dataMap.remove("driver");
		dataMap.remove("url");
		dataMap.remove("username");
		dataMap.remove("password");
		try {
			// ��������Դ��������
			Class.forName(driver);
			// ����c3p0���ݿ����ӳ�����
			DataSource unpooled = DataSources.unpooledDataSource(url, username, password);
			DataSource pooled = DataSources.pooledDataSource(unpooled, dataMap);
			//���������Ƿ�ɹ�
			connectToDB(pooled);
			return pooled;
		} catch (Exception e) {
			logger.error("��������Դ����", e);
		}
		return null;
	}
	
	/**
	 * �������������ݿ�
	 * @author xiongdun
	 * @created 2016��9��7�� ����1:57:59
	 * @since 
	 * @param dataSource
	 */
	private static void connectToDB(DataSource dataSource) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			//logger.info("����Դ���ӳɹ���");
		} catch (Exception e) {
			logger.error("������Դ��ȡ���ӳ���", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				logger.error("������Դ��ȡ���ӳ���", ex);
			}
		}
	}
	
	/**
	 * ��ȡȱʡ����Դ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����4:38:03
	 * @since 
	 * @return
	 */
	public DataSource getDataSource() {
		// ��ֻ��һ������Դ����ֱ�ӷ��ظ�����Դ
		if (datasourceMap.size() == 1) {
			Object[] datasourceArray = datasourceMap.values().toArray();
			return (DataSource) datasourceArray[0];
		}
		
		// ���ж������Դ�������ȱʡ������Դ
		if (StringUtil.isEmpty(_defaultDB)) {
			return null;
		}
		
		return getDataSource(_defaultDB);
	}
	
	/**
	 * ���������ļ��е�����Դ���õ���Ӧ������Դ����
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����4:42:34
	 * @since 
	 * @param id
	 * @return
	 */
	public DataSource getDataSource(String id) {
		return (DataSource) datasourceMap.get(id);
	}
	
	/**
	 * �ж�ָ��������ԴID�Ƿ����
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����4:47:01
	 * @since 
	 * @param id
	 * @return
	 */
	public static boolean isExistDataSource(String id) {
		return datasourceMap.containsKey(id);
	}
	
	/**
	 * ����datasource.xml�ļ������õ�����ԴID���õ���Ӧ������Դ��xml����
	 * 
	 * @param id
	 * @return
	 */
	public HashMap getDbConnXmlMap(String id) {
		if (StringUtil.isBlank(id)) {
			id = _defaultDB;
		}
		if (StringUtil.isBlank(id)) {
			return null;
		} else {
			return (HashMap) datasourceMap.get(id);
		}
	}
}

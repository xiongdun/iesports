/**
 * 
 */
package com.iesports.util.jdbc.session.impl;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.iesports.util.DataList;
import com.iesports.util.DataPage;
import com.iesports.util.StringUtil;
import com.iesports.util.config.Configuration;
import com.iesports.util.jdbc.DatabaseType;
import com.iesports.util.jdbc.session.Session;

/**
 * ������	�̳нӿ�Session,ʵ�����еķ�����Ա����ȡ��sessionֵ
 * @author xiongdun
 * @created 2016��9��7�� ����8:42:32
 * @since 
 */
public class SessionImpl implements Session{

	private static Logger logger = Logger.getLogger(SessionImpl.class);
	
	/**
	 * ����ӡsql�����ı�־
	 */
	private static final String nologFlag 		 = Configuration.getString("system.nolog_db_flag");
	
	/**
	 * ��ӡsql��������󳤶�
	 */
	private static final int logDbMaxLength 	 = Configuration.getInt("system.log_db_maxLength", 100);
	
	/**
	 * ��ӡselect���������¼��
	 */
	private static final int logDbMaxSelectCount = Configuration.getInt("system.log_db_maxSelectCount", 3);
	
	/**
	 * ����ӡ��־�ı�
	 */
	private static final Set<String> nologTables = new HashSet<String>();
	
	/**
	 * �����������ԡ���ʹ�������ֶ����ã���ִ�в������ʱ��������
	 */
	private static final String IS_AUTO_INCREMENT = Configuration.getString("system.isAutoIncrement");
	
	/**
	 * ִ��sql��ʹ�õ�ʱ��ֵ��������������
	 */
	private static final int EXECUTE_SQL_TIME = 1000;
	
	/**
	 * ʹ�þ�̬����������
	 */
	static {
		String tables = Configuration.getString("system.nolog_db_tables");
		if (StringUtil.isNotEmpty(tables)) {
			nologTables.addAll(StringUtil.split2List(tables.toLowerCase(), ","));
		}
	}
	
	private Connection conn = null;
	
	private int databaseType = 0;
	
	/**
	 * ��������
	 */
	private String generatedKeys = "";

	public void setGeneratedKeys(String generatedKeys) {
		this.generatedKeys = generatedKeys;
	}
	
	@Override
	public String getGeneratedKeys() {
		return this.generatedKeys;
	}
	
	/**
	 * ���췽��
	 * @param conn
	 */
	public SessionImpl(Connection conn) {
		this.conn = conn;
		setDataBaseType(conn);
	}
	
	/**
	 * �������ݿ������,���Ͷ�����DataBaseType��
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����8:44:40
	 * @since 
	 * @return
	 */
	public int getDataBaseType() {
		return databaseType;
	}
	
	/**
	 * �������������ݿ�汾����
	 * @author xiongdun
	 * @created 2016��9��12�� ����11:25:48
	 * @since 
	 * @param conn
	 */
	public void setDataBaseType(Connection conn) {
		try {
			String dataBaseTypeStr = conn.getMetaData().getDatabaseProductName();
			if (dataBaseTypeStr.equalsIgnoreCase("oracle")) {
				databaseType = DatabaseType.ORACLE;
			} else if (dataBaseTypeStr.equalsIgnoreCase("MySQL")) {
				databaseType = DatabaseType.MYSQL;
			} else if (dataBaseTypeStr.equalsIgnoreCase("Microsoft SQL Server")) {
				databaseType = DatabaseType.MSSQL;
			} else if (dataBaseTypeStr.equalsIgnoreCase("DB2")) {
				databaseType = DatabaseType.DB2;
			} else if (dataBaseTypeStr.equalsIgnoreCase("PostgreSQL")) {
				databaseType = DatabaseType.POSTGRESQL;
			} else {
				databaseType = DatabaseType.OTHER;
			}
		} catch (Exception ex) {
			logger.error("�������ݿ����ͳ���", ex);
		}
	}
	
	@Override
	public Connection getConnection() {
		return conn;
	}

	@Override
	public int getDatabaseType() {
		return databaseType;
	}

	@Override
	public int insert(String tableName, DataList data) {
		StringBuilder sqlBuffer = new StringBuilder();
		// ƴ�Ӳ������ݵ�sql���
		sqlBuffer.append("insert into " + tableName + "(");
		
		String interrogationStr = "";
		int i = 0;
		// ����Ҫ����ļ�¼����valueList������
		List valueList = new ArrayList();
		for (Iterator iterator = data.keySet().iterator(); iterator.hasNext();) {
			i++;
			String key = (String) iterator.next();
			valueList.add(data.get(key));
			
			if (i < data.size()) {
				sqlBuffer.append(key + ",");
				interrogationStr += "?,";
			} else {
				sqlBuffer.append(key);
				interrogationStr += "?";
			}
		}
		sqlBuffer.append(") values (");
		sqlBuffer.append(interrogationStr);
		sqlBuffer.append(") ");
		return update(sqlBuffer.toString(), valueList.toArray());
	}

	@Override
	public int update(String tableName, DataList data, String identify,
			Object identifyValue) {
		return update(tableName, data, new String[] { identify }, new Object[] { identifyValue });
	}

	@Override
	public int update(String tableName, DataList data, String[] identifies,
			Object[] identifiesValue) {
		StringBuilder sqlBuffer = new StringBuilder();
		sqlBuffer.append("update " + tableName + " set ");
		int i = 0;
		List valueList = new ArrayList();
		
		for (int j = 0; j < identifies.length; j++) {
			data.remove(identifies[j]);
		}
		
		for (Iterator iterator = data.keySet().iterator(); iterator.hasNext();) {
			i++;
			String key = (String) iterator.next();
			valueList.add(data.get(key));
			
			if (i < data.size()) {
				sqlBuffer.append(key + "=?,");
			} else {
				sqlBuffer.append(key + "=?");
			}
		}
		
		for (int k = 0; k < identifies.length; k++) {
			sqlBuffer.append((k == 0 ? " where " : " and ") + identifies[k] + "=?");
			valueList.add(identifiesValue[k]);
		}
		return update(sqlBuffer.toString(), valueList.toArray());
	}

	@Override
	public int delete(String tableName, String identify, Object identifyValue) {
		String sql = "delete from " + tableName + " where " + identify + "=?";
		return update(sql, new Object[] { identifyValue });
	}

	@Override
	public int update(String sql) {
		return update(sql, null);
	}

	/**
	 * ��������sql���ҳ�������������־��ӡ
	 * @author xiongdun
	 * @created 2016��9��12�� ����9:51:52
	 * @since 
	 * @param sql
	 * @return
	 */
	private String getTableNameFromSql(String sql) {
		String sql2 = StringUtil.trim(sql).toLowerCase();
		if(sql2.startsWith("insert")) {
			// insert
			int index1 = sql2.indexOf("into");
			if(index1 == -1) {
				return null;
			}
			sql2 = sql2.substring(index1 + 4).trim();
			int index2 = sql2.indexOf("values");
			if(index2 == -1) {
				return null;
			}
			sql2 = sql2.substring(0, index2);
			int index3 = sql2.indexOf("(");
			if(index3 == -1) {
				return sql2.trim();
			} else {
				return sql2.substring(0, index3).trim();
			}
		}else if(sql2.startsWith("update")) {
			// update
			sql2 = sql2.substring(6).trim();
			int index1 = sql2.indexOf(" ");
			if(index1 == -1) {
				return null;
			}
			return sql2.substring(0, index1);
		}else{
			//select and delete
			int index1 = sql2.indexOf("from");
			if(index1 == -1) {
				return null;
			}
			sql2 = sql2.substring(index1 + 4).trim();
			int index2 = sql2.indexOf(" ");
			if(index2 == -1) {
				return null;
			}
			return sql2.substring(0, index2);
		}
	}
	
	/**
	 * �������������Ƴ��ȣ�����������ת�����ַ��������ڴ�ӡ��־
	 * @author xiongdun
	 * @created 2016��9��12�� ����9:58:40
	 * @since 
	 * @param objs
	 * @return
	 */
	private String toLogStr(Object[] objs) {
		if (objs == null) {
			return "null";
		}
		int iMax = objs.length - 1;
		if (iMax == -1) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0;; i++) {
			String val = String.valueOf(objs[i]);
			int len = val.length();
			if(len <= logDbMaxLength) {
				sb.append(val);
			} else {
				sb.append("length<").append(len).append(">");
			}
			if (i == iMax) {
				return sb.append(']').toString();
			}
			sb.append(",");
		}
	}
	
	/**
	 * �������������Ƴ��ȣ���DataRow����ת�����ַ��������ڴ�ӡ��־
	 * @author xiongdun
	 * @created 2016��9��12�� ����10:05:48
	 * @since 
	 * @param dl
	 * @return
	 */
	private String toLogStr(DataList dl) {
		if (dl == null) {
			return "null";
		}
		if (dl.size() == 0) {
			return "{}";
		}

		StringBuilder b = new StringBuilder();
		b.append('{');
		for (Object key : dl.keySet()) {
			String keyStr = String.valueOf(key);
			String valStr = dl.getString(keyStr);
			int len = valStr.length();
			if(len <= logDbMaxLength) {
				b.append(keyStr).append(":").append(valStr).append(",");
			} else {
				b.append(keyStr+".length:").append(len).append(",");
			}
		}
		return b.deleteCharAt(b.length() - 1).append('}').toString();
	}
	
	/**
	 * �������������Ƴ��ȣ���List<DataRow>����ת�����ַ��������ڴ�ӡ��־
	 * @author xiongdun
	 * @created 2016��9��12�� ����10:07:10
	 * @since 
	 * @param dlList
	 * @return
	 */
	private String toLogStr(List<DataList> dlList) {
		if (dlList == null) {
			return "null";
		}
		int iMax = dlList.size() - 1;
		if (iMax == -1) {
			return "[]";
		}
		StringBuilder b = new StringBuilder();
		b.append('[');
		for (int i = 0;; i++) {
			b.append(toLogStr(dlList.get(i)));
			if (i == iMax) {
				return b.append(']').toString();
			} else if(i == logDbMaxSelectCount - 1) {
				return b.append(", ...]").toString();
			}
			b.append(",");
		}
	}
	
	@Override
	public int update(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql = " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			// ���ÿ�ʼʱ��
			long beginTime = System.currentTimeMillis();
			
			if ("1".equals(IS_AUTO_INCREMENT) && sql.toLowerCase().startsWith("insert")) {
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				pstmt = conn.prepareStatement(sql);
			}
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			int result = pstmt.executeUpdate();
			
			if ("1".equals(IS_AUTO_INCREMENT) && sql.toLowerCase().startsWith("insert")) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					String key = rs.getString(1);
					setGeneratedKeys(key);
					logger.debug("insert���������������[" + key + "]");
				}
			}
			// ����ִ��ʱ��
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����[" + result + "]����ʱ [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			return result;
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ��!" + e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		
		return 0;
	}

	@Override
	public int[] batchUpdate(String[] sqlArray) {
		Statement stmt = null;
		try {
			logger.debug("��ʼ����ִ��");
			// ���ÿ�ʼʱ��
			long beginTime = System.currentTimeMillis();
			
			stmt = conn.createStatement();
			for (int i = 0; i < sqlArray.length; i++) {
				logger.debug("sql = " + sqlArray[i]);
				stmt.addBatch(sqlArray[i]);
			}
			int[] result = stmt.executeBatch();
			// ����ִ��ʱ��
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("����ִ�н����" + Arrays.toString(result) + "����ʱ [" + time + " millisencond]");
			return result;
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeStatement(stmt);
		}
		return null;
	}

	@Override
	public int[] batchUpdate(String sql, Object[][] args) {
		PreparedStatement pstmt = null;
		try {
			logger.debug("��ʼ����ִ�� [sql = " + sql + "]");
			// ����ִ�п�ʼʱ��
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			// �Ƿ��ӡsql�����ͽ���ı�ʶ
			boolean logFlag = !"".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				// ��Ҫ��ӡ������־
				StringBuilder sb = new StringBuilder();
				if (args.length > 0) {
					for (int i = 0; i < args.length; i++) {
						Object[] curArgs = args[i];
						sb.append(toLogStr(curArgs)).append(",");
						for (int j = 1; j <= args.length; j++) {
							pstmt.setObject(j, curArgs[j - 1]);
						}
						pstmt.addBatch();	
					}
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("]");
				logger.debug("ִ�в�����" + sb.toString());
			} else {
				// ����Ҫ��ӡ������־
				for (int i = 0; i < args.length; i++) {
					Object[] curArgs = args[i];
					for (int j = 1; j <= curArgs.length; j++) {
						pstmt.setObject(j, curArgs[j - 1]);
					}
					pstmt.addBatch();
				}
			}
			
			int[] result = pstmt.executeBatch();
			
			if (logFlag) {
				long time = System.currentTimeMillis() - beginTime;
				logger.debug("����ִ�н����[" + Arrays.toString(result) + "]����ʱ [" + time + " millisecond]");
			}
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeStatement(pstmt);
		}
		return null;
	}

	@Override
	public int queryInt(String sql) {
		return queryInt(sql, null);
	}

	@Override
	public int queryInt(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			int result = 0;
			if (rs.next()) {
				result = rs.getInt(1);
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����[" + result + "]����ʱ [" + time + " millisecond]");
			} 
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return 0;
	}

	@Override
	public int[] queryIntArray(String sql) {
		return queryIntArray(sql, null);
	}

	@Override
	public int[] queryIntArray(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			// �Ƿ��ӡsql�����ͽ���ı�ʶ
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 1; i < args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			rs = pstmt.executeQuery();
			
			List<Integer> resultList = new ArrayList<Integer>();
			while (rs.next()) {
				int value = rs.getInt(1);
				resultList.add(new Integer(value));
			}
			
			int[] result = null;
			if (resultList.size() > 0) {
				result = new int[resultList.size()];
				for (int i = 0; i < result.length; i++) {
					result[i] = resultList.get(i).intValue();
				}
			}
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����" + resultList + "����ʱ [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		
		return null;
	}

	@Override
	public long queryLong(String sql) {
		return queryLong(sql, null);
	}

	@Override
	public long queryLong(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			long result = 0;
			if (rs.next()) {
				result = rs.getLong(1);
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����[" + result + "]����ʱ [" + time + " millisecond]");
			} 
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return 0;
	}

	@Override
	public long[] queryLongArray(String sql) {
		return queryLongArray(sql, null);
	}

	@Override
	public long[] queryLongArray(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			rs = pstmt.executeQuery();
			List<Long> resultList = new ArrayList<Long>();
			while (rs.next()) {
				long value = rs.getLong(1);
				resultList.add(new Long(value));
			}
			
			long[] result = null;
			if (resultList.size() > 0) {
				result = new long[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					result[i] = resultList.get(i).longValue();
				}
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����" + resultList + "����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}

	@Override
	public double queryDouble(String sql) {
		return queryDouble(sql, null);
	}

	@Override
	public double queryDouble(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//�Ƿ��ӡsql�����ͽ���ı�ʶ
			if(logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();

			double result = 0L;
			if (rs.next()) {
				result = rs.getDouble(1);
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����[" + result + "]����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
		} catch (SQLException ex) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", ex);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return 0;
	}

	@Override
	public double[] queryDoubleArray(String sql) {
		return queryDoubleArray(sql, null);
	}

	@Override
	public double[] queryDoubleArray(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			rs = pstmt.executeQuery();
			List<Double> resultList = new ArrayList<Double>();
			while (rs.next()) {
				long value = rs.getLong(1);
				resultList.add(new Double(value));
			}
			
			double[] result = null;
			if (resultList.size() > 0) {
				result = new double[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					result[i] = resultList.get(i).doubleValue();
				}
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����" + resultList + "����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}

	@Override
	public float queryFloat(String sql) {
		return queryFloat(sql, null);
	}

	@Override
	public float queryFloat(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//�Ƿ��ӡsql�����ͽ���ı�ʶ
			if(logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();

			float result = 0f;
			if (rs.next()) {
				result = rs.getFloat(1);
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����[" + result + "]����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
		} catch (SQLException ex) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", ex);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return 0;
	}

	@Override
	public float[] queryFloatArray(String sql) {
		return queryFloatArray(sql, null);
	}

	@Override
	public float[] queryFloatArray(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			rs = pstmt.executeQuery();
			List<Float> resultList = new ArrayList<Float>();
			while (rs.next()) {
				long value = rs.getLong(1);
				resultList.add(new Float(value));
			}
			
			float[] result = null;
			if (resultList.size() > 0) {
				result = new float[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					result[i] = resultList.get(i).floatValue();
				}
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����" + resultList + "����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}

	@Override
	public String queryString(String sql) {
		return queryString(sql, null);
	}

	@Override
	public String queryString(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs =  pstmt.executeQuery();
			String result = "";
			if (rs.next()) {
				result = rs.getString(1);
			}
			long time =  System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����[" + result + "]����ʱ [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return result;
		} catch (SQLException e) {
			logger.error("ִ�����ݲ���ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		
		return null;
	}

	@Override
	public String[] queryStringArray(String sql) {
		return queryStringArray(sql, null);
	}

	@Override
	public String[] queryStringArray(String sql, Object[] args) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			rs = pstmt.executeQuery();
			List<String> resultList = new ArrayList<String>();
			while (rs.next()) {
				String value = rs.getString(1);
				resultList.add(value);
			}
			String[] result = null;
			if (resultList.size() > 0) {
				result = new String[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					result[i] = resultList.get(i);
				}
			}
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����" + resultList + "����ʱ [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			return result;
		} catch (SQLException e) {
			logger.error("ִ�����ݲ���ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		
		return null;
	}

	@Override
	public DataList queryMap(String sql) {
		return queryMap(sql, null);
	}

	@Override
	public DataList queryMap(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			rs = pstmt.executeQuery();
			DataList result = null;
			ResultSetMetaData metaData = rs.getMetaData();
			if (rs.next()) {
				result = toDataList(rs, metaData);
			}
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("ִ�н����" + toLogStr(result) + "����ʱ [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			return result;
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}

	@Override
	public List query(String sql) {
		return query(sql, null);
	}

	@Override
	public List query(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//�Ƿ��ӡsql�����ͽ���ı�ʶ
			if(logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();

			List<DataList> list = new ArrayList<DataList>();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				list.add(toDataList(rs, metaData));
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����" + toLogStr(list) + "����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return list;
		} catch (SQLException ex) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", ex);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}

	@Override
	public List query(String sql, int rows) {
		return query(sql, null, rows);
	}

	@Override
	public List query(String sql, Object[] args, int rows) {
		return query(sql, args, 0, rows);
	}

	@Override
	public List query(String sql, int startRow, int rows) {
		return query(sql, null, startRow, rows);
	}

	@Override
	public List query(String sql, Object[] args, int startRow, int rows) {
		if (databaseType == DatabaseType.ORACLE) { 
			//��oracle���ݿ�
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
			sqlBuffer.append(sql);
			sqlBuffer.append(" ) row_ where rownum <= " + (startRow + rows) + ") where rownum_ > " + startRow + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else if (databaseType == DatabaseType.MYSQL) {
			//��mysql���ݿ�
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + startRow + "," + rows + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else if (databaseType == DatabaseType.MSSQL) {
			//��mssql���ݿ�
			/*
			ע�⣬�˴���û��ʹ��MSSQL���е����ݿ��ҳ��䣬����ΪMSSQL��JTDS�����Ĺ����������ֱ��
			     ʹ�õķ������˵�CURSOR������ֱ���ڷ������˽��м�¼���ƶ�������ʹ��JTDS����ʱ��
			     ����ֱ��ʹ�ù�������������ܲ���̫�
			     ����SQLServerʱҪʹ��JTDS��������Ҫʹ��MS�Լ�������
			*/
			return queryFromOtherDB(sql, args, startRow, rows);
		} else if (databaseType == DatabaseType.DB2) {
			//��DB2���ݿ�
			String temp = sql.toUpperCase();
			int fromIdx = temp.lastIndexOf(" FROM ");
			int orderIdx = temp.lastIndexOf(" ORDER ");
			String sFrom = "";
			String sOrderBy = "";
			if (orderIdx == -1) {
				sFrom = sql.substring(fromIdx, sql.length());
			} else {
				sFrom = sql.substring(fromIdx, orderIdx);
				sOrderBy = sql.substring(orderIdx);
			}
			String sSelect = sql.substring(0, fromIdx);
			
			int iBegin = startRow;
			int iEnd = startRow + rows;
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append("SELECT * FROM (" + sSelect + ", ROW_NUMBER() OVER(" + sOrderBy + ") AS rn " + sFrom + ") originTable WHERE rn BETWEEN " + iBegin + " AND " + iEnd);
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else if (databaseType == DatabaseType.POSTGRESQL) {
			//��PostgreSQL���ݿ�
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + rows + " offset " + (startRow - 1) + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else {
			//���������ݿ⣬��ʱʹ�ù������������֧��
			return queryFromOtherDB(sql, args, startRow, rows);
		}
	}

	@Override
	public DataPage queryPage(String sql, int curPage, int numPerPage) {
		return queryPage(sql, null, curPage, numPerPage);
	}

	@Override
	public DataPage queryPage(String sql, Object[] args, int curPage,
			int numPerPage) {
		//�����ܵļ�¼��
		String temp = sql;
		int orderIdx = sql.toUpperCase().lastIndexOf(" ORDER ");
		if (orderIdx != -1) {
			//temp = temp.substring(0, orderIdx);
			temp = sql.substring(0, orderIdx);
		}
		StringBuilder totalSQL = new StringBuilder(" SELECT count(1) FROM ( ");
		totalSQL.append(temp);
		totalSQL.append(" ) totalTable ");
		int totalRows = queryInt(totalSQL.toString(), args);
		
		//�����ҳ����
		DataPage page = new DataPage(curPage, numPerPage);
		//�����ܵļ�¼��
		page.setTotalRows(totalRows);
		
		//��ѯ��Ӧҳ������
		int startIndex = page.getStartIndex();
		int endIndex = page.getLastIndex();
		int rows = endIndex - startIndex;
		rows = (rows < 0) ? 0 : rows;
		
		List<DataList> list = query(sql, args, startIndex, rows);
		//��������
		page.setDataList(list);
		return page;
	}

	/**
	 * �������������ݿ�Ĳ�ѯ
	 * @author xiongdun
	 * @created 2016��9��12�� ����8:51:18
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	private List<DataList> queryFromSpecialDB(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//�Ƿ��ӡsql�����ͽ���ı�ʶ
			if(logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setFetchSize(50);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();

			List<DataList> list = new ArrayList<DataList>();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				list.add(toDataList(rs, metaData));
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����" + toLogStr(list) + "����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return list;
		} catch (SQLException ex) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", ex);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}
	
	/**
	 * �������������ݿ����͵Ĳ�ѯ
	 * @author xiongdun
	 * @created 2016��9��12�� ����8:53:13
	 * @since 
	 * @param sql
	 * @param args
	 * @param startRow
	 * @param rows
	 * @return
	 */
	private List<DataList> queryFromOtherDB(String sql, Object[] args, int startRow, int rows) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			logger.debug("��ʼִ�У�[sql= " + sql + "]��startRow=" + startRow + "��rows=" + rows);
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("ִ�в�����" + toLogStr(args));
			}
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setFetchSize(50);
			if (args != null) {
				for (int i = 1; i <= args.length; i++) {
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();

			List<DataList> list = new ArrayList<DataList>();
			//�ƶ�����ʼ��
			rs.absolute(startRow);
			ResultSetMetaData metaData = rs.getMetaData();
			int count = 0;
			while (rs.next()) {
				count++;
				list.add(toDataList(rs, metaData));
				if (count == rows) {
					break;
				}
			}
			
			long time = System.currentTimeMillis() - beginTime;
			if(logFlag) {
				logger.debug("ִ�н����" + toLogStr(list) + "����ʱ [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("ִ�� [sql= " + sql + "]ʱ���������ǰִ��ʱ��[" + time + " millisecond]");
			}
			
			return list;
		} catch (SQLException e) {
			logger.error("ִ�����ݿ����ʧ�ܣ�", e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}
	
	@Override
	public void beginTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(false);
			}
		} catch (Exception e) {
			logger.error("���ݿ�������ʧ�ܣ�", e);
		}
	}

	@Override
	public void commitTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			logger.error("���ݿ������ύʧ�ܣ�", e);
		}
	}

	@Override
	public void rollbackTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			logger.error("���ݿ�ʧ�ܻع�ʧ�ܣ�", e);
		}
	}

	@Override
	public void close() {
		// �ر����ݿ�Ự
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			conn = null;
		} catch (Exception e) {
			logger.error("�ر����ݿ����ӳ���", e);
		}
	}

	/**
	 * �������رս����
	 * @author xiongdun
	 * @created 2016��9��12�� ����11:17:40
	 * @since 
	 * @param rs
	 */
	private void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex) {
			logger.error("�رս��������", ex);
		}
	}
	
	/**
	 * ������
	 * @author xiongdun
	 * @created 2016��9��12�� ����11:19:12
	 * @since 
	 * @param pstmt
	 */
	private void closeStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception ex) {
			logger.error("�ر�PreparedStatement����", ex);
		}
	}
	
	/**
	 * ������
	 * @author xiongdun
	 * @created 2016��9��12�� ����11:19:17
	 * @since 
	 * @param stmt
	 */
	private void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {
			logger.error("�ر�statement����", ex);
		}
	}

	/**
	 * �������������ת��ΪDataList����
	 * @author xiongdun
	 * @created 2016��9��12�� ����11:21:19
	 * @since 
	 * @param rs
	 * @param metaData
	 * @return
	 * @throws SQLException 
	 */
	private DataList toDataList(ResultSet rs, ResultSetMetaData metaData) throws SQLException {
		DataList DataList = new DataList();
		int count = metaData.getColumnCount();
		for (int i = 0; i < count; i++) {
			String fieldName = "";
			if (databaseType == DatabaseType.MYSQL) {
				fieldName = metaData.getColumnLabel(i + 1);
			} else {
				fieldName = metaData.getColumnName(i + 1);
			}
			Object value = rs.getObject(fieldName);
			if (value instanceof Clob) {
				value = rs.getString(fieldName);
			} else if (value instanceof Blob) {
				value = rs.getBytes(fieldName);
			} else if (value instanceof Date) {
				value = rs.getTimestamp(fieldName);
			}
			//���ֶ���ת��ΪСд
			DataList.set(fieldName.toLowerCase(), value);
		}
		return DataList;
	}
}

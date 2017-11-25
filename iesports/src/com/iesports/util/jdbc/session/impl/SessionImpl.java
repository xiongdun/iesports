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
 * 描述：	继承接口Session,实现类中的方法成员，获取到session值
 * @author xiongdun
 * @created 2016年9月7日 上午8:42:32
 * @since 
 */
public class SessionImpl implements Session{

	private static Logger logger = Logger.getLogger(SessionImpl.class);
	
	/**
	 * 不打印sql参数的标志
	 */
	private static final String nologFlag 		 = Configuration.getString("system.nolog_db_flag");
	
	/**
	 * 打印sql参数的最大长度
	 */
	private static final int logDbMaxLength 	 = Configuration.getInt("system.log_db_maxLength", 100);
	
	/**
	 * 打印select结果的最大记录数
	 */
	private static final int logDbMaxSelectCount = Configuration.getInt("system.log_db_maxSelectCount", 3);
	
	/**
	 * 不打印日志的表
	 */
	private static final Set<String> nologTables = new HashSet<String>();
	
	/**
	 * 设置自增属性。当使用自增字段配置，并执行插入语句时进行设置
	 */
	private static final String IS_AUTO_INCREMENT = Configuration.getString("system.isAutoIncrement");
	
	/**
	 * 执行sql所使用的时间值，超过做出警告
	 */
	private static final int EXECUTE_SQL_TIME = 1000;
	
	/**
	 * 使用静态代码块的特性
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
	 * 自增主键
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
	 * 构造方法
	 * @param conn
	 */
	public SessionImpl(Connection conn) {
		this.conn = conn;
		setDataBaseType(conn);
	}
	
	/**
	 * 返回数据库的类型,类型定义在DataBaseType中
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午8:44:40
	 * @since 
	 * @return
	 */
	public int getDataBaseType() {
		return databaseType;
	}
	
	/**
	 * 描述：设置数据库版本类型
	 * @author xiongdun
	 * @created 2016年9月12日 上午11:25:48
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
			logger.error("设置数据库类型出错", ex);
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
		// 拼接插入数据的sql语句
		sqlBuffer.append("insert into " + tableName + "(");
		
		String interrogationStr = "";
		int i = 0;
		// 把需要插入的记录放入valueList集合中
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
	 * 描述：从sql中找出表名，用于日志打印
	 * @author xiongdun
	 * @created 2016年9月12日 上午9:51:52
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
	 * 描述：根据限制长度，将对象数组转换成字符串，用于打印日志
	 * @author xiongdun
	 * @created 2016年9月12日 上午9:58:40
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
	 * 描述：根据限制长度，将DataRow对象转换成字符串，用于打印日志
	 * @author xiongdun
	 * @created 2016年9月12日 上午10:05:48
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
	 * 描述：根据限制长度，将List<DataRow>对象转换成字符串，用于打印日志
	 * @author xiongdun
	 * @created 2016年9月12日 上午10:07:10
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
			logger.debug("开始执行：[sql = " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
			}
			// 设置开始时间
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
					logger.debug("insert语句生成自增主键[" + key + "]");
				}
			}
			// 计算执行时间
			long time = System.currentTimeMillis() - beginTime;
			if (logFlag) {
				logger.debug("执行结果：[" + result + "]，用时 [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			return result;
		} catch (SQLException e) {
			logger.error("执行数据库操作失败!" + e);
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
			logger.debug("开始批量执行");
			// 设置开始时间
			long beginTime = System.currentTimeMillis();
			
			stmt = conn.createStatement();
			for (int i = 0; i < sqlArray.length; i++) {
				logger.debug("sql = " + sqlArray[i]);
				stmt.addBatch(sqlArray[i]);
			}
			int[] result = stmt.executeBatch();
			// 计算执行时间
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("批量执行结果：" + Arrays.toString(result) + "，用时 [" + time + " millisencond]");
			return result;
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
		} finally {
			closeStatement(stmt);
		}
		return null;
	}

	@Override
	public int[] batchUpdate(String sql, Object[][] args) {
		PreparedStatement pstmt = null;
		try {
			logger.debug("开始批量执行 [sql = " + sql + "]");
			// 设置执行开始时间
			long beginTime = System.currentTimeMillis();
			pstmt = conn.prepareStatement(sql);
			// 是否打印sql参数和结果的标识
			boolean logFlag = !"".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				// 需要打印参数日志
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
				logger.debug("执行参数：" + sb.toString());
			} else {
				// 不需要打印参数日志
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
				logger.debug("批量执行结果：[" + Arrays.toString(result) + "]，用时 [" + time + " millisecond]");
			}
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：[" + result + "]，用时 [" + time + " millisecond]");
			} 
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			// 是否打印sql参数和结果的标识
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + resultList + "，用时 [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：[" + result + "]，用时 [" + time + " millisecond]");
			} 
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + resultList + "，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//是否打印sql参数和结果的标识
			if(logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：[" + result + "]，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
		} catch (SQLException ex) {
			logger.error("执行数据库操作失败！", ex);
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
			
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + resultList + "，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//是否打印sql参数和结果的标识
			if(logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：[" + result + "]，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
		} catch (SQLException ex) {
			logger.error("执行数据库操作失败！", ex);
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
			
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + resultList + "，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
			
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：[" + result + "]，用时 [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return result;
		} catch (SQLException e) {
			logger.error("执行数据操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + resultList + "，用时 [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			return result;
		} catch (SQLException e) {
			logger.error("执行数据操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + toLogStr(result) + "，用时 [" + time + " millisecond]");
			}
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			return result;
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//是否打印sql参数和结果的标识
			if(logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + toLogStr(list) + "，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return list;
		} catch (SQLException ex) {
			logger.error("执行数据库操作失败！", ex);
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
			//是oracle数据库
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
			sqlBuffer.append(sql);
			sqlBuffer.append(" ) row_ where rownum <= " + (startRow + rows) + ") where rownum_ > " + startRow + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else if (databaseType == DatabaseType.MYSQL) {
			//是mysql数据库
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + startRow + "," + rows + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else if (databaseType == DatabaseType.MSSQL) {
			//是mssql数据库
			/*
			注意，此处并没有使用MSSQL特有的数据库分页语句，是因为MSSQL的JTDS驱动的滚动结果集是直接
			     使用的服务器端的CURSOR，可以直接在服务器端进行记录的移动，所以使用JTDS驱动时，
			     可以直接使用滚动结果集，性能不会太差。
			     操作SQLServer时要使用JTDS驱动，不要使用MS自己的驱动
			*/
			return queryFromOtherDB(sql, args, startRow, rows);
		} else if (databaseType == DatabaseType.DB2) {
			//是DB2数据库
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
			//是PostgreSQL数据库
			StringBuilder sqlBuffer = new StringBuilder();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + rows + " offset " + (startRow - 1) + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		} else {
			//是其它数据库，暂时使用滚动结果集进行支持
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
		//计算总的记录数
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
		
		//构造分页对象
		DataPage page = new DataPage(curPage, numPerPage);
		//设置总的记录数
		page.setTotalRows(totalRows);
		
		//查询对应页的数据
		int startIndex = page.getStartIndex();
		int endIndex = page.getLastIndex();
		int rows = endIndex - startIndex;
		rows = (rows < 0) ? 0 : rows;
		
		List<DataList> list = query(sql, args, startIndex, rows);
		//设置数据
		page.setDataList(list);
		return page;
	}

	/**
	 * 描述：特殊数据库的查询
	 * @author xiongdun
	 * @created 2016年9月12日 下午8:51:18
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	private List<DataList> queryFromSpecialDB(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			logger.debug("开始执行：[sql= " + sql + "]");
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));//是否打印sql参数和结果的标识
			if(logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
				logger.debug("执行结果：" + toLogStr(list) + "，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return list;
		} catch (SQLException ex) {
			logger.error("执行数据库操作失败！", ex);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
		}
		return null;
	}
	
	/**
	 * 描述：其他数据库类型的查询
	 * @author xiongdun
	 * @created 2016年9月12日 下午8:53:13
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
			logger.debug("开始执行：[sql= " + sql + "]，startRow=" + startRow + "，rows=" + rows);
			boolean logFlag = !"1".equals(nologFlag) && !nologTables.contains(getTableNameFromSql(sql));
			if (logFlag) {
				logger.debug("执行参数：" + toLogStr(args));
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
			//移动到开始行
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
				logger.debug("执行结果：" + toLogStr(list) + "，用时 [" + time + " millisecond]");
			}
				
			if (time > EXECUTE_SQL_TIME) {
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[" + time + " millisecond]");
			}
			
			return list;
		} catch (SQLException e) {
			logger.error("执行数据库操作失败！", e);
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
			logger.error("数据库事务开启失败！", e);
		}
	}

	@Override
	public void commitTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			logger.error("数据库事务提交失败！", e);
		}
	}

	@Override
	public void rollbackTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			logger.error("数据库失败回滚失败！", e);
		}
	}

	@Override
	public void close() {
		// 关闭数据库会话
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			conn = null;
		} catch (Exception e) {
			logger.error("关闭数据库连接出错", e);
		}
	}

	/**
	 * 描述：关闭结果集
	 * @author xiongdun
	 * @created 2016年9月12日 上午11:17:40
	 * @since 
	 * @param rs
	 */
	private void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex) {
			logger.error("关闭结果集出错", ex);
		}
	}
	
	/**
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月12日 上午11:19:12
	 * @since 
	 * @param pstmt
	 */
	private void closeStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception ex) {
			logger.error("关闭PreparedStatement出错", ex);
		}
	}
	
	/**
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月12日 上午11:19:17
	 * @since 
	 * @param stmt
	 */
	private void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {
			logger.error("关闭statement出错", ex);
		}
	}

	/**
	 * 描述：将结果集转换为DataList对象
	 * @author xiongdun
	 * @created 2016年9月12日 上午11:21:19
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
			//把字段名转换为小写
			DataList.set(fieldName.toLowerCase(), value);
		}
		return DataList;
	}
}

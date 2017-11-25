/**
 * 
 */
package com.iesports.util.jdbc;

import java.util.List;

import org.apache.log4j.Logger;

import com.iesports.util.DataList;
import com.iesports.util.DataPage;
import com.iesports.util.StringUtil;
import com.iesports.util.jdbc.session.Session;
import com.iesports.util.jdbc.session.SessionFactory;

/**
 * 描述：jdbc操作模板
 * @author xiongdun
 * @created 2016年9月7日 上午8:38:39
 * @since 
 */
public class JdbcTemplate {
	
	private static Logger logger = Logger.getLogger(JdbcTemplate.class);
	
	private String datasourceId = "";
	private String generatedkeys = "";
	
	/**
	 * 构造数据库操作对象
	 */
	public JdbcTemplate() {
		
	}
	
	public JdbcTemplate(String datasourceId) {
		this.datasourceId = datasourceId;
	}
	
	/**
	 * 描述：获取sessionId值
	 * @author xiongdun
	 * @created 2016年9月13日 上午8:34:34
	 * @since 
	 * @return 数据源连接对象
	 */
	private Session getSession() {
		if (StringUtil.isEmpty(datasourceId)) {
			return SessionFactory.getSession();
		} else {
			return SessionFactory.getSession(datasourceId);
		}
	}
	
	/**
	 * 描述：插入数据库记录
	 * @author xiongdun
	 * @created 2016年9月13日 上午8:34:58
	 * @since 
	 * @param tableName 数据库表名
	 * @param data 插入的记录值
	 * @return 受影响的行数
	 */
	public int insert(String tableName, DataList data) {
		Session session = null;
		try {
			session = getSession();
			int result = session.insert(tableName, data);
			generatedkeys = session.getGeneratedKeys();
			return result;
		} catch (Exception e) {
			logger.error("数据插入失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}

	/**
	 * 描述：删除数据库记录
	 * @author xiongdun
	 * @created 2016年9月13日 上午9:05:23
	 * @since 
	 * @param tableName 数据库表名
	 * @param identify 需要删除的字段名
	 * @param identifyValue 需要删除的字段值
	 * @return
	 */
	public int delete(String tableName, String identify, Object identifyValue) {
		Session session = null;
		try {
			session = getSession();
			int result = session.delete(tableName, identify, identifyValue);
			generatedkeys = session.getGeneratedKeys();
			return result;
		} catch (Exception e) {
			logger.error("数据删除失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：更新一条表记录
	 * @author xiongdun
	 * @created 2016年9月13日 下午2:16:05
	 * @since 
	 * @param tableName 表名
	 * @param data 更新的数据
	 * @param identify 字段名
	 * @param identifyValue 字段值
	 * @return
	 */
	public int update(String tableName, DataList data, String identify, String identifyValue) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(tableName, data, identify, identifyValue);
			generatedkeys = session.getGeneratedKeys();
			return result;
		} catch (Exception e) {
			logger.error("执行数据库更新操作失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：更新一条表记录
	 * @author xiongdun
	 * @created 2016年9月13日 下午2:17:56
	 * @since 
	 * @param tableName 表名 
	 * @param data 更新的数据
	 * @param identifies 字段名
	 * @param identifiesValue 字段值
	 * @return
	 */
	public int update(String tableName, DataList data, String[] identifies, String[] identifiesValue) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(tableName, data, identifies, identifiesValue);
			return result;
		} catch (Exception e) {
			logger.error("执行数据库更新操作失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：	使用指定的sql语句更新表记录，并返回受影响的行数
	 * @author xiongdun
	 * @created 2016年9月13日 下午2:19:46
	 * @since 
	 * @param sql 指定的sql语句
	 * @return 受影响的行数
	 */
	public int update(String sql) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(sql);
			return result;
		} catch (Exception e) {
			logger.error("执行数据库更新操作失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：使用指定的sql语句更新表记录，并返回受影响的行数
	 * @author xiongdun
	 * @created 2016年9月13日 下午2:20:59
	 * @since 
	 * @param sql 指定的sql语句
	 * @param args 需要更新的参数值
	 * @return 受影响的行数
	 */
	public int udpate(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			int result = session.update(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("执行数据库更新操作失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：批量执行更新并返回每次的更新记录数
	 * @author xiongdun
	 * @created 2016年9月13日 下午2:25:00
	 * @since 
	 * @param sqlArray SQL语句数组
	 * @return 每次执行更新的记录数数组
	 */
	public int[] batchUpdate(String[] sqlArray) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.batchUpdate(sqlArray);
			return result;
		} catch (Exception e) {
			logger.error("数据库批量更更新失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：批量执行更新并返回每次的更新记录数
	 * @author xiongdun
	 * @created 2016年9月13日 下午4:50:03
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 每次执行更新的记录数数组
	 */
	public int[] batchUpdate(String sql, Object[][] args) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.batchUpdate(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("数据库批量更更新失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个整型结果
	 * @author xiongdun
	 * @created 2016年9月13日 下午4:50:52
	 * @since 
	 * @param sql SQL语句
	 * @return 查询的第一行的第一个字段的整型值。
	 */
	public int queryInt(String sql) {
		Session session = null;
		try {
			session = getSession();
			int result = session.queryInt(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询整型数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：查询一个整型结果
	 * @author xiongdun
	 * @created 2016年9月13日 下午4:51:54
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的第一个字段的整型值。
	 */
	public int queryInt(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			int result = session.queryInt(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询整型数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：查询一个整型数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午4:52:31
	 * @since 
	 * @param sql SQL语句
	 * @return 查询的多条记录第一个字段的整型值。
	 */
	public int[] queryIntArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.queryIntArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询整型数组数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个整型数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午4:53:19
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值*
	 * @return 查询的多条记录第一个字段的整型值。
	 */
	public int[] queryIntArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			int[] result = session.queryIntArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询整型数组数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个长整型结果。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:57
	 * @since 
	 * @param sql SQL语句
	 * @return 查询的第一行的第一个字段的长整型值。
	 */
	public long queryLong(String sql) {
		Session session = null;
		try {
			session = getSession();
			long result = session.queryLong(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询长整型数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：查询一个长整型结果。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:53
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的第一个字段的长整型值。
	 */
	public long queryLong(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			long result = session.queryLong(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询长整型数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：返回一个长整型数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:48
	 * @since 
	 * @param sql SQL语句
	 * @return  查询的多条记录第一个字段的长整型值。
	 */
	public long[] queryLongArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			long[] result = session.queryLongArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询长整型数组数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：返回一个长整型数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:44
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值*
	 * @return 查询的多条记录第一个字段的长整型值。
	 */
	public long[] queryLongArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			long[] result = session.queryLongArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询长整型数组数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询双精度浮点型数据
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:41
	 * @since 
	 * @param sql SQL语句
	 * @return double数据
	 */
	@Deprecated
	public double queryDouble(String sql) {
		return 0;
	}
	
	/**
	 * 描述：查询双精度浮点型数据
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:35
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return double数据
	 */
	@Deprecated
	public double queryDouble(String sql, Object[] args) {
		return 0;
	}
	
	/**
	 * 描述：查询double类型的数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:28
	 * @since 
	 * @param sql SQL语句
	 * @return double类型数据
	 */
	public double[] queryDoubleArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			double[] result = session.queryDoubleArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询double数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询double类型的数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:28
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return double类型数据
	 */
	public double[] queryDoubleArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			double[] result = session.queryDoubleArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询double数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询float类型数据
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:21
	 * @since 
	 * @param sql SQL语句
	 * @return float类型的数据
	 */
	public float queryFloat(String sql) {
		Session session = null;
		try {
			session = getSession();
			float result = session.queryFloat(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询float数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：查询float类型数据
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:17
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return float类型的值
	 */
	public float queryFloat(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			float result = session.queryFloat(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询float数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return 0;
	}
	
	/**
	 * 描述：float类型数据的数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:13
	 * @since 
	 * @param sql SQL语句
	 * @return float类型的数据数组
	 */
	public float[] queryFloatArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			float[] result = session.queryFloatArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询float数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：float类型数据的数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:09
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return float类型的数据数组
	 */
	public float[] queryFloatArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			float[] result = session.queryFloatArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询float数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询字符串值
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:05
	 * @since 
	 * @param sql SQL语句
	 * @return 查询的第一行的第一个字段的字符串值。
	 */
	public String queryString(String sql) {
		Session session = null;
		try {
			session = getSession();
			String result = session.queryString(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询String数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个字符串结果。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:17:01
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的第一个字段的字符串值。
	 */
	public String queryString(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			String result = session.queryString(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询String数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：返回一个字符串数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:16:56
	 * @since  
	 * @param sql SQL语句
	 * @return 查询的多条记录第一个字段的字符串值。
	 */
	public String[] queryStringArray(String sql) {
		Session session = null;
		try {
			session = getSession();
			String[] result = session.queryStringArray(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询String数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：返回一个字符串数组
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:16:52
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 查询的多条记录第一个字段的字符串值。
	 */
	public String[] queryStringArray(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			String[] result = session.queryStringArray(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询String数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一条记录，返回类型为DataRow，
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:16:33
	 * @since 
	 * @param sql SQL语句
	 * @return 查询的第一行的结果,反回结果中的字段名都为小写
	 */
	public DataList queryMap(String sql) {
		Session session = null;
		try {
			session = getSession();
			DataList result = session.queryMap(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一条记录，返回类型为DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:16:00
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的结果,反回结果中的字段名都为小写。
	 */
	public DataList queryMap(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			DataList result = session.queryMap(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个对象列表结果,列表中的每一行为一个DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:15:28
	 * @since 
	 * @param sql SQL语句
	 * @return 查询所有结果列表。
	 */
	public List query(String sql) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个对象列表结果,列表中的每一行为一个DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:13:17
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @return 查询所有结果。
	 */
	public List query(String sql, Object[] args) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, args);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个对象列表结果,列表中的每一行为一个DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:12:50
	 * @since 
	 * @param sql SQL语句
	 * @param rows 返回的记录数量
	 * @return 查询固定的记录数
	 */
	public List query(String sql, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, rows);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个对象列表结果,列表中的每一行为一个DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:12:20
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @param rows 返回的记录数量*
	 * @return 查询固定的记录数
	 */
	public List query(String sql, Object[] args, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, args, rows);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个对象列表结果,列表中的每一行为一个DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:11:37
	 * @since 
	 * @param sql SQL语句
	 * @param startRows 起始的行数
	 * @param rows 记录的数量
	 * @return 查询所有结果并。
	 */
	public List query(String sql, int startRows, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, startRows, rows);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个对象列表结果,列表中的每一行为一个DataRow。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:10:52
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @param startRows 起始的行数
	 * @param rows 记录的数量
	 * @return 查询所有结果并
	 */
	public List query(String sql, Object[] args, int startRows, int rows) {
		Session session = null;
		try {
			session = getSession();
			List result = session.query(sql, args, startRows, rows);
			return result;
		} catch (Exception e) {
			logger.error("查询DataList数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个分页列表结果。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:10:13
	 * @since 
	 * @param sql SQL语句
	 * @param curPage 当前页数
	 * @param numPerPage 每页显示的记录数
	 * @return 分页对象
	 */
	public DataPage queryPage(String sql, int curPage, int numPerPage) {
		Session session = null;
		try {
			session = getSession();
			DataPage result = session.queryPage(sql, curPage, numPerPage);
			return result;
		} catch (Exception e) {
			logger.error("查询DataPage数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	/**
	 * 描述：查询一个分页列表结果。
	 * @author xiongdun
	 * @created 2016年9月13日 下午9:09:08
	 * @since 
	 * @param sql SQL语句
	 * @param args 参数中的值
	 * @param curPage 当前页数
	 * @param numPerPage 每页显示的记录数
	 * @return 分页对象
	 */
	public DataPage queryPage(String sql, Object[] args, int curPage, int numPerPage) {
		Session session = null;
		try {
			session = getSession();
			DataPage result = session.queryPage(sql, args, curPage, numPerPage);
			return result;
		} catch (Exception e) {
			logger.error("查询DataPage数据失败！", e);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return null;
	}
	
	
	/**
	 * 描述：获取数据源id
	 * @author xiongdun
	 * @created 2016年9月13日 上午8:33:12
	 * @since 
	 * @return
	 */
	private String getDatasourceId() {
		return datasourceId;
	}

	/**
	 * 描述：设置数据据源Id
	 * @author xiongdun
	 * @created 2016年9月13日 上午8:33:29
	 * @since 
	 * @param datasourceId
	 */
	private void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	/**
	 * 描述：获取自增主键
	 * @author xiongdun
	 * @created 2016年9月13日 上午8:32:08
	 * @since 
	 * @return
	 */
	public String getGeneratedkeys() {
		return generatedkeys;
	}

	/**
	 * 描述：设置自增主键
	 * @author xiongdun
	 * @created 2016年9月13日 上午8:32:23
	 * @since 
	 * @param generatedkeys
	 */
	private void setGeneratedkeys(String generatedkeys) {
		this.generatedkeys = generatedkeys;
	}
	
}

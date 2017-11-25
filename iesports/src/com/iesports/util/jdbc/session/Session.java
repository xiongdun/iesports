/**
 * 
 */
package com.iesports.util.jdbc.session;

import java.sql.Connection;
import java.util.List;

import com.iesports.util.DataList;
import com.iesports.util.DataPage;

/**
 * 描述：获取会话session
 * @author xiongdun
 * @created 2016年9月7日 上午8:40:30
 * @since 
 */
public interface Session {
	
	/**
	 * 返回会话中的connection
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午8:57:10
	 * @since 
	 * @return
	 */
	public Connection getConnection();
	
	/**
	 * 返回数据库的类型，类型定义在DatabaseType类中
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午8:57:47
	 * @since 
	 * @return
	 */
	public int getDatabaseType();
	
	/**
	 * 向指定表中插入一条记录
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:02:25
	 * @since 
	 * @param tableName 需要插入记录的表名
	 * @param data 需要插入的数据
	 * @return
	 */
	public int insert(String tableName, DataList data);
	
	/**
	 * 向指定数据表中更新一天记录
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:08:07
	 * @since 
	 * @param tableName 指定的表名
	 * @param data 需要插入的数据
	 * @param identify 更新记录的主键ID
	 * @param identifyValue 更新的记录值
	 * @return
	 */
	public int update(String tableName, DataList data, String identify, Object identifyValue);
	
	/**
	 * 更新表记录
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:11:22
	 * @since 
	 * @param tableName 指定的数据表名
	 * @param data 需要更新的值
	 * @param identifies 字段名
	 * @param identifiesValue 字段值
	 * @return
	 */
	public int update(String tableName, DataList data, String[] identifies, Object[] identifiesValue);
	
	/**
	 * 刪除指定表中的记录
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:16:31
	 * @since 
	 * @param tableName 指定的数据表名
	 * @param identify 字段名
	 * @param identifyValue 字段值
	 * @return
	 */
	public int delete(String tableName, String identify, Object identifyValue);
	
	/**
	 * 使用指定的sql 更新数据表记录
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:20:49
	 * @since 
	 * @param sql 指定的Sql语句
	 * @return 受影响的行数
	 */
	public int update(String sql);
	
	/**
	 * 
	 * 描述：使用指定的sql 更新数据表记录
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:22:44
	 * @since 
	 * @param sql
	 * @param args 需要修改的参数值
	 * @return
	 */
	public int update(String sql, Object[] args);
	
	/**
	 * 执行批量更新
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:26:01
	 * @since 
	 * @param sqlArray  批量更新需要的sql数组数据
	 * @return
	 */
	public int[] batchUpdate(String[] sqlArray);
	
	/**
	 * 执行批量更新
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:27:24
	 * @since 
	 * @param sql 指定的Sql语句
	 * @param args 需要更新的参数数据
	 * @return
	 */
	public int[] batchUpdate(String sql, Object[][] args);
	
	/**
	 * 查询一个整型结果
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:29:30
	 * @since 
	 * @param sql 指定的sql语句
	 * @return
	 */
	public int queryInt(String sql);
	
	/**
	 * 查询一个整型结果。
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的第一个字段的整型值。
	 */
	public int queryInt(String sql, Object[] args);
	
	/**
	 * 返回一个数字数组
	 *
	 * @param sql SQL语句
	 * @return 查询的多条记录第一个字段的整型值。
	 */
	public int[] queryIntArray(String sql);
	
	/**
	 * 返回一个数字数组
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值*
	 * @return 查询的多条记录第一个字段的整型值。
	 */
	public int[] queryIntArray(String sql, Object[] args);
	
	/**
	 * 查询一条长整型数据
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:31:05
	 * @since 
	 * @param sql 指定的sql语句
	 * @return
	 */
	public long queryLong(String sql);
	
	/**
	 * 查询一条长整型数据结果
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:32:12
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public long queryLong(String sql, Object[] args);
	
	/**
	 * 查询一个长整型数据数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:34:00
	 * @since 
	 * @param sql
	 * @return
	 */
	public long[] queryLongArray(String sql);
	
	/**
	 * 查询一条长整型数据数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:34:52
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public long[] queryLongArray(String sql, Object[] args);
	
	/**
	 * 查询一条双精度浮点型数据
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:38:25
	 * @since 
	 * @param sql
	 * @return
	 */
	public double queryDouble(String sql);
	
	/**
	 * 查询一条双精度浮点型数据
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:38:55
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public double queryDouble(String sql, Object[] args);
	
	/**
	 * 查询一条双精度浮点型数据数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:38:55
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public double[] queryDoubleArray(String sql);
	
	/**
	 * 查询一条双精度浮点型数据数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:38:55
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public double[] queryDoubleArray(String sql, Object[] args);
	
	/**
	 * 查询一条浮点型数据
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:41:55
	 * @since 
	 * @param sql
	 * @return
	 */
	public float queryFloat(String sql);
	
	/**
	 * 查询一条浮点型数据
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:42:08
	 * @since 
	 * @param sql
	 * @param args
	 * @return
	 */
	public float queryFloat(String sql, Object[] args);
	
	/**
	 * 描述：查询一条浮点型数据数组
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:44:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public float[] queryFloatArray(String sql);
	
	/**
	 * 描述：查询一条浮点型数据数组
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:44:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public float[] queryFloatArray(String sql, Object[] args);
	
	/**
	 * 查询一条字符串结果
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String queryString(String sql);
	
	/**
	 * 查询一条字符串结果
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String queryString(String sql, Object[] args);
	
	/**
	 * 查询一条字符串结果数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String[] queryStringArray(String sql);
	
	/**
	 * 查询一条字符串结果数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:47:04
	 * @since 
	 * @param sql
	 * @return
	 */
	public String[] queryStringArray(String sql, Object[] args);
	
	/**
	 * 查询一条数据表记录，使用DataList封装返回
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:50:47
	 * @since 
	 * @param sql
	 * @return
	 */
	public DataList queryMap(String sql);
	
	/**
	 * 查询一条数据表记录，使用DataList封装返回
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午9:50:47
	 * @since 
	 * @param sql
	 * @return
	 */
	public DataList queryMap(String sql, Object[] args);
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DataRow。
	 *
	 * @param sql      SQL语句
	 * @param args     参数中的值
	 * @param startRow 起始的行数
	 * @param rows     记录的数量
	 * @return 查询所有结果并。
	 */
	public List query(String sql);
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DataRow。
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 查询所有结果。
	 */
	public List query(String sql, Object[] args);
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DataRow。
	 *
	 * @param sql  SQL语句
	 * @param rows 返回的记录数量
	 * @return 查询固定的记录数
	 */
	public List query(String sql, int rows);
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DataRow。
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @param rows 返回的记录数量*
	 * @return 查询固定的记录数
	 */
	public List query(String sql, Object[] args, int rows);
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DataRow。
	 *
	 * @param sql      SQL语句
	 * @param args     参数中的值
	 * @param startRow 起始的行数
	 * @param rows     记录的数量
	 * @return 查询所有结果并。
	 */
	public List query(String sql, int startRow, int rows);
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DataRow。
	 *
	 * @param sql      SQL语句
	 * @param args     参数中的值
	 * @param startRow 起始的行数
	 * @param rows     记录的数量
	 * @return 查询所有结果并。
	 */
	public List query(String sql, Object[] args, int startRow, int rows);
	
	/**
	 * 查询一个分页列表结果。返回的数据的每一行数据类型为DataRow
	 *
	 * @param sql        SQL语句
	 * @param args       参数中的值
	 * @param curPage    当前页数
	 * @param numPerPage 每页显示的记录数
	 * @return 分页对象
	 */
	public DataPage queryPage(String sql, int curPage, int numPerPage);
	
	/**
	 * 查询一个分页列表结果。返回的数据的每一行数据类型为DataRow
	 *
	 * @param sql        SQL语句
	 * @param args       参数中的值
	 * @param curPage    当前页数
	 * @param numPerPage 每页显示的记录数
	 * @return 分页对象
	 */
	public DataPage queryPage(String sql, Object[] args, int curPage, int numPerPage);
	
	/**
	 * 事务开始
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:08:22
	 * @since
	 */
	public void beginTrans();
	
	/**
	 * 事务提交
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:08:16
	 * @since
	 */
	public void commitTrans();
	
	/**
	 * 事务回滚
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:08:08
	 * @since
	 */
	public void rollbackTrans();
	
	/**
	 * 会话关闭
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:08:01
	 * @since
	 */
	public void close();
	
	/**
	 * 获取自增ID值
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:07:11
	 * @since 
	 * @return
	 */
	public String getGeneratedKeys();
}
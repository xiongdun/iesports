/**
 * 
 */
package com.iesports.util.jdbc;

/**
 * 描述：数据库类型,定义不同的数据库所特定的编号类型
 * @author xiongdun
 * @created 2016年9月7日 下午7:33:22
 * @since 
 */
public final class DatabaseType {
	/**
	 * 其他数据库
	 */
	public static final int OTHER 	   = 0;
	/**
	 * oracle 数据库
	 */
	public static final int ORACLE	   = 1;
	/**
	 * mysql 数据库
	 */
	public static final int MYSQL      = 2;
	/**
	 * mssql 数据库
	 */
	public static final int MSSQL      = 3;
	/**
	 * db2数据库
	 */
	public static final int DB2        = 4;
	/**
	 * postgersql 数据库
	 */
	public static final int POSTGRESQL = 5;
}

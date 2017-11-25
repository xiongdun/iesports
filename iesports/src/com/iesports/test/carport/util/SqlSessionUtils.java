/**
 * 
 */
package com.iesports.test.carport.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 描述：sql session 工具类
 * 
 * @author zhangyijie
 * @created 2016年12月4日 下午4:11:47
 * @since
 */
public class SqlSessionUtils {

	private static SqlSessionFactory sessionFactory = null;
	static {
		sessionFactory = MybatisUtils.getInstance();
	}

	public static SqlSession openSession() {
		return sessionFactory.openSession();
	}
}

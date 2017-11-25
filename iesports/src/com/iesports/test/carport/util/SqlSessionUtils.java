/**
 * 
 */
package com.iesports.test.carport.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * ������sql session ������
 * 
 * @author zhangyijie
 * @created 2016��12��4�� ����4:11:47
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

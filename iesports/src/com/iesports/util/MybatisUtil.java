/**
 * 
 */
package com.iesports.util;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.iesports.test.dao.MybatisTest;

/**
 * 描述：mybatis帮助工具类
 * @author xiongdun
 * @created 2016年10月9日 下午1:56:14
 * @since 
 */
public class MybatisUtil {
	
	private static final String CONFIG_FILE = "mybatis-config.xml";
	private static SqlSessionFactory sessionFactory = null;
	
	static {
		InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * 描述：获取sessionFactory
	 * @author xiongdun
	 * @created 2016年10月9日 下午3:07:41
	 * @since 
	 * @return
	 */
	public static SqlSessionFactory getSessionFactory() {
		InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		return sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * 描述：插入记录
	 * @author xiongdun
	 * @created 2016年10月9日 下午3:10:22
	 * @since 
	 * @param statment
	 * @return
	 */
	public static int insert(String statement) { 
		SqlSession session = sessionFactory.openSession();
		int result = session.insert(statement);
		session.commit();
		session.close();
		return result;
	}
	
	/**
	 * 描述：指定插入记录
	 * @author xiongdun
	 * @created 2016年10月9日 下午3:10:22
	 * @since 
	 * @param statment
	 * @return
	 */
	public static int insert(String statement, Object param) {
		// 这里的session默认是自动提交的
		SqlSession session = sessionFactory.openSession();
		int result = session.insert(statement, param);
		// 但是在这里需要手动提交，否则会导致sesion依然存在缓存中，并不会将记录插入进数据表中
		session.commit();
		session.close();
		return result;
	}
	
	/**
	 * 描述：按参数查询
	 * @author xiongdun
	 * @created 2016年10月9日 下午2:23:34
	 * @since 
	 * @param statement
	 * @param param
	 * @return
	 */
	public static Object selectOne(String statement, String param) {
		SqlSession session = sessionFactory.openSession();
		Object object = session.selectOne(statement, param);
		session.close();
		return object;
	}
	
	/**
	 * 描述：无参查询
	 * @author xiongdun
	 * @created 2016年10月9日 下午2:24:03
	 * @since 
	 * @param statement
	 * @return
	 */
	public static Object selectOne(String statement) {
		SqlSession session = sessionFactory.openSession();
		Object object = session.selectOne(statement);
		session.close();
		return object;
	}
	
	/**
	 * 描述：无参多记录查询
	 * @author xiongdun
	 * @created 2016年10月9日 下午4:44:25
	 * @since 
	 * @param statement
	 * @return 记录集合
	 */
	public static List<Object> selectList(String statement) {
		SqlSession session = sessionFactory.openSession();
		List<Object> lists = session.selectList(statement);
		session.close();
		return lists;
	}
	
	/**
	 * 描述：指定参数多记录查询
	 * @author xiongdun
	 * @created 2016年10月9日 下午4:45:31
	 * @since 
	 * @param statement
	 * @param param 指定参数
	 * @return 记录集合
	 */
	public static List<Object> selectList(String statement, Object param) {
		SqlSession session = sessionFactory.openSession();
		List<Object> lists = session.selectList(statement, param);
		session.close();
		return lists;
	}
	
	public static Map<Object, Object> selectMap(String statement, String param) {
		SqlSession session = sessionFactory.openSession();
		Map<Object, Object> map = session.selectMap(statement, param);
		session.close();
		return map;
	}
	
	/**
	 * 描述：删除记录
	 * @author xiongdun
	 * @created 2016年10月9日 下午4:41:59
	 * @since 
	 * @param statement
	 * @return
	 */
	public static int delete(String statement) {
		SqlSession session = sessionFactory.openSession();
		int result = session.update(statement);
		session.commit();
		session.close();
		return result;
	}
	
	/**
	 * 描述：指定参数删除记录
	 * @author xiongdun
	 * @created 2016年10月9日 下午4:42:10
	 * @since 
	 * @param statement
	 * @param param
	 * @return
	 */
	public static int delete(String statement, Object param) {
		SqlSession session = sessionFactory.openSession();
		int result = session.update(statement, param);
		session.commit();
		session.close();
		return result;
	}
	
	/**
	 * 描述：修改记录
	 * @author xiongdun
	 * @created 2016年10月9日 下午4:28:47
	 * @since 
	 * @param statement
	 * @return
	 */
	public static int update(String statement) {
		SqlSession session = sessionFactory.openSession();
		int result = session.update(statement);
		session.commit();
		session.close();
		return result;
	}
	
	/**
	 * 描述：修改指定参数记录
	 * @author xiongdun
	 * @created 2016年10月9日 下午4:28:55
	 * @since 
	 * @param statement
	 * @param param
	 * @return
	 */
	public static int update(String statement, Object param) {
		SqlSession session = sessionFactory.openSession();
		int result = session.update(statement, param);
		session.commit();
		session.close();
		return result;
	}
	
}

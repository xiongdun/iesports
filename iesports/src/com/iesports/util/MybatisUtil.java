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
 * ������mybatis����������
 * @author xiongdun
 * @created 2016��10��9�� ����1:56:14
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
	 * ��������ȡsessionFactory
	 * @author xiongdun
	 * @created 2016��10��9�� ����3:07:41
	 * @since 
	 * @return
	 */
	public static SqlSessionFactory getSessionFactory() {
		InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		return sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * �����������¼
	 * @author xiongdun
	 * @created 2016��10��9�� ����3:10:22
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
	 * ������ָ�������¼
	 * @author xiongdun
	 * @created 2016��10��9�� ����3:10:22
	 * @since 
	 * @param statment
	 * @return
	 */
	public static int insert(String statement, Object param) {
		// �����sessionĬ�����Զ��ύ��
		SqlSession session = sessionFactory.openSession();
		int result = session.insert(statement, param);
		// ������������Ҫ�ֶ��ύ������ᵼ��sesion��Ȼ���ڻ����У������Ὣ��¼��������ݱ���
		session.commit();
		session.close();
		return result;
	}
	
	/**
	 * ��������������ѯ
	 * @author xiongdun
	 * @created 2016��10��9�� ����2:23:34
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
	 * �������޲β�ѯ
	 * @author xiongdun
	 * @created 2016��10��9�� ����2:24:03
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
	 * �������޲ζ��¼��ѯ
	 * @author xiongdun
	 * @created 2016��10��9�� ����4:44:25
	 * @since 
	 * @param statement
	 * @return ��¼����
	 */
	public static List<Object> selectList(String statement) {
		SqlSession session = sessionFactory.openSession();
		List<Object> lists = session.selectList(statement);
		session.close();
		return lists;
	}
	
	/**
	 * ������ָ���������¼��ѯ
	 * @author xiongdun
	 * @created 2016��10��9�� ����4:45:31
	 * @since 
	 * @param statement
	 * @param param ָ������
	 * @return ��¼����
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
	 * ������ɾ����¼
	 * @author xiongdun
	 * @created 2016��10��9�� ����4:41:59
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
	 * ������ָ������ɾ����¼
	 * @author xiongdun
	 * @created 2016��10��9�� ����4:42:10
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
	 * �������޸ļ�¼
	 * @author xiongdun
	 * @created 2016��10��9�� ����4:28:47
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
	 * �������޸�ָ��������¼
	 * @author xiongdun
	 * @created 2016��10��9�� ����4:28:55
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

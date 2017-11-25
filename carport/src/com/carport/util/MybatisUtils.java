/**
 * 
 */
package com.carport.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * ������mybatis���г־û�������
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����10:59:18
 * @since
 */
public class MybatisUtils {

	private static Logger logger = Logger.getLogger(MybatisUtils.class);

	private static SqlSessionFactory sessionFactory = null;

	private static Reader reader = null;

	// ���������ļ�
	static {
		try {
			// mybatis �����ļ�
			String resource = "mybatis-config.xml";
			// ʹ��Mybatis�ṩ��resource��������mybatis�������ļ���ͬ�����ع�����ӳ���ļ���
			reader = Resources.getResourceAsReader(resource);
			// ����sqlSessionFactory
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (Exception e) {
			logger.info("mybatis�����ļ�����ʧ�ܣ�ʧ��ԭ��" + e);
		}
	}

	/**
	 * ��������ȡMybatis�־û�ʵ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����11:15:23
	 * @since
	 * @return
	 */
	public static SqlSessionFactory getInstance() {
		return sessionFactory;
	}
}

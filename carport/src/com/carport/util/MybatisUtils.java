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
 * 描述：mybatis进行持久化工具类
 * 
 * @author zhangyijie
 * @created 2016年12月2日 下午10:59:18
 * @since
 */
public class MybatisUtils {

	private static Logger logger = Logger.getLogger(MybatisUtils.class);

	private static SqlSessionFactory sessionFactory = null;

	private static Reader reader = null;

	// 加载配置文件
	static {
		try {
			// mybatis 配置文件
			String resource = "mybatis-config.xml";
			// 使用Mybatis提供的resource类来加载mybatis的配置文件（同样加载关联的映射文件）
			reader = Resources.getResourceAsReader(resource);
			// 构建sqlSessionFactory
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		} catch (Exception e) {
			logger.info("mybatis配置文件加载失败！失败原因：" + e);
		}
	}

	/**
	 * 描述：获取Mybatis持久化实例
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 下午11:15:23
	 * @since
	 * @return
	 */
	public static SqlSessionFactory getInstance() {
		return sessionFactory;
	}
}

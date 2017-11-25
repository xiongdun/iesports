/**
 * 
 */
package com.iesports.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iesports.util.config.Configuration;

/**
 * 描述：spring bean 操作工具类
 * 本类主要用来加载并保持一个Spring的上下文环境
 * @author xiongdun
 * @created 2016年9月12日 上午11:45:24
 * @since 
 */
public class SpringUtil {
	private static Logger logger = Logger.getLogger(SpringUtil.class);
	private static AtomicBoolean isInit = new AtomicBoolean(false);
	private static ApplicationContext act = null;
	
	static {
		isInit.set(true);
		init();
	}
	
	/**
	 * 描述：初始化上下文
	 * @author xiongdun
	 * @created 2016年9月26日 下午3:11:29
	 * @since
	 */
	public static void init() {
		String serviceFile = Configuration.getString("system.service_conf_path", "application_context.xml");
		act = new ClassPathXmlApplicationContext(serviceFile.split("\\|"));
	}
	
	/**
	 * 描述：获取spring 上下文
	 * @author xiongdun
	 * @created 2016年9月26日 下午3:11:41
	 * @since 
	 * @return
	 */
	public static ApplicationContext getContext() {
		if (!isInit.get()) {
			logger.info("SpringUtil ctx  is begin init  on getContext");
			init();
		}
		return act;
	}
	
	/**
	 * 描述：获取指定beanId的bean
	 * @author xiongdun
	 * @created 2016年9月26日 下午3:11:54
	 * @since 
	 * @param beanId
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String beanId, Class<T> clazz) {
		return act.getBean(beanId, clazz);
	}
	
	/**
	 * 描述：获取指定beanId的bean
	 * @author xiongdun
	 * @created 2016年9月26日 下午3:12:17
	 * @since 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		return act.getBean(beanId);
	}
}
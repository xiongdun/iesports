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
 * ������spring bean ����������
 * ������Ҫ�������ز�����һ��Spring�������Ļ���
 * @author xiongdun
 * @created 2016��9��12�� ����11:45:24
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
	 * ��������ʼ��������
	 * @author xiongdun
	 * @created 2016��9��26�� ����3:11:29
	 * @since
	 */
	public static void init() {
		String serviceFile = Configuration.getString("system.service_conf_path", "application_context.xml");
		act = new ClassPathXmlApplicationContext(serviceFile.split("\\|"));
	}
	
	/**
	 * ��������ȡspring ������
	 * @author xiongdun
	 * @created 2016��9��26�� ����3:11:41
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
	 * ��������ȡָ��beanId��bean
	 * @author xiongdun
	 * @created 2016��9��26�� ����3:11:54
	 * @since 
	 * @param beanId
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String beanId, Class<T> clazz) {
		return act.getBean(beanId, clazz);
	}
	
	/**
	 * ��������ȡָ��beanId��bean
	 * @author xiongdun
	 * @created 2016��9��26�� ����3:12:17
	 * @since 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		return act.getBean(beanId);
	}
}
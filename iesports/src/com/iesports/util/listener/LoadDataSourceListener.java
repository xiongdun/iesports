/**
 * 
 */
package com.iesports.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.iesports.util.jdbc.JDBCProperties;

/**
 * ��������Ŀ����ʱ��������jdbc.properties��д��
 * ����Դ
 * @author xiongdun
 * @created 2016��9��19�� ����5:40:30
 * @since 
 */
public class LoadDataSourceListener implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private static final String filePath = "D:\\develop\\workspace\\iesports\\WebContent\\classes\\jdbc.properties";
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		JDBCProperties.readValue(filePath, "driver");
	}
}

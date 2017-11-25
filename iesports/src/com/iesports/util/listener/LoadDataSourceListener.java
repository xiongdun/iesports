/**
 * 
 */
package com.iesports.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.iesports.util.jdbc.JDBCProperties;

/**
 * 描述：项目启动时立即加载jdbc.properties类写入
 * 数据源
 * @author xiongdun
 * @created 2016年9月19日 下午5:40:30
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

/**
 * 
 */
package com.iesports.util.spring;

import java.sql.Connection;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.iesports.util.jdbc.connection.Configure;
import com.iesports.util.jdbc.connection.ConnManager;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年9月20日 下午2:14:29
 * @since 
 */
public class SpringLoadDatasourceListener implements ApplicationListener<ApplicationEvent>{

	private static Connection conn = null;
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		conn = ConnManager.getConnection();
	}
	
	public void close() {
		ConnManager.destoryConnection(conn);
	}
}

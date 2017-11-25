/**
 * 
 */
package com.iesports.util.jdbc.session;

import java.sql.Connection;

import com.iesports.util.jdbc.connection.ConnManager;
import com.iesports.util.jdbc.session.impl.SessionImpl;

/**
 * 描述：从session中获取会话对象和数据源
 * final 修饰类表示该类不能别继承
 * @author xiongdun
 * @created 2016年9月7日 上午10:15:37
 * @since 
 */
public final class SessionFactory {
	/**
     * 根据datasource.xml文件中配置数据源ID，得到对应的会话对象
     *
     * @param id 数据源ID
     * @return
     */
	public static Session getSession(String datasourceId) {
		Connection connection = ConnManager.getConnection(datasourceId);
		return new SessionImpl(connection);
	}
	
	/**
     * 获得缺省的数据源会话对象
     *
     * @return
     */
	public static Session getSession() {
		Connection connection = ConnManager.getConnection();
		return new SessionImpl(connection);
	}
}

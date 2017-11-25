/**
 * 
 */
package com.iesports.util.jdbc.session;

import java.sql.Connection;

import com.iesports.util.jdbc.connection.ConnManager;
import com.iesports.util.jdbc.session.impl.SessionImpl;

/**
 * ��������session�л�ȡ�Ự���������Դ
 * final �������ʾ���಻�ܱ�̳�
 * @author xiongdun
 * @created 2016��9��7�� ����10:15:37
 * @since 
 */
public final class SessionFactory {
	/**
     * ����datasource.xml�ļ�����������ԴID���õ���Ӧ�ĻỰ����
     *
     * @param id ����ԴID
     * @return
     */
	public static Session getSession(String datasourceId) {
		Connection connection = ConnManager.getConnection(datasourceId);
		return new SessionImpl(connection);
	}
	
	/**
     * ���ȱʡ������Դ�Ự����
     *
     * @return
     */
	public static Session getSession() {
		Connection connection = ConnManager.getConnection();
		return new SessionImpl(connection);
	}
}

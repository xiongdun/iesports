/**
 * 
 */
package com.iesports.util.config;

/**
 * ��������ȡϵͳ�����ļ���Ϣ
 * @author xiongdun
 * @created 2016��9��2�� ����2:22:01
 * @since 
 */
public class SysConfig {
	private static final String SYS_NAME   = Configuration.getString("system.sysname");
	private static final String PORT	   = Configuration.getString("system.port");
	public static final String SERVER_NAME = "IESPORTS";
	public static final String SERVER_PORT = "8080";
	static {
		System.out.println(SYS_NAME + PORT);
	}
}

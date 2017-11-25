/**
 * 
 */
package com.iesports.util.config;

/**
 * 描述：读取系统配置文件信息
 * @author xiongdun
 * @created 2016年9月2日 下午2:22:01
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

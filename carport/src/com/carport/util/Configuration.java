/**
 * 
 */
package com.carport.util;

import java.util.ResourceBundle;

/**
 * 定时任务配置文件加载{
 * @author zhangyijie
 *
 */
public class Configuration {
	
	private static final ResourceBundle TIMER_CONFIG = ResourceBundle.getBundle("config");
	
	private Configuration () {
		
	}
	
	private static Configuration timerConfig = null;
	
	public static Configuration getInstance() {
		if (timerConfig == null) {
			timerConfig = new Configuration();
		}
		return timerConfig;
	}
	
	public static String getString(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		return TIMER_CONFIG.getString(str);
	}
	
	public static int getInt(String str) {
		if (StringUtils.isBlank(str)) {
			return 0;
		}
		return Integer.parseInt(TIMER_CONFIG.getString(str));
	}
}

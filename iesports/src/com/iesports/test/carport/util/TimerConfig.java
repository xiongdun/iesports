/**
 * 
 */
package com.iesports.test.carport.util;

import java.util.ResourceBundle;

/**
 * ��ʱ���������ļ�����{
 * @author zhangyijie
 *
 */
public class TimerConfig {
	
	private static final ResourceBundle TIMER_CONFIG = ResourceBundle.getBundle("config");
	
	private TimerConfig () {
		
	}
	
	private static TimerConfig timerConfig = null;
	
	public static TimerConfig getInstance() {
		if (timerConfig == null) {
			timerConfig = new TimerConfig();
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

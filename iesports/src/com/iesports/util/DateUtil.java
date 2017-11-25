/**
 * 
 */
package com.iesports.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：日期处理工具类
 * @author xiongdun
 * @created 2016年9月12日 上午8:41:51
 * @since 
 */
public class DateUtil {
	
	private DateUtil() {
		
	}
	
	/**
	 * 描述：获取当前系统时间
	 * @author xiongdun
	 * @created 2016年9月12日 上午8:43:02
	 * @since 
	 * @return
	 */
	public static String getCurrentTime() {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String fmtime = sdf.format(new Date(currentTime));
		return fmtime;
	}
	
	/**
	 * 描述：获取指定格式的当前时间字符串
	 * @author xiongdun
	 * @created 2016年9月27日 上午10:25:16
	 * @since 
	 * @param pattern 指定的日期格式
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String fmtime = sdf.format(new Date(currentTime));
		return fmtime;
	}
}

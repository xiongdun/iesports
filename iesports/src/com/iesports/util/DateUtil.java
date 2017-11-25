/**
 * 
 */
package com.iesports.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���������ڴ�������
 * @author xiongdun
 * @created 2016��9��12�� ����8:41:51
 * @since 
 */
public class DateUtil {
	
	private DateUtil() {
		
	}
	
	/**
	 * ��������ȡ��ǰϵͳʱ��
	 * @author xiongdun
	 * @created 2016��9��12�� ����8:43:02
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
	 * ��������ȡָ����ʽ�ĵ�ǰʱ���ַ���
	 * @author xiongdun
	 * @created 2016��9��27�� ����10:25:16
	 * @since 
	 * @param pattern ָ�������ڸ�ʽ
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String fmtime = sdf.format(new Date(currentTime));
		return fmtime;
	}
}

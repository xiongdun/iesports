/**
 * 
 */
package com.iesports.test.carport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * ���������ڰ�����
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����9:09:44
 * @since
 */
public class DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);
	/**
	 * ��������ȡ��ǰʱ�� ����+ʱ��
	 * 
	 * @author zhangyijie
	 * @created 2016��12��2�� ����9:05:14
	 * @since
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * ��������ȡ��ǰʱ�� ����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��5�� ����11:20:12
	 * @since
	 * @return
	 */
	public static String getDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * �������ж�ʱ���Ƿ��ڸ���ʱ�����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����9:36:13
	 * @since
	 * @param nowDate
	 *            ��ǰʱ��
	 * @param strDateBegin
	 *            ������ʼʱ��
	 * @param strDateEnd
	 *            ��������ʱ��
	 * @return
	 */
	public static boolean isInDate(String strDateNow, String strDateBegin,
			String strDateEnd, boolean bool) {
		// ��ȡ��ǰʱ��ʱ����
		int strDateH = Integer.parseInt(strDateNow.substring(11, 13));
		int strDateM = Integer.parseInt(strDateNow.substring(14, 16));
		int strDateS = Integer.parseInt(strDateNow.substring(17, 19));
		// ��ȡ��ʼʱ��ʱ����
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// ��ȡ����ʱ��ʱ����
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));

		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// ��ǰʱ��Сʱ���ڿ�ʼʱ��ͽ���ʱ��Сʱ��֮��
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// ��ǰʱ��Сʱ�����ڿ�ʼʱ��Сʱ�����������ڿ�ʼ�ͽ���֮��
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// ��ǰʱ��Сʱ�����ڿ�ʼʱ��Сʱ�������������ڿ�ʼʱ��������������ڿ�ʼ�ͽ���֮��
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
				// ��ǰʱ��Сʱ������ڿ�ʼʱ��Сʱ�������ڽ���ʱ��Сʱ����������С���ڽ���ʱ�������
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH 
					&& strDateM <= strDateEndM) {
				return true;
				// ��ǰʱ��Сʱ������ڿ�ʼʱ��Сʱ�������ڽ���ʱ��Сʱ�������������ڽ���ʱ�������������С���ڽ���ʱ������
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * �������ж�ʱ���Ƿ��ڸ���ʱ�����
	 * 
	 * @author zhangyijie
	 * @created 2016��12��3�� ����9:36:13
	 * @since
	 * @param nowDate
	 *            ��ǰʱ��
	 * @param strDateBegin
	 *            ������ʼʱ��
	 * @param strDateEnd
	 *            ��������ʱ��
	 * @return
	 */
	public static boolean isInDate(String strDateNow, String strDateBegin,
			String strDateEnd) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sbDateBegin = new StringBuffer();
		sbDateBegin.append(getDate());
		sbDateBegin.append(" ");
		sbDateBegin.append(strDateBegin);
		StringBuffer sbDateEnd = new StringBuffer();
		sbDateEnd.append(getDate());
		sbDateEnd.append(" ");
		sbDateEnd.append(strDateEnd);
		try {
			Date dateNow = sdf.parse(strDateNow);
			long timeNow = dateNow.getTime();
			Date dateBegin = sdf.parse(sbDateBegin.toString());
			long timeBegin = dateBegin.getTime();
			Date dateEnd = sdf.parse(sbDateEnd.toString());
			long timeEnd = dateEnd.getTime();
			
			if (timeNow >= timeBegin && timeNow <= timeEnd) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			logger.error("����ת���쳣��ԭ��" + e);
		}
		return false;
	}
}

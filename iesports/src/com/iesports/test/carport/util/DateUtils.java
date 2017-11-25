/**
 * 
 */
package com.iesports.test.carport.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 描述：日期帮助类
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午9:09:44
 * @since
 */
public class DateUtils {

	private static Logger logger = Logger.getLogger(DateUtils.class);
	/**
	 * 描述：获取当前时间 日期+时间
	 * 
	 * @author zhangyijie
	 * @created 2016年12月2日 上午9:05:14
	 * @since
	 * @return
	 */
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * 描述：获取当前时间 日期
	 * 
	 * @author zhangyijie
	 * @created 2016年12月5日 上午11:20:12
	 * @since
	 * @return
	 */
	public static String getDatetime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 描述：判断时间是否在给定时间段内
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午9:36:13
	 * @since
	 * @param nowDate
	 *            当前时间
	 * @param strDateBegin
	 *            给定开始时间
	 * @param strDateEnd
	 *            给定结束时间
	 * @return
	 */
	public static boolean isInDate(String strDateNow, String strDateBegin,
			String strDateEnd, boolean bool) {
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDateNow.substring(11, 13));
		int strDateM = Integer.parseInt(strDateNow.substring(14, 16));
		int strDateS = Integer.parseInt(strDateNow.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));

		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH 
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
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
	 * 描述：判断时间是否在给定时间段内
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午9:36:13
	 * @since
	 * @param nowDate
	 *            当前时间
	 * @param strDateBegin
	 *            给定开始时间
	 * @param strDateEnd
	 *            给定结束时间
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
			logger.error("日期转换异常！原因：" + e);
		}
		return false;
	}
}

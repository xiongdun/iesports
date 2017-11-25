/**
 * 
 */
package com.iesports.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：字符串处理的工具类
 * @author xiongdun
 * @created 2016年9月1日 上午10:09:19
 * @since 
 */
public class StringUtil {
	
	/**
	 * 判断字符串是否为空
	 * 描述：为空返回true,不为空返回false
	 * @author xiongdun
	 * @created 2016年9月1日 下午3:48:56
	 * @since 
	 * @param name
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 描述：转为String类型的字符串
	 * @author xiongdun
	 * @created 2016年9月2日 上午9:51:33
	 * @since 
	 * @param array
	 * @return
	 */
	public static String toString(Object[] array) {
		int len = array.length;
		if (len == 0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(len * 12);
		for (int i = 0; i < len - 1; ++i) {
			buf.append(array[i]).append(", ");
		}
		return buf.append(array[(len - 1)]).toString();
	}
	
	/**
	 * 描述：判断字符串为空（判断空格字符）
	 * @author xiongdun
	 * @created 2016年9月2日 上午9:54:33
	 * @since 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		boolean b = true;
		if (str == null) {
			b = true;
		} else {
			int strLen = str.length();
			if (strLen == 0) {
				b = true;
			}

			for (int i = 0; i < strLen; ++i) {
				if (Character.isWhitespace(str.charAt(i)))
					continue;
				b = false;
				break;
			}

		}

		return b;
	}

	/**
	 * 描述：判断字符串不为空（是否有为空格的字符）
	 * @author xiongdun
	 * @created 2016年9月2日 上午9:54:00
	 * @since 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		int strLen = 0;
		if (str != null)
			strLen = str.length();
		if ((str == null) || (strLen == 0)) {
			return false;
		}
		for (int i = 0; i < strLen; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断字符串不为空
	 * 不为空返回true，为空返回false;
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午9:52:43
	 * @since 
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return ((string != null) && (string.length() > 0));
	}
	
	/**
	 * 判断字符串是否为邮箱格式
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午10:02:14
	 * @since 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}

	/**
	 * 判断字符串是否为11手机号码
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午10:01:59
	 * @since 
	 * @param str
	 * @return
	 */
	public static boolean isMoblie(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}


	/**
	 * 将字符串格式化为日期格式
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午10:00:57
	 * @since 
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		if ((isBlank(date)) || (date.length() < 8)) {
			return "";
		}
		StringBuffer dateBuf = new StringBuffer();
		dateBuf.append(date.substring(0, 4));
		dateBuf.append("-");
		dateBuf.append(date.substring(4, 6));
		dateBuf.append("-");
		dateBuf.append(date.substring(6, 8));
		return dateBuf.toString();
	}

	/**
	 * 判断字符串是否为一个数字
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午10:00:40
	 * @since 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^\\d+(\\.0)?$", 2);
		return pattern.matcher(str).matches();
	}

	
	/**
	 * 截取字符串指定长度
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午10:00:25
	 * @since 
	 * @param string
	 * @param byteCount
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String substring(String string, int byteCount)
			throws UnsupportedEncodingException {
		if (isBlank(string)) {
			return string;
		}
		byte[] bytes = string.getBytes("Unicode");
		int viewBytes = 0;
		int ucs2Bytes = 2;

		for (; (ucs2Bytes < bytes.length) && (viewBytes < byteCount); ++ucs2Bytes) {
			if (ucs2Bytes % 2 == 1) {
				++viewBytes;
			} else {
				if (bytes[ucs2Bytes] == 0)
					continue;
				++viewBytes;
			}

		}

		if (ucs2Bytes % 2 == 1) {
			ucs2Bytes += 1;
		}
		String result = new String(bytes, 0, ucs2Bytes, "Unicode");
		if (bytes.length > ucs2Bytes) {
			result = result + "...";
		}
		return result;
	}

	/**
	 * 截取字符串，返回字符串数组
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月2日 上午9:59:26
	 * @since 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String[] splite(String str, int length) {
		if (isEmpty(str)) {
			return null;
		}
		String[] strArr = new String[(str.length() + length - 1) / length];
		for (int i = 0; i < strArr.length; ++i) {
			if (str.length() > i * length + length - 1) {
				strArr[i] = str.substring(i * length, i * length + length - 1);
			} else {
				strArr[i] = str.substring(i * length);
			}
		}
		return strArr;
	}
	
	/**
	 * 截取字符串空格
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:46:25
	 * @since 
	 * @param value
	 * @return
	 */
	public static String trim(String value){
		return value == null ? "" : value.trim();
	}

	/**
	 * 将字符串用分隔符断裂成字符串列表
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:46:18
	 * @since 
	 * @param value
	 * @param separator
	 * @return
	 */
	public static List<String> split2List(String value, String separator) {
		List<String> ls = new ArrayList<String>();
		int i = 0,j = 0;
		while ((i = value.indexOf(separator, i)) != -1) {
			ls.add(value.substring(j, i));
			++i;
			j = i;
		}
		ls.add(value.substring(j));
		return ls;
	}
	
	/**
	 * 将字符串用分隔符断裂成字符串数组
	 * 在不需要使用正则表达式时，用来代替String.split方法
	 * 效率在String.split的3-4倍左右
	 * 备注：String.split方法效率也还可以，如果不是在循环中使用，也可以忽略此方法
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:47:02
	 * @since 
	 * @param value
	 * @param separator
	 * @return
	 */
	public static String[] split(String value, String separator) {
		List<String> ls = split2List(value, separator);
		return ls.toArray(new String[ls.size()]);
	}
	
	/**
	 * 将数组用分隔符连接成新字符串(split的逆方法)
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:47:22
	 * @since 
	 * @param strs
	 * @param sep
	 * @return
	 */
	public static String join(String[] strs, String sep) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			res.append(strs[i] + sep);
		}
		return res.substring(0, res.length() - sep.length());
	}
	
	/**
	 * 获得一个UUID 
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午7:47:36
	 * @since 
	 * @return
	 */
	public static String getUUID() {
		// 标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxx(8-4-4-4-12)
		String str = UUID.randomUUID().toString();
		// 去掉"-"符号，不用replaceAll的原因与split一样，
		// replaceAll支持正则表达式，频繁使用时效率不够高(当然偶尔用一下影响也不会特别严重)
		return join(split(str, "-"), "");
	}
}

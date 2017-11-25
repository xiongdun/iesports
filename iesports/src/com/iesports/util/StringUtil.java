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
 * �������ַ�������Ĺ�����
 * @author xiongdun
 * @created 2016��9��1�� ����10:09:19
 * @since 
 */
public class StringUtil {
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * ������Ϊ�շ���true,��Ϊ�շ���false
	 * @author xiongdun
	 * @created 2016��9��1�� ����3:48:56
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
	 * ������תΪString���͵��ַ���
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:51:33
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
	 * �������ж��ַ���Ϊ�գ��жϿո��ַ���
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:54:33
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
	 * �������ж��ַ�����Ϊ�գ��Ƿ���Ϊ�ո���ַ���
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:54:00
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
	 * �ж��ַ�����Ϊ��
	 * ��Ϊ�շ���true��Ϊ�շ���false;
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:52:43
	 * @since 
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return ((string != null) && (string.length() > 0));
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ�����ʽ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����10:02:14
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
	 * �ж��ַ����Ƿ�Ϊ11�ֻ�����
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����10:01:59
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
	 * ���ַ�����ʽ��Ϊ���ڸ�ʽ
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����10:00:57
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
	 * �ж��ַ����Ƿ�Ϊһ������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����10:00:40
	 * @since 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^\\d+(\\.0)?$", 2);
		return pattern.matcher(str).matches();
	}

	
	/**
	 * ��ȡ�ַ���ָ������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����10:00:25
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
	 * ��ȡ�ַ����������ַ�������
	 * ������
	 * @author xiongdun
	 * @created 2016��9��2�� ����9:59:26
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
	 * ��ȡ�ַ����ո�
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:46:25
	 * @since 
	 * @param value
	 * @return
	 */
	public static String trim(String value){
		return value == null ? "" : value.trim();
	}

	/**
	 * ���ַ����÷ָ������ѳ��ַ����б�
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:46:18
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
	 * ���ַ����÷ָ������ѳ��ַ�������
	 * �ڲ���Ҫʹ��������ʽʱ����������String.split����
	 * Ч����String.split��3-4������
	 * ��ע��String.split����Ч��Ҳ�����ԣ����������ѭ����ʹ�ã�Ҳ���Ժ��Դ˷���
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:47:02
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
	 * �������÷ָ������ӳ����ַ���(split���淽��)
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:47:22
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
	 * ���һ��UUID 
	 * ������
	 * @author xiongdun
	 * @created 2016��9��7�� ����7:47:36
	 * @since 
	 * @return
	 */
	public static String getUUID() {
		// ��׼��UUID��ʽΪ��xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxx(8-4-4-4-12)
		String str = UUID.randomUUID().toString();
		// ȥ��"-"���ţ�����replaceAll��ԭ����splitһ����
		// replaceAll֧��������ʽ��Ƶ��ʹ��ʱЧ�ʲ�����(��Ȼż����һ��Ӱ��Ҳ�����ر�����)
		return join(split(str, "-"), "");
	}
}

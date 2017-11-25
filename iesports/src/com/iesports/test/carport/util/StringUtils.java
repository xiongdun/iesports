/**
 * 
 */
package com.iesports.test.carport.util;

/**
 * 描述：字符处理工具类
 * 
 * @author zhangyijie
 * @created 2016年12月3日 下午1:42:19
 * @since
 */
public class StringUtils {

	
	/**
	 * 描述：获取项目根目录
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:44:44
	 * @since
	 * @param str
	 * @param patten
	 * @return
	 */
	public static String getContextRootPath() {
		String rootPath = Class.class.getResource("/").getFile().toString();
		return rootPath;
	}
	
	/**
	 * 描述：截取字符串
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午1:44:44
	 * @since
	 * @param str
	 * @param patten
	 * @return
	 */
	public static String[] splitStr(String str, String patten) {

		if (str == null || str == "") {
			return null;
		}

		return str.split(patten);

	}

	/**
	 * 描述：将字符数转为整型数组
	 * 
	 * @author zhangyijie
	 * @created 2016年12月4日 下午12:40:14
	 * @since
	 * @param str
	 * @param patten
	 * @return
	 */
	public static Integer[] splitStrToInt(String str, String patten) {

		String[] strArr = splitStr(str, patten);
		if (strArr.length <= 0) {
			return null;
		}
		Integer[] intArr = new Integer[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}

	/**
	 * 描述：判断为null
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午11:44:51
	 * @since
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return ((string != null) && (string.length() > 0));
	}

	/**
	 * 描述：判断不为null
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午11:45:10
	 * @since
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	/**
	 * 描述：判断为空字符
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午11:45:22
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
	 * 描述：判断不为空字符
	 * 
	 * @author zhangyijie
	 * @created 2016年12月3日 下午11:45:38
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
	
	public static String replace(String source, String old, String replace) {
	    StringBuffer output = new StringBuffer();

	    int sourceLen = source.length();
	    int oldLen = old.length();

	    int posStart = 0;
	    int pos;
	    while ((pos = source.indexOf(old, posStart)) >= 0) {
	    	output.append(source.substring(posStart, pos));
	    	output.append(replace);
	    	posStart = pos + oldLen;
	    }

	    if (posStart < sourceLen) {
	    	output.append(source.substring(posStart));
	    }

	    return output.toString();
	  }

	  public static String replace(String template, String placeholder, String replacement, boolean wholeWords) {
		  int loc = template.indexOf(placeholder);
		  if (loc < 0) {
			  return template;
		  }

		  boolean actuallyReplace = (wholeWords) || (loc + placeholder.length() == template.length()) || (!Character.isJavaIdentifierPart(template.charAt(loc + placeholder.length())));
		  String actualReplacement = actuallyReplace ? replacement : placeholder;
		  return new StringBuffer(template.substring(0, loc)).append(actualReplacement).append(replace(template.substring(loc + placeholder.length()), placeholder, replacement, wholeWords)).toString();
	  
	  }

	  public static String replaceOnce(String template, String placeholder, String replacement) {
		  int loc = template.indexOf(placeholder);
		  if (loc < 0) {
			  return template;
		  }

		  return new StringBuffer(template.substring(0, loc)).append(replacement).append(template.substring(loc + placeholder.length())).toString();
	  }
}

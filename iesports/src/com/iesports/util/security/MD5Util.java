/**
 * 
 */
package com.iesports.util.security;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * 描述：字符串转base64值工具类
 * @author xiongdun
 * @created 2016年9月7日 下午3:31:06
 * @since 
 */
public class MD5Util {
	
	private static Logger logger = Logger.getLogger(MD5Util.class);
	
	private static MessageDigest md5 = null;
	
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error("MD5值转换失败！", e);
		}
	}
	
	
	/**
	 * 描述：将输入字符串转换为MD5值
	 * @author xiongdun
	 * @created 2016年9月12日 下午2:55:26
	 * @since 
	 * @param str 需要转换的字符串
	 * @return md5值
	 */
	public static String convertStringToMD5(String str) {
		byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff) >> 4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
	}
	
	/**
	 * 描述：将普通字符串转换转为md5值后与md5值比较
	 * @author xiongdun
	 * @created 2016年9月19日 上午9:08:05
	 * @since 
	 * @param norStr 普通字符串
	 * @param md5Str md5字符串
	 * @return 比较结果
	 */
	public static boolean compareStringWithMD5(String norStr, String md5Str) {
		
		if (norStr == null || "".equals(norStr)) {
			return false;
		}
		if (md5Str == null || "".equals(md5Str)) {
			return false;
		}
		
		String convertMd5 = convertStringToMD5(norStr);
		if (convertMd5 == md5Str || convertMd5.equals(md5Str)) {
			return true;
		}
		
		return false;
	}
}

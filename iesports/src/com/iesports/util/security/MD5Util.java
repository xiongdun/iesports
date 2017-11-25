/**
 * 
 */
package com.iesports.util.security;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * �������ַ���תbase64ֵ������
 * @author xiongdun
 * @created 2016��9��7�� ����3:31:06
 * @since 
 */
public class MD5Util {
	
	private static Logger logger = Logger.getLogger(MD5Util.class);
	
	private static MessageDigest md5 = null;
	
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error("MD5ֵת��ʧ�ܣ�", e);
		}
	}
	
	
	/**
	 * �������������ַ���ת��ΪMD5ֵ
	 * @author xiongdun
	 * @created 2016��9��12�� ����2:55:26
	 * @since 
	 * @param str ��Ҫת�����ַ���
	 * @return md5ֵ
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
	 * ����������ͨ�ַ���ת��תΪmd5ֵ����md5ֵ�Ƚ�
	 * @author xiongdun
	 * @created 2016��9��19�� ����9:08:05
	 * @since 
	 * @param norStr ��ͨ�ַ���
	 * @param md5Str md5�ַ���
	 * @return �ȽϽ��
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

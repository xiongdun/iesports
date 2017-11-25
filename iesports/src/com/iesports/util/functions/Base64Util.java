/**
 * 
 */
package com.iesports.util.functions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * ������ BASE64ֵ������
 * @author xiongdun
 * @created 2016��9��7�� ����3:31:06
 * @since 
 */
public class Base64Util {

	private static Logger logger = Logger.getLogger(Base64Util.class);
	
	public Base64Util() {
		
	}
	
	/**
	 * ��������URLת����base64
	 * @author xiongdun
	 * @created 2016��7��10�� ����1:18:18
	 * @since
	 * @param url
	 * @return
	 */
	public static String urlToBase64(String url) {
		
		if (url == null || "".equals(url)) {
			return null;
		}
		String base64 = null;
		InputStream inStream = null;
		try {
			URL imageUrl = new URL(url);
			HttpURLConnection httpUrl = (HttpURLConnection) imageUrl.openConnection();
			httpUrl.connect();
			inStream = httpUrl.getInputStream();
			byte[] data = readInputStream(inStream);
			base64 = Base64.encodeBase64String(data);
		} catch (Exception e) {
			logger.error("urlתbase64ʧ�ܣ�", e);
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return base64;
	}

	/**
	 * ��������ȡ������ļ���������
	 * @author xiongdun
	 * @created 2016��7��10�� ����1:17:49
	 * @since
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// ����һ��Buffer�ַ���
		byte[] buffer = new byte[1024];
		// ÿ�ζ�ȡ���ַ������ȣ����Ϊ-1������ȫ����ȡ���
		int len = 0;
		// ʹ��һ����������buffer������ݶ�ȡ����
		while ((len = inStream.read(buffer)) != -1) {
			// ���������buffer��д�����ݣ��м����������ĸ�λ�ÿ�ʼ����len�����ȡ�ĳ���
			outStream.write(buffer, 0, len);
		}
		// �ر�������
		inStream.close();
		// ��outStream�������д���ڴ�
		return outStream.toByteArray();
	}
}

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
 * 描述： BASE64值工具类
 * @author xiongdun
 * @created 2016年9月7日 下午3:31:06
 * @since 
 */
public class Base64Util {

	private static Logger logger = Logger.getLogger(Base64Util.class);
	
	public Base64Util() {
		
	}
	
	/**
	 * 描述：将URL转换成base64
	 * @author xiongdun
	 * @created 2016年7月10日 下午1:18:18
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
			logger.error("url转base64失败！", e);
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
	 * 描述：读取输入的文件二进制流
	 * @author xiongdun
	 * @created 2016年7月10日 下午1:17:49
	 * @since
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}

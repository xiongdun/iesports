package com.iesports.util.face;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iesports.util.face.EncryptionHelper.MD5Helper.Md5EncodingException;
import com.iesports.util.face.EncryptionHelper.RSAHelper.PublicKeyException;

/**
 * @描述: 人脸识别处理类
 * @版权: Copyright (c) 2015
 * @公司: 思迪科技
 * @作者: 宁铁明
 * @版本: 1.0
 * @创建日期: 2016年02月15日
 * @创建时间: 下午3:06:46
 */
public class FaceRecogUtil {

	public static int connectTimeOut = 5000;
	public static int readTimeOut = 7000;
	private static final char[] toDigit = ("0123456789ABCDEF").toCharArray();

	/**
	 * @描述：人脸识别,传入两张待比对识别图片,返回识别结果
	 * @作者：胡波修改
	 * @时间：2016年03月9日下午3:06:40
	 * @param requestInterfaceInfoMap
	 *            map对象，userId 用户ID，policeImgStr 公安图片，frontViewImgStr
	 *            用户正面免冠照，生活照，policeImgType 公安照类型
	 * @return
	 */
	public static int faceRecognize(Map<String, String> requestInterfaceInfoMap, String userId,
			String policeImgStr, String frontViewImgStr, int policeImgType)
			throws Exception {
		ObjectMapper uploadImgMapper = new ObjectMapper();
		Map<String, Object> uploadImgRequestData = new HashMap<String, Object>();
		uploadImgRequestData.put("database_image_content", policeImgStr);
		uploadImgRequestData.put("database_image_type", policeImgType);
		uploadImgRequestData.put("query_image_content", frontViewImgStr);
		uploadImgRequestData.put("query_image_type", 3);
		String uploadImgRequestDataStr = "";
		uploadImgRequestDataStr = uploadImgMapper.writeValueAsString(uploadImgRequestData);
		String recognizeImgResponseStr = sendPost(
				EncryptionHelper.RSAHelper.loadPublicKey(requestInterfaceInfoMap.get("pubKeyStr")),
				requestInterfaceInfoMap.get("verificationUrl"),
				requestInterfaceInfoMap.get("accessId"),
				requestInterfaceInfoMap.get("accessKey"),
				uploadImgRequestDataStr, "232323");

		JSONObject recognizeImgResponse = JSONObject.parseObject(recognizeImgResponseStr);
		// 读取返回
		int recognizeImgRtErrorCode = recognizeImgResponse.getIntValue("rtn");
		if (recognizeImgRtErrorCode == 0) {
			// 返回 数字1和0判断 是不是同一个人！
			// 返回相似度
			int verifyResult = recognizeImgResponse.getIntValue("pair_verify_similarity");
			return verifyResult;
		} else {
			String recognizeImgErrorMsg = recognizeImgResponse.getString("message");
			throw new Exception("图片识别HTTP请求服务端报错:" + recognizeImgErrorMsg);
		}
	}

	public static String getPhotoBase64String(String imgFilePath)
			throws Exception {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;

		// 读取图片字节数组
		try {
			File file = new File(imgFilePath);
			if (!file.isFile()) {
				String desc = "将本地图片转变为BASE64编码后的图片数据时，传入的文件路径不是一个文件，请检查！";

				throw new Exception(desc);
			}
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		// 对字节数组Base64编码
		return Base64.encodeBase64String(data);
	}

	public static String sendPost(PublicKey publicKey, String url,
			String accessId, String accessKey, String bodyString,
			String userDefinedContent) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		URL realUrl = new URL(url);
		// 打开和URL之间的连接
		URLConnection conn = realUrl.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) conn;
		// 设置通用的请求属性,设置accessId和signature
		httpConn.setRequestProperty("accept", "*/*");
		httpConn.setRequestProperty("connection", "Keep-Alive");
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("x-access-id", accessId);
		httpConn.setRequestProperty("x-signature",
				"e4305df7e61b38adbd59eabef7f8b706");
		httpConn.setConnectTimeout(connectTimeOut);
		httpConn.setReadTimeout(readTimeOut);
		// 发送POST请求必须设置如下两行
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
		httpConn.setRequestMethod("POST");

		httpConn.connect();
		// 获取URLConnection对象对应的输出流
		out = new PrintWriter(httpConn.getOutputStream());
		// 发送请求参数
		out.print(bodyString);
		// flush输出流的缓冲
		out.flush();
		// 定义BufferedReader输入流来读取URL的响应,当返回码不为200的时候取出错误信息
		int code = httpConn.getResponseCode();
		if (httpConn.getResponseCode() != 200) {
			in = new BufferedReader(new InputStreamReader(
					httpConn.getErrorStream(), "UTF-8"));
		} else {
			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "UTF-8"));
		}

		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		if (out != null) {
			out.close();
		}
		if (in != null) {
			in.close();
		}
		return result;
	}

	/**
	 * 生成Signature
	 * 
	 * @param accessKey
	 *            , access Key
	 * @param bodyString
	 *            , HTTP 请求内容
	 * @param userDefinedContent
	 *            , 客户自定义域，要求小于41字节
	 * @return 生成的加密后的Signature
	 * @throws Md5EncodingException
	 * @throws PublicKeyException
	 */
	public static String generateSignature(PublicKey publicKey,
			String accessKey, String bodyString, String userDefinedContent)
			throws Exception {

		String result = null;

		// 生成unix时间戳
		int unixTime = (int) (System.currentTimeMillis() / 1000L);
		byte[] unixTimeArray = ByteBuffer.allocate(4).putInt(unixTime).array();

		// 生成随机数
		SecureRandom sr = new SecureRandom();
		byte[] rndBytes = new byte[8];
		sr.nextBytes(rndBytes);

		// 拼接Signature
		byte[] signatureStr = mergeArray(
				accessKey.getBytes(Charset.forName("UTF-8")),
				EncryptionHelper.MD5Helper.md5(bodyString).getBytes(
						Charset.forName("UTF-8")));
		signatureStr = mergeArray(signatureStr, unixTimeArray);
		signatureStr = mergeArray(signatureStr, rndBytes);
		signatureStr = mergeArray(signatureStr, userDefinedContent.getBytes(Charset.forName("UTF-8")));
		// RSA加密
		result = hexEncode(EncryptionHelper.RSAHelper.encrypt(signatureStr, publicKey));
		return result;
	}

	/**
	 * 合并两个byte数组
	 * 
	 * @param a
	 * @param b
	 * @return a + b
	 */
	public static byte[] mergeArray(byte[] a, byte[] b) {
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	/**
	 * 16进制编码
	 * 
	 * @param bytes
	 *            , 输入字符数组
	 * @return 经过16进制编码后的数组
	 */
	public static String hexEncode(byte[] bytes) {
		char[] chars = new char[2 * bytes.length];
		int j = 0;

		for (int i = 0; i < bytes.length; ++i) {
			byte bits = bytes[i];

			chars[j++] = toDigit[((bits >>> 4) & 0xF)];
			chars[j++] = toDigit[(bits & 0xF)];
		}

		return new String(chars);
	}
}

class EncryptionHelper {

	/**
	 * MD5工具类
	 */
	public static class MD5Helper {

		public static class Md5EncodingException extends Exception {
			public Md5EncodingException() {
			}

			public Md5EncodingException(String msg) {
				super(msg);
			}
		}

		/**
		 * 产生MD5, 格式为32字节的16进制编码
		 * 
		 * @param filename
		 *            , 公钥文件路径
		 * @return 公钥类
		 * @throws Md5EncodingException
		 */
		public static String md5(String message) throws Md5EncodingException {
			try {
				// 产生 MD5 Hash(16字节)
				MessageDigest digest = java.security.MessageDigest
						.getInstance("MD5");
				digest.update(message.getBytes(Charset.forName("UTF-8")));
				byte messageDigest[] = digest.digest();

				// 16进制编码(32字节)
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < messageDigest.length; i++)
					hexString.append(Integer.toHexString(
							(messageDigest[i] & 0xFF) | 0x100).substring(1, 3));

				return hexString.toString();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Md5EncodingException("无法生成MD5字符串");
			}
		}
	}

	/**
	 * RSA工具类
	 */
	public static class RSAHelper {

		public static class PublicKeyException extends Exception {
			public PublicKeyException() {
			}

			public PublicKeyException(String msg) {
				super(msg);
			}
		}

		/**
		 * 从pem文件读取公钥
		 * 
		 * @param filename
		 *            , 公钥文件路径
		 * @return 公钥类
		 * @throws PublicKeyException
		 */
		public static PublicKey loadPublicKey(String pubKeyStr)
				throws PublicKeyException {
			try {
				Security.addProvider(new BouncyCastleProvider());
				KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
				byte[] content = new Base64().decode(pubKeyStr);
				X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
				return factory.generatePublic(pubKeySpec);
			} catch (Exception e) {
				throw new PublicKeyException("载入公钥错误");
			}
		}

		/**
		 * 使用公钥加密
		 * 
		 * @param message
		 *            , 明文内容
		 * @param publicKey
		 *            , 公钥
		 * @return 密文内容
		 * @throws PublicKeyException
		 */
		public static byte[] encrypt(byte[] message, PublicKey publicKey)
				throws PublicKeyException {
			try {
				Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				byte[] enBytes = cipher.doFinal(message);

				return enBytes;
			} catch (Exception e) {
				throw new PublicKeyException("用公钥加密时出错");
			}
		}
	}
}
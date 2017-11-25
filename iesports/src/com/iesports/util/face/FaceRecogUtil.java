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
 * @����: ����ʶ������
 * @��Ȩ: Copyright (c) 2015
 * @��˾: ˼�ϿƼ�
 * @����: ������
 * @�汾: 1.0
 * @��������: 2016��02��15��
 * @����ʱ��: ����3:06:46
 */
public class FaceRecogUtil {

	public static int connectTimeOut = 5000;
	public static int readTimeOut = 7000;
	private static final char[] toDigit = ("0123456789ABCDEF").toCharArray();

	/**
	 * @����������ʶ��,�������Ŵ��ȶ�ʶ��ͼƬ,����ʶ����
	 * @���ߣ������޸�
	 * @ʱ�䣺2016��03��9������3:06:40
	 * @param requestInterfaceInfoMap
	 *            map����userId �û�ID��policeImgStr ����ͼƬ��frontViewImgStr
	 *            �û���������գ������գ�policeImgType ����������
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
		// ��ȡ����
		int recognizeImgRtErrorCode = recognizeImgResponse.getIntValue("rtn");
		if (recognizeImgRtErrorCode == 0) {
			// ���� ����1��0�ж� �ǲ���ͬһ���ˣ�
			// �������ƶ�
			int verifyResult = recognizeImgResponse.getIntValue("pair_verify_similarity");
			return verifyResult;
		} else {
			String recognizeImgErrorMsg = recognizeImgResponse.getString("message");
			throw new Exception("ͼƬʶ��HTTP�������˱���:" + recognizeImgErrorMsg);
		}
	}

	public static String getPhotoBase64String(String imgFilePath)
			throws Exception {
		// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
		byte[] data = null;

		// ��ȡͼƬ�ֽ�����
		try {
			File file = new File(imgFilePath);
			if (!file.isFile()) {
				String desc = "������ͼƬת��ΪBASE64������ͼƬ����ʱ��������ļ�·������һ���ļ������飡";

				throw new Exception(desc);
			}
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		// ���ֽ�����Base64����
		return Base64.encodeBase64String(data);
	}

	public static String sendPost(PublicKey publicKey, String url,
			String accessId, String accessKey, String bodyString,
			String userDefinedContent) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		URL realUrl = new URL(url);
		// �򿪺�URL֮�������
		URLConnection conn = realUrl.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) conn;
		// ����ͨ�õ���������,����accessId��signature
		httpConn.setRequestProperty("accept", "*/*");
		httpConn.setRequestProperty("connection", "Keep-Alive");
		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("x-access-id", accessId);
		httpConn.setRequestProperty("x-signature",
				"e4305df7e61b38adbd59eabef7f8b706");
		httpConn.setConnectTimeout(connectTimeOut);
		httpConn.setReadTimeout(readTimeOut);
		// ����POST�������������������
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
		httpConn.setRequestMethod("POST");

		httpConn.connect();
		// ��ȡURLConnection�����Ӧ�������
		out = new PrintWriter(httpConn.getOutputStream());
		// �����������
		out.print(bodyString);
		// flush������Ļ���
		out.flush();
		// ����BufferedReader����������ȡURL����Ӧ,�������벻Ϊ200��ʱ��ȡ��������Ϣ
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
	 * ����Signature
	 * 
	 * @param accessKey
	 *            , access Key
	 * @param bodyString
	 *            , HTTP ��������
	 * @param userDefinedContent
	 *            , �ͻ��Զ�����Ҫ��С��41�ֽ�
	 * @return ���ɵļ��ܺ��Signature
	 * @throws Md5EncodingException
	 * @throws PublicKeyException
	 */
	public static String generateSignature(PublicKey publicKey,
			String accessKey, String bodyString, String userDefinedContent)
			throws Exception {

		String result = null;

		// ����unixʱ���
		int unixTime = (int) (System.currentTimeMillis() / 1000L);
		byte[] unixTimeArray = ByteBuffer.allocate(4).putInt(unixTime).array();

		// ���������
		SecureRandom sr = new SecureRandom();
		byte[] rndBytes = new byte[8];
		sr.nextBytes(rndBytes);

		// ƴ��Signature
		byte[] signatureStr = mergeArray(
				accessKey.getBytes(Charset.forName("UTF-8")),
				EncryptionHelper.MD5Helper.md5(bodyString).getBytes(
						Charset.forName("UTF-8")));
		signatureStr = mergeArray(signatureStr, unixTimeArray);
		signatureStr = mergeArray(signatureStr, rndBytes);
		signatureStr = mergeArray(signatureStr, userDefinedContent.getBytes(Charset.forName("UTF-8")));
		// RSA����
		result = hexEncode(EncryptionHelper.RSAHelper.encrypt(signatureStr, publicKey));
		return result;
	}

	/**
	 * �ϲ�����byte����
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
	 * 16���Ʊ���
	 * 
	 * @param bytes
	 *            , �����ַ�����
	 * @return ����16���Ʊ���������
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
	 * MD5������
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
		 * ����MD5, ��ʽΪ32�ֽڵ�16���Ʊ���
		 * 
		 * @param filename
		 *            , ��Կ�ļ�·��
		 * @return ��Կ��
		 * @throws Md5EncodingException
		 */
		public static String md5(String message) throws Md5EncodingException {
			try {
				// ���� MD5 Hash(16�ֽ�)
				MessageDigest digest = java.security.MessageDigest
						.getInstance("MD5");
				digest.update(message.getBytes(Charset.forName("UTF-8")));
				byte messageDigest[] = digest.digest();

				// 16���Ʊ���(32�ֽ�)
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < messageDigest.length; i++)
					hexString.append(Integer.toHexString(
							(messageDigest[i] & 0xFF) | 0x100).substring(1, 3));

				return hexString.toString();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Md5EncodingException("�޷�����MD5�ַ���");
			}
		}
	}

	/**
	 * RSA������
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
		 * ��pem�ļ���ȡ��Կ
		 * 
		 * @param filename
		 *            , ��Կ�ļ�·��
		 * @return ��Կ��
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
				throw new PublicKeyException("���빫Կ����");
			}
		}

		/**
		 * ʹ�ù�Կ����
		 * 
		 * @param message
		 *            , ��������
		 * @param publicKey
		 *            , ��Կ
		 * @return ��������
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
				throw new PublicKeyException("�ù�Կ����ʱ����");
			}
		}
	}
}
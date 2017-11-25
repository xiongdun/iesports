/**
 * 
 */
package com.iesports.util.face;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

/**
 * ����������ʶ����
 * @author xiongdun
 * @created 2016��9��23�� ����11:06:22
 * @since 
 */
public class FaceFunc {
	
	private static String uploadImgUrlS = "";
	private static String policeImgTypeS = "3";
	private static String imgRecognitionUrlS = "";
	private static String accessKeyS = "";
	private static String pubKeyStrS = "";
	private static String configPath = "/com/iesports/util/face/config.properties";
	static {
		Properties prop = new Properties();
		InputStream in = Object.class.getResourceAsStream(configPath);
		try {
			prop.load(in);
			uploadImgUrlS = prop.getProperty("uploadImgUrl").trim();
			imgRecognitionUrlS = prop.getProperty("imgRecognitionUrl").trim();
			accessKeyS = prop.getProperty("accessKey").trim();
			pubKeyStrS = prop.getProperty("pubKeyStr").trim();
			policeImgTypeS = prop.getProperty("policeImgType").trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Map getResult(String imgFace, String ImgNet) {
		long startTime = System.currentTimeMillis();
		Map result = new HashMap();
		String imgFaceData = urlToBase64(imgFace); 
		String imgNetData = urlToBase64(ImgNet);
		String accessId = String.valueOf(System.currentTimeMillis() + "" + System.currentTimeMillis());
		String is_success = "0";
		// �ж�ͼƬ�Ƿ���Ч
		if (imgFaceData == null || "".equals(imgFaceData)) {
			result.put("is_success", is_success);
			return result;
		}
		if (imgNetData == null || "".equals(imgNetData)) {
			result.put("is_success", is_success);
			return result;
		}
		//�̶�ֵ
        String imgRecognitionUrl = imgRecognitionUrlS;
        String accessKey = accessKeyS;
        String pubKeyStr = pubKeyStrS;
        //�˲���Ƭ����
        int policeImgType = Integer.parseInt(policeImgTypeS);
        //Map��װ����
        Map<String,String> requestInterfaceInfoMap = new HashMap<String,String>();
        requestInterfaceInfoMap.put("accessId", accessId);
        requestInterfaceInfoMap.put("accessKey", accessKey);
        requestInterfaceInfoMap.put("uploadImgUrl", uploadImgUrlS);
        requestInterfaceInfoMap.put("verificationUrl", imgRecognitionUrl);
        requestInterfaceInfoMap.put("pubKeyStr", pubKeyStr);
       
        
        //���ƶ�
        int recognitionFaceResult = 0;	//�����ͼ�ԱȽ��
        String checkResultStatus ="0"; //����ʶ��ĳɹ���־
        String policeImgStr = imgNetData;
        String frontViewFaceImgStr = imgFaceData;
        try {
            recognitionFaceResult = FaceRecogUtil.faceRecognize(requestInterfaceInfoMap,accessId+"1",policeImgStr,frontViewFaceImgStr,policeImgType);
            checkResultStatus ="1";
            is_success = "1";
        } catch (Exception e) {
        	checkResultStatus ="0";
        	recognitionFaceResult = -1;
        	is_success = "0";
    		try {
    			recognitionFaceResult = FaceRecogUtil.faceRecognize(requestInterfaceInfoMap,accessId+"1",policeImgStr,frontViewFaceImgStr,policeImgType);
    			checkResultStatus ="1";
    			is_success = "1";
			} catch (Exception e2) {
	        	checkResultStatus ="0";
	        	recognitionFaceResult = -1;
	        	is_success = "0";
			}
        }
        long castTime = System.currentTimeMillis() - startTime;// ʶ���ʱ
        result.put("is_success", is_success);
        result.put("face_result",recognitionFaceResult);
        result.put("auth_result", "-1");		//���֤�նԱȽ�� Ϊ-1
        result.put("create_time", formatDate(new Date()));
        result.put("checkResultStatus", checkResultStatus);
        result.put("cast_time", castTime);
		return result;
	}
	
	private static String formatDate(Date date) {
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdfDay.format(new Date());
	}
	
	/**
	 * ��������ȡ�����
	 * @author xiongdun
	 * @created 2016��9��23�� ����2:22:55
	 * @since 
	 * @return
	 */
	private static long getRandom(){
		Random random = new Random();
		long rd = random.nextLong();
		return rd;
	}
	
	public static void main(String[] args) {
		long rd = getRandom();
		System.out.println(rd);
	}
	
	/**
	 * ��URLת����base64 ������
	 * 
	 * @author xiongdun
	 * @created 2016��7��10�� ����1:18:18
	 * @since
	 * @param url
	 * @return
	 */
	private String urlToBase64(String url) {
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

		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
				}
			}
		}
		return base64;
	}

	/**
	 * ��ȡ������ļ��������� ������
	 * 
	 * @author xiongdun
	 * @created 2016��7��10�� ����1:17:49
	 * @since
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	private byte[] readInputStream(InputStream inStream) throws Exception {
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

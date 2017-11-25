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
 * 描述：人脸识别功能
 * @author xiongdun
 * @created 2016年9月23日 上午11:06:22
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
		// 判断图片是否有效
		if (imgFaceData == null || "".equals(imgFaceData)) {
			result.put("is_success", is_success);
			return result;
		}
		if (imgNetData == null || "".equals(imgNetData)) {
			result.put("is_success", is_success);
			return result;
		}
		//固定值
        String imgRecognitionUrl = imgRecognitionUrlS;
        String accessKey = accessKeyS;
        String pubKeyStr = pubKeyStrS;
        //核查照片类型
        int policeImgType = Integer.parseInt(policeImgTypeS);
        //Map封装参数
        Map<String,String> requestInterfaceInfoMap = new HashMap<String,String>();
        requestInterfaceInfoMap.put("accessId", accessId);
        requestInterfaceInfoMap.put("accessKey", accessKey);
        requestInterfaceInfoMap.put("uploadImgUrl", uploadImgUrlS);
        requestInterfaceInfoMap.put("verificationUrl", imgRecognitionUrl);
        requestInterfaceInfoMap.put("pubKeyStr", pubKeyStr);
       
        
        //相似度
        int recognitionFaceResult = 0;	//生活截图对比结果
        String checkResultStatus ="0"; //人脸识别的成功标志
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
        long castTime = System.currentTimeMillis() - startTime;// 识别耗时
        result.put("is_success", is_success);
        result.put("face_result",recognitionFaceResult);
        result.put("auth_result", "-1");		//身份证照对比结果 为-1
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
	 * 描述：获取随机数
	 * @author xiongdun
	 * @created 2016年9月23日 下午2:22:55
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
	 * 将URL转换成base64 描述：
	 * 
	 * @author xiongdun
	 * @created 2016年7月10日 下午1:18:18
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
	 * 读取输入的文件二进制流 描述：
	 * 
	 * @author xiongdun
	 * @created 2016年7月10日 下午1:17:49
	 * @since
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	private byte[] readInputStream(InputStream inStream) throws Exception {
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

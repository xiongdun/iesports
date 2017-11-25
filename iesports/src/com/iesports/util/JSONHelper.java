/**
 * 
 */
package com.iesports.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * ������JSON����������
 * @author xiongdun
 * @created 2016��9��27�� ����9:53:41
 * @since 
 */
public class JSONHelper {
	
	/**
	 * ���������л�java_bean����
	 * @author xiongdun
	 * @created 2016��9��27�� ����9:56:13
	 * @since 
	 * @param content
	 * @param clazz
	 * @return
	 */
	public static <T> T parse(String content, Class<T> clazz) {
		return (T) JSON.parse(content);
	}
	
	/**
	 * ��������Object ����ת��Ϊjson�ַ���
	 * @author xiongdun
	 * @created 2016��9��27�� ����9:57:37
	 * @since 
	 * @param value
	 * @return
	 */
	public static String toJSONString(Object value) {
		return JSON.toJSONString(value);
	}
	
	/**
	 * ��������listתΪJSONArray
	 * @author xiongdun
	 * @created 2016��9��29�� ����4:16:11
	 * @since 
	 * @param list
	 * @return
	 */
	public static JSONArray toJSONArray(List list) {
		JSONArray jsonArr = new JSONArray();
		jsonArr.add(list);
		JSONObject.toJSON(jsonArr);
		return jsonArr;
	}
}

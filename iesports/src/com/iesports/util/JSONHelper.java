/**
 * 
 */
package com.iesports.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述：JSON帮助工具类
 * @author xiongdun
 * @created 2016年9月27日 上午9:53:41
 * @since 
 */
public class JSONHelper {
	
	/**
	 * 描述：序列化java_bean对象
	 * @author xiongdun
	 * @created 2016年9月27日 上午9:56:13
	 * @since 
	 * @param content
	 * @param clazz
	 * @return
	 */
	public static <T> T parse(String content, Class<T> clazz) {
		return (T) JSON.parse(content);
	}
	
	/**
	 * 描述：将Object 对象转换为json字符串
	 * @author xiongdun
	 * @created 2016年9月27日 上午9:57:37
	 * @since 
	 * @param value
	 * @return
	 */
	public static String toJSONString(Object value) {
		return JSON.toJSONString(value);
	}
	
	/**
	 * 描述：将list转为JSONArray
	 * @author xiongdun
	 * @created 2016年9月29日 下午4:16:11
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

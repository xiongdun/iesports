/**
 * 
 */
package com.iesports.util.ajax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 描述：ajax异步请求处理帮助工具类
 * @author xiongdun
 * @created 2016年10月14日 上午11:13:57
 * @since 
 */
public class AjaxUtil {
	
	public static Object toAjaxRequest(Object object) throws FileNotFoundException {
		File file = new File("");
		PrintWriter out = new PrintWriter(file);
		out.write("");
		out.flush();
		out.close();
		return "";
	}
	
	public static List<Object> toAjaxRequest() {
		return null;
	}
	
	
}

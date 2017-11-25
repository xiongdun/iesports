/**
 * 
 */
package com.iesports.util.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.iesports.util.StringUtil;
import com.iesports.util.XMLHelper;

/**
 * @ 解析sql配置的sql文件
 * @author xiongdun@thinkive.com
 * @ 说明
 * 1、该类目前测试可以正常使用的
 * 如果不能正确的获取值，请检查可能的情况1)类名传的是否正确2)请求的键是否在xml文件中存在拼接键格式（小写类名首字母+标签ID）
 * 
 * 2、所需的xml 文档有一定格式
 * 详情可以参照com.thinkive.ncbank.plat.web.module.contract.service.impl
 * 包下的contractServiceImpl.xml 进行编写xml文档
 * 
 * 3、在设置请求键的时候尽量将首字母设为小写，当然不设也没有关系，类中的方法会自动将首字母大写
 * 
 */
public class SqlXmlResolver {
	
	private static Logger logger = Logger.getLogger(SqlXmlResolver.class);
	
	private static Map sqlMap = new HashMap();
	
	private static String classSimpleName = "";
	
	public SqlXmlResolver() {
		
	}
	
	static {
		// 加载文档路径
		// 解析xml文档
		// 获取文档内容
		// init(clazzSimplePath);
	}

	/**
	 * 将首字母改为小写
	 * @param clazz
	 * @return
	 */
	private static String lowFirstChar(Class clazz) {
		String className = clazz.getSimpleName();
		if (!Character.isLowerCase(className.charAt(0))) {
			className = (new StringBuilder()).append(Character.toLowerCase(className.charAt(0))).append(className.substring(1)).toString();
		}
		classSimpleName = className;
		return className + ".xml";
	}
	
	/**
	 * 解析xml文件
	 */
	private static void init(Class clazzPath) {
		try {
			// 读取xml文本内容
			Document document = XMLHelper.getDocument(clazzPath, lowFirstChar(clazzPath));
			if (document != null) {
				// 获取根节点
				Element sqlElement = document.getRootElement();
				// 获取所有子节点
				List children = sqlElement.elements();
				// 遍历所有子节点，并获取节点内容放入map中
				for (Iterator iterator = children.iterator(); iterator.hasNext();) {
					Element child = (Element) iterator.next();
					// 获取节点ID
					String childId = child.attributeValue("id");
					// 获取节点内容
					String childData = child.getData().toString().trim();
					// 如果子节点ID为空则跳过
					if (StringUtil.isEmpty(childId)) {
						continue;
					}
					String mapKey = classSimpleName + "." + childId;
					// 如果已经存在该map键则跳过，只取第一次存的值
					if (sqlMap.containsKey(mapKey)) {
						continue;
					}
					// 将可用值放入map中
					sqlMap.put(mapKey, childData);
				}
			}
		} catch (Exception e) {
			logger.error("SQL xml文件解析失败！" + e);
		}
	}
	
	/**
	 * 将请求的key值首字母改为小写
	 * @param key
	 * @return
	 */
	private static String lowKeyFirstChar(String key) {
		if (!Character.isLowerCase(key.charAt(0))) {
			key = (new StringBuilder()).append(Character.toLowerCase(key.charAt(0))).append(key.substring(1)).toString();
		}
		return key;
	}
	
	/**
	 * 获取sql文档中的sql值
	 * @param clazz 当前类的类名
	 * @param key 获取值的键
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getString(Class clazz, String key, String defaultValue) {
		init(clazz);
		key = lowKeyFirstChar(key);
		if (sqlMap.containsKey(key)) {
			return (String) sqlMap.get(key);
		}
		
		return defaultValue;
	}
	
	/**
	 * 获取sql文档中的sql值
	 * @param clazz 当前类的类名
	 * @param key 获取值的键
	 * @return
	 */
	public static String getString(Class clazz, String key) {
		return getString(clazz, key, null);
	}
	
}
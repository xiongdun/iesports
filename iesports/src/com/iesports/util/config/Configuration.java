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
 * 描述：读取configuration.xml中的内容并解析
 * @author xiongdun
 * @created 2016年9月1日 上午10:08:13
 * @since
 */
public class Configuration {
	private static Logger logger = Logger.getLogger(Configuration.class);

	private static Map items = new HashMap();

	/**
	 * 配置文件名
	 */
	private static final String CONFIG_FILE_NAME = "configuration.xml";
	/**
	 * 配置文件目录
	 */
	private static final String CONFIG_CATEGORY = "category";
	/**
	 * 配置文件目录名
	 */
	private static final String CONFIG_CATEGORY_NAME = "name";
	/**
	 * 目录项
	 */
	private static final String CATEGORY_ITEM = "item";
	/**
	 * 目录项名
	 */
	private static final String CATEGORY_ITEM_NAME = "name";
	/**
	 * 目录项值
	 */
	private static final String CATEGORY_TIEM_VALUE = "value";

	/**
	 * 使用静态代码块，提高文件类加载级别
	 */
	static {
		loadConfig();
	}

	/**
	 * 描述：加载指定文件并从中读取内容
	 * @author xiongdun
	 * @created 2016年9月2日 上午10:47:13
	 * @since
	 */
	private static void loadConfig() {

		try {
			Document document = XMLHelper.getDocument(Configuration.class,
					CONFIG_FILE_NAME);
			if (document != null) {
				Element systemElement = document.getRootElement();
				List cateList = systemElement.elements(CONFIG_CATEGORY);
				for (Iterator cateIter = cateList.iterator(); cateIter.hasNext();) {
					Element cateElement = (Element) cateIter.next();
					String cateName = cateElement.attributeValue(CONFIG_CATEGORY_NAME);
					if (StringUtil.isEmpty(cateName)) {
						continue;
					}

					List itemList = cateElement.elements(CATEGORY_ITEM);
					for (Iterator itemIter = itemList.iterator(); itemIter.hasNext();) {
						Element itemElement = (Element) itemIter.next();
						String itemName = itemElement.attributeValue(CATEGORY_ITEM_NAME);
						String itemValue = itemElement.attributeValue(CATEGORY_TIEM_VALUE);
						if (!(StringUtil.isEmpty(itemName))) {
							items.put(cateName + "." + itemName, itemValue);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("文件加载失败！");
		} finally {

		}
	}
	
	/**
	 * 描述：返回所有的配置items
	 * @author xiongdun
	 * @created 2016年9月2日 下午2:14:40
	 * @since 
	 * @return
	 */
	public static Map getItems() {
		return items;
	}

	/**
	 * 描述：获取指定配置的字符串值
	 * @author xiongdun
	 * @created 2016年9月2日 下午2:08:25
	 * @since
	 * @param name
	 * @return
	 */
	public static String getString(String name) {
		String value = (String) items.get(name);
		return value == null ? "" : value;
	}

	/**
	 * 描述：获取指定配置的字符串值,为空则使用传入的默认值
	 * @author xiongdun
	 * @created 2016年9月2日 下午2:10:48
	 * @since
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String name, String defaultValue) {
		String value = (String) items.get(name);
		if (value != null && value.length() > 0) {
			return value;
		} else {
			return defaultValue;
		}
	}

	/**
	 * 获得整型配置值
	 * 
	 * @param name
	 * @return
	 */
	public static int getInt(String name) {
		String value = getString(name);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			logger.debug("配置文件key[" + name + "]配置错误，return 0", ex);
			return 0;
		}
	}

	/**
	 * 获得整型配置值
	 * 
	 * @param name
	 * @return
	 */
	public static int getInt(String name, int defaultValue) {
		String value = getString(name);
		if ("".equals(value)) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			logger.debug("配置文件key[" + name + "]配置错误，return " + defaultValue, ex);
		}
		return defaultValue;
	}

	/**
	 * 获得布尔型配置值
	 * 
	 * @param name
	 * @return
	 */
	public static boolean getBoolean(String name) {
		String value = getString(name);
		return Boolean.valueOf(value).booleanValue();
	}

	/**
	 * 获得双精度浮点数配置值
	 * 
	 * @param name
	 * @return
	 */
	public static double getDouble(String name, double defaultValue) {
		String value = getString(name);
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			logger.error("配置文件key[" + name + "]配置错误，return " + defaultValue, ex);
		}
		return defaultValue;
	}

	/**
	 * 获得整型配置值
	 * 
	 * @param name
	 * @return
	 */
	public static double getDouble(String name) {
		String value = getString(name);
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			logger.debug("配置文件key[" + name + "]配置错误，return 0", ex);
			return 0;
		}
	}
	
}

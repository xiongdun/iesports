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
 * ��������ȡconfiguration.xml�е����ݲ�����
 * @author xiongdun
 * @created 2016��9��1�� ����10:08:13
 * @since
 */
public class Configuration {
	private static Logger logger = Logger.getLogger(Configuration.class);

	private static Map items = new HashMap();

	/**
	 * �����ļ���
	 */
	private static final String CONFIG_FILE_NAME = "configuration.xml";
	/**
	 * �����ļ�Ŀ¼
	 */
	private static final String CONFIG_CATEGORY = "category";
	/**
	 * �����ļ�Ŀ¼��
	 */
	private static final String CONFIG_CATEGORY_NAME = "name";
	/**
	 * Ŀ¼��
	 */
	private static final String CATEGORY_ITEM = "item";
	/**
	 * Ŀ¼����
	 */
	private static final String CATEGORY_ITEM_NAME = "name";
	/**
	 * Ŀ¼��ֵ
	 */
	private static final String CATEGORY_TIEM_VALUE = "value";

	/**
	 * ʹ�þ�̬����飬����ļ�����ؼ���
	 */
	static {
		loadConfig();
	}

	/**
	 * ����������ָ���ļ������ж�ȡ����
	 * @author xiongdun
	 * @created 2016��9��2�� ����10:47:13
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
			logger.error("�ļ�����ʧ�ܣ�");
		} finally {

		}
	}
	
	/**
	 * �������������е�����items
	 * @author xiongdun
	 * @created 2016��9��2�� ����2:14:40
	 * @since 
	 * @return
	 */
	public static Map getItems() {
		return items;
	}

	/**
	 * ��������ȡָ�����õ��ַ���ֵ
	 * @author xiongdun
	 * @created 2016��9��2�� ����2:08:25
	 * @since
	 * @param name
	 * @return
	 */
	public static String getString(String name) {
		String value = (String) items.get(name);
		return value == null ? "" : value;
	}

	/**
	 * ��������ȡָ�����õ��ַ���ֵ,Ϊ����ʹ�ô����Ĭ��ֵ
	 * @author xiongdun
	 * @created 2016��9��2�� ����2:10:48
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
	 * �����������ֵ
	 * 
	 * @param name
	 * @return
	 */
	public static int getInt(String name) {
		String value = getString(name);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException ex) {
			logger.debug("�����ļ�key[" + name + "]���ô���return 0", ex);
			return 0;
		}
	}

	/**
	 * �����������ֵ
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
			logger.debug("�����ļ�key[" + name + "]���ô���return " + defaultValue, ex);
		}
		return defaultValue;
	}

	/**
	 * ��ò���������ֵ
	 * 
	 * @param name
	 * @return
	 */
	public static boolean getBoolean(String name) {
		String value = getString(name);
		return Boolean.valueOf(value).booleanValue();
	}

	/**
	 * ���˫���ȸ���������ֵ
	 * 
	 * @param name
	 * @return
	 */
	public static double getDouble(String name, double defaultValue) {
		String value = getString(name);
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			logger.error("�����ļ�key[" + name + "]���ô���return " + defaultValue, ex);
		}
		return defaultValue;
	}

	/**
	 * �����������ֵ
	 * 
	 * @param name
	 * @return
	 */
	public static double getDouble(String name) {
		String value = getString(name);
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			logger.debug("�����ļ�key[" + name + "]���ô���return 0", ex);
			return 0;
		}
	}
	
}

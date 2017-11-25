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
 * @ ����sql���õ�sql�ļ�
 * @author xiongdun@thinkive.com
 * @ ˵��
 * 1������Ŀǰ���Կ�������ʹ�õ�
 * ���������ȷ�Ļ�ȡֵ��������ܵ����1)���������Ƿ���ȷ2)����ļ��Ƿ���xml�ļ��д���ƴ�Ӽ���ʽ��Сд��������ĸ+��ǩID��
 * 
 * 2�������xml �ĵ���һ����ʽ
 * ������Բ���com.thinkive.ncbank.plat.web.module.contract.service.impl
 * ���µ�contractServiceImpl.xml ���б�дxml�ĵ�
 * 
 * 3���������������ʱ����������ĸ��ΪСд����Ȼ����Ҳû�й�ϵ�����еķ������Զ�������ĸ��д
 * 
 */
public class SqlXmlResolver {
	
	private static Logger logger = Logger.getLogger(SqlXmlResolver.class);
	
	private static Map sqlMap = new HashMap();
	
	private static String classSimpleName = "";
	
	public SqlXmlResolver() {
		
	}
	
	static {
		// �����ĵ�·��
		// ����xml�ĵ�
		// ��ȡ�ĵ�����
		// init(clazzSimplePath);
	}

	/**
	 * ������ĸ��ΪСд
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
	 * ����xml�ļ�
	 */
	private static void init(Class clazzPath) {
		try {
			// ��ȡxml�ı�����
			Document document = XMLHelper.getDocument(clazzPath, lowFirstChar(clazzPath));
			if (document != null) {
				// ��ȡ���ڵ�
				Element sqlElement = document.getRootElement();
				// ��ȡ�����ӽڵ�
				List children = sqlElement.elements();
				// ���������ӽڵ㣬����ȡ�ڵ����ݷ���map��
				for (Iterator iterator = children.iterator(); iterator.hasNext();) {
					Element child = (Element) iterator.next();
					// ��ȡ�ڵ�ID
					String childId = child.attributeValue("id");
					// ��ȡ�ڵ�����
					String childData = child.getData().toString().trim();
					// ����ӽڵ�IDΪ��������
					if (StringUtil.isEmpty(childId)) {
						continue;
					}
					String mapKey = classSimpleName + "." + childId;
					// ����Ѿ����ڸ�map����������ֻȡ��һ�δ��ֵ
					if (sqlMap.containsKey(mapKey)) {
						continue;
					}
					// ������ֵ����map��
					sqlMap.put(mapKey, childData);
				}
			}
		} catch (Exception e) {
			logger.error("SQL xml�ļ�����ʧ�ܣ�" + e);
		}
	}
	
	/**
	 * �������keyֵ����ĸ��ΪСд
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
	 * ��ȡsql�ĵ��е�sqlֵ
	 * @param clazz ��ǰ�������
	 * @param key ��ȡֵ�ļ�
	 * @param defaultValue Ĭ��ֵ
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
	 * ��ȡsql�ĵ��е�sqlֵ
	 * @param clazz ��ǰ�������
	 * @param key ��ȡֵ�ļ�
	 * @return
	 */
	public static String getString(Class clazz, String key) {
		return getString(clazz, key, null);
	}
	
}
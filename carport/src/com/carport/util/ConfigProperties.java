/**
 * 
 */
package com.carport.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ��������configuration.xml�ж�ȡ��������Դ�����ļ���Ϣ�� д�뵽jdbc.Properties �ļ���
 * 
 * @author zhangyijie
 * @created 2016��9��19�� ����3:05:44
 * @since
 */
public class ConfigProperties {
	
	private static Logger logger = Logger.getLogger(ConfigProperties.class);
	
	/**
	 * �ļ�·������������Ϊ�����õ�
	 */
	private static String filePath = "/config.properties";

	// ��ȡ�ļ���·��
	private static Properties props = new Properties();
	/**
	 * ʹ�þ�̬�������أ����class���ؼ���
	 */
	static {
		try {
			InputStream is = Object.class.getResourceAsStream(filePath);
			props.load(is);
		} catch (FileNotFoundException e) {
			logger.error("�ļ�δ�ҵ��쳣", e);
			System.exit(-1);
		} catch (IOException e) {
			logger.error("�ļ��������쳣", e);
			System.exit(-1);
		} catch(Exception e){
			logger.error("�ļ���Ϊ���쳣", e);
		}
	}
	
	public ConfigProperties() {

	}

	/**
	 * ��������key��ȡkey-valueֵ ��ȡ�����ļ�����Ӧ����ֵ
	 * 
	 * @author zhangyijie
	 * @created 2016��9��19�� ����3:18:12
	 * @since
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return props.getProperty(key);
	}

	/**
	 * ��������ȡָ����ֵ��Ϊ����ʹ��Ĭ��ֵ
	 * 
	 * @author zhangyijie
	 * @created 2016��9��26�� ����5:13:12
	 * @since
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String key, String defaultValue) {
		String value = getString(key);
		if (value == null || "".equals(value)) {
			if (defaultValue == null || "".equals(defaultValue)) {
				return null;
			}
			return defaultValue;
		}
		return value;
	}

	/**
	 * ��������ȡָ����ֵ��Ϊ����ʹ��Ĭ��ֵ
	 * 
	 * @author zhangyijie
	 * @created 2016��9��26�� ����5:13:12
	 * @since
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		String value = getString(key);
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * ��������ȡָ��������ֵ
	 * 
	 * @author zhangyijie
	 * @created 2016��12��4�� ����12:47:19
	 * @since
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		String value = getString(key);
		if (value == null || "".equals(value)) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	/**
	 * ��������������key��ȡ������ֵvalue
	 * 
	 * @author zhangyijie
	 * @created 2016��9��19�� ����3:18:36
	 * @since
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = Object.class.getResourceAsStream(filePath);
			props.load(in);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			logger.error("��ȡkey-valueʧ��", e);
			return null;
		}
	}

	/**
	 * ��������������key��ȡ������ֵvalue
	 * 
	 * @author zhangyijie
	 * @created 2016��9��26�� ����4:58:47
	 * @since
	 * @param key
	 * @return
	 */
	public static String readValue(String key) {
		String value = readValue(filePath, key);
		if (value != null || !("".equals(value))) {
			return value;
		}
		return null;
	}

	/**
	 * ���������£�����룩һ��properties��Ϣ(���������ֵ) ����������Ѿ����ڣ����¸�������ֵ�� ��������������ڣ�����һ�Լ�ֵ��
	 * 
	 * @author zhangyijie
	 * @created 2016��9��19�� ����3:19:09
	 * @since
	 * @param keyname
	 * @param keyvalue
	 */
	public static void writeProperties(String keyname, String keyvalue) {
		try {
			// ���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�
			// ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
			OutputStream fos = new FileOutputStream(filePath);
			props.setProperty(keyname, keyvalue);
			// ���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��
			// ���� Properties ���е������б�����Ԫ�ضԣ�д�������
			props.store(fos, "Update '" + keyname + "' value");
			// System.out.println(1);
		} catch (IOException e) {
			logger.error("�����ļ�д�����", e);
		}
	}

	/**
	 * ����������properties�ļ��ļ�ֵ�� ����������Ѿ����ڣ����¸�������ֵ�� ��������������ڣ�����һ�Լ�ֵ��
	 * 
	 * @author zhangyijie
	 * @created 2016��9��19�� ����3:19:28
	 * @since
	 * @param keyname
	 * @param keyvalue
	 */
	public static void updateProperties(String keyname, String keyvalue) {
		try {
			props.load(new FileInputStream(filePath));
			// ���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�
			// ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
			OutputStream fos = new FileOutputStream(filePath);
			props.setProperty(keyname, keyvalue);
			// ���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��
			// ���� Properties ���е������б�����Ԫ�ضԣ�д�������
			props.store(fos, "Update '" + keyname + "' value");
		} catch (IOException e) {
			logger.error("�����ļ����´���", e);
		}
	}
}

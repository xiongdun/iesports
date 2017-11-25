/**
 * 
 */
package com.iesports.util.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 描述：将configuration.xml中读取到的数据源配置文件信息，
 * 写入到jdbc.Properties 文件中
 * @author xiongdun
 * @created 2016年9月19日 下午3:05:44
 * @since 
 */
public class JDBCProperties {
	private static Logger logger = Logger.getLogger(JDBCProperties.class);
	/**
	 * 文件路径，可以设置为可配置的
	 */
	private static String filePath = "/jdbc.properties";
	
	private static Properties props = new Properties();
	/**
	 * 使用静态代码块加载，提高class加载级别
	 */
    static {
        try {
        	InputStream in = Object.class.getResourceAsStream(filePath);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("文件未找到异常", e);
            System.exit(-1);
        } catch (IOException e) {
            System.exit(-1);
        }
    }
    
    /**
     * 描述：按key获取key-value值
     * 读取属性文件中相应键的值
     * @author xiongdun
     * @created 2016年9月19日 下午3:18:12
     * @since 
     * @param key
     * @return
     */
    public static String getString(String key) {
        return props.getProperty(key);
    }
    
    /**
     * 描述：获取指定键值，为空则使用默认值
     * @author xiongdun
     * @created 2016年9月26日 下午5:13:12
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
     * 描述：获取指定键值，为空则使用默认值
     * @author xiongdun
     * @created 2016年9月26日 下午5:13:12
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
     * 描述：根据主键key读取主键的值value
     * @author xiongdun
     * @created 2016年9月19日 下午3:18:36
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
            logger.error("获取key-value失败", e);
            return null;
        }
    }
    
    /**
     * 描述：根据主键key读取主键的值value
     * @author xiongdun
     * @created 2016年9月26日 下午4:58:47
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
     * 描述：更新（或插入）一对properties信息(主键及其键值)
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     * @author xiongdun
     * @created 2016年9月19日 下午3:19:09
     * @since 
     * @param keyname 
     * @param keyvalue
     */
    public static void writeProperties(String keyname, String keyvalue) {       
        try {
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
            //System.out.println(1);
        } catch (IOException e) {
            logger.error("属性文件写入错误", e);
        }
    }
    
    /**
     * 描述：更新properties文件的键值对
     * 如果该主键已经存在，更新该主键的值；
     * 如果该主键不存在，则插件一对键值。
     * @author xiongdun
     * @created 2016年9月19日 下午3:19:28
     * @since 
     * @param keyname
     * @param keyvalue
     */
    public static void updateProperties(String keyname, String keyvalue) {
        try {
            props.load(new FileInputStream(filePath));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);           
            props.setProperty(keyname, keyvalue);
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
        	logger.error("属性文件更新错误", e);
        }
    }
    
    /*public static void main(String[] args) {
		int value = getInt("passwords", 23432);
		System.out.println(value);
    	//writeProperties("maxdbpool", "1000");
	}*/
}

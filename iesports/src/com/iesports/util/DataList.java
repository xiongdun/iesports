/**
 * 
 */
package com.iesports.util;

import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * ���������ݷ�װ������,�̳���HashMap
 * 
 * @author xiongdun
 * @created 2016��9��1�� ����10:09:19
 * @since
 */
public class DataList extends HashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1414527383125318579L;
	
	private static Logger logger = Logger.getLogger(DataList.class);
	
	/**
	 * ��������ֵ��ֵ��
	 * @author xiongdun
	 * @created 2016��9��8�� ����10:11:44
	 * @since 
	 * @param name
	 * @param value
	 */
	public void set(String name, String value) {
		if (name == null || "".equals(name)) {
			return;
		}
		if (value == null) {
			put(name, "");
		} else {
			put(name, value);
		}
	}
	
	public void set(String name, boolean value) {
		put(name, new Boolean(value));
	}
	
	public void set(String name, int value) {
		put(name, new Integer(value));
	}
	
	public void set(String name, long value) {
		put(name, new Long(value));
	}
	
	public void set(String name, float value) {
		put(name, new Float(value));
	}
	
	public void set(String name, double value) {
		put(name, new Double(value));
	}
	
	public void set(String name, Object value) {
		put(name, value);
	}
	
	/**
	 * ������ȡֵ��ֵ��
	 * @author xiongdun
	 * @created 2016��9��8�� ����10:14:21
	 * @since 
	 * @param name
	 * @return
	 */
	public String getString(String name) {
		if (name == null || "".equals(name)) {
			return "";
		}
		Object obj = this.get(name);
		return obj == null ? "" : (String) obj;
	}
	
	public boolean getBoolean(String name) {
		if (name == null || "".equals(name)) {
			return false;
		}
		
		boolean value = false;
		if (this.containsKey(name) == false) {
			return false;
		}
		Object obj = this.get(name);
		if (obj == null) {
			return false;
		}
		if (obj instanceof Boolean) {
			value = ((Boolean) obj).booleanValue();
			obj = null;
		}
		value = Boolean.valueOf(obj.toString()).booleanValue();
		return value;
	}
	
	public int getInt(String name) {
		
		if (name == null || "".equals(name)) {
			return 0;
		}
		
		int value = 0;
		if (this.containsKey(name) == false) {
			return 0;
		}
		Object obj = this.get(name);
		if (obj == null) {
			return 0;
		}
		if (!(obj instanceof Integer)) {
			try {
				value = Integer.parseInt(obj.toString());
			} catch (Exception e) {
				logger.debug(name + "��Ӧ��ֵ����int���ͣ�return 0", e);
				value = 0;
			}
		} else {
			value = ((Integer) obj).intValue();
			obj = null;
		}
		
		return value;
	}
	
	public long getLong(String name) {
		if (name == null || "".equals(name)) {
			return 0;
		}
		
		long value = 0;
		if (this.containsKey(name) == false) {
			return 0;
		}
		Object obj = this.get(name);
		if (obj == null) {
			return 0;
		}
		if (!(obj instanceof Long)) {
			try {
				value = Long.parseLong(obj.toString());
			} catch (Exception e) {
				logger.debug(name + "��Ӧ��ֵ����long���ͣ�return 0", e);
				value = 0;
			}
		} else {
			value = ((Long) obj).longValue();
			obj = null;
		}
		
		return value;
	}
	
	public float getFloat(String name) {
		if (name == null || "".equals(name)) {
			return 0;
		}
		
		float value = 0;
		if (this.containsKey(name) == false) {
			return 0;
		}
		Object obj = this.get(name);
		if (obj == null) {
			return 0;
		}
		if (!(obj instanceof Float)) {
			try {
				value = Float.parseFloat(obj.toString());
			} catch (Exception e) {
				logger.debug(name + "��Ӧ��ֵ����float���ͣ�return 0", e);
				value = 0;
			}
		} else {
			value = ((Float) obj).floatValue();
			obj = null;
		}
		
		return value;
	}
	
	public double getDouble(String name) {
		if (name == null || "".equals(name)) {
			return 0;
		}
		
		double value = 0;
		if (this.containsKey(name) == false) {
			return 0;
		}
		Object obj = this.get(name);
		if (obj == null) {
			return 0;
		}
		if (!(obj instanceof Double)) {
			try {
				value = Double.parseDouble(obj.toString());
			} catch (Exception e) {
				logger.debug(name + "��Ӧ��ֵ����double���ͣ�return 0", e);
				value = 0;
			}
		} else {
			value = ((Double) obj).doubleValue();
			obj = null;
		}
		
		return value;
	}
	
	public Object getObject(String name) {
		if (name == null || "".equals(name)) {
			return null;
		}
		if (this.containsKey(name) == false) {
			return null;
		}
		return this.get(name);
	}
}

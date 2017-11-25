/**
 * 
 */
package com.iesports.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 描述：封装结果数据集工具类
 * @author xiongdun
 * @created 2016年9月19日 上午10:22:25
 * @since 
 */
public class ResultData {
	
	private static Logger logger = Logger.getLogger(ResultData.class);
	private static final String ERROR_NO = "error_no";
	private static final String ERROR_INFO = "error_info";
	private static final String FIRST_DSNAME = "first_dsname";
	private static final String DEFAULT_RESULT_NAME = "results";
	private static final String DS_AMOUNT = "ds_amount";
	private static final String DS_NAME = "ds_name";
	
	private HashMap innerMap = null;
	public Map toMap() {
		return this.innerMap;
	}

	protected void set(String name, String value) {
		if ((name == null) || (name.equals(""))) {
			return;
		}
		if (value == null)
			this.innerMap.put(name, "");
		else
			this.innerMap.put(name, value);
	}

	protected void set(String name, int value) {
		this.innerMap.put(name, new Integer(value));
	}

	protected void set(String name, boolean value) {
		this.innerMap.put(name, new Boolean(value));
	}

	protected void set(String name, long value) {
		this.innerMap.put(name, new Long(value));
	}

	protected void set(String name, float value) {
		this.innerMap.put(name, new Float(value));
	}

	protected void set(String name, double value) {
		this.innerMap.put(name, new Double(value));
	}

	protected void set(String name, Object value) {
		this.innerMap.put(name, value);
	}

	protected String getString(String name) {
		if ((name == null) || (name.equals(""))) {
			return "";
		}
		String value = "";
		if (!(this.innerMap.containsKey(name)))
			return "";
		Object obj = this.innerMap.get(name);
		if (obj != null)
			value = obj.toString();
		obj = null;

		return value;
	}

	protected int getInt(String name) {
		if ((name == null) || (name.equals(""))) {
			return 0;
		}
		int value = 0;
		if (!(this.innerMap.containsKey(name))) {
			return 0;
		}
		Object obj = this.innerMap.get(name);
		if (obj == null) {
			return 0;
		}
		if (!(obj instanceof Integer)) {
			try {
				value = Integer.parseInt(obj.toString());
			} catch (Exception ex) {
				logger.debug(name + "对应的值不是数字，return 0", ex);
				value = 0;
			}
		} else {
			value = ((Integer) obj).intValue();
			obj = null;
		}

		return value;
	}

	protected long getLong(String name) {
		if ((name == null) || (name.equals(""))) {
			return 0L;
		}
		long value = 0L;
		if (!(this.innerMap.containsKey(name))) {
			return 0L;
		}
		Object obj = this.innerMap.get(name);
		if (obj == null) {
			return 0L;
		}
		if (!(obj instanceof Long)) {
			try {
				value = Long.parseLong(obj.toString());
			} catch (Exception ex) {
				logger.error(name + "对应的值不是数字，return 0", ex);
				value = 0L;
			}
		} else {
			value = ((Long) obj).longValue();
			obj = null;
		}

		return value;
	}

	protected float getFloat(String name) {
		if ((name == null) || (name.equals(""))) {
			return 0.0F;
		}
		float value = 0.0F;
		if (!(this.innerMap.containsKey(name))) {
			return 0.0F;
		}
		Object obj = this.innerMap.get(name);
		if (obj == null) {
			return 0.0F;
		}
		if (!(obj instanceof Float)) {
			try {
				value = Float.parseFloat(obj.toString());
			} catch (Exception ex) {
				logger.error(name + "对应的值不是数字，return 0", ex);
				value = 0.0F;
			}
		} else {
			value = ((Float) obj).floatValue();
			obj = null;
		}

		return value;
	}

	protected double getDouble(String name) {
		if ((name == null) || (name.equals(""))) {
			return 0.0D;
		}
		double value = 0.0D;
		if (!(this.innerMap.containsKey(name))) {
			return 0.0D;
		}
		Object obj = this.innerMap.get(name);
		if (obj == null) {
			return 0.0D;
		}
		if (!(obj instanceof Double)) {
			try {
				value = Double.parseDouble(obj.toString());
			} catch (Exception ex) {
				logger.error(name + "对应的值不是数字，return 0", ex);
				value = 0.0D;
			}
		} else {
			value = ((Double) obj).doubleValue();
			obj = null;
		}

		return value;
	}

	protected boolean getBoolean(String name) {
		if ((name == null) || (name.equals(""))) {
			return false;
		}
		boolean value = false;
		if (!(this.innerMap.containsKey(name)))
			return false;
		Object obj = this.innerMap.get(name);
		if (obj == null) {
			return false;
		}
		if (obj instanceof Boolean) {
			return ((Boolean) obj).booleanValue();
		}

		value = Boolean.valueOf(obj.toString()).booleanValue();
		obj = null;
		return value;
	}

	protected Object getObject(String name) {
		if ((name == null) || (name.equals(""))) {
			return null;
		}
		if (!(this.innerMap.containsKey(name)))
			return null;
		return this.innerMap.get(name);
	}
	/**
	 * 描述：获取错误码
	 * @author xiongdun
	 * @created 2016年9月19日 上午10:49:56
	 * @since 
	 * @return
	 */
	public int getErr_no() {
		return getInt("error_no");
	}

	/**
	 * 描述：设置错误码
	 * @author xiongdun
	 * @created 2016年9月19日 上午10:50:11
	 * @since 
	 * @param errNo
	 */
	public void setErr_no(int errNo) {
		set("error_no", errNo);
	}

	/**
	 * 描述：获取错误信息
	 * @author xiongdun
	 * @created 2016年9月19日 上午10:52:08
	 * @since 
	 * @return
	 */
	public String getErr_info() {
		return getString("error_info");
	}

	/**
	 * 描述：设置错误信息
	 * @author xiongdun
	 * @created 2016年9月19日 上午10:52:22
	 * @since 
	 * @param errInfo
	 */
	public void setErr_info(String errInfo) {
		set("error_info", errInfo);
	}

	public void setFirstDataSetName(String firstDataSetName) {
		set("first_dsname", firstDataSetName);
	}

	public String getFirstDataSetName() {
		return getString("first_dsname");
	}

	public int getDataSetNum() {
		return getDsNameArr().size();
	}

	public void setDataSetNum(int dataSetNum) {
		set("ds_amount", dataSetNum);
	}

	/**
	 * 描述：设置数据结果
	 * @author xiongdun
	 * @created 2016年9月19日 上午10:52:54
	 * @since 
	 * @param DataList
	 */
	public void setResult(DataList DataList) {
		setResult("results", DataList);
	}

	public void setResult(String name, DataList DataList) {
		List dataList = new ArrayList();
		dataList.add(DataList);
		setResult(name, dataList);
	}

	public void setResult(DataPage DataPage) {
		setResult("results", DataPage);
	}

	public void setResult(String name, DataPage DataPage) {
		List dataList = new ArrayList();
		dataList.add(DataPage);
		setResult(name, dataList);
	}

	public void setResult(List results) {
		setResult("results", results);
	}

	public void setResult(String name, List results) {
		if (StringUtil.isEmpty(getFirstDataSetName())) {
			setFirstDataSetName(name);
		}
		set(name, results);
		setDsName(name);
	}

	public List getList(String dsName) {
		Object value = getObject(dsName);
		if (value instanceof List) {
			return ((List) value);
		}

		return new ArrayList();
	}

	public DataList getData(String dsName) {
		List dataList = getList(dsName);
		if ((dataList != null) && (dataList.size() > 0)) {
			Object obj = dataList.get(0);
			if (obj instanceof DataList) {
				return ((DataList) obj);
			}

			logger.error("!!!!!!!!!!!返回的不是DataList类型");
		}

		return new DataList();
	}

	public DataList getData() {
		return getData(getFirstDataSetName());
	}

	public List getList() {
		return getList(getFirstDataSetName());
	}

	public DataPage getPage() {
		return getPage(getFirstDataSetName());
	}

	public DataPage getPage(String dsName) {
		List dataList = getList(dsName);
		if ((dataList != null) && (dataList.size() > 0)) {
			Object obj = dataList.get(0);
			if (obj instanceof DataPage) {
				return ((DataPage) obj);
			}

			DataPage DataPage = new DataPage(1, 2147483647);
			DataPage.setTotalRows(dataList.size());
			DataPage.setDataList(dataList);
			return DataPage;
		}

		return null;
	}

	public Set<String> dsNameSet() {
		return getDsNameArr();
	}

	public void setDsName(String dsName) {
		HashSet result = (HashSet) getObject("ds_name");
		if (result == null) {
			result = new HashSet();
			set("ds_name", result);
		}
		result.add(dsName);
	}

	public HashSet<String> getDsNameArr() {
		Object obj = getObject("ds_name");
		if ((obj != null) && (obj instanceof HashSet)) {
			return ((HashSet) obj);
		}

		return new HashSet();
	}

	public void remove(String key) {
		if (getObject(key) == null)
			return;
		remove(key);
	}
}

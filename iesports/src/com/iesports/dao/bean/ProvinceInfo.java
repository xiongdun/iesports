/**
 * 
 */
package com.iesports.dao.bean;

import java.util.List;

/**
 * 描述：省、州实体类
 * @author xiongdun
 * @created 2016年10月10日 上午11:03:27
 * @since 
 */
public class ProvinceInfo {
	private Integer province_id;
	private String province_name;
	private String province_en_name;
	private String province_nickname;
	private String province_code;
	private String province_capital;
	private List<CityInfo> citys;
	
	public List<CityInfo> getCitys() {
		return citys;
	}
	public void setCitys(List<CityInfo> citys) {
		this.citys = citys;
	}
	public Integer getProvince_id() {
		return province_id;
	}
	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getProvince_en_name() {
		return province_en_name;
	}
	public void setProvince_en_name(String province_en_name) {
		this.province_en_name = province_en_name;
	}
	public String getProvince_nickname() {
		return province_nickname;
	}
	public void setProvince_nickname(String province_nickname) {
		this.province_nickname = province_nickname;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	
	public String getProvince_capital() {
		return province_capital;
	}
	public void setProvince_capital(String province_capital) {
		this.province_capital = province_capital;
	}
	
	public ProvinceInfo(Integer province_id, String province_name,
			String province_en_name, String province_nickname,
			String province_code, String province_capital,
			List<CityInfo> citys) {
		super();
		this.province_id = province_id;
		this.province_name = province_name;
		this.province_en_name = province_en_name;
		this.province_nickname = province_nickname;
		this.province_code = province_code;
		this.province_capital = province_capital;
		this.citys = citys;
	}
	public ProvinceInfo() {
		super();
	}
	
	
}

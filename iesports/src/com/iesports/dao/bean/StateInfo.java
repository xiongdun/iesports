/**
 * 
 */
package com.iesports.dao.bean;

import java.util.List;

/**
 * 描述：国家实体类
 * @author xiongdun
 * @created 2016年10月10日 上午11:26:23
 * @since 
 */
public class StateInfo {
	private Integer state_id;
	private String state_en_name;
	private String state_nickname;
	private String state_name;
	private String state_code;
	private String state_capital;
	private String phone_code;
	private String remark;
	private List<ProvinceInfo> provinces;
	
	public List<ProvinceInfo> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<ProvinceInfo> provinces) {
		this.provinces = provinces;
	}
	public Integer getState_id() {
		return state_id;
	}
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}
	public String getState_en_name() {
		return state_en_name;
	}
	public void setState_en_name(String state_en_name) {
		this.state_en_name = state_en_name;
	}
	public String getState_nickname() {
		return state_nickname;
	}
	public void setState_nickname(String state_nickname) {
		this.state_nickname = state_nickname;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getState_capital() {
		return state_capital;
	}
	public void setState_capital(String state_capital) {
		this.state_capital = state_capital;
	}
	public String getPhone_code() {
		return phone_code;
	}
	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public StateInfo(Integer state_id, String state_en_name,
			String state_nickname, String state_name, String state_code,
			String state_capital, String phone_code, String remark,
			List<ProvinceInfo> provinces) {
		super();
		this.state_id = state_id;
		this.state_en_name = state_en_name;
		this.state_nickname = state_nickname;
		this.state_name = state_name;
		this.state_code = state_code;
		this.state_capital = state_capital;
		this.phone_code = phone_code;
		this.remark = remark;
		this.provinces = provinces;
	}
	public StateInfo() {
		super();
	}
	
}

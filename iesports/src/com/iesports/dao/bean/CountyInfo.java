/**
 * 
 */
package com.iesports.dao.bean;

import java.util.List;

/**
 * 描述：郡县实体类
 * @author xiongdun
 * @created 2016年10月10日 上午10:59:18
 * @since 
 */
public class CountyInfo {
	private Integer county_id;
	private String county_name;
	private String county_en_name;
	private String county_nick_name;
	private String county_en_nick;
	private String county_code;
	private String remark;
	private String county_postalcode;
	private List<TownInfo> towns;
	
	
	
	public List<TownInfo> getTowns() {
		return towns;
	}
	public void setTowns(List<TownInfo> towns) {
		this.towns = towns;
	}
	public Integer getCounty_id() {
		return county_id;
	}
	public void setCounty_id(Integer county_id) {
		this.county_id = county_id;
	}
	public String getCounty_name() {
		return county_name;
	}
	public void setCounty_name(String county_name) {
		this.county_name = county_name;
	}
	public String getCounty_en_name() {
		return county_en_name;
	}
	public void setCounty_en_name(String county_en_name) {
		this.county_en_name = county_en_name;
	}
	public String getCounty_nick_name() {
		return county_nick_name;
	}
	public void setCounty_nick_name(String county_nick_name) {
		this.county_nick_name = county_nick_name;
	}
	public String getCounty_en_nick() {
		return county_en_nick;
	}
	public void setCounty_en_nick(String county_en_nick) {
		this.county_en_nick = county_en_nick;
	}
	public String getCounty_code() {
		return county_code;
	}
	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCounty_postalcode() {
		return county_postalcode;
	}
	public void setCounty_postalcode(String county_postalcode) {
		this.county_postalcode = county_postalcode;
	}
	
	public CountyInfo() {
		super();
	}
	public CountyInfo(Integer county_id, String county_name,
			String county_en_name, String county_nick_name,
			String county_en_nick, String county_code, String remark,
			String county_postalcode, List<TownInfo> towns) {
		super();
		this.county_id = county_id;
		this.county_name = county_name;
		this.county_en_name = county_en_name;
		this.county_nick_name = county_nick_name;
		this.county_en_nick = county_en_nick;
		this.county_code = county_code;
		this.remark = remark;
		this.county_postalcode = county_postalcode;
		this.towns = towns;
	}
	@Override
	public String toString() {
		return "CountyInfo [county_id=" + county_id + ", county_name="
				+ county_name + ", county_en_name=" + county_en_name
				+ ", county_nick_name=" + county_nick_name
				+ ", county_en_nick=" + county_en_nick + ", county_code="
				+ county_code + ", remark=" + remark + ", county_postalcode="
				+ county_postalcode + ", towns=" + towns + "]";
	}

	
}

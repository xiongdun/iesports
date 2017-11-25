/**
 * 
 */
package com.iesports.dao.bean;

import java.util.List;

/**
 * 描述：城市实体类
 * @author xiongdun
 * @created 2016年10月10日 上午10:56:37
 * @since 
 */
public class CityInfo {
	private Integer city_id;
	private String city_name;
	private String city_en_name;
	private String city_nickname;
	private String city_code;
	private String remark;
	private String city_en_nick;
	private List<CountyInfo> countys;
	
	
	public List<CountyInfo> getCountys() {
		return countys;
	}
	public void setCountys(List<CountyInfo> countys) {
		this.countys = countys;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_en_name() {
		return city_en_name;
	}
	public void setCity_en_name(String city_en_name) {
		this.city_en_name = city_en_name;
	}
	public String getCity_nickname() {
		return city_nickname;
	}
	public void setCity_nickname(String city_nickname) {
		this.city_nickname = city_nickname;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCity_en_nick() {
		return city_en_nick;
	}
	public void setCity_en_nick(String city_en_nick) {
		this.city_en_nick = city_en_nick;
	}
	
	public CityInfo(Integer city_id, String city_name, String city_en_name,
			String city_nickname, String city_code, String remark,
			String city_en_nick, List<CountyInfo> countys) {
		super();
		this.city_id = city_id;
		this.city_name = city_name;
		this.city_en_name = city_en_name;
		this.city_nickname = city_nickname;
		this.city_code = city_code;
		this.remark = remark;
		this.city_en_nick = city_en_nick;
		this.countys = countys;
	}
	public CityInfo() {
		super();
	}
	@Override
	public String toString() {
		return "CityInfo [city_id=" + city_id + ", city_name=" + city_name
				+ ", city_en_name=" + city_en_name + ", city_nickname="
				+ city_nickname + ", city_code=" + city_code + ", remark="
				+ remark + ", city_en_nick=" + city_en_nick + ", countys="
				+ countys + "]";
	}
	
}

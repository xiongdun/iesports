/**
 * 
 */
package com.iesports.dao.bean;

/**
 * 描述：乡村实体类
 * @author xiongdun
 * @created 2016年10月10日 上午11:32:00
 * @since 
 */
public class VillageInfo {
	private Integer village_id;
	private String village_name;
	private String village_en_name;
	private String village_nickname;
	private String village_en_nick;
	private String village_code;
	private String remark;

	public Integer getVillage_id() {
		return village_id;
	}
	public void setVillage_id(Integer village_id) {
		this.village_id = village_id;
	}
	public String getVillage_name() {
		return village_name;
	}
	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}
	public String getVillage_en_name() {
		return village_en_name;
	}
	public void setVillage_en_name(String village_en_name) {
		this.village_en_name = village_en_name;
	}
	public String getVillage_nickname() {
		return village_nickname;
	}
	public void setVillage_nickname(String village_nickname) {
		this.village_nickname = village_nickname;
	}
	public String getVillage_en_nick() {
		return village_en_nick;
	}
	public void setVillage_en_nick(String village_en_nick) {
		this.village_en_nick = village_en_nick;
	}
	public String getVillage_code() {
		return village_code;
	}
	public void setVillage_code(String village_code) {
		this.village_code = village_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public VillageInfo(Integer village_id, String village_name,
			String village_en_name, String village_nickname,
			String village_en_nick, String village_code, String remark) {
		super();
		this.village_id = village_id;
		this.village_name = village_name;
		this.village_en_name = village_en_name;
		this.village_nickname = village_nickname;
		this.village_en_nick = village_en_nick;
		this.village_code = village_code;
		this.remark = remark;
	}
	public VillageInfo() {
		super();
	}
	
	
}

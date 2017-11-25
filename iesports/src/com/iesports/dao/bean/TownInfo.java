/**
 * 
 */
package com.iesports.dao.bean;

import java.util.List;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月10日 上午11:29:45
 * @since 
 */
public class TownInfo {
	private Integer town_id;
	private String town_name;
	private String town_en_name;
	private String town_nickname;
	private String town_en_nick;
	private String remark;
	private String town_code;
	private List<VillageInfo> villages;
	
	public List<VillageInfo> getVillages() {
		return villages;
	}
	public void setVillages(List<VillageInfo> villages) {
		this.villages = villages;
	}
	public Integer getTown_id() {
		return town_id;
	}
	public void setTown_id(Integer town_id) {
		this.town_id = town_id;
	}
	public String getTown_name() {
		return town_name;
	}
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
	public String getTown_en_name() {
		return town_en_name;
	}
	public void setTown_en_name(String town_en_name) {
		this.town_en_name = town_en_name;
	}
	public String getTown_nickname() {
		return town_nickname;
	}
	public void setTown_nickname(String town_nickname) {
		this.town_nickname = town_nickname;
	}
	public String getTown_en_nick() {
		return town_en_nick;
	}
	public void setTown_en_nick(String town_en_nick) {
		this.town_en_nick = town_en_nick;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTown_code() {
		return town_code;
	}
	public void setTown_code(String town_code) {
		this.town_code = town_code;
	}
	
	
	public TownInfo(Integer town_id, String town_name, String town_en_name,
			String town_nickname, String town_en_nick, String remark,
			String town_code, List<VillageInfo> villages) {
		super();
		this.town_id = town_id;
		this.town_name = town_name;
		this.town_en_name = town_en_name;
		this.town_nickname = town_nickname;
		this.town_en_nick = town_en_nick;
		this.remark = remark;
		this.town_code = town_code;
		this.villages = villages;
	}
	public TownInfo() {
		super();
	}
	
	
}

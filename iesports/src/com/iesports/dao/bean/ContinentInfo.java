/**
 * 
 */
package com.iesports.dao.bean;

import java.util.List;

/**
 * 描述：大洲实体类
 * @author xiongdun
 * @created 2016年10月10日 上午10:53:56
 * @since 
 */
public class ContinentInfo {
	private Integer continent_id;
	private String continent_name;
	private String continent_en_name;
	private String continent_nick_name;
	private String continent_code;
	private List<StateInfo> states;
	
	public List<StateInfo> getStates() {
		return states;
	}
	public void setStates(List<StateInfo> states) {
		this.states = states;
	}
	public Integer getContinent_id() {
		return continent_id;
	}
	public void setContinent_id(Integer continent_id) {
		this.continent_id = continent_id;
	}
	public String getContinent_name() {
		return continent_name;
	}
	public void setContinent_name(String continent_name) {
		this.continent_name = continent_name;
	}
	public String getContinent_en_name() {
		return continent_en_name;
	}
	public void setContinent_en_name(String continent_en_name) {
		this.continent_en_name = continent_en_name;
	}
	public String getContinent_nick_name() {
		return continent_nick_name;
	}
	public void setContinent_nick_name(String continent_nick_name) {
		this.continent_nick_name = continent_nick_name;
	}
	public String getContinent_code() {
		return continent_code;
	}
	public void setContinent_code(String continent_code) {
		this.continent_code = continent_code;
	}
	
	public ContinentInfo(Integer continent_id, String continent_name,
			String continent_en_name, String continent_nick_name,
			String continent_code, List<StateInfo> states) {
		super();
		this.continent_id = continent_id;
		this.continent_name = continent_name;
		this.continent_en_name = continent_en_name;
		this.continent_nick_name = continent_nick_name;
		this.continent_code = continent_code;
		this.states = states;
	}
	public ContinentInfo() {
		super();
	}
	
	
}

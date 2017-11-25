/**
 * 
 */
package com.carport.model;

/**
 * 描述：抢车位历史model
 * @author xiongdun
 * @created 2016年12月19日 下午4:21:43
 * @since 
 */
public class CarportHistoryModel {
	private String cphis_id;// 历史Id
	private String user_id;// 用户Id
	private String user_name;//用户名称
	private String apartment_id;// 部门Id
	private String apartment_name;// 部门名称
	private String org_id;// 组织机构Id
	private String org_name;// 组织机构名称
	private String carport_no;// 车位号码
	private String status;// 状态
	private String datetime;// 时间
	public String getCphis_id() {
		return cphis_id;
	}
	public void setCphis_id(String cphis_id) {
		this.cphis_id = cphis_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getApartment_id() {
		return apartment_id;
	}
	public void setApartment_id(String apartment_id) {
		this.apartment_id = apartment_id;
	}
	public String getApartment_name() {
		return apartment_name;
	}
	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getCarport_no() {
		return carport_no;
	}
	public void setCarport_no(String carport_no) {
		this.carport_no = carport_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}

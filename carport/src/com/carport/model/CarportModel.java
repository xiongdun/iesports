/**
 * 
 */
package com.carport.model;

/**
 * 描述：车位部门机构model
 * @author xiongdun
 * @created 2016年12月13日 下午3:49:48
 * @since 
 */
public class CarportModel {
	private String carport_id;// 车位号id
	private String carport_no;// 车位号
	private String org_id;// 组织机构id
	private String status;// 状态
	private String apartment_id;// 部门Id
	private String datetime;// 插入时间
	private String apartment_name;
	private String org_name;
	public String getCarport_id() {
		return carport_id;
	}
	public void setCarport_id(String carport_id) {
		this.carport_id = carport_id;
	}
	public String getCarport_no() {
		return carport_no;
	}
	public void setCarport_no(String carport_no) {
		this.carport_no = carport_no;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApartment_id() {
		return apartment_id;
	}
	public void setApartment_id(String apartment_id) {
		this.apartment_id = apartment_id;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getApartment_name() {
		return apartment_name;
	}
	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
}

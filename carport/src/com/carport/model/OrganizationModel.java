/**
 * 
 */
package com.carport.model;

/**
 * 描述：组织机构model
 * @author xiongdun
 * @created 2016年12月19日 下午4:44:30
 * @since 
 */
public class OrganizationModel {

	private String org_id;// 组织机构ID
	private String org_name;// 组织机构名
	private String status;// 状态
	private String apartment_id;// 部门Id
	private String apartment_name;// 部门名称
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
	public String getApartment_name() {
		return apartment_name;
	}
	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}
	
}

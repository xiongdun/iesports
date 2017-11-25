/**
 * 
 */
package com.iesports.test.carport.bean;

/**
 * 描述：组织机构
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午4:40:15
 * @since
 */
public class Organization {

	private String org_id;// 组织机构ID
	private String org_name;// 组织机构名
	private String status;// 状态
	private String apartment_id;// 部门Id

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

	public Organization(String org_id, String org_name, String status,
			String apartment_id) {
		super();
		this.org_id = org_id;
		this.org_name = org_name;
		this.status = status;
		this.apartment_id = apartment_id;
	}

	public Organization() {
		super();
	}

}

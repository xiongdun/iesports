/**
 * 
 */
package com.carport.model;

/**
 * ��������֯����model
 * @author xiongdun
 * @created 2016��12��19�� ����4:44:30
 * @since 
 */
public class OrganizationModel {

	private String org_id;// ��֯����ID
	private String org_name;// ��֯������
	private String status;// ״̬
	private String apartment_id;// ����Id
	private String apartment_name;// ��������
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

/**
 * 
 */
package com.carport.model;

/**
 * ��������λ���Ż���model
 * @author xiongdun
 * @created 2016��12��13�� ����3:49:48
 * @since 
 */
public class CarportModel {
	private String carport_id;// ��λ��id
	private String carport_no;// ��λ��
	private String org_id;// ��֯����id
	private String status;// ״̬
	private String apartment_id;// ����Id
	private String datetime;// ����ʱ��
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
/**
 * 
 */
package com.carport.model;

/**
 * ����������λ��ʷmodel
 * @author xiongdun
 * @created 2016��12��19�� ����4:21:43
 * @since 
 */
public class CarportHistoryModel {
	private String cphis_id;// ��ʷId
	private String user_id;// �û�Id
	private String user_name;//�û�����
	private String apartment_id;// ����Id
	private String apartment_name;// ��������
	private String org_id;// ��֯����Id
	private String org_name;// ��֯��������
	private String carport_no;// ��λ����
	private String status;// ״̬
	private String datetime;// ʱ��
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

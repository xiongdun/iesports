/**
 * 
 */
package com.iesports.test.carport.bean;

/**
 * ��������λ��Ϣ
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����4:40:15
 * @since
 */
public class Carport {

	private String carport_id;// ��λ��id
	private String carport_no;// ��λ��
	private String org_id;// ��֯����id
	private String status;// ״̬
	private String apartment_id;// ����Id
	private String datetime;// ����ʱ��

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

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

	public Carport(String carport_no, String org_id, String status,
			String apartment_id, String datetime) {
		super();
		this.carport_no = carport_no;
		this.org_id = org_id;
		this.status = status;
		this.apartment_id = apartment_id;
		this.datetime = datetime;
	}

	public Carport(String carport_id, String carport_no, String org_id,
			String status, String apartment_id, String datetime) {
		super();
		this.carport_id = carport_id;
		this.carport_no = carport_no;
		this.org_id = org_id;
		this.status = status;
		this.apartment_id = apartment_id;
		this.datetime = datetime;
	}

	public Carport() {
		super();
	}

}

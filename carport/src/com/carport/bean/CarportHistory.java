/**
 * 
 */
package com.carport.bean;

/**
 * ����������λ��ʷ��Ϣ
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����4:40:15
 * @since
 */
public class CarportHistory {

	private String cphis_id;// ��ʷId
	private String user_id;// �û�Id
	private String apartment_id;// �û���
	private String org_id;// ��֯����Id
	private String carport_no;// ��λId
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

	public String getApartment_id() {
		return apartment_id;
	}

	public void setApartment_id(String apartment_id) {
		this.apartment_id = apartment_id;
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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	public CarportHistory(String cphis_id, String user_id, String apartment_id, String org_id, String carport_no,
			String status, String datetime) {
		super();
		this.cphis_id = cphis_id;
		this.user_id = user_id;
		this.apartment_id = apartment_id;
		this.org_id = org_id;
		this.carport_no = carport_no;
		this.status = status;
		this.datetime = datetime;
	}

	public String getCarport_no() {
		return carport_no;
	}

	public void setCarport_no(String carport_no) {
		this.carport_no = carport_no;
	}

	public CarportHistory() {
		super();
	}

}

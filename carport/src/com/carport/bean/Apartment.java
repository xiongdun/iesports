/**
 * 
 */
package com.carport.bean;

/**
 * ������������Ϣ
 * 
 * @author zhangyijie
 * @created 2016��12��2�� ����10:49:45
 * @since
 */
public class Apartment {

	private String apartment_id;// ����Id
	private String apartment_name;// ��������
	private String apartment_no;// ���ű��
	private String status;// ״̬
	private String carport_nums;// ��λ��

	public String getCarport_nums() {
		return carport_nums;
	}

	public void setCarport_nums(String carport_nums) {
		this.carport_nums = carport_nums;
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

	public String getApartment_no() {
		return apartment_no;
	}

	public void setApartment_no(String apartment_no) {
		this.apartment_no = apartment_no;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Apartment(String apartment_id, String apartment_name,
			String apartment_no, String status, String carport_nums) {
		super();
		this.apartment_id = apartment_id;
		this.apartment_name = apartment_name;
		this.apartment_no = apartment_no;
		this.status = status;
		this.carport_nums = carport_nums;
	}

	public Apartment() {
		super();
	}

}

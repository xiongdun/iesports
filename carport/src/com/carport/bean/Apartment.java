/**
 * 
 */
package com.carport.bean;

/**
 * 描述：部门信息
 * 
 * @author zhangyijie
 * @created 2016年12月2日 下午10:49:45
 * @since
 */
public class Apartment {

	private String apartment_id;// 部门Id
	private String apartment_name;// 部门名称
	private String apartment_no;// 部门编号
	private String status;// 状态
	private String carport_nums;// 车位号

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

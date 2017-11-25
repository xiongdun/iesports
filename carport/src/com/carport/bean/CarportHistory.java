/**
 * 
 */
package com.carport.bean;

/**
 * 描述：抢车位历史信息
 * 
 * @author zhangyijie
 * @created 2016年12月1日 下午4:40:15
 * @since
 */
public class CarportHistory {

	private String cphis_id;// 历史Id
	private String user_id;// 用户Id
	private String apartment_id;// 用户名
	private String org_id;// 组织机构Id
	private String carport_no;// 车位Id
	private String status;// 状态
	private String datetime;// 时间

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

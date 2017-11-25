/**
 * 
 */
package com.carport.bean;

/**
 * ������ע���û�
 * 
 * @author zhangyijie
 * @created 2016��12��1�� ����4:40:15
 * @since
 */
public class UserInfo {

	private String user_id; // �û�ID
	private String user_name;// �û���
	private String user_pwd;// �û�����
	private String org_id;// ��֯����ID
	private String car_no;// ���ƺ�
	private String status;// ״̬
	private String reg_time; // ע��ʱ��
	private String job_number;// ����
	private String apartment_id;// ����Id
	private String is_system;
	
	public String getIs_system() {
		return is_system;
	}

	public void setIs_system(String is_system) {
		this.is_system = is_system;
	}

	public String getApartment_id() {
		return apartment_id;
	}

	public void setApartment_id(String apartment_id) {
		this.apartment_id = apartment_id;
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

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
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

	public String getJob_number() {
		return job_number;
	}

	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}

	public String getCar_no() {
		return car_no;
	}

	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public UserInfo(String user_id, String user_name, String user_pwd,
			String org_id, String car_no, String status, String reg_time,
			String job_number) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.org_id = org_id;
		this.car_no = car_no;
		this.status = status;
		this.reg_time = reg_time;
		this.job_number = job_number;
	}

	public UserInfo() {
		super();
	}

}

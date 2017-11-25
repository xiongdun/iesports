/**
 * 
 */
package com.carport.model;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月15日 下午5:03:52
 * @since 
 */
public class UserInfoModel {
	
	private String user_id; // 用户ID
	private String user_name;// 用户名
	private String user_pwd;// 用户密码
	private String org_id;// 组织机构ID
	private String org_name;// 组织机构名称
	private String car_no;// 车牌号
	private String status;// 状态
	private String reg_time; // 注册时间
	private String job_number;// 工号
	private String apartment_id;// 部门Id
	private String apartment_name;// 部门名称
	private String is_system;
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
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getCar_no() {
		return car_no;
	}
	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
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
	public String getIs_system() {
		return is_system;
	}
	public void setIs_system(String is_system) {
		this.is_system = is_system;
	}
	public UserInfoModel(String user_id, String user_name, String user_pwd, String org_id, String org_name,
			String car_no, String status, String reg_time, String job_number, String apartment_id,
			String apartment_name, String is_system) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.org_id = org_id;
		this.org_name = org_name;
		this.car_no = car_no;
		this.status = status;
		this.reg_time = reg_time;
		this.job_number = job_number;
		this.apartment_id = apartment_id;
		this.apartment_name = apartment_name;
		this.is_system = is_system;
	}
	public UserInfoModel(String user_name, String user_pwd, String org_id, String org_name, String car_no,
			String status, String reg_time, String job_number, String apartment_id, String apartment_name,
			String is_system) {
		super();
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.org_id = org_id;
		this.org_name = org_name;
		this.car_no = car_no;
		this.status = status;
		this.reg_time = reg_time;
		this.job_number = job_number;
		this.apartment_id = apartment_id;
		this.apartment_name = apartment_name;
		this.is_system = is_system;
	}
	
	public UserInfoModel() {
		
	}
}

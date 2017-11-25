/**
 * 
 */
package com.iesports.dao.bean;

import java.util.Date;

/**
 * 描述：用户实体类
 * @author xiongdun
 * @created 2016年9月1日 上午9:54:07
 * @since
 */
public class UserInfo {
	private Integer id;// 1用户Id
	private String login_name;// 2登录名
	private String name;// 3用户名
	private String password;// 4用户密码
	private Integer age; // 5年龄
	private String gender;// 6性别
	private Double salary;// 7薪水
	private Date bairthday;//8出生日期
	private String address;// 9地址
	private String work_address;//10工作地址
	private String marital_status;//11婚姻状况
	private String education;//12学历
	private String address_detail;// 13详细地址
	private String phone;// 14电话号码
	private String idno;// 15身份证号码
	private String email;// 16邮箱地址
	private String state;// 17用户状态
	private String create_time; // 18用户创建时间
	private Double login_times;// 19用户登录次数
	private String last_time;// 20用户最后登录时间
	private String modify_time;// 21上一次修改信息时间
	private String is_system;// 22是否为系统管理用户
	private String group_no; // 23所在组编号
	private String group_id; // 24所在组ID
	private String remark;// 25备注
	private String national;//26民族
	private String social_no;//27社交账号
	
	
	
	public String getSocial_no() {
		return social_no;
	}

	public void setSocial_no(String social_no) {
		this.social_no = social_no;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getWork_address() {
		return work_address;
	}

	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getBairthday() {
		return bairthday;
	}

	public void setBairthday(Date bairthday) {
		this.bairthday = bairthday;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Double getLogin_times() {
		return login_times;
	}

	public void setLogin_times(Double login_times) {
		this.login_times = login_times;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getIs_system() {
		return is_system;
	}

	public void setIs_system(String is_system) {
		this.is_system = is_system;
	}

	public String getGroup_no() {
		return group_no;
	}

	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserInfo(String name, String password, Integer age) {
		super();
		this.name = name;
		this.password = password;
		this.age = age;
	}
	
	public UserInfo(Integer id, String login_name, String name,
			String password, Integer age, String gender, Double salary,
			Date bairthday, String address, String work_address,
			String marital_status, String education, String address_detail,
			String phone, String idno, String email, String state,
			String create_time, Double login_times, String last_time,
			String modify_time, String is_system, String group_no,
			String group_id, String remark, String national, String social_no) {
		super();
		this.id = id;
		this.login_name = login_name;
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
		this.bairthday = bairthday;
		this.address = address;
		this.work_address = work_address;
		this.marital_status = marital_status;
		this.education = education;
		this.address_detail = address_detail;
		this.phone = phone;
		this.idno = idno;
		this.email = email;
		this.state = state;
		this.create_time = create_time;
		this.login_times = login_times;
		this.last_time = last_time;
		this.modify_time = modify_time;
		this.is_system = is_system;
		this.group_no = group_no;
		this.group_id = group_id;
		this.remark = remark;
		this.national = national;
		this.social_no = social_no;
	}

	public UserInfo(Integer id, String login_name, String name,
			String password, Integer age, String gender, Double salary,
			Date bairthday, String address, String phone, String idno,
			String email, String national) {
		super();
		this.id = id;
		this.login_name = login_name;
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
		this.bairthday = bairthday;
		this.address = address;
		this.phone = phone;
		this.idno = idno;
		this.email = email;
		this.national = national;
	}

	public UserInfo() {
		super();
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", password="
				+ password + ", age=" + age + ", gender=" + gender
				+ ", salary=" + salary + ", bairthday=" + bairthday
				+ ", address=" + address + ", phone=" + phone + ", idno="
				+ idno + ", email=" + email + "]";
	}

	
}

/**
 * 
 */
package com.iesports.dao.bean;

import java.util.Date;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月9日 下午3:38:29
 * @since 
 */
public class TestInfo {
	private int id;
	private String name;
	private String password;
	private String age;
	private String phone;
	private String sex;
	private String hobby;
	private Date birthday;
	
	
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 在spring mvc 中不需要在页面中指定对象名
	 * 框架会自动匹配标签name 然后寻找该对象中属性的getter 和 setter方法
	 * 没有找到就不会填充值，打印出的值则为Null
	 * 
	 */
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
	public TestInfo(String name, String password, String age, String phone,
			String sex, String hobby) {
		super();
		this.name = name;
		this.password = password;
		this.age = age;
		this.phone = phone;
		this.sex = sex;
		this.hobby = hobby;
	}
	
	
	public TestInfo(int id, String name, String password, String age,
			String phone, String sex, String hobby) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.phone = phone;
		this.sex = sex;
		this.hobby = hobby;
	}
	public TestInfo() {
		super();
	}
	@Override
	public String toString() {
		return "TestInfo [name=" + name + ", password=" + password + ", age="
				+ age + ", phone=" + phone + ", sex=" + sex + ", hobby="
				+ hobby + "]";
	}
	
	
}

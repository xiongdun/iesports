/**
 * 
 */
package com.iesports.dao.bean;

import java.util.Date;

/**
 * ������
 * @author xiongdun
 * @created 2016��10��9�� ����3:38:29
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
	 * ��spring mvc �в���Ҫ��ҳ����ָ��������
	 * ��ܻ��Զ�ƥ���ǩname Ȼ��Ѱ�Ҹö��������Ե�getter �� setter����
	 * û���ҵ��Ͳ������ֵ����ӡ����ֵ��ΪNull
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

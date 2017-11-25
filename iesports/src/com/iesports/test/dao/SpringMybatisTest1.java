/**
 * 
 */
package com.iesports.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iesports.dao.bean.UserInfo;
import com.iesports.dao.mapper.UserInfoMapper;

/**
 * ������spring����mybatis������
 * @author xiongdun
 * @created 2016��10��11�� ����9:36:05
 * @since 
 */

@RunWith(SpringJUnit4ClassRunner.class)// ʹ��spring�Զ�����junit���Կ��
@ContextConfiguration("/spring-mybatis.xml")//����spring�������ļ�
public class SpringMybatisTest1 {
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	public void testInsert() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("�ܶ�");
		userInfo.setLogin_name("dundun");
		userInfo.setEmail("1274328268@qq.com");
		int result = userInfoMapper.insertUserInfo(userInfo);
		System.out.println(result);
	}
	//@Test
	public void testSelectById() {
		UserInfo userInfo = userInfoMapper.selectById("23");
		System.out.println(userInfo);
	}
	@Test
	public void testSelectAll() {
		List<UserInfo> users = userInfoMapper.selectAll();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i));
		}
	}
	//@Test
	public void testUpdateUserInfo() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(20);
		userInfo.setIdno("362226199307181520");
		int result = userInfoMapper.updateUserInfo(userInfo);
		System.out.println(result);
	}
	//@Test
	public void testDeleteUserInfo() {
		int result = userInfoMapper.deleteById("23");
		System.out.println(result);
	}
}

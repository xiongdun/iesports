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
 * 描述：spring整合mybatis测试类
 * @author xiongdun
 * @created 2016年10月11日 上午9:36:05
 * @since 
 */

@RunWith(SpringJUnit4ClassRunner.class)// 使用spring自动整合junit测试框架
@ContextConfiguration("/spring-mybatis.xml")//加载spring的配置文件
public class SpringMybatisTest1 {
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	public void testInsert() {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("熊墩");
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

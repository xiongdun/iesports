/**
 * 
 */
package com.iesports.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iesports.service.UserInfoService;

/**
 * ������
 * @author xiongdun
 * @created 2016��9��1�� ����3:05:25
 * @since 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=("classpath:spring-mybatis.xml"))
public class QueryTest {
	@Resource
	private UserInfoService userInfoService = null;
	@Test
	public void test() { 
		List list = userInfoService.queryAllUserInfo();
		System.out.println(list.size());
	}
}

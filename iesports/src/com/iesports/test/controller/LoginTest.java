/**
 * 
 */
package com.iesports.test.controller;

import java.util.List;

import org.junit.Test;

import com.iesports.service.UserInfoService;
import com.iesports.service.impl.UserInfoServiceImpl;

/**
 * ������
 * @author xiongdun
 * @created 2016��9��1�� ����3:04:51
 * @since 
 */
public class LoginTest {
	@Test
	public void selectAllUserInfoTest() {
		UserInfoService service = new UserInfoServiceImpl();
		List list = service.queryAllUserInfo();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

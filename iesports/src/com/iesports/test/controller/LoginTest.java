/**
 * 
 */
package com.iesports.test.controller;

import java.util.List;

import org.junit.Test;

import com.iesports.service.UserInfoService;
import com.iesports.service.impl.UserInfoServiceImpl;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年9月1日 下午3:04:51
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

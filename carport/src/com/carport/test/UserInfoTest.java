/**
 * 
 */
package com.carport.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.carport.model.UserInfoModel;
import com.carport.service.UserService;
import com.carport.util.BaseSpringTest;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年12月20日 上午10:21:31
 * @since 
 */
public class UserInfoTest extends BaseSpringTest {

	@Autowired
	private UserService service;
	
	@Test
	public void test() {
		PageInfo<UserInfoModel> pages = service.queryUserInfoByPage(1, 10);
		List<UserInfoModel> models = pages.getList();
		for (UserInfoModel userInfoModel : models) {
			System.out.println(userInfoModel.getApartment_name() + userInfoModel.getOrg_name()
			 + userInfoModel.getUser_name());
		}
	}
}

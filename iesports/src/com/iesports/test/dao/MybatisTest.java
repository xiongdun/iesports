/**
 * 
 */
package com.iesports.test.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.iesports.dao.bean.CityInfo;
import com.iesports.dao.bean.CountyInfo;
import com.iesports.dao.bean.TestInfo;
import com.iesports.dao.bean.UserInfo;
import com.iesports.util.MybatisUtil;
import com.iesports.util.security.MD5Util;

/**
 * 描述：Mybatis学习测试类
 * @author xiongdun
 * @created 2016年10月8日 下午4:33:52
 * @since 
 */
public class MybatisTest {
	
	
	//@Test
	public void selectTest() {
		InputStream inputStream = MybatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sessionFactory.openSession();
		
		String statement = "com.iesports.dao.mapper.UserInfoMapper.selectById";
		UserInfo userInfo = session.selectOne(statement, 14);
		session.close();
		System.out.println(userInfo.getName() + userInfo.getAddress());
	}
	
	//@Test
	public void selectTest2() {
		String statement = "com.iesports.dao.mapper.UserInfoMapper.selectById";
		String param = "14";
		UserInfo userInfo = (UserInfo) MybatisUtil.selectOne(statement, param);
		System.out.println(userInfo.getName() + userInfo.getAddress());
	}
	
	//@Test
	public void insertTest1() {
		String statement = "com.iesports.dao.mapper.UserInfoMapper.insertUserInfo";
		UserInfo userInfo = new UserInfo();
		userInfo.setLogin_name("minlu");
		userInfo.setName("闵露");
		userInfo.setEmail("756850212@qq.com");
		userInfo.setIdno("362226199307182759");
		userInfo.setPassword(MD5Util.convertStringToMD5("minlu"));
		userInfo.setPhone("18720955517");
		userInfo.setAddress("江苏省苏州市丽丰购物中心");
		userInfo.setAge(34);
		userInfo.setSalary(5000.0);
		
		int result = MybatisUtil.insert(statement, userInfo);
		System.out.println(result);
	}
	//@Test
	public void inserTest2() {
		TestInfo testInfo = new TestInfo();
		String statement = "com.iesports.dao.mapper.UserInfoMapper.insertTest";
		SqlSessionFactory sessionFactory = MybatisUtil.getSessionFactory();
		SqlSession session = sessionFactory.openSession();
		
		int result = session.insert(statement, testInfo);
		session.close();
		System.out.println(result);
	}
	
	//@Test
	public void updateTest1() {
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword(MD5Util.convertStringToMD5("woaini"));
		userInfo.setId(20);
		String statement = "com.iesports.dao.mapper.UserInfoMapper.updatePassword";
		int result = MybatisUtil.update(statement, userInfo);
		System.out.println(result);//023299564b0db47d5f3e476a254d0c21
								   //023299564b0db47d5f3e476a254d0c21
	}
	
	//@Test
	public void deleteTest1() {
		String statement = "com.iesports.dao.mapper.UserInfoMapper.deleteById";
		int result = MybatisUtil.delete(statement, "14");
		System.out.println(result);
	}
	//@Test
	public void selectAll() {
		String statement = "com.iesports.dao.mapper.UserInfoMapper.selectAll";
		List<Object> lists = MybatisUtil.selectList(statement);
		for (int i = 0; i < lists.size(); i++) {
			UserInfo userInfo = (UserInfo)lists.get(i);
			System.out.println((i + 1) + userInfo.getName() + userInfo.getAddress());
		}
	}
	
	@Test
	public void selectCityInfo() {
		String statement = "com.iesports.dao.mapper.CityInfoMapper.getCity";
		List<Object> list = MybatisUtil.selectList(statement, "2");
		for (int i = 0; i < list.size(); i++) {
			CityInfo cityInfo = (CityInfo) list.get(i);
			System.out.println(cityInfo);
		}
	}
	
}

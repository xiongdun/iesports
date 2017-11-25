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

import com.iesports.dao.bean.TestInfo;
import com.iesports.dao.mapper.TestInfoMapper;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月20日 下午8:08:55
 * @since 
 */
@RunWith(SpringJUnit4ClassRunner.class)// 使用spring自动整合junit测试框架
@ContextConfiguration("/spring-mybatis.xml")//加载spring的配置文件
public class TestInfoTest {
	@Autowired
	private TestInfoMapper testInfoMapper;
	
	@Test
	public void testSelectAll() {
		List<TestInfo> list = testInfoMapper.selectAll();
		
		for (int i = 0; i < list.size(); i++) {
			TestInfo test = list.get(i);
			System.out.println(test);
		}
	}
	
}

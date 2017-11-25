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
 * ������
 * @author xiongdun
 * @created 2016��10��20�� ����8:08:55
 * @since 
 */
@RunWith(SpringJUnit4ClassRunner.class)// ʹ��spring�Զ�����junit���Կ��
@ContextConfiguration("/spring-mybatis.xml")//����spring�������ļ�
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

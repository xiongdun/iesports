/**
 * 
 */
package com.iesports.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.bean.TestInfo;
import com.iesports.dao.mapper.TestInfoMapper;
import com.iesports.service.TestInfoService;

/**
 * 描述：
 * 
 * @author xiongdun
 * @created 2016年9月1日 上午10:00:20
 * @since
 */
@Service
public class TestInfoServiceImpl implements TestInfoService {

	private static Logger logger = Logger.getLogger(TestInfoServiceImpl.class);

	@Autowired
	private TestInfoMapper testInfoMapper;
	
	@Override
	public List<TestInfo> queryAll() {
		logger.info("查询开始！");
		List<TestInfo> list = this.testInfoMapper.selectAll();
		if (list.size() < 1) {
			list = null;
		}
		logger.info("查询结束！");
		return list;
	}

	@Test
	public void test() {
		this.queryAll();
	}
	@Override
	public TestInfo queryTestById(String id) {
		TestInfo test = this.testInfoMapper.selectById(id);
		
		return test;
	}

	@Override
	public int addTestInfo(TestInfo test) {
		if (test == null) {
			return -1;
		}
		int result = this.testInfoMapper.insetTestInfo(test);
		return result;
	}

	@Override
	public int modifyTestInfo(String id, TestInfo test) {
		if (test == null) {
			return -1;
		}
		int result = this.testInfoMapper.updateTestInfo(test);
		return result;
	}

	@Override
	public int deleteTestInfoById(String id) {
		if (id == null) {
			return -1;
		}
		int result = this.testInfoMapper.deleteTestInfoById(id);
		return result;
	}
	
}

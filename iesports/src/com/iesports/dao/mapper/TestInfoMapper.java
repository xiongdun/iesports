/**
 * 
 */
package com.iesports.dao.mapper;

import java.util.List;

import com.iesports.dao.bean.TestInfo;

/**
 * ������������mapperӳ����
 * @author xiongdun
 * @created 2016��10��20�� ����8:50:19
 * @since 
 */
public interface TestInfoMapper {
	
	public List<TestInfo> selectAll();
	
	public TestInfo selectById(String id);
	
	public TestInfo selectByName(String name);
	
	public int insetTestInfo(TestInfo test);
	
	public int updateTestInfo(TestInfo test);
	
	public int deleteTestInfoById(String id);
}

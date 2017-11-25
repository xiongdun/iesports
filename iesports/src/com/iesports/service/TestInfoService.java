/**
 * 
 */
package com.iesports.service;

import java.util.List;

import com.iesports.dao.bean.TestInfo;


/**
 * 描述：
 * @author xiongdun
 * @created 2016年9月1日 上午10:01:33
 * @since 
 */
public interface TestInfoService {
	
	/**
	 * 描述：查询所有
	 * @author xiongdun
	 * @created 2016年10月24日 下午9:01:22
	 * @since 
	 * @return
	 */
	public List<TestInfo> queryAll();
	
	/**
	 * 描述：按Id查询
	 * @author xiongdun
	 * @created 2016年10月24日 下午9:01:36
	 * @since 
	 * @param id
	 * @return
	 */
	public TestInfo queryTestById(String id);
	
	/**
	 * 描述：插入信息
	 * @author xiongdun
	 * @created 2016年10月24日 下午9:01:45
	 * @since 
	 * @param test
	 * @return
	 */
	public int addTestInfo(TestInfo test);
	
	/**
	 * 描述：修改
	 * @author xiongdun
	 * @created 2016年10月24日 下午9:02:01
	 * @since 
	 * @param test
	 * @return
	 */
	public int modifyTestInfo(String id, TestInfo test);
	
	/**
	 * 描述：删除
	 * @author xiongdun
	 * @created 2016年10月24日 下午9:02:44
	 * @since 
	 * @param id
	 * @return
	 */
	public int deleteTestInfoById(String id);
}

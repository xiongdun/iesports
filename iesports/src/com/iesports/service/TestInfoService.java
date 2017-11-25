/**
 * 
 */
package com.iesports.service;

import java.util.List;

import com.iesports.dao.bean.TestInfo;


/**
 * ������
 * @author xiongdun
 * @created 2016��9��1�� ����10:01:33
 * @since 
 */
public interface TestInfoService {
	
	/**
	 * ��������ѯ����
	 * @author xiongdun
	 * @created 2016��10��24�� ����9:01:22
	 * @since 
	 * @return
	 */
	public List<TestInfo> queryAll();
	
	/**
	 * ��������Id��ѯ
	 * @author xiongdun
	 * @created 2016��10��24�� ����9:01:36
	 * @since 
	 * @param id
	 * @return
	 */
	public TestInfo queryTestById(String id);
	
	/**
	 * ������������Ϣ
	 * @author xiongdun
	 * @created 2016��10��24�� ����9:01:45
	 * @since 
	 * @param test
	 * @return
	 */
	public int addTestInfo(TestInfo test);
	
	/**
	 * �������޸�
	 * @author xiongdun
	 * @created 2016��10��24�� ����9:02:01
	 * @since 
	 * @param test
	 * @return
	 */
	public int modifyTestInfo(String id, TestInfo test);
	
	/**
	 * ������ɾ��
	 * @author xiongdun
	 * @created 2016��10��24�� ����9:02:44
	 * @since 
	 * @param id
	 * @return
	 */
	public int deleteTestInfoById(String id);
}

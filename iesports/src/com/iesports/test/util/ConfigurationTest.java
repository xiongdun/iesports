/**
 * 
 */
package com.iesports.test.util;

import org.junit.Test;

import com.iesports.util.config.Configuration;

/**
 * ����������configuration�����configuration.xml�����ļ�����
 * 
 * @author xiongdun
 * @created 2016��9��1�� ����3:04:51
 * @since
 */
public class ConfigurationTest {
	@Test
	public void getConfigContentTest() {
		String value = Configuration.getString("system.sysname");
		System.out.println(value);
	}
}

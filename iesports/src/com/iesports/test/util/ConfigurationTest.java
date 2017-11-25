/**
 * 
 */
package com.iesports.test.util;

import org.junit.Test;

import com.iesports.util.config.Configuration;

/**
 * 描述：测试configuration类读到configuration.xml配置文件内容
 * 
 * @author xiongdun
 * @created 2016年9月1日 下午3:04:51
 * @since
 */
public class ConfigurationTest {
	@Test
	public void getConfigContentTest() {
		String value = Configuration.getString("system.sysname");
		System.out.println(value);
	}
}

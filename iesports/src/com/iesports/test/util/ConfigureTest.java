/**
 * 
 */
package com.iesports.test.util;

import org.junit.Test;

import com.iesports.util.jdbc.connection.Configure;
import com.iesports.util.security.MD5Util;

/**
 * ������Configure.classtest��
 * @author xiongdun
 * @created 2016��9��7�� ����4:57:36
 * @since 
 */
public class ConfigureTest {
	public static void main(String[] args) {
		Configure configure = Configure.getInstance();
		
		//Configure.destoryDataSource();
	}
	//fed865a4f9f9fc3ee42e2c30852f2d9b
	//fed865a4f9f9fc3ee42e2c30852f2d9b
	//02c45ad6a5b40f84f34fa5dcefd1edc8
	@Test
	public void md5Test() {
		String str = "xiongdun1231";
		String md5Str = MD5Util.convertStringToMD5(str);
		System.out.println(md5Str);
	}
	
}



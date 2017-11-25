/**
 * 
 */
package com.carport.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述：spring 测试基类
 * @author xiongdun
 * @created 2016年12月19日 下午7:17:25
 * @since 
 */
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseSpringTest {
	
}

/**
 * 
 */
package com.carport.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ������spring ���Ի���
 * @author xiongdun
 * @created 2016��12��19�� ����7:17:25
 * @since 
 */
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseSpringTest {
	
}

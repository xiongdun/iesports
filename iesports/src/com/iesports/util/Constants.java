/**
 * 
 */
package com.iesports.util;

/**
 * ��������Ŀ�ڳ���������
 * ����һЩ
 * @author xiongdun
 * @created 2016��9��1�� ����10:09:19
 * @since 
 */
public class Constants {
	/**
	 * ������������
	 */
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * ���ݿ����ӵ�ַ
	 */
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/iesports?characterEncoding=utf8";
	/**
	 * ���ݿ��û���
	 */
	public static final String USERNAME = "root";
	/**
	 * ���ݿ�����
	 */
	public static final String PASSWORD = "123456";
	/**
	 * ��ʼ���ӳش�С
	 */
	public static final Integer INITIAL_POOL_SIZE = 10;
	/**
	 * ��С���ӳش�С
	 */
	public static final Integer MIN_POOL_SIZE = 10;
	/**
	 * ������ӳش�С
	 */
	public static final Integer MAX_POOL_SIZE = 100;
	/**
	 * ���ӳ�ʱʱ��
	 */
	public static final Integer CHECKOUT_TIMEOUT = 100000;
	/**
	 * ���״̬˵��
	 */
	public static final Integer MAX_STATEMENTS = 100;
	/**
	 * �������Ӳ�������
	 */
	public static final Integer IDLE_CONNECTION_TEST_PERIOD = 3000;
	/**
	 * �������ֵ
	 */
	public static final Integer ACQUIRE_INCREMENT = 3;
	
	/**
	 * ϵͳ��ʼ������
	 */
	public static final String SYSTEM_CONFIG_SQL = "select * from ";
	
	public static final String county = "";
}

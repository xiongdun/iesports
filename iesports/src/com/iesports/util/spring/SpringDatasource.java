/**
 * 
 */
package com.iesports.util.spring;

import org.apache.log4j.Logger;

/**
 * ��������spring�����õ�����Դ������bean��
 * spring���ص�ʱ���������������Դ��Ȼ��Ϳ������mybatis���г־û�����
 * @author xiongdun
 * @created 2016��9��20�� ����10:45:40
 * @since 
 */
public class SpringDatasource {
	private static Logger logger = Logger.getLogger(SpringDatasource.class);
	
	static {
		// ������������⣬���ֱ�ӽ�������Դ���ӣ����ǲ�û�н��йرգ����Ծͻ��������Դ�ظ�����������
		// �����ͻ�����ڴ�ռ�øߣ��޷���������⣬�����ݲ��������ַ���
		// �����뵽�Ľ�������������ֳ�ʼ�����ӳأ�����������Դ���ӣ�ֻ���ȳ�ʼ��
		// �����Ͳ�������ظ�ռ�õ����⣬������ô������˼������
		// ConnManager.getConnection();
		// logger.info("�������������Դ");
	}
}

/**
 * 
 */
package com.iesports.jms;

import javax.jms.Destination;

/**
 * ������ActiveMQ ��JMS����Ϣ������
 * @author xiongdun
 * @created 2016��10��31�� ����9:05:15
 * @since 
 */
public interface ProducerService {
	
	/**
	 * ��������Ϣ�����߷�����Ϣ�ķ���
	 * @author xiongdun
	 * @created 2016��10��31�� ����9:07:21
	 * @since 
	 * @param destination ��Ϣ���͵�Ŀ�ĵ�
	 * @param message ������Ϣ������
	 */
	public void sendMessage(Destination destination, final String message);
}

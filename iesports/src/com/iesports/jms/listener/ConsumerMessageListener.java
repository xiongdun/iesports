package com.iesports.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

/**
 * ��������������߾�Ҫʵ��һ����Ϣ������,
 *  ����������Ϣ�󣬽�����Ӧ��ҵ����
 *  ÿ��Ŀ�ĵض���һ��MessageListenerContainer��
 *  ����MessageListenerContainer��Ҫ������Ϣ��
 *  Ŀ�ĵ���Ϣ���ͽ����ߵ���Ϣ������
 * @author xiongdun
 * @created 2016��10��31�� ����9:32:36
 * @since
 */
public class ConsumerMessageListener implements MessageListener {

	private static Logger logger = Logger.getLogger(ConsumerMessageListener.class);
	
	@Override
	public void onMessage(Message paramMessage) {
		TextMessage textMessage = (TextMessage) paramMessage;
		try {
			System.out.println("�������ܵ���Ϣ��" + textMessage.getText());
            System.out.println("��ʼ���н���������serviceִ��....");
		} catch (JMSException e) {
			logger.error("��Ϣ�����쳣", e);
		}
	}

}

package com.iesports.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

/**
 * 描述：接收者这边就要实现一个消息监听器,
 *  当监听到消息后，进行相应的业务处理，
 *  每个目的地都有一个MessageListenerContainer，
 *  配置MessageListenerContainer需要链接信息，
 *  目的地信息，和接受者的消息监听器
 * @author xiongdun
 * @created 2016年10月31日 下午9:32:36
 * @since
 */
public class ConsumerMessageListener implements MessageListener {

	private static Logger logger = Logger.getLogger(ConsumerMessageListener.class);
	
	@Override
	public void onMessage(Message paramMessage) {
		TextMessage textMessage = (TextMessage) paramMessage;
		try {
			System.out.println("接收者受到消息：" + textMessage.getText());
            System.out.println("开始进行解析并调用service执行....");
		} catch (JMSException e) {
			logger.error("消息发送异常", e);
		}
	}

}

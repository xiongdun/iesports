package com.iesports.jms.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.iesports.jms.ProducerService;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月31日 下午9:09:14
 * @since 
 */
@Service
public class ProducerServiceImpl implements ProducerService {

	@Resource
	private JmsTemplate jmsTemplate;
	
	@Override
	public void sendMessage(Destination destination, final String message) {
		jmsTemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session paramSession) throws JMSException {
				return paramSession.createTextMessage(message);
			}
		});
	}

}
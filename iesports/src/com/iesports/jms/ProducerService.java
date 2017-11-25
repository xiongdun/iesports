/**
 * 
 */
package com.iesports.jms;

import javax.jms.Destination;

/**
 * 描述：ActiveMQ 的JMS的消息发送者
 * @author xiongdun
 * @created 2016年10月31日 下午9:05:15
 * @since 
 */
public interface ProducerService {
	
	/**
	 * 描述：消息生产者发送消息的方法
	 * @author xiongdun
	 * @created 2016年10月31日 下午9:07:21
	 * @since 
	 * @param destination 消息发送的目的地
	 * @param message 发送消息的内容
	 */
	public void sendMessage(Destination destination, final String message);
}

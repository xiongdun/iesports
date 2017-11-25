/**
 * 
 */
package com.iesports.util.net;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

/**
 * 描述：udp用户数据包协议帮助工具类
 * @author xiongdun
 * @created 2016年10月19日 下午4:33:27
 * @since 
 */
public class UDPUtil {
	private static Logger logger = Logger.getLogger(UDPUtil.class);
	
	public static void getUdpConnection(int port) {
		try {
			DatagramSocket ds = new DatagramSocket(port);
		} catch (SocketException e) {
			logger.error("获取UDP连接失败！", e);
		}
		
	}
	
	public static void getUdpConnection() {
		try {
			DatagramSocket ds = new DatagramSocket();
		} catch (SocketException e) {
			logger.error("获取UDP连接失败！", e);
		}
	}
}

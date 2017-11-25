/**
 * 
 */
package com.iesports.util.net;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;

/**
 * ������udp�û����ݰ�Э�����������
 * @author xiongdun
 * @created 2016��10��19�� ����4:33:27
 * @since 
 */
public class UDPUtil {
	private static Logger logger = Logger.getLogger(UDPUtil.class);
	
	public static void getUdpConnection(int port) {
		try {
			DatagramSocket ds = new DatagramSocket(port);
		} catch (SocketException e) {
			logger.error("��ȡUDP����ʧ�ܣ�", e);
		}
		
	}
	
	public static void getUdpConnection() {
		try {
			DatagramSocket ds = new DatagramSocket();
		} catch (SocketException e) {
			logger.error("��ȡUDP����ʧ�ܣ�", e);
		}
	}
}

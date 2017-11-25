/**
 * 
 */
package com.iesports.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * ������socketͨѶ����������
 * @author xiongdun
 * @created 2016��10��19�� ����4:43:22
 * @since 
 */
public class SocketUtil {
	private static Logger logger = Logger.getLogger(SocketUtil.class);
	
	/**
	 * ���������������socket����
	 * @author xiongdun
	 * @created 2016��10��19�� ����5:04:48
	 * @since 
	 * @param port �˿ں�
	 */
	public static void getServerSocket(String ip,int port, String data) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			// ��������
			serverSocket = new ServerSocket(port);
			// �������
			socket = serverSocket.accept();
			// ���ܿͻ��˷�������
			is = socket.getInputStream();
			byte[] bt = new byte[1024];
			int n = is.read(bt);
			// ���ؽ��յ�����
			logger.info("socket���ܵ������ݣ�" + new String(bt, 0, n));
			os = socket.getOutputStream();
			os.write(bt, 0, n);
		} catch (IOException e) {
			logger.error("����socketͨѶ����ʧ�ܣ�", e);
		} finally {
			try {
				// �ر�socket���Ӻ���
				os.close();
				is.close();
				socket.close();
				serverSocket.close();
			} catch (Exception e2) {
				logger.error("�ر�socketͨѶ����ʧ�ܣ�", e2);
			}
		}
		
	}
	
	/**
	 * �����������ͻ���socket����
	 * @author xiongdun
	 * @created 2016��10��19�� ����5:05:20
	 * @since 
	 * @param address �������˵�ַ
	 */
	public static void getClientSocket(String address) {
		getClientSocket(address, 80);
	}
	
	/**
	 * �����������ͻ���socket����
	 * @author xiongdun
	 * @created 2016��10��19�� ����5:05:45
	 * @since 
	 * @param address �������˵�ַ
	 * @param timeout ���õ����ӳ�ʱʱ��
	 */
	public static void getClientSocket(String address, int timeout) {
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			// ���������˵�socket����
			socket = new Socket(address, timeout);
			// ��ȡ�����
			os = socket.getOutputStream();
			// ��ȡ������
			is = socket.getInputStream();
		} catch (UnknownHostException e) {
			logger.error("����socketͨѶ����ʧ�ܣ�", e);
		} catch (IOException e) {
			logger.error("����socketͨѶ����ʧ�ܣ�", e);
		} finally {
			try {
				// �ر���������
				is.close();
				os.close();
				socket.close();
			} catch (IOException e) {
				logger.error("�ر�socketͨѶ����ʧ�ܣ�", e);
			}
		}
		
	}
}

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
 * 描述：socket通讯帮助工具类
 * @author xiongdun
 * @created 2016年10月19日 下午4:43:22
 * @since 
 */
public class SocketUtil {
	private static Logger logger = Logger.getLogger(SocketUtil.class);
	
	/**
	 * 描述：建立服务端socket连接
	 * @author xiongdun
	 * @created 2016年10月19日 下午5:04:48
	 * @since 
	 * @param port 端口号
	 */
	public static void getServerSocket(String ip,int port, String data) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			// 建立连接
			serverSocket = new ServerSocket(port);
			// 获得链接
			socket = serverSocket.accept();
			// 接受客户端发送内容
			is = socket.getInputStream();
			byte[] bt = new byte[1024];
			int n = is.read(bt);
			// 返回接收的数据
			logger.info("socket接受到的数据：" + new String(bt, 0, n));
			os = socket.getOutputStream();
			os.write(bt, 0, n);
		} catch (IOException e) {
			logger.error("建立socket通讯连接失败！", e);
		} finally {
			try {
				// 关闭socket连接和流
				os.close();
				is.close();
				socket.close();
				serverSocket.close();
			} catch (Exception e2) {
				logger.error("关闭socket通讯连接失败！", e2);
			}
		}
		
	}
	
	/**
	 * 描述：建立客户端socket连接
	 * @author xiongdun
	 * @created 2016年10月19日 下午5:05:20
	 * @since 
	 * @param address 请求服务端地址
	 */
	public static void getClientSocket(String address) {
		getClientSocket(address, 80);
	}
	
	/**
	 * 描述：建立客户端socket连接
	 * @author xiongdun
	 * @created 2016年10月19日 下午5:05:45
	 * @since 
	 * @param address 请求服务端地址
	 * @param timeout 设置的连接超时时间
	 */
	public static void getClientSocket(String address, int timeout) {
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			// 建立与服务端的socket连接
			socket = new Socket(address, timeout);
			// 获取输出流
			os = socket.getOutputStream();
			// 获取输入流
			is = socket.getInputStream();
		} catch (UnknownHostException e) {
			logger.error("建立socket通讯连接失败！", e);
		} catch (IOException e) {
			logger.error("建立socket通讯连接失败！", e);
		} finally {
			try {
				// 关闭流和连接
				is.close();
				os.close();
				socket.close();
			} catch (IOException e) {
				logger.error("关闭socket通讯连接失败！", e);
			}
		}
		
	}
}

/**
 * 
 */
package com.iesports.util.remote;

import java.rmi.Remote;

/**
 * ������
 * @author xiongdun
 * @created 2016��11��9�� ����4:58:29
 * @since 
 */
public interface WareHouse extends Remote {
	double getPrice(String description) throws RuntimeException;
}

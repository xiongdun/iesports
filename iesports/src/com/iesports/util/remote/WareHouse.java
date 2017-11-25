/**
 * 
 */
package com.iesports.util.remote;

import java.rmi.Remote;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年11月9日 下午4:58:29
 * @since 
 */
public interface WareHouse extends Remote {
	double getPrice(String description) throws RuntimeException;
}

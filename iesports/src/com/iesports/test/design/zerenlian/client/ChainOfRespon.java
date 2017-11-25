/**
 * 
 */
package com.iesports.test.design.zerenlian.client;

import java.util.Random;

import com.iesports.test.design.zerenlian.PriceHandler;
import com.iesports.test.design.zerenlian.PriceHandlerFactory;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月27日 下午8:05:52
 * @since 
 */
public class ChainOfRespon {

	private PriceHandler priceHandler;
	
	
	public void setPriceHandler(PriceHandler priceHandler) {
		this.priceHandler = priceHandler;
	}
	public void requestDiscount(float discount) {
		priceHandler.processDiscount(discount);
	}
	/**
	 * 描述：客户，需要申请一些折扣
	 * @author xiongdun
	 * @created 2016年10月27日 下午8:05:55
	 * @since 
	 * @param args
	 */
	public static void main(String[] args) {
		ChainOfRespon cor = new ChainOfRespon();
		cor.setPriceHandler(PriceHandlerFactory.createPriceHandler());
		
		Random random = new Random();
		
		for (int i = 0; i <= 100; i++) {
			System.out.print(i + ":");
			cor.requestDiscount(random.nextFloat());
		}
	}

}

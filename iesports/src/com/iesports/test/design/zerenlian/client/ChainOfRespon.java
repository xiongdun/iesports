/**
 * 
 */
package com.iesports.test.design.zerenlian.client;

import java.util.Random;

import com.iesports.test.design.zerenlian.PriceHandler;
import com.iesports.test.design.zerenlian.PriceHandlerFactory;

/**
 * ������
 * @author xiongdun
 * @created 2016��10��27�� ����8:05:52
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
	 * �������ͻ�����Ҫ����һЩ�ۿ�
	 * @author xiongdun
	 * @created 2016��10��27�� ����8:05:55
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

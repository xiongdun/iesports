/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * ������
 * @author xiongdun
 * @created 2016��10��27�� ����8:47:47
 * @since 
 */
public class Director extends PriceHandler {

	/* (non-Javadoc)
	 * @see com.iesports.test.design.zerenlian.PriceHandler#processDiscount(float)
	 */
	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.3) {
			System.out.format("%s��׼���ۿۣ�%.2f%n", this.getClass().getName(), discount);
		} else {
			successor.processDiscount(discount);
		}
	}

}

/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * ������С�鳤
 * @author xiongdun
 * @created 2016��10��27�� ����8:35:09
 * @since 
 */
public class Lead extends PriceHandler {

	/* (non-Javadoc)
	 * @see com.iesports.test.design.zerenlian.PriceHandler#processDiscount(float)
	 */
	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.15) {
			System.out.format("%s��׼���ۿۣ�%.2f%n", this.getClass().getName(), discount);
		} else {
			successor.processDiscount(discount);
		}
	}

}

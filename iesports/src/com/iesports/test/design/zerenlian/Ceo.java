/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * ��������ͨ������Ա
 * @author xiongdun
 * @created 2016��10��27�� ����8:35:09
 * @since 
 */
public class Ceo extends PriceHandler {

	/* (non-Javadoc)
	 * @see com.iesports.test.design.zerenlian.PriceHandler#processDiscount(float)
	 */
	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.55) {
			System.out.format("%s��׼���ۿۣ�%.2f%n", this.getClass().getName(), discount);
		} else {
			System.out.format("%s�ܾ����ۿۣ�%.2f%n", this.getClass().getName(), discount);
		}
	}

}

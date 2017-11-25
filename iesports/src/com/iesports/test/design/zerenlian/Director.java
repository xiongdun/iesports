/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月27日 下午8:47:47
 * @since 
 */
public class Director extends PriceHandler {

	/* (non-Javadoc)
	 * @see com.iesports.test.design.zerenlian.PriceHandler#processDiscount(float)
	 */
	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.3) {
			System.out.format("%s批准了折扣：%.2f%n", this.getClass().getName(), discount);
		} else {
			successor.processDiscount(discount);
		}
	}

}

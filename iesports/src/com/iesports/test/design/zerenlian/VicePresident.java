/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * 描述：普通销售人员
 * @author xiongdun
 * @created 2016年10月27日 下午8:35:09
 * @since 
 */
public class VicePresident extends PriceHandler {

	/* (non-Javadoc)
	 * @see com.iesports.test.design.zerenlian.PriceHandler#processDiscount(float)
	 */
	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.40) {
			System.out.format("%s批准了折扣：%.2f%n", this.getClass().getName(), discount);
		} else {
			successor.processDiscount(discount);
		}
	}

}

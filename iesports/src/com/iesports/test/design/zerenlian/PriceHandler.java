/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * 描述：价格处理，负责处理客户折扣申请
 * @author xiongdun
 * @created 2016年10月27日 下午8:29:19
 * @since 
 */
public abstract class PriceHandler {

	/**
	 * 直接后继， 用于传递请求
	 */
	protected PriceHandler successor;
	
	public void setSuccessor(PriceHandler successor) {
		this.successor = successor;
	}
	
	 /**
	  * 描述：处理折扣申请
	  * @author xiongdun
	  * @created 2016年10月27日 下午8:33:41
	  * @since 
	  * @param discount
	  */
	public abstract void processDiscount(float discount);

	
}

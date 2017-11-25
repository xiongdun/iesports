/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * �������۸���������ͻ��ۿ�����
 * @author xiongdun
 * @created 2016��10��27�� ����8:29:19
 * @since 
 */
public abstract class PriceHandler {

	/**
	 * ֱ�Ӻ�̣� ���ڴ�������
	 */
	protected PriceHandler successor;
	
	public void setSuccessor(PriceHandler successor) {
		this.successor = successor;
	}
	
	 /**
	  * �����������ۿ�����
	  * @author xiongdun
	  * @created 2016��10��27�� ����8:33:41
	  * @since 
	  * @param discount
	  */
	public abstract void processDiscount(float discount);

	
}

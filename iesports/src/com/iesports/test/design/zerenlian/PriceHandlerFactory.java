/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * 描述：
 * @author xiongdun
 * @created 2016年10月27日 下午9:24:30
 * @since 
 */
public class PriceHandlerFactory {
	
	public static PriceHandler createPriceHandler() {
		PriceHandler sales = new Sales();
		PriceHandler lead = new Lead();
		PriceHandler man = new Manager();
		PriceHandler dir = new Director();
		PriceHandler vp = new VicePresident();
		PriceHandler ceo = new Ceo();
		
		sales.setSuccessor(lead);
		lead.setSuccessor(man);
		man.setSuccessor(dir);
		dir.setSuccessor(vp);
		vp.setSuccessor(ceo);
		return sales;
	}
}

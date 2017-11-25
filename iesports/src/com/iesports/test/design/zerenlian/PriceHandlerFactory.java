/**
 * 
 */
package com.iesports.test.design.zerenlian;

/**
 * ������
 * @author xiongdun
 * @created 2016��10��27�� ����9:24:30
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

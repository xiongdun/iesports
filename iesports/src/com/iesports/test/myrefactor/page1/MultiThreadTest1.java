/**
 * 
 */
package com.iesports.test.myrefactor.page1;

/**
 * 描述：
 * @author xiongdun
 * @created 2017年1月15日 下午9:08:56
 * @since 
 */
public class MultiThreadTest1 {

	public static void main(String[] args) {
		//final
		
		System.out.println(Thread.currentThread().getName());
		for (int i = 0; i < 10; i++) {
			new Thread("" + i) {
				public void run() {
					System.out.println("Thread's name is : " + getName() + " running now!");
				}
			}.start();
		}
	}
}

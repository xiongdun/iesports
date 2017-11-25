/**
 * 
 */
package com.iesports.test.myrefactor.page1;

/**
 * 描述：
 * @author xiongdun
 * @created 2017年1月15日 下午7:09:00
 * @since 
 */
public class Practice {
	
	public static void main(String[] args) {
		System.out.print(new InnerB().getValue());
	}
	static class InnerA {
		protected int value;
		public InnerA(int v) {
			setValue(v);
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		public int getValue() {
			try {
				value++;
				return value;
			} finally {
				this.setValue(value);
				System.out.print(value);
			}
		}
	}
	static class InnerB extends InnerA {
		public InnerB() {
			super(5);
			setValue(getValue() - 3);
		}
		
		public void setValue(int value) {
			super.setValue(2 * value);
		}
	}
}

package com.iesports.test.myrefactor.page2;

public class TestString {

	public static void main(String[] args) {
		//str1();
		str2();
	}
	
	
	private static void str2() {
		Boolean flag = true;
		if (flag = true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	
	private static void str1() {
		//String str;
		//System.out.println(str);
		// 没有初始化变量不能通过编译
		// Object object = new Object();
		//object.
		Object o=new Object(){
            public boolean equals(Object obj){
                return true;
            }
        };
        System.out.println(o.equals("Fred"));
	}
	
	
}

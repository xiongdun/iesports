package com.iesports.test.myrefactor.page1;

class BaseTest {
	BaseTest() {
		System.out.println("BASE");
	}
	
	
	
	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		int i2 = 1;
		System.out.println(i2 == i1);
		String str1 = new String("hello world");
		String str2 = "hello";
		String str3 = "world";
		System.out.println((str2 + str3) == str1);
	}
}


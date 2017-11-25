package com.iesports.test.myrefactor.page1;

public class Alpla extends BaseTest {
	
	Alpla() {
		System.out.println("this");
	}
	
	final int i1 = 2;
	
	private String name;
	private static void getPassoword() {
	}
	
	protected static int i = 1000;
	
	public static void main(String[] args) {
		// System.out.println(isAdmin("ADmin"));
		// String 中文 = "中文我是的好司机";
		// System.out.println(中文);
		
		System.out.println(Math.log10(4));
		
		
		// System.out.println(foo(5));
	}
	private static int foo(int n) {
		if (n < 2) {
			return n;
		}
		return foo(n - 1) + foo(n - 2);
	}
	
	public static boolean isAdmin(String username) {
		return username.toLowerCase() == "admin";
	}
	
}

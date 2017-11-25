package com.iesports.test.myrefactor.page1;

public class Alpla2 {
	
	private static int i = 100;
	
	public static void main(String[] args) {
		Alpla alpla = new Alpla();
		
		System.out.println(++Alpla.i);
		Alpla2 alpla2 = new Alpla2();
		alpla2.i++;
		System.out.println(i);
	}
}
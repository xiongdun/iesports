/**
 * 
 */
package com.iesports.test.myrefactor.page1;

/**
 * ������
 * @author xiongdun
 * @created 2017��1��14�� ����10:53:36
 * @since 
 */
public class TestClass1 {

	private String name;
	private String age;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAge(String age) {
		this.age = age;
	}

	public static void main(String[] args) {
		
		int j = 0;
		j = + ++j;
		
		System.out.println(new TestClass1().fucb(10));
		
	}
	
	public int func(int v) {
		int vt = v;
        // ����4�н�һ����תΪһ��������ͬ��ż�Ե�4bit(��Χ��0-15)������  ����vΪ21��4�м����vΪ4��21��4�Ķ����Ʊ�ʾ1�ĸ�������3��
        v ^= v >> 16;
        v ^= v >> 8;
        v ^= v >> 4;
        v &= 0xf;
        //0x6996 �൱��һ�ű������ܱ�ʾ0-15��16�����ֵĶ����Ʊ�ʾ�У�Ϊ1�ĸ�������ż�ԡ�
        int t = (0x6996 >> v) & 1;
        //System.out.println(vt + "\t" + v + "\t" + t);
        return t;
	}
	
	public int fucb(int v) {
        int t = 1 << v;
        int val = 0;
        for (int i = 0; i < t; i++) {
            val += func(i);
        }
        return val;
    }
}

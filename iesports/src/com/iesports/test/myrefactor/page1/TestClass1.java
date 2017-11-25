/**
 * 
 */
package com.iesports.test.myrefactor.page1;

/**
 * 描述：
 * @author xiongdun
 * @created 2017年1月14日 上午10:53:36
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
        // 以下4行将一个数转为一个具有相同奇偶性的4bit(范围是0-15)的数。  比如v为21，4行计算后v为4，21和4的二进制表示1的个数都是3。
        v ^= v >> 16;
        v ^= v >> 8;
        v ^= v >> 4;
        v &= 0xf;
        //0x6996 相当于一张表，正好能表示0-15这16个数字的二进制表示中，为1的个数的奇偶性。
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

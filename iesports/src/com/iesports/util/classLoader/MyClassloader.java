/**
 * 
 */
package com.iesports.util.classLoader;

/**
 * �������Զ����������
 * 
 * java������������������Զ����������
 * 1.ϵͳ�������
 * 2.��չ�������
 * 3.bootstrap
 * @author xiongdun
 * @created 2016��11��8�� ����5:02:12
 * @since 
 */
public class MyClassloader extends ClassLoader{
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
}

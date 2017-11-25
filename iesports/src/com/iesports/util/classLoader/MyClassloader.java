/**
 * 
 */
package com.iesports.util.classLoader;

/**
 * 描述：自定义类加载器
 * 
 * java中有三大类加载器和自定义类加载器
 * 1.系统类加载器
 * 2.拓展类加载器
 * 3.bootstrap
 * @author xiongdun
 * @created 2016年11月8日 下午5:02:12
 * @since 
 */
public class MyClassloader extends ClassLoader{
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
}

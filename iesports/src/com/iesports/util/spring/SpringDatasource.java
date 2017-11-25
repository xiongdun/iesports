/**
 * 
 */
package com.iesports.util.spring;

import org.apache.log4j.Logger;

/**
 * 描述：供spring中配置的数据源，放在bean中
 * spring加载的时候就立即加载数据源，然后就可以配合mybatis进行持久化处理
 * @author xiongdun
 * @created 2016年9月20日 上午10:45:40
 * @since 
 */
public class SpringDatasource {
	private static Logger logger = Logger.getLogger(SpringDatasource.class);
	
	static {
		// 这里出现了问题，如果直接进行数据源连接，但是并没有进行关闭，所以就会造成数据源重复开启的问题
		// 这样就会造成内存占用高，无法清楚的问题，所以暂不适用这种方法
		// 初步想到的解决方案，就是现初始化连接池，不进行数据源连接，只是先初始化
		// 这样就不会造成重复占用的问题，但是怎么处理还在思考当中
		// ConnManager.getConnection();
		// logger.info("在这里加载数据源");
	}
}

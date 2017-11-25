/**
 * 
 */
package com.iesports.util.jdbc.connection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.iesports.util.StringUtil;
import com.iesports.util.config.Configuration;
import com.mchange.v2.c3p0.DataSources;

/**
 * 描述：获取数据源配置信息
 * @author xiongdun
 * @created 2016年9月7日 上午10:38:39
 * @since 
 */
public final class Configure {
	
	private static Logger logger = Logger.getLogger(Configure.class);
	
	/**
	 * 从DataSource.class 中获取dataMap键值对集合
	 */
	private static HashMap dataMap = com.iesports.util.config.DataSource.dataMap;
	
	private static Configure instance = new Configure();
	
	private static HashMap dbConnXmlMap = new HashMap();
	
	/**
	 * 数据源对象集合
	 */
	public static HashMap datasourceMap = new HashMap();
	
	/**
	 * 默认的数据源id
	 */
	private static String _defaultDB = Configuration.getString("datasource.dbName", "db");
	
	static {
		loadConfig();
		logger.info("this is Configure.class,hello everybody" + _defaultDB);
	}
	
	/**
	 * 描述：对数据源进行处理
	 * @author xiongdun
	 * @created 2016年9月7日 上午11:44:20
	 * @since
	 */
	private static void loadConfig() {
		try {
			 DataSource dataSource = buildDataSource(dataMap);
			 if (dataSource != null) {
				 datasourceMap.put(_defaultDB, dataSource);
			 }
		} catch (Exception e) {
			logger.error("数据源信息获取异常！", e);
		}
	}
	
	/**
	 * 私有化构造方法
	 */
	private Configure() {
		
	}
	
	/**
	 * 
	 * 描述：创建数据源实例对象
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:43:54
	 * @since 
	 * @return
	 */
	public static Configure getInstance() {
		return instance;
	}
	
	/**
	 * 销毁数据源
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 上午10:51:00
	 * @since
	 */
	public static void destoryDataSource() {
		
		try {
			for (Iterator iterator = datasourceMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				DataSource dataSource = (DataSource) datasourceMap.get(key);
				// c3p0连接池销毁
				DataSources.destroy(dataSource);
			}
		} catch (Exception e) {
			logger.error("销毁数据源出错！", e);
		}
	}
	
	/**
	 * 描述：创建c3p0数据源连接池
	 * @author xiongdun
	 * @created 2016年9月7日 下午12:00:12
	 * @since 
	 * @param dataMap
	 * @return
	 */
	private static DataSource buildDataSource(HashMap dataMap) {
		// 获取数据库连接值
		String driver  = (String) dataMap.get("driver");
		String url = (String) dataMap.get("url");
		String username = (String) dataMap.get("username");
		String password = (String) dataMap.get("password");
		// 将已经获取到的键值移除
		dataMap.remove("driver");
		dataMap.remove("url");
		dataMap.remove("username");
		dataMap.remove("password");
		try {
			// 加载数据源连接驱动
			Class.forName(driver);
			// 进行c3p0数据库连接池配置
			DataSource unpooled = DataSources.unpooledDataSource(url, username, password);
			DataSource pooled = DataSources.pooledDataSource(unpooled, dataMap);
			//测试连接是否成功
			connectToDB(pooled);
			return pooled;
		} catch (Exception e) {
			logger.error("创建数据源出错！", e);
		}
		return null;
	}
	
	/**
	 * 描述：连接数据库
	 * @author xiongdun
	 * @created 2016年9月7日 下午1:57:59
	 * @since 
	 * @param dataSource
	 */
	private static void connectToDB(DataSource dataSource) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			//logger.info("数据源连接成功！");
		} catch (Exception e) {
			logger.error("从数据源获取连接出错！", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				logger.error("从数据源获取连接出错！", ex);
			}
		}
	}
	
	/**
	 * 获取缺省数据源
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午4:38:03
	 * @since 
	 * @return
	 */
	public DataSource getDataSource() {
		// 若只有一个数据源，则直接返回该数据源
		if (datasourceMap.size() == 1) {
			Object[] datasourceArray = datasourceMap.values().toArray();
			return (DataSource) datasourceArray[0];
		}
		
		// 若有多个数据源，则查找缺省的数据源
		if (StringUtil.isEmpty(_defaultDB)) {
			return null;
		}
		
		return getDataSource(_defaultDB);
	}
	
	/**
	 * 根据配置文件中的数据源，得到对应的数据源对象
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午4:42:34
	 * @since 
	 * @param id
	 * @return
	 */
	public DataSource getDataSource(String id) {
		return (DataSource) datasourceMap.get(id);
	}
	
	/**
	 * 判断指定的数据源ID是否存在
	 * 描述：
	 * @author xiongdun
	 * @created 2016年9月7日 下午4:47:01
	 * @since 
	 * @param id
	 * @return
	 */
	public static boolean isExistDataSource(String id) {
		return datasourceMap.containsKey(id);
	}
	
	/**
	 * 根据datasource.xml文件中配置的数据源ID，得到对应的数据源的xml配置
	 * 
	 * @param id
	 * @return
	 */
	public HashMap getDbConnXmlMap(String id) {
		if (StringUtil.isBlank(id)) {
			id = _defaultDB;
		}
		if (StringUtil.isBlank(id)) {
			return null;
		} else {
			return (HashMap) datasourceMap.get(id);
		}
	}
}

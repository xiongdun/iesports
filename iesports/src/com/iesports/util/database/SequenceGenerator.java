/**
 * 
 */
package com.iesports.util.database;

/**
 * 描述：序列生成器类
 * @author xiongdun
 * @created 2016年9月19日 上午10:15:07
 * @since 
 */
public class SequenceGenerator {

	public static int getSequenceId(String tableName) {
		String sql = "select max(id)+1 from '" + tableName + "' where 1=1";
		int result = 1;
		return result;
	}
}

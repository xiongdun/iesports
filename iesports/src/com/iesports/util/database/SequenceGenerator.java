/**
 * 
 */
package com.iesports.util.database;

/**
 * ������������������
 * @author xiongdun
 * @created 2016��9��19�� ����10:15:07
 * @since 
 */
public class SequenceGenerator {

	public static int getSequenceId(String tableName) {
		String sql = "select max(id)+1 from '" + tableName + "' where 1=1";
		int result = 1;
		return result;
	}
}

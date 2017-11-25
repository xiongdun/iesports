/**
 * 
 */
package com.iesports.test.dao;

import java.util.List;

import org.junit.Test;

import com.iesports.util.DataList;
import com.iesports.util.jdbc.JdbcTemplate;

/**
 * 描述：查询测试
 * @author xiongdun
 * @created 2016年9月1日 下午3:05:07
 * @since 
 */
public class SelectTest {
	private static JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	//@Test
	public void selectAllUserInfoTest() {
		
		String name = "%xi%";
		String id = "1";
		String sql = "select * from t_user_info where user_password like ?";
		List list = jdbcTemplate.query(sql, new Object[] { name } );
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	//@Test
	public void updateUserInfo() {
		String name = "熊墩";
		//name = TransCodingUtil.toUTF_8(str);
		String id = "8";
		String sql = "update t_user_info set user_name=? where user_id=?";
		int rt = jdbcTemplate.udpate(sql, new Object[]{name,id});
		System.out.println(rt);
	}
	//@Test
	public void deleteUserInfo() {
		String id = "10";
		int rt = jdbcTemplate.delete("t_user_info", "user_id", id);
		System.out.println(rt);
	}
	
	
	public static void main(String[] args) {
		String sql = "select * from t_user_info";
		List list = jdbcTemplate.query(sql);
		System.out.println(list.size());
	}
	
	@Test
	public void insertUserInfo() {
		DataList data = new DataList();
		data.set("user_name", "骏绎");
		data.set("user_password", "xiongjunyi");
		data.set("user_age", "10");
		data.set("user_salary", "9000");
		data.set("user_address", "江西省宜春市奉新县");
		data.set("user_phone", "15728002451");
		data.set("user_idno", "36003219940220039x");
		
		int rt = jdbcTemplate.insert("t_user_info", data);
		System.out.println(rt);
	}
	
}

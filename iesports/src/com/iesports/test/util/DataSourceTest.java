/**
 * 
 */
package com.iesports.test.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iesports.dao.bean.UserInfo;

/**
 * 描述：数据源连接测试类
 * @author xiongdun
 * @created 2016年9月2日 下午5:21:49
 * @since 
 */
public class DataSourceTest {
	public static void main(String[] args) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		Connection conn = null;
		//conn = DataSource.getConnection();
		sql = "select * from t_user_info where 1=1";
		try {
			pst = conn.prepareStatement(sql);
			System.out.println("数据库操作对象已经被实例化！");
			rs = pst.executeQuery();
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(Integer.valueOf(rs.getString("user_id")));
				userInfo.setName(rs.getString("user_name"));
				System.out.println(userInfo.getId() + "," + userInfo.getName());
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

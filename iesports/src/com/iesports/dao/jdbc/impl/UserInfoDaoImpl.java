/**
 * 
 */
package com.iesports.dao.jdbc.impl;

import java.util.List;

import com.iesports.dao.bean.UserInfo;
import com.iesports.dao.jdbc.UserInfoDao;
import com.iesports.util.jdbc.JdbcTemplate;

/**
 * ������
 * @author xiongdun
 * @created 2016��10��12�� ����8:41:23
 * @since 
 */
public class UserInfoDaoImpl implements UserInfoDao {

	private static JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	@Override
	public List<UserInfo> selectAllUserInfo() {
		String sql = "select * from t_user_info where 1=1";
		List<UserInfo> list = jdbcTemplate.query(sql);
		
		return list;
	}

	@Override
	public UserInfo selectUserInfoById(String id) {
		return null;
	}

}

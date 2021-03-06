package com.key.common.base.dao;

import com.key.common.base.entity.User;
import com.key.common.dao.BaseDaoImpl;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

	@Override
	public void resetUserGroup(String groupId) {
		String sql="UPDATE t_user SET user_group_id = '' WHERE id = id";
		this.getSession().createSQLQuery(sql).executeUpdate();
	}
	
}

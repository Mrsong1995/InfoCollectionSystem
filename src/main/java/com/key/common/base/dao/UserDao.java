package com.key.common.base.dao;


import com.key.common.base.entity.User;
import com.key.common.dao.BaseDao;



public interface UserDao extends BaseDao<User, String> {

	void resetUserGroup(String groupId);

}

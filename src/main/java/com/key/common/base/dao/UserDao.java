package com.key.common.base.dao;


import com.key.common.base.entity.User;
import com.key.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


/**
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */

//@Mapper
//@Repository
//public interface UserDao extends BaseMapper<User>{
//
//	@Update("UPDATE t_user SET user_group_id = '' WHERE id = id")
//	void resetUserGroup(String groupId);
//}
public interface UserDao extends BaseDao<User, String> {

	public void resetUserGroup(String groupId);

}

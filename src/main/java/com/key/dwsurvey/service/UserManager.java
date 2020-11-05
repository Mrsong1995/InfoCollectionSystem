package com.key.dwsurvey.service;

import com.key.common.base.entity.User;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.plugs.page.Page;
import com.key.common.service.BaseService;


public interface UserManager{//extends BaseService<User, String> {

	public void adminSave(User entity, String[] userRoleIds);

	public void adminSave1(User entity, String[] userRoleIds);

//	public Page<User> findPage(Page<User> page, User entity);

	public Page<User> findPage(IPageToPage<User> page, User entity);

	public void disUser(String id);

	int deleteUser(String id);

	public User findNameUn(String id, String loginName);

	public User findEmailUn(String id, String email);

	public User findByCode(String code);

	public User findByActivationCode(String code);

	public User findById(String id);
//	public void resetUserGroup(String groupId);

}

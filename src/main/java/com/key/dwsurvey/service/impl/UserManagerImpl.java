package com.key.dwsurvey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.key.common.base.dao.UserDao;
import com.key.common.base.entity.User;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.plugs.page.Page;
import com.key.common.service.BaseServiceImpl;
import com.key.common.utils.IdUtils;
import com.key.common.utils.security.DigestUtils;
import com.key.dwsurvey.mapper.UserMapper;
import com.key.dwsurvey.service.UserManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 用户管理
 */
@Service
public class UserManagerImpl  implements UserManager {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;

//	@Override
//	public void setBaseDao() {
//		this.baseDao=userDao;
//	}

	@Override
	public void adminSave(User entity, String[] userRoleIds) {
		if(entity!=null){
			
			String pwd=entity.getPwd();
			if(pwd!=null && !"".equals(pwd)){
				//加点盐
//				String salt=RandomUtils.randomWordNum(5);
				String shaPassword = DigestUtils.sha1Hex(pwd);
				entity.setShaPassword(shaPassword);
//				entity.setSalt(salt);
			}
			String id = entity.getId();
			if (null != id && "".equals(id)) {
				entity.setId(null);
			}
			if (entity.getId() == null){
				userMapper.insertSelective(entity);
			}else{
				userMapper.updateByPrimaryKey(entity);
			}
//			save(entity);
		}
	}
	@Override
	public void adminSave1(User entity, String[] userRoleIds){
		if (entity != null){

			String pwd = entity.getPwd();
			if(pwd!=null && !"".equals(pwd)){
				String shaPassword = DigestUtils.sha1Hex(pwd);
				entity.setShaPassword(shaPassword);
			}
//			if (entity.getId()== null) {
//				entity.setId(UUID.randomUUID().toString());
////				entity.setId( new IdUtils().getId());
//			}
//			userMapper.
//			userMapper.insertSelective(entity);
			userMapper.insert(entity);
		}
	}

//	@Override
//	public Page<User> findPage(Page<User> page, User entity) {
//
//		List<Criterion> criterions=new ArrayList<Criterion>();
//		Integer status = entity.getStatus();
//		String loginName = entity.getLoginName();
//		if(status!=null && !"".equals(status)){
//			criterions.add(Restrictions.eq("status", status));
//		}
//		if(loginName!=null && !"".equals(loginName)){
//			criterions.add(Restrictions.like("loginName", "%"+loginName+"%"));
//		}
//		return super.findPageByCri(page, criterions);
//	}

	@Override
	public Page<User> findPage(IPageToPage<User> page, User entity){
		Map<String,Object> map = new HashMap<>();
//		IPageToPage<User> useripagetopage = new IPageToPage<>();
		Integer status = entity.getStatus();
		String loginName = entity.getLoginName();
		if(status!=null){
			map.put("status", status);
		}
		if(loginName!=null && !"".equals(loginName)){
			map.put("loginName", "%"+loginName+"%");
		}
		com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> page1 =
				new com.baomidou.mybatisplus.extension.plugins.pagination.Page<User>(page.getCurrent(),page.getSize());
		List<User> users = userMapper.selectPage(page1,map);
		Page<User> pageResult = new Page<User>(page);
		pageResult.setResult(users);
		pageResult.setTotalItems(page1.getTotal());
		return pageResult;
	}

	/**
	 * 禁用账号
	 */
	@Transactional
	@Override
	public void disUser(String id) {
		User user = userMapper.selectByPrimaryKey(id);
//		User user=get(id);
		int status=user.getStatus();
		if(status==0){
			user.setStatus(1);	
		}else{
			user.setStatus(0);
		}
		userMapper.updateByPrimaryKey(user);
//		save(user);
	}

	/**
	 * 删除账号
	 */
	@Transactional
	@Override
	public int deleteUser(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User findNameUn(String id, String loginName) {
		Map<String,String> map = new HashMap<>();
		map.put("loginName", loginName);
//		List<Criterion> criterions=new ArrayList<Criterion>();
//		criterions.add(Restrictions.eq("loginName", loginName));
		if(id!=null && !"".equals(id)){
			map.put("id", id);
//			criterions.add(Restrictions.ne("id", id));
		}
		List<User> users = userMapper.findList(map);
//		List<User> users = userMapper.selectList(queryWrapper);
		if (users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
//		return userDao.findFirst(criterions);
	}
	
	@Override
	public User findEmailUn(String id, String email) {
		Map<String,String> map = new HashMap<>();
		map.put("email", email);
//		List<Criterion> criterions=new ArrayList<Criterion>();
//		criterions.add(Restrictions.eq("email", email));
		if(id!=null && !"".equals(id)){
			map.put("id", id);
//			criterions.add(Restrictions.ne("id", id));
		}
		List<User> users = userMapper.findList(map);
//		List<User> users = userMapper.selectList(queryWrapper);
		if (users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
//		return userDao.findFirst(criterions);
	}

	@Override
	public User findByCode(String code) {
		Map<String,String> map = new HashMap<>();
		map.put("findPwdCode", code);
//		Criterion criterion=Restrictions.eq("findPwdCode", code);
		List<User> users = userMapper.findList(map);
//		List<User> users = userMapper.selectList(queryWrapper);
		if (users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
//		return userDao.findFirst(criterion);
	}
	
	@Override
	public User findByActivationCode(String activationCode) {
		Map<String,String> map = new HashMap<>();
		map.put("activationCode",activationCode);
//		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("activation_code",activationCode);
//		Criterion criterion=Restrictions.eq("findPwdCode", code);
		List<User> users = userMapper.findList(map);
		//		List<User> users = userMapper.selectList(queryWrapper);
		if (users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
//		Criterion criterion=Restrictions.eq("activationCode", activationCode);
//		return userDao.findFirst(criterion);
	}

	@Override
	public User findById(String id) {
		if (id != null){
			return userMapper.selectByPrimaryKey(id);
		}else{
			return new User();
		}
	}
//
//	@Override
//	public void resetUserGroup(String groupId) {
//
//		userDao.resetUserGroup(groupId);
//	}
}

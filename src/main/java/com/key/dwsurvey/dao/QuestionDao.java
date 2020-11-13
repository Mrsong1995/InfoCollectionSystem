package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.Question;

/**
 * 题基础 interface

 */
public interface QuestionDao extends BaseDao<Question, String> {
	
	public void update(Question entity);
	public void quOrderByIdDel1(String belongId, Integer orderById);
}

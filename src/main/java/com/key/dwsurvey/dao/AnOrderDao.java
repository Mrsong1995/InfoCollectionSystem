package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnOrder;
import com.key.dwsurvey.entity.Question;

/**
 * 排序题 interface

 */
public interface AnOrderDao extends BaseDao<AnOrder, String> {

	void findGroupStats(Question question);
	
}

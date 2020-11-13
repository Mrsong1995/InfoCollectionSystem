package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnFillblank;
import com.key.dwsurvey.entity.Question;

/**
 * 填空题 interface

 */
public interface AnFillblankDao extends BaseDao<AnFillblank, String> {

	void findGroupStats(Question question);

}

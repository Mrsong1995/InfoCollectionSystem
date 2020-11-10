package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnScore;
import com.key.dwsurvey.entity.Question;

/**
 * 评分题 interface

 */
public interface AnScoreDao extends BaseDao<AnScore, String> {

	void findGroupStats(Question question);
	
}

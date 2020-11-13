package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnChenScore;
import com.key.dwsurvey.entity.Question;

/**
 * 矩陈评分题 interface

 */
public interface AnChenScoreDao extends BaseDao<AnChenScore, String> {

	void findGroupStats(Question question);

}

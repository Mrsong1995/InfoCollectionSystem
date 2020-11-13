package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnAnswer;
import com.key.dwsurvey.entity.Question;

/**
 * 答卷数据 interface

 */
public interface AnAnswerDao extends BaseDao<AnAnswer, String> {

	void findGroupStats(Question question);

}

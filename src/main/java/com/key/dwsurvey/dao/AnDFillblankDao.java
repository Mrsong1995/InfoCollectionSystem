package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnDFillblank;
import com.key.dwsurvey.entity.Question;

/**
 * 多项填空题 interface

 */
public interface AnDFillblankDao extends BaseDao<AnDFillblank, String> {

	void findGroupStats(Question question);

}

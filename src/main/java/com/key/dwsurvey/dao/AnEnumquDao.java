package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnEnumqu;
import com.key.dwsurvey.entity.Question;

/**
 * 枚举题 interface

 */
public interface AnEnumquDao extends BaseDao<AnEnumqu, String> {

	void findGroupStats(Question question);

}

package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnChenCheckbox;
import com.key.dwsurvey.entity.Question;

/**
 * 矩陈多选题数据 interface

 */
public interface AnChenCheckboxDao extends BaseDao<AnChenCheckbox, String> {

	void findGroupStats(Question question);

}

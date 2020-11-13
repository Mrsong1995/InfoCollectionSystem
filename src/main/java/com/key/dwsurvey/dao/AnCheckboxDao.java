package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnCheckbox;
import com.key.dwsurvey.entity.DataCross;
import com.key.dwsurvey.entity.Question;

import java.util.List;


/**
 * 多选题数据 interface

 */
public interface AnCheckboxDao extends BaseDao<AnCheckbox, String> {

	void findGroupStats(Question question);

	List<DataCross> findStatsDataCross(Question rowQuestion,
                                              Question colQuestion);

	List<DataCross> findStatsDataChart(Question question);

}

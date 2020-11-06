package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnRadio;
import com.key.dwsurvey.entity.DataCross;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 单选题 interface
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */
public interface AnRadioDao extends BaseDao<AnRadio, String> {

	void findGroupStats(Question question);

	List<DataCross> findStatsDataCross(Question rowQuestion,
                                              Question colQuestion);

	List<DataCross> findStatsDataChart(Question question);

}

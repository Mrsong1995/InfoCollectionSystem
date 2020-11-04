package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnChenRadio;
import com.key.dwsurvey.entity.DataCross;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 矩陈单选题
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */

public interface AnChenRadioManager extends BaseService<AnChenRadio, String> {
	public List<AnChenRadio> findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);

	public List<DataCross> findStatsDataChart(Question question);
}

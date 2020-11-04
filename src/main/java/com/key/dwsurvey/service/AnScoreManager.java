package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnScore;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 评分题
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
public interface AnScoreManager extends BaseService<AnScore, String> {
	public List<AnScore>  findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);
}

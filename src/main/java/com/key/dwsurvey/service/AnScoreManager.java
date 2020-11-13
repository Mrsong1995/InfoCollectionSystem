package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnScore;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 评分题

 */
public interface AnScoreManager extends BaseService<AnScore, String> {
	public List<AnScore>  findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);
}

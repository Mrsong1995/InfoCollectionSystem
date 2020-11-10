package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnChenScore;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 矩陈评分题
 */
public interface AnChenScoreManager extends BaseService<AnChenScore, String> {
	public List<AnChenScore> findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);
}

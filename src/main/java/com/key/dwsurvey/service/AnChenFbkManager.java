package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnChenFbk;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 矩陈填空题
 */

public interface AnChenFbkManager extends BaseService<AnChenFbk, String> {
	public List<AnChenFbk> findAnswer(String belongAnswerId, String quId);
	
	public void findGroupStats(Question question);
}

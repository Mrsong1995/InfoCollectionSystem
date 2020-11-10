package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnAnswer;
import com.key.dwsurvey.entity.Question;

/**
 * 答卷业务

 */

public interface AnAnswerManager {//extends BaseService<AnAnswer, String> {
	public AnAnswer findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);

	public void save(AnAnswer AnAnswer);
}

package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnChenCheckbox;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 矩陈多选题业务
 */

public interface AnChenCheckboxManager extends BaseService<AnChenCheckbox, String> {
	public List<AnChenCheckbox> findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);
}

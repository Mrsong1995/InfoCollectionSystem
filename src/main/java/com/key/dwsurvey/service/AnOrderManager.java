package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnOrder;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 排序题

 */
public interface AnOrderManager extends BaseService<AnOrder, String> {
	public List<AnOrder>  findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);
}

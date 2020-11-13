package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnEnumqu;
import com.key.dwsurvey.entity.Question;

import java.util.List;

/**
 * 枚举题

 */
public interface AnEnumquManager extends BaseService<AnEnumqu, String> {
	public  List<AnEnumqu> findAnswer(String belongAnswerId, String quId);

	public void findGroupStats(Question question);
}

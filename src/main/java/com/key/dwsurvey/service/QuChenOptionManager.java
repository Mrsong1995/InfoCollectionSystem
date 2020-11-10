package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.QuChenOption;

import java.util.List;

/**
 * 矩陈题选项

 */
public interface QuChenOptionManager extends BaseService<QuChenOption, String> {
	public List<QuChenOption> findByQuId(String quId);
	
	public String getContentByQuId(String quId);
	
	public QuChenOption upOptionName(String quId, String quItemId, String optionName);
}

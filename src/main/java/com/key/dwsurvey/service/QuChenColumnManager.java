package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.QuChenColumn;

import java.util.List;

public interface QuChenColumnManager extends BaseService<QuChenColumn, String> {

	public List<QuChenColumn> findByQuId(String quId);
	
	public String getContentByQuId(String quId);
	
	public QuChenColumn upOptionName(String quId, String quItemId, String optionName);

	public void ajaxDelete(String quItemId);
	
}

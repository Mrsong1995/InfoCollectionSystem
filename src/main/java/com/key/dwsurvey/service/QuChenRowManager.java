package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.QuChenRow;

import java.util.List;

/**
 * 矩陈题行

 */
public interface QuChenRowManager extends BaseService<QuChenRow, String> {
	public List<QuChenRow> findByQuId(String quId);
	
	public String getContentByQuId(String quId);
	
	public QuChenRow upOptionName(String quId, String quItemId, String optionName);

	public void ajaxDelete(String quItemId);
}

package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.QuChenOption;

import java.util.List;

/**
 * 矩陈题选项
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
public interface QuChenOptionManager extends BaseService<QuChenOption, String> {
	public List<QuChenOption> findByQuId(String quId);
	
	public String getContentByQuId(String quId);
	
	public QuChenOption upOptionName(String quId, String quItemId, String optionName);
}

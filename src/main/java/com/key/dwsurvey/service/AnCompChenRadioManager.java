package com.key.dwsurvey.service;

import com.key.common.service.BaseService;
import com.key.dwsurvey.entity.AnCompChenRadio;

import java.util.List;

/**
 * 复合矩陈单选题

 */

public interface AnCompChenRadioManager extends BaseService<AnCompChenRadio, String> {
	
	public List<AnCompChenRadio> findAnswer(String belongAnswerId, String quId);
	
}

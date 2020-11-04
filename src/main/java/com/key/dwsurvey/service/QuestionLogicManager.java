package com.key.dwsurvey.service;

import com.key.dwsurvey.entity.QuestionLogic;

import java.util.List;

/**
 * 题逻辑
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
public interface QuestionLogicManager {

	List<QuestionLogic> findByCkQuId(String quId);

}

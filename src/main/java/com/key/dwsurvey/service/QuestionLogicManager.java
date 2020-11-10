package com.key.dwsurvey.service;

import com.key.dwsurvey.entity.QuestionLogic;

import java.util.List;

/**
 * 题逻辑

 */
public interface QuestionLogicManager {

	List<QuestionLogic> findByCkQuId(String quId);

}

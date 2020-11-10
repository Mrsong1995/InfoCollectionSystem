package com.key.dwsurvey.service.impl;

import com.key.dwsurvey.dao.SurveyStyleDao;
import com.key.dwsurvey.entity.SurveyStyle;
import com.key.dwsurvey.mapper.SurveyStyleMapper;
import com.key.dwsurvey.service.SurveyStyleManager;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


/**
 * 问卷样式
 */
@Service
public class SurveyStyleManagerImpl implements SurveyStyleManager {

	@Autowired
	private SurveyStyleDao surveyStyleDao;

	@Autowired
	private SurveyStyleMapper surveyStyleMapper;
	@Override
	public SurveyStyle get(String id) {
		return surveyStyleDao.findUniqueBy("id", id);
	}

	@Override
	@Transactional
	public SurveyStyle getBySurveyId(String surveyId) {

		Map<String,String> map = new HashMap<>();
		map.put("surveyId",surveyId);
//		Criterion cri1=Restrictions.eq("surveyId", surveyId);
//		SurveyStyle surveyStyle1 = surveyStyleDao.findFirst(cri1);

		return surveyStyleMapper.findFirst(map);
	}

	@Override
	@Transactional
	public void save(SurveyStyle surveyStyle){
		surveyStyleDao.save(surveyStyle);
	}
	
}

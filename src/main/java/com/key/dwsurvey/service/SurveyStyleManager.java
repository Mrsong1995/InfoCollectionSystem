package com.key.dwsurvey.service;

import com.key.dwsurvey.entity.SurveyStyle;

/**
 * 问卷样式

 */
public interface SurveyStyleManager  {

	public SurveyStyle get(String id) ;
	
	public SurveyStyle getBySurveyId(String surveyId) ;
	
	public void save(SurveyStyle surveyStyle) ;
}

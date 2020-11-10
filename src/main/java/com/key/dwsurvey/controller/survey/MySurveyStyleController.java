package com.key.dwsurvey.controller.survey;

import com.key.common.base.service.AccountManager;
import com.key.constant.CommonConstant;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import com.key.dwsurvey.service.SurveyStyleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 问卷样式
 */

@Controller
@RequestMapping("/design/my-survey-style")
public class MySurveyStyleController {
	
	private String surveyId;
	@Autowired
	private SurveyStyleManager surveyStyleManager;
	@Autowired
	private SurveyDirectoryManager surveyDirectoryManager;
	@Autowired
	private AccountManager accountManager;

	@RequestMapping("/save")
	public String save() throws Exception  {
		return null;
	}
	
	public String ajaxGetStyle() throws Exception {
	    return null;
	}

	
}

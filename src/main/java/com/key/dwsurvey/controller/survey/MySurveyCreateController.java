package com.key.dwsurvey.controller.survey;

import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;


/**
 * 创建问卷
 */

@Controller
@RequestMapping("/design/my-survey-create")
public class MySurveyCreateController {// extends ActionSupport{
	@Autowired
	private SurveyDirectoryManager directoryManager;

	private String surveyId;

	@RequestMapping("/execute")
	public String execute() throws Exception {
	    return "/success.jsp";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request, Map<String,Object> model) throws Exception {
		HttpServletResponse response;
		String surveyName= request.getParameter("surveyName");
		SurveyDirectory survey = new SurveyDirectory();
	    try{
	    	survey.setDirType(2);
	    	//如果没有输入问卷命，默认为"请输入模板标题"
	    	if(surveyName==null || "".equals(surveyName.trim())){
	    		surveyName="请输入模板标题";
	    	}else{
	    		surveyName=URLDecoder.decode(surveyName,"utf-8");
	    	}
	 	    survey.setSurveyName(surveyName);
	    	//创建问卷
	 	    directoryManager.insertOrUpdate(survey);
	 	    surveyId = survey.getId();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return  "redirect:/design/my-survey-design/execute?surveyId="+surveyId;
	}
	
	public String getSurveyId() {
	    return surveyId;
	}
	
}

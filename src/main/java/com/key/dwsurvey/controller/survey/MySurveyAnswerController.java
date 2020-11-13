package com.key.dwsurvey.controller.survey;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.plugs.page.Page;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.entity.SurveyAnswer;
import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.service.SurveyAnswerManager;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 答卷数据
 */

@Controller
@RequestMapping("/da/my-survey-answer")
public class MySurveyAnswerController {
	@Autowired
	private SurveyAnswerManager surveyAnswerManager;

	@Autowired
	private SurveyDirectoryManager directoryManager;

	@Autowired
	private AccountManager accountManager;

	private Page<SurveyAnswer> page=new Page<>();

	private String surveyId;
	
	@RequestMapping("/execute")
	public String execute(String surveyId, HttpServletRequest request, Map<String,Object> map) throws Exception {
		this.surveyId = surveyId;
		map.put("surveyId",surveyId);
		User user=accountManager.getCurUser();
    	if(user!=null){
    		SurveyDirectory survey=directoryManager.getSurveyByUser(surveyId, user.getId());
    		if(survey!=null){
    			page=surveyAnswerManager.answerPage(page, surveyId);
    			request.setAttribute("survey", survey);
    			map.put("directory",survey);
    			map.put("page",page);
    		}
    	}
		
		return "/content/diaowen-da/survey-answer-data";
	}

	@RequestMapping("/responseAnswer")
	public String responseAnswer(HttpServletRequest request) throws Exception {
		String answerId=request.getParameter("answerId");
		List<Question> questions;
		try {
			SurveyDirectory directory=new SurveyDirectory();
			if (answerId != null) {
				SurveyAnswer answer = surveyAnswerManager.getModel(answerId);
				questions = surveyAnswerManager.findAnswerDetail(answer);
				User user=accountManager.getCurUser();
		    	if(answer.getSurveyId() != null && user!=null){
		    		SurveyDirectory survey=directoryManager.getSurveyByUser(answer.getSurveyId(), user.getId());
		    		if(survey!=null){
		    			request.setAttribute("questions", questions);
		    			request.setAttribute("directory", directory);
		    		}
		    	}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/content/diaowen-answer/response-answer";
	}

	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String result="error";
		try{
			String answerId=request.getParameter("answerId");
			if (answerId != null) {
				SurveyAnswer answer = surveyAnswerManager.getModel(answerId);
				User user=accountManager.getCurUser();
		    	if(answer.getSurveyId() != null && user!=null){
		    		SurveyDirectory survey=directoryManager.getSurveyByUser(answer.getSurveyId(), user.getId());
		    		if(survey!=null){
		    			surveyAnswerManager.delete(answer);
						Integer answerNum = survey.getAnswerNum();
						if(answerNum!=null && answerNum>=1){
							survey.setAnswerNum(answerNum-1);
							directoryManager.save(survey);
						}
		    		}
		    	}
			}
			result="true";
		}catch(Exception e){
			e.printStackTrace();
		}
		response.getWriter().write(result);
		return null;
	}

	@RequestMapping("/exportXLS")
	public String exportXLS(HttpServletRequest request,HttpServletResponse response){
		try{
			String savePath = request.getSession().getServletContext().getRealPath("/");
			User user=accountManager.getCurUser();
	    	if(user!=null){
	    		SurveyDirectory survey=directoryManager.getSurveyByUser(surveyId, user.getId());
	    		if(survey!=null){
	    			savePath=surveyAnswerManager.exportXLS(surveyId,savePath);
	    			response.sendRedirect(request.getContextPath()+savePath);
	    		}
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Page<SurveyAnswer> getPage() {
		return page;
	}

	public void setPage(Page<SurveyAnswer> page) {
		this.page = page;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	
}

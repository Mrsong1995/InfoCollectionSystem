package com.key.dwsurvey.controller.survey;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.entity.SurveyStats;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import com.key.dwsurvey.service.SurveyStatsManager;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 分析报告 controller
 */
@Controller
@RequestMapping("/da/survey-report")
public class SurveyReportController extends ActionSupport{
	
	protected final static String DEFAULT_REPORT="default_report";
	protected final static String LINE_CHART="line_chart";
	protected final static String PIE_CHART="pie_chart";
	
	@Autowired
	private SurveyStatsManager surveyStatsManager;
	@Autowired
	private SurveyDirectoryManager directoryManager;
	@Autowired
	private AccountManager accountManager;
	
	private SurveyStats surveyStats = new SurveyStats();
	private SurveyDirectory directory = new SurveyDirectory();
	
	private String surveyId;

	@RequestMapping("/defaultReport")
	public String defaultReport(String surveyId, Map<String,Object> map) throws Exception {
		this.surveyId = surveyId;
		map.put("surveyId",surveyId);
		// 得到频数分析数据
		User user = accountManager.getCurUser();
		if(user!=null){
			directory=directoryManager.getSurveyByUser(surveyId, user.getId());
			if(directory!=null){
				map.put("directory",directory);
				List<Question> questions = surveyStatsManager.findFrequency(directory);
				surveyStats.setQuestions(questions);
				map.put("surveyStats",surveyStats);
			}
		}
		return "/content/diaowen-da/default-report";
	}

	@RequestMapping("/lineChart")
	public String lineChart() throws Exception {
		User user = accountManager.getCurUser();
		if(user!=null){
			directory=directoryManager.getSurveyByUser(surveyId, user.getId());

			if(directory!=null){
				List<Question> questions = surveyStatsManager.dataChart1s(directory);
				surveyStats.setQuestions(questions);
			}
		}
		return "/content/diaowen-da/line-chart";
	}

	@RequestMapping("/pieChart")
	public String pieChart() throws Exception {
		User user = accountManager.getCurUser();
		if(user!=null){
			directory=directoryManager.getSurveyByUser(surveyId, user.getId());
			if(directory!=null){
				List<Question> questions = surveyStatsManager.dataChart1s(directory);
				surveyStats.setQuestions(questions);
			}
		}
		return "/content/diaowen-da/pie-chart";
	}
	
	@RequestMapping("/chartData")
	public String chartData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//取柱状图数据
		User user = accountManager.getCurUser();
		if(user!=null){
			String questionId= request.getParameter("quId");
			Question question=new Question();
			question.setId(questionId);
			surveyStatsManager.questionDateCross(question);
			response.getWriter().write(question.getStatJson());
		}
		return null;
	}
	
	public SurveyStats getSurveyStats() {
		return surveyStats;
	}
	
	public SurveyDirectory getDirectory() {
		return directory;
	}

	public String getSurveyId() {
	    return surveyId;
	}

	public void setSurveyId(String surveyId) {
	    this.surveyId = surveyId;
	}
	
}

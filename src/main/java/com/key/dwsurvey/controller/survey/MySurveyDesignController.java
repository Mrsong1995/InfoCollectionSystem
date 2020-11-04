package com.key.dwsurvey.controller.survey;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.utils.DiaowenProperty;
import com.key.common.utils.JspToHtml;
import com.key.constant.CommonConstant;
import com.key.dwsurvey.entity.Question;
import com.key.dwsurvey.entity.SurveyDetail;
import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.entity.SurveyStyle;
import com.key.dwsurvey.mapper.SurveyDirectoryMapper;
import com.key.dwsurvey.service.QuestionManager;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import com.key.dwsurvey.service.SurveyReqUrlManager;
import com.key.dwsurvey.service.SurveyStyleManager;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 设计问卷
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */

@Controller
@RequestMapping("/design/my-survey-design")
//@Namespace("/design")
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack")})
//@Results({
	//@Result(name=ActionSupport.SUCCESS,location="/WEB-INF/page/content/diaowen-design/design-survey.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= MySurveyDesignAction.PREVIEWDEV,location="/WEB-INF/page/content/diaowen-design/survey_preview_dev.jsp",type= Struts2Utils.DISPATCHER),
	//@Result(name= MySurveyDesignAction.COLLECTSURVEY,location="my-collect.controller?surveyId=${surveyId}",type= Struts2Utils.REDIRECT),
//	@Result(name= MySurveyDesignAction.RELOADDESIGN,location="/design/my-survey-design.controller?surveyId=${surveyId}",type= Struts2Utils.REDIRECT)
//})
//@AllowedMethods({"previewDev","devSurvey","ajaxSave","copySurvey"})
public class MySurveyDesignController {// extends ActionSupport{
	//发布设置
	protected final static String PREVIEWDEV="previewDev";
	protected final static String COLLECTSURVEY="collectSurvey";
	protected final static String RELOADDESIGN="reloadDesign";
	
	@Autowired
	private QuestionManager questionManager;
	@Autowired
	private SurveyDirectoryManager surveyDirectoryManager;
	@Autowired
	private SurveyStyleManager surveyStyleManager;
	@Autowired
	private SurveyReqUrlManager surveyReqUrlManager;
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private SurveyDirectoryMapper surveyDirectoryMapper;
	private String surveyId;
	
	@RequestMapping("/execute")
	public String execute(HttpServletRequest request,HttpServletResponse response,String surveyId) {
		this.surveyId = surveyId;
		buildSurvey(request);
		return "/content/diaowen-design/design-survey";
	}

	@RequestMapping("/previewDev")
	public String previewDev(HttpServletRequest request) throws Exception {
		buildSurvey(request);
		
		return "/content/diaowen-design/survey_preview_dev";
	}

	@RequestMapping("/devSurvey")
	public String devSurvey(String surveyId,HttpServletRequest request) throws Exception {
		this.surveyId = surveyId;
		SurveyDirectory survey=surveyDirectoryManager.get(surveyId);
		Date createDate=survey.getCreateDate();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd/");
		try{
			String url="/survey/answerSurvey?surveyId="+surveyId;
			String filePath="WEB-INF/wjHtml/"+dateFormat.format(createDate);
			String fileName=surveyId+".html";
			new JspToHtml().postJspToHtml(request, url, filePath, fileName);
			survey.setHtmlPath(filePath+fileName);

			url="/survey/answerSurveryMobile?surveyId="+surveyId;
			filePath="WEB-INF/wjHtml/"+dateFormat.format(createDate);
			fileName="m_"+surveyId+".html";
			new JspToHtml().postJspToHtml(request, url, filePath, fileName);

			List<Question> questions=questionManager.find(surveyId, "2");
			survey.setSurveyQuNum(questions.size());
			survey.setSurveyState(1);
			//begin add  by jesse at 2020-07-08  for  更改html数据库的地址

			//end add by jesse at 2020-07-08
			surveyDirectoryManager.insertOrUpdate(survey);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/design/my-collect/execute?surveyId="+surveyId;
	}
	
	private void buildSurvey(HttpServletRequest request) {
		//判断是否拥有权限
		User user= accountManager.getCurUser();
		if(user!=null){
			String userId=user.getId();
			SurveyDirectory surveyDirectory=surveyDirectoryManager.getSurveyByUser(surveyId, userId);
			if(surveyDirectory!=null){
				surveyDirectoryManager.getSurveyDetail(surveyId, surveyDirectory);
//				SurveyDirectory survey=surveyDirectoryManager.getSurvey(surveyId);
				List<Question> questions=questionManager.findDetails(surveyId, "2");
				surveyDirectory.setQuestions(questions);
				surveyDirectory.setSurveyQuNum(questions.size());
				surveyDirectoryMapper.updateByPrimaryKeySelective(surveyDirectory);
				//begin delete  by jesse at 2020-07-14  for 优化
				//				surveyDirectoryManager.save(surveyDirectory);
				//end delete by jesse at 2020-07-14

				request.setAttribute("survey",surveyDirectory);
//				Struts2Utils.setReqAttribute("survey", surveyDirectory);
				SurveyStyle surveyStyle=surveyStyleManager.getBySurveyId(surveyId);
				request.setAttribute("surveyStyle", surveyStyle);
//				Struts2Utils.setReqAttribute("surveyStyle", surveyStyle);
				request.setAttribute("prevHost", DiaowenProperty.STORAGE_URL_PREFIX);
//				Struts2Utils.setReqAttribute("prevHost", DiaowenProperty.STORAGE_URL_PREFIX);
			}else{
				request.setAttribute("msg", "未登录或没有相应数据权限");
//				Struts2Utils.setReqAttribute("msg", "未登录或没有相应数据权限");
			}
		}else{
			request.setAttribute("msg", "未登录或没有相应数据权限");
//			Struts2Utils.setReqAttribute("msg", "未登录或没有相应数据权限");
		}
	}


	@RequestMapping("/ajaxSave")
	public String ajaxSave(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		HttpServletRequest request= Struts2Utils.getRequest();
//		HttpServletResponse response= Struts2Utils.getResponse();
		String svyName=request.getParameter("svyName");
		String svyNote=request.getParameter("svyNote");
		//属性
		String effective=request.getParameter("effective");
		String effectiveIp=request.getParameter("effectiveIp");
		String rule=request.getParameter("rule");
		String ruleCode=request.getParameter("ruleCode");
		String refresh=request.getParameter("refresh");
		String mailOnly=request.getParameter("mailOnly");
		String ynEndNum=request.getParameter("ynEndNum");
		String endNum=request.getParameter("endNum");
		String ynEndTime=request.getParameter("ynEndTime");
		String endTime=request.getParameter("endTime");
		String showShareSurvey=request.getParameter("showShareSurvey");
		String showAnswerDa=request.getParameter("showAnswerDa");
		
		
		SurveyDirectory survey=surveyDirectoryManager.getSurvey(surveyId);
		SurveyDetail surveyDetail=survey.getSurveyDetail();
		User user= accountManager.getCurUser();
		if(user!=null && survey!=null){
			String userId=user.getId();
			if(userId.equals(survey.getUserId())){
				
				if( svyNote!=null){
					svyNote=URLDecoder.decode(svyNote,"utf-8");
					surveyDetail.setSurveyNote(svyNote);
				}
				if(svyName!=null && !"".equals(svyName)){
					svyName=URLDecoder.decode(svyName,"utf-8");
					survey.setSurveyName(svyName);
				}

				//保存属性
				if(effective!=null && !"".equals(effective)){
				    surveyDetail.setEffective(Integer.parseInt(effective));
				}
				if(effectiveIp!=null && !"".equals(effectiveIp)){
				    surveyDetail.setEffectiveIp(Integer.parseInt(effectiveIp));
				}
				if(rule!=null && !"".equals(rule)){
				    surveyDetail.setRule(Integer.parseInt(rule));
				    surveyDetail.setRuleCode(ruleCode);
				}
				if(refresh!=null && !"".equals(refresh)){
				    surveyDetail.setRefresh(Integer.parseInt(refresh));
				}
				if(mailOnly!=null && !"".equals(mailOnly)){
				    surveyDetail.setMailOnly(Integer.parseInt(mailOnly));
				}
				if(ynEndNum!=null && !"".equals(ynEndNum)){
				    surveyDetail.setYnEndNum(Integer.parseInt(ynEndNum));
				    //surveyDetail.setEndNum(Integer.parseInt(endNum));
				    if(endNum!=null && endNum.matches("\\d*")){
					surveyDetail.setEndNum(Integer.parseInt(endNum));			
				    }
				}
				if(ynEndTime!=null && !"".equals(ynEndTime)){
				    surveyDetail.setYnEndTime(Integer.parseInt(ynEndTime));
//				    surveyDetail.setEndTime(endTime);
				    surveyDetail.setEndTime(new Date());
				}
				if(showShareSurvey!=null && !"".equals(showShareSurvey)){
				    surveyDetail.setShowShareSurvey(Integer.parseInt(showShareSurvey));
				    survey.setIsShare(Integer.parseInt(showShareSurvey));
				}
				if(showAnswerDa!=null && !"".equals(showAnswerDa)){
				    surveyDetail.setShowAnswerDa(Integer.parseInt(showAnswerDa));
				    survey.setViewAnswer(Integer.parseInt(showAnswerDa));
				}
				
				surveyDirectoryManager.insertOrUpdate(survey);

				response.getWriter().write("true");
				
			}
		}
		return null;
//		return CommonConstant.NONE;
	}
	
	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	@RequestMapping("/copySurvey")
	public String copySurvey(HttpServletRequest request) throws Exception {
		//引用问卷
//		id="402880e541d051000141d0f708ff0004";
//		HttpServletRequest request= Struts2Utils.getRequest();
		String fromBankId=request.getParameter("fromBankId");
		String surveyName=request.getParameter("surveyName");
		surveyName=URLDecoder.decode(surveyName,"utf-8");
		String tag=request.getParameter("tag");
		tag="2";
		SurveyDirectory directory=surveyDirectoryManager.createBySurvey(fromBankId,surveyName,tag);
		surveyId=directory.getId();
		return "redirect:/design/my-survey-design/execute?surveyId="+surveyId;
	}
	
	private void buildSurveyHtml(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		HttpServletRequest request= Struts2Utils.getRequest();
//		HttpServletResponse response= Struts2Utils.getResponse();
		String url = "";
		String name = "";
		ServletContext sc = ServletActionContext.getServletContext();

		String file_name = request.getParameter("file_name");
		url = "/design/my-collect/execute?surveyId=402880ea4675ac62014675ac7b3a0000";
		// 这是生成的html文件名,如index.htm.
		name = "/survey.htm";
		name = sc.getRealPath(name);
		
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		final ByteArrayOutputStream os = new ByteArrayOutputStream();

		final ServletOutputStream stream = new ServletOutputStream() {


			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {

			}

			public void write(byte[] data, int offset, int length) {
				os.write(data, offset, length);
			}

			public void write(int b) throws IOException {
				os.write(b);
			}
		};
		
		final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"utf-8"));

		HttpServletResponse rep = new HttpServletResponseWrapper(response) {
			@Override
			public ServletOutputStream getOutputStream() {
				return stream;
			}

			@Override
			public PrintWriter getWriter() {
				return pw;
			}
		};

//		rd.include(request, rep);
		rd.forward(request,rep);
		pw.flush();
		
		// 把jsp输出的内容写到xxx.htm
		File file = new File(name);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		
		os.writeTo(fos);
		fos.close();
	}

	
}

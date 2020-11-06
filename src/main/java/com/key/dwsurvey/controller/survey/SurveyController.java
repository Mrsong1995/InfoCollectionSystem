package com.key.dwsurvey.controller.survey;

import com.key.common.plugs.ipaddr.IPService;
import com.key.common.plugs.zxing.ZxingUtil;
import com.key.common.utils.DiaowenProperty;
import com.key.constant.CommonConstant;
import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.entity.SurveyStyle;
import com.key.dwsurvey.service.QuestionManager;
import com.key.dwsurvey.service.SurveyAnswerManager;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import com.key.dwsurvey.service.SurveyStyleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 问卷 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://www.dwsurvey.net
 *
 */
@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	protected final static String INDEXJSP="indexJsp";
	protected final static String ANSERSURVEY="answerSurvey";
	protected final static String ANSERSURVEY_MOBILE="answerSurveyMobile";
	protected final static String SURVEYMODEL="surveyModel";
	protected final static String ANSWER_INPUT_ERROR = "answerInputError";//已经答过，在间隔时间内
	protected final static String ANSWER_INPUT_RULE = "answer_input_rule";//令牌
	
	@Autowired
	private SurveyDirectoryManager surveyDirectoryManager;
	@Autowired
	private QuestionManager questionManager;
	@Autowired
	private SurveyStyleManager surveyStyleManager;
	@Autowired
	private IPService ipService;
	@Autowired
	private SurveyAnswerManager surveyAnswerManager;

	private String sid;
	//外部回答公共访问路径--静态访问
	@RequestMapping("/execute")
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String htmlPath="http://wj.diaowen.net/test";
		response.sendRedirect(htmlPath);
		return CommonConstant.NONE;
	}

	//问卷的动态访问方式
	@RequestMapping("/answerSurvey")
	public String answerSurvey(HttpServletRequest request) throws Exception {
		String surveyId = request.getParameter("surveyId");
		SurveyDirectory survey=surveyDirectoryManager.getSurvey(surveyId);
		buildSurvey(request,survey);
		return "/content/diaowen-design/answer-survey-p";
	}
	//问卷动态访问-移动端
	@RequestMapping("/answerSurveryMobile")
	public String answerSurveryMobile(HttpServletRequest request) throws Exception {
		String surveyId = request.getParameter("surveyId");
		SurveyDirectory survey=surveyDirectoryManager.getSurvey(surveyId);
		buildSurvey(request,survey);
	    return "/content/diaowen-design/answer-survey-m";
	}
	
	private void buildSurvey(HttpServletRequest request,SurveyDirectory survey) {
		String surveyId = request.getParameter("surveyId");
		if (survey==null) {
			survey = surveyDirectoryManager.getSurvey(surveyId);
		}
		survey.setQuestions(questionManager.findDetails(surveyId, "2"));
		request.setAttribute("survey", survey);
		SurveyStyle surveyStyle=surveyStyleManager.getBySurveyId(surveyId);
		request.setAttribute("surveyStyle", surveyStyle);
		request.setAttribute("prevHost", DiaowenProperty.STORAGE_URL_PREFIX);
	}

	//回答问卷的二维码
	@RequestMapping("/answerTD")
	public String answerTD(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String surveyId = request.getParameter("surveyId");
	    	String down=request.getParameter("down");

			String baseUrl = "";
			baseUrl = request.getScheme() +"://" + request.getServerName()
					+ (request.getServerPort() == 80 ? "" : ":" +request.getServerPort())
					+ request.getContextPath();

	    	try{
				String encoderContent=baseUrl+"/response!answerMobile.controller?surveyId="+surveyId;
				ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
				BufferedImage twoDimensionImg = ZxingUtil.qRCodeCommon(encoderContent, "jpg", 7);
				ImageIO.write(twoDimensionImg, "jpg", jpegOutputStream);
				if(down==null){
					response.setHeader("Cache-Control", "no-store");
					response.setHeader("Pragma", "no-cache");
					response.setDateHeader("Expires", 0);
					response.setContentType("image/jpeg");
					ServletOutputStream responseOutputStream = response.getOutputStream();
					responseOutputStream.write(jpegOutputStream.toByteArray());
					responseOutputStream.flush();
					responseOutputStream.close();
				}else{
					response.addHeader("Content-Disposition", "attachment;filename=" + new String(("diaowen_"+surveyId+".jpg").getBytes()));
					byte[] bys = jpegOutputStream.toByteArray();
					response.addHeader("Content-Length", "" + bys.length);
					ServletOutputStream responseOutputStream = response.getOutputStream();
					response.setContentType("application/octet-stream");
					responseOutputStream.write(bys);
					responseOutputStream.flush();
					responseOutputStream.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
	        return null;
	}

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}


}

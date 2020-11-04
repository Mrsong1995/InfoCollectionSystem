package com.key.dwsurvey.controller.survey;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 收集入口 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://www.dwsurvey.net
 *
 */
@Controller
@RequestMapping("/design/my-collect")
//@Namespace("/design")
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack")})
//@Results({
//	@Result(name= MyCollectAction.COLLECT1,location="/WEB-INF/page/content/diaowen-collect/collect_1.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= MyCollectAction.IFRAME,location="/WEB-INF/page/content/diaowen-collect/collect_iframe.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= MyCollectAction.SITECOMP,location="/WEB-INF/page/content/diaowen-collect/collect_website.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= MyCollectAction.WEIXIN,location="/WEB-INF/page/content/diaowen-collect/collect_weixin.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= MyCollectAction.SHARE,location="/WEB-INF/page/content/diaowen-collect/collect_2.jsp",type= Struts2Utils.DISPATCHER)
//})
public class MyCollectController {//} extends ActionSupport{
	
	protected final static String COLLECT1="collect1";
	protected final static String IFRAME="iframe";
	protected final static String SITECOMP="sitecomp";
	protected final static String WEIXIN="weixin";
	protected final static String SHARE="share";
	
	private String surveyId;
	@Autowired
	private SurveyDirectoryManager surveyDirectoryManager;
	@Autowired
	private AccountManager accountManager;

	
//	@Override
	@RequestMapping("/execute")
	public String execute(String surveyId, HttpServletRequest request, Map<String,Object> map) throws Exception {
		this.surveyId = surveyId;
//		HttpServletRequest request= Struts2Utils.getRequest();
		String tabId=request.getParameter("tabId");
		map.put("surveyId",surveyId);
		String baseUrl = "";
		baseUrl = request.getScheme() +"://" + request.getServerName()  
						+ (request.getServerPort() == 80 ? "" : ":" +request.getServerPort())
                        + request.getContextPath();

		request.setAttribute("baseUrl", baseUrl);

		User user=accountManager.getCurUser();
    	if(user!=null){
    		SurveyDirectory surveyDirectory=surveyDirectoryManager.getSurveyByUser(surveyId, user.getId());
    		if(surveyDirectory!=null){
    			request.setAttribute("survey", surveyDirectory);
    			map.put("survey",surveyDirectory);
    			if(IFRAME.equals(tabId)){
    				return "/content/diaowen-collect/collect_iframe";
    			}else if(SITECOMP.equals(tabId)){
    				return "/content/diaowen-collect/collect_website";
    			}else if(WEIXIN.equals(tabId)){
    				return "/content/diaowen-collect/collect_weixin";
    			}else if(SHARE.equals(tabId)){
    				return "/content/diaowen-collect/collect_2";
    			}
    			return "/content/diaowen-collect/collect_1";
    		}
    	}
		return null;
	}
	
	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	
	
}

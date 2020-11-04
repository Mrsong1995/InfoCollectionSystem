package com.key.dwsurvey.controller.question;

import com.key.dwsurvey.service.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 题目 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */
@Controller
@RequestMapping("/design/question")
//@Namespaces({@Namespace("/design")})
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack") })
//@Results({})
//@AllowedMethods({"ajaxDelete"})
public class QuestionController {// extends ActionSupport{
	@Autowired
	private QuestionManager questionManager;
	/**
	 * ajax删除
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxDelete")
	public String ajaxDelete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
//		HttpServletRequest request= Struts2Utils.getRequest();
//		HttpServletResponse response= Struts2Utils.getResponse();
		String responseStr="";
		try{
			String delQuId=request.getParameter("quId");
			questionManager.delete(delQuId);	
			responseStr="true";
		}catch (Exception e) {
			responseStr="false";
		}
		response.getWriter().write(responseStr);
		return null;
	}
	
}

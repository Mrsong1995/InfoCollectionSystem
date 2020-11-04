package com.key.dwsurvey.controller.survey;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.plugs.page.Page;
import com.key.dwsurvey.entity.SurveyDirectory;
import com.key.dwsurvey.mapper.SurveyDirectoryMapper;
import com.key.dwsurvey.mapper.UserMapper;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * 我的问卷 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */


@Controller
@RequestMapping("/design/my-survey")
//@Namespace("/design")
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack")})
//@Results({
   // @Result(name= MySurveyAction.SUCCESS,location="/WEB-INF/page/content/diaowen-design/list.jsp",type= Struts2Utils.DISPATCHER),
//    @Result(name="design",location="/design/my-survey-design.controller?surveyId=${id}",type= Struts2Utils.REDIRECT),
//})
//@AllowedMethods({"surveyState","attrs"})
public class MySurveyController<ID extends Serializable> {// extends CrudActionSupport<SurveyDirectory, String> {
	
	@Autowired
	private SurveyDirectoryManager surveyDirectoryManager;
	@Autowired
	private AccountManager accountManager;

	@Autowired
	private SurveyDirectoryMapper surveyDirectoryMapper;
	@Autowired
	private UserMapper userMapper;
//	protected T entity;
//	private SurveyDirectory entity = new SurveyDirectory();

	//TODO   id  errors
//	private ID id;



	@RequestMapping("/list")
	public String list(String result,HttpServletRequest request, Map<String, Object> map) {
		SurveyDirectory entity = new SurveyDirectory();
		Page<SurveyDirectory> page = new Page<>();
//		request.getR
//		HttpServletRequest request= Struts2Utils.getRequest();
		String surveyState = request.getParameter("surveyState");
		if(surveyState==null||"".equals(surveyState)){
			entity.setSurveyState(null);
		}else {
			entity.setSurveyState(Integer.valueOf(surveyState));
		}
		String pageNo = request.getParameter("page.pageNo");
		if (pageNo != null && !"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		String surveyName = request.getParameter("surveyName");
		if (surveyName != null && !"".equals(surveyName)){
			entity.setSurveyName(surveyName);
		}

	    page=surveyDirectoryManager.findByUser(page,entity);
		map.put("surveyState",surveyState);
		map.put("page",page);
		map.put("result",result);
	    return "/content/diaowen-design/list";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletResponse response,String id) throws Exception {
//		this.id = (ID) id;
//	    HttpServletResponse response= Struts2Utils.getResponse();
	    String result="false";
	    try{
		User user = accountManager.getCurUser();
		if(user!=null){
		    String userId=user.getId();

		    SurveyDirectory surveyDirectory=surveyDirectoryManager.getSurveyByUser((String) id,userId);
		    if(surveyDirectory!=null){
//		    	surveyDirectoryManager.delete( id);
		    	surveyDirectoryMapper.deleteByPrimaryKey(id);
		    	result="true";
		    }
		}
	    }catch (Exception e) {
			result="false";
	    }
//	    response.getWriter().write(result);
	    return result;
//	    return "redirect:/design/my-survey/list?result="+result;
	}
	
	//问卷壮态设置
	@RequestMapping("/surveyState")
	public String surveyState(HttpServletResponse resp,String id) throws Exception{

//		HttpServletResponse resp= Struts2Utils.getResponse();
//		SurveyDirectory entity = surveyDirectoryManager.getModel(id);
		SurveyDirectory entity = surveyDirectoryMapper.selectByPrimaryKey(id);
		User user1 = userMapper.selectByPrimaryKey(entity.getUserId());
		entity.setUserName(user1.getName());
		entity.setId(id);
		String result="";
		try{
			User user= accountManager.getCurUser();
			if(user!=null){
				String userId=user.getId();

				SurveyDirectory surveyDirectory=surveyDirectoryManager.getSurveyByUser(id, userId);
				if(surveyDirectory!=null){
					int surveyState=entity.getSurveyState();
					if (surveyDirectory.getSurveyState()==1){
						surveyDirectory.setSurveyState(2);
					}else if (surveyDirectory.getSurveyState()==2){
						surveyDirectory.setSurveyState(1);
					}

//					surveyDirectory.setSurveyState(surveyState);
					surveyDirectoryManager.save(surveyDirectory);
				}
			}
			result="true";
		}catch(Exception e){
			e.printStackTrace();
			result="error";
		}
		resp.getWriter().write(result);
//		return "content/diaowen-design/list";
		return null;
	}
	


	@RequestMapping("/attrs")
	public String attrs(HttpServletRequest request,HttpServletResponse response,String surveyId) throws Exception {
//		HttpServletRequest request= Struts2Utils.getRequest();
//		HttpServletResponse response= Struts2Utils.getResponse();
//		id = surveyId;
		surveyId = request.getParameter("surveyId");
		try{
			SurveyDirectory survey=surveyDirectoryManager.getSurvey( surveyId);
			JsonConfig cfg = new JsonConfig();
			cfg.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
			JSONObject jsonObject=JSONObject.fromObject(survey,cfg);
			response.getWriter().write(jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/design/my-survey-design/execute?surveyId="+surveyId;
	}
	
	
//
//	protected void prepareModel() throws Exception {
//		entity=surveyDirectoryManager.getModel((String) id);
//	}
//
//	public void prepareSurveyState() throws Exception {
//		prepareModel();
//	}
//
//	public void prepareExecute() throws Exception {
//		prepareModel();
//	}
	
}

package com.key.dwsurvey.controller.sysuser;


import com.key.common.base.entity.User;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import com.key.dwsurvey.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/sy/user/user-admin","/sy/user/nosm/user-admin"})
//@Namespaces({@Namespace("/sy/user"),@Namespace("/sy/user/nosm")})
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack") })
//@Results({
//	@Result(name= CrudActionSupport.SUCCESS,location="/WEB-INF/page/content/diaowen-useradmin/list.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= CrudActionSupport.INPUT,location="/WEB-INF/page/content/diaowen-useradmin/input.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= CrudActionSupport.RELOAD,location="/sy/user/user-admin.controller",type= Struts2Utils.REDIRECT)
//})
//@AllowedMethods({"checkLoginNamelUn","checkEmailUn"})
public class UserAdminController<ID extends Serializable>{// extends CrudActionSupport<User, String> {
	protected final static String USER_ROLE="userRole";
	@Autowired
	private UserManager userManager;

	private Page<User> page=new Page<User>();

	//TODO  id errors
	private String id;

	private User entity = new User();
	private List<PropertyFilter> filters=new ArrayList<PropertyFilter>();

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Map<String , Object> map) throws Exception {
		Page<User> page = new Page<>();
		try{
//			HttpServletRequest request= Struts2Utils.getRequest();
			String surveyState = request.getParameter("status");
			if(surveyState==null||"".equals(surveyState)){
				entity.setStatus(null);
			}else{
				entity.setStatus(Integer.parseInt(surveyState));
			}
			String pageNo = request.getParameter("page.pageNo");
			if (pageNo != null && !"".equals(pageNo)){
				page.setPageNo(Integer.parseInt(pageNo));
			}
			IPageToPage<User> iPageToPage = new IPageToPage<>(page.getPageNo(),page.getPageSize());
			page = userManager.findPage(iPageToPage,entity);
//			page=userManager.findPage(page,entity);
		}catch (Exception e) {
			e.printStackTrace();
		}
		map.put("page",page);
		return "/content/diaowen-useradmin/list";
//		return SUCCESS;
	}

	@RequestMapping("/input")
	public String input(HttpServletRequest request) throws Exception {
//		HttpServletRequest request= Struts2Utils.getRequest();
		return "/content/diaowen-useradmin/input";
//		return INPUT;
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request,String loginName,String name,String email, String pwd) throws Exception {
//		HttpServletRequest request= Struts2Utils.getRequest();
		User user = new User();
		user.setLoginName(loginName);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
//		this.entity = user;
		userManager.adminSave1(user,null);
		return "redirect:/sy/user/user-admin/list";
//		return RELOAD;
	}

	/**
	 * 账号禁用
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletResponse response,String id) throws Exception {
//		HttpServletResponse response= Struts2Utils.getResponse();
		String result="false";
		this.id = id;
		try{
			userManager.disUser( id);
			result="true";
		}catch (Exception e) {
			// TODO: handle exception
		}
		response.getWriter().write(result);
		return null;
	}

	@RequestMapping("/prepareModel")
	protected void prepareModel() throws Exception {
		entity = userManager.findById(id);
//		entity=userManager.getModel((String) id);

	}

	@RequestMapping("prepareExecute")
	public void prepareExecute() throws Exception {
		prepareModel();
	}
	
	@RequestMapping("/checkLoginNamelUn")
	public void checkLoginNamelUn(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		HttpServletRequest request= Struts2Utils.getRequest();
//		HttpServletResponse response= Struts2Utils.getResponse();
		String loginName=request.getParameter("loginName");
		User user=userManager.findNameUn((String) id,loginName);
		String result="true";
		if(user!=null){
			result="false";
		}
		response.getWriter().write(result);
	}

	@RequestMapping("/checkEmailUn")
	public void checkEmailUn(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		HttpServletRequest request= Struts2Utils.getRequest();
//		HttpServletResponse response= Struts2Utils.getResponse();
		String email=request.getParameter("email");
		User user=userManager.findEmailUn((String) id,email);
		String result="true";
		if(user!=null){
			result="false";
		}
		response.getWriter().write(result);
	}
	
}

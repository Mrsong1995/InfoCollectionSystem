package com.key.dwsurvey.controller.sysuser;


import com.key.common.base.entity.User;
import com.key.common.plugs.page.IPageToPage;
import com.key.common.plugs.page.Page;
import com.key.dwsurvey.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = {"/sy/user/user-admin","/sy/user/nosm/user-admin"})
public class UserAdminController {
	protected final static String USER_ROLE="userRole";
	@Autowired
	private UserManager userManager;

	//TODO  id errors
	private String id;

	private User entity = new User();

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Map<String , Object> map) throws Exception {
		Page<User> page = new Page<>();
		try{
			String surveyState = request.getParameter("status");
			if(surveyState==null||"".equals(surveyState)){
				entity.setStatus(null);
			}else{
				entity.setStatus(Integer.parseInt(surveyState));
			}
			entity.setLoginName(request.getParameter("loginName"));
			String pageNo = request.getParameter("page.pageNo");
			if (pageNo != null && !"".equals(pageNo)){
				page.setPageNo(Integer.parseInt(pageNo));
			}
			IPageToPage<User> iPageToPage = new IPageToPage<>(page.getPageNo(),page.getPageSize());
			page = userManager.findPage(iPageToPage,entity);
		}catch (Exception e) {
			e.printStackTrace();
		}
		map.put("page",page);
		return "/content/diaowen-useradmin/list";
	}

	@RequestMapping("/input")
	public String input(HttpServletRequest request) throws Exception {
		return "/content/diaowen-useradmin/input";
	}

	@RequestMapping("/add")
	public String add(HttpServletRequest request) throws Exception {
		return "/content/diaowen-useradmin/addUser";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request,String loginName,String name,String email, String pwd) throws Exception {
		User user = new User();
		user.setLoginName(loginName);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
		userManager.adminSave1(user,null);
		return "redirect:/sy/user/user-admin/list";
	}

	/**
	 * 账号禁用
	 */
	@RequestMapping("/disable")
	public String disable(HttpServletResponse response,String id) throws Exception {
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

	/**
	 * 账号删除
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletResponse response,String id) throws Exception {
		String result="false";
		try{
			userManager.deleteUser(id);
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

	}

	@RequestMapping("prepareExecute")
	public void prepareExecute() throws Exception {
		prepareModel();
	}
	
	@RequestMapping("/checkLoginNamelUn")
	public void checkLoginNamelUn(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
		String email=request.getParameter("email");
		String id = request.getParameter("id");
		User user=userManager.findEmailUn((String) id,email);
		String result="true";
		if(user!=null){
			result="false";
		}
		if (user!=null&&user.getId().equals(id)) {
			result="true";
		}
		response.getWriter().write(result);
	}

	@RequestMapping("/updateUser1")
	public void updateUser(HttpServletResponse response) throws Exception{
		//String id=request.getParameter("userId");
		User user = new User();
		user.setId("30cc73f324da11eb979d0242ac110002");
		user.setEmail("123@qq.com");
		user.setName("hahaha");
		user.setShaPassword("0000");
		user.setLoginName("xiaoming");
		int result = userManager.updateByUserId(user);
		response.getWriter().write(result);
	}

	@RequestMapping("/updateUser")
	public String update(HttpServletRequest request,String loginName,String name,String email, String pwd,String id) throws Exception {
		User user = new User();
		user.setLoginName(loginName);
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
		user.setId(id);
		userManager.adminUpdate(user,null);
		return "redirect:/sy/user/user-admin/list";
	}
	
}

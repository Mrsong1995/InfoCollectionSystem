package com.key.dwsurvey.controller.sysuser;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户中心 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */
@Controller
@RequestMapping("/ic/user")
//@Namespace("/ic")
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack")})
//@Results({
//	@Result(name= UserAction.MYACCOUNT,location="/WEB-INF/page/content/diaowen-center/my-account.jsp",type= Struts2Utils.DISPATCHER),
//		@Result(name="editPwd",location="/WEB-INF/page/content/diaowen-center/reset-pwd.jsp",type= Struts2Utils.DISPATCHER),
//		@Result(name= UserAction.SUCCESS,location="user!myaccount.controller",type= Struts2Utils.REDIRECT)
//})
//@AllowedMethods({"myaccount","pwd","updatePwd"})
public class UserController {//extends ActionSupport{
	
	public final static String MYACCOUNT="myaccount";

	@Autowired
	private AccountManager accountManager;

	@RequestMapping("/myaccount")
	public String myaccount(HttpServletRequest request) throws Exception {
//		HttpServletRequest request= Struts2Utils.getRequest();
		User user=accountManager.getCurUser();
		request.setAttribute("user", user);
//		return MYACCOUNT;
		return "/content/diaowen-center/my-account";
	}


	@RequestMapping("/pwd")
	public String pwd(HttpServletRequest request) throws Exception {
//		HttpServletRequest request = Struts2Utils.getRequest();
//		return "editPwd";
		return "/content/diaowen-center/reset-pwd";
	}

	@RequestMapping("/updatePwd")
	public String updatePwd(HttpServletRequest request) throws Exception {
//		HttpServletRequest request = Struts2Utils.getRequest();
		String curpwd=request.getParameter("curpwd");
		String newPwd = request.getParameter("pwd");
		//先检查原密码是否正确
		accountManager.updatePwd(curpwd,newPwd);
//		return SUCCESS;
		return "redirect:user!myaccount.controller";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request) throws Exception {
//		HttpServletRequest request= Struts2Utils.getRequest();
		User user=accountManager.getCurUser();
		String email = request.getParameter("email");
		String cellphone = request.getParameter("cellphone");
		String name = request.getParameter("name");
		user.setEmail(email);
		user.setCellphone(cellphone);
		user.setName(name);
		accountManager.saveUp(user);
//		return SUCCESS;
		return "redirect:user!myaccount.controller";
	}

}

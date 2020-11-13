package com.key.dwsurvey.controller.sysuser;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户中心 controller
 */
@Controller
@RequestMapping("/ic/user")
public class UserController {

	@Autowired
	private AccountManager accountManager;

	@RequestMapping("/myaccount")
	public String myaccount(HttpServletRequest request) throws Exception {
		User user=accountManager.getCurUser();
		request.setAttribute("user", user);
		return "/content/diaowen-center/my-account";
	}


	@RequestMapping("/pwd")
	public String pwd(HttpServletRequest request) throws Exception {
		return "/content/diaowen-center/reset-pwd";
	}

	@RequestMapping("/updatePwd")
	public String updatePwd(HttpServletRequest request) throws Exception {
		String curpwd=request.getParameter("curpwd");
		String newPwd = request.getParameter("pwd");
		//先检查原密码是否正确
		accountManager.updatePwd(curpwd,newPwd);
		return "redirect:user!myaccount.controller";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request) throws Exception {
		User user=accountManager.getCurUser();
		String email = request.getParameter("email");
		String cellphone = request.getParameter("cellphone");
		String name = request.getParameter("name");
		user.setEmail(email);
		user.setCellphone(cellphone);
		user.setName(name);
		accountManager.saveUp(user);
		return "redirect:user!myaccount.controller";
	}

}

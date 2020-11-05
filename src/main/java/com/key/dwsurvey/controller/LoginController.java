/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.key.dwsurvey.controller;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.plugs.security.FormAuthenticationWithLockFilter;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author keyuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 */
@Controller
//@Namespace("/")
//@Results({
//	@Result(name = "login", location = "login.jsp", type = "redirect")
//	})
//@AllowedMethods({"login","logout"})
public class LoginController extends ActionSupport {

	private static final long serialVersionUID = 7392913081177740732L;
	@Autowired
	private FormAuthenticationWithLockFilter formAuthFilter;
	@Autowired
	protected AccountManager accountManager;

	@RequestMapping("/")
	public String home(){
		return "forward:login.jsp";
	}

	@RequestMapping("/hello")
	public String hello(){
		return "forward:/WEB-INF/wjHtml/2020/07/08/40288005732c11b401732c28a8580000.html";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		System.out.println("username1-1");
//		HttpServletRequest request = Struts2Utils.getRequest();
//		HttpServletResponse response = Struts2Utils.getResponse();
		
		Subject subject = SecurityUtils.getSubject();
		boolean isAuth = subject.isAuthenticated();
		// 返回成功与否
		String error="";
		Long resetnum=0L;
		if (!isAuth) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
//			System.out.println(username+" "+password);
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			if(!formAuthFilter.checkIfAccountLocked(request)){
				try {
					subject.login(token);
					formAuthFilter.resetAccountLock(username);
					isAuth = true;
				}catch (IncorrectCredentialsException e) {
					formAuthFilter.decreaseAccountLoginAttempts(request);
		            isAuth = false;
		            error="IncorrectCredentialsException";
		            resetnum=formAuthFilter.getAccountLocked(username);
		        } catch (AuthenticationException e) {
		            isAuth = false;
		            error="AuthenticationException";
		        }
			}else{
				//ExcessiveAttemptsException超过登录次数
				 error="ExcessiveAttemptsException";
			}
		}
		//PrintWriter writer = response.getWriter();    
		//writer.write(isAuth + ","+error);//此种方式，在$.getJson()进行仿问时会出现不执行回调函数
//		System.out.println(isAuth+","+error);
		response.setContentType("text/html; charset=UTF-8");// 1.设置返回响应的类型
		//2. 01 一定要注意：要包括jsoncallback参数，不然回调函数不执行。
		//2. 02 返回的数据一定要是严格符合json格式 ，不然回调函数不执行。
        response.getWriter().write( request.getParameter("jsoncallback") + "({isAuth:'"+isAuth+"',error:'"+error+"',resetnum:'"+resetnum+"'})" );
		return "forward:login.jsp";
		// 跳转到成功页面
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) throws Exception {
		System.out.println("............logout..................");
		if (SecurityUtils.getSubject() != null) {
			SecurityUtils.getSubject().logout();
		}
		request.getSession().invalidate();
//		Struts2Utils.getSession().invalidate();
		return "forward:login.jsp";
	}
	/* 给某个锁定的账号开锁,管理员使用 */

	@RequestMapping("/lockout")
	public String lockout(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpServletRequest request=Struts2Utils.getRequest();
//		HttpServletResponse response=Struts2Utils.getResponse();
		String username=request.getParameter("username");
		//确认有没账号
		boolean isup=false;
		String error="用户不存在";
		
		if(username!=null){
//			User user=accountManager.findUserByLoginName(username);
			User user = accountManager.findUserByLoginNameOrEmail(username);
			if(user!=null){
				formAuthFilter.resetAccountLock(username);
				isup=true;
			}
		}
		response.getWriter().write(isup?username+"解锁成功":username+"，"+error);
		return null;
	}
	
	public String register() throws Exception {
		
		return "";
	}

}

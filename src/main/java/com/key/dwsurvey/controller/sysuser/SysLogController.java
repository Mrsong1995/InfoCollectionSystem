package com.key.dwsurvey.controller.sysuser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统日志分析 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */
@Controller
@RequestMapping(value = {"/sy/system/sys-log","/sy/system/nosm/sys-log"})
//@Namespaces({@Namespace("/sy/system"),@Namespace("/sy/system/nosm")})
//@InterceptorRefs({ @InterceptorRef("paramsPrepareParamsStack") })
//@Results({
//	@Result(name= CrudActionSupport.SUCCESS,location="/WEB-INF/page/diaowen-system/log-list.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= CrudActionSupport.INPUT,location="/WEB-INF/page/diaowen-system/log-list.jsp",type= Struts2Utils.DISPATCHER),
//	@Result(name= CrudActionSupport.RELOAD,location="/WEB-INF/page/ref-parent.jsp",type= Struts2Utils.DISPATCHER)
//})
public class SysLogController {// extends CrudActionSupport<SysLog, String> {

	@RequestMapping("/input")
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "/diaowen-system/log-list";
//		return super.input();
	}
	
	@RequestMapping("/list")
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return "/diaowen-system/log-list";
//		return super.list();
	}
	
}

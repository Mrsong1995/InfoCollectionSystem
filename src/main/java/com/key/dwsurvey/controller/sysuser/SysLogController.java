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
public class SysLogController {

	@RequestMapping("/input")
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "/diaowen-system/log-list";
	}
	
	@RequestMapping("/list")
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return "/diaowen-system/log-list";
	}
	
}

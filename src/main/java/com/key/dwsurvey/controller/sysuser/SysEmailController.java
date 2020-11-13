package com.key.dwsurvey.controller.sysuser;

import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import com.key.dwsurvey.entity.SysEmail;
import com.key.dwsurvey.service.SysEmailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统邮件集成 controller
 */
@Controller
@RequestMapping(value = {"/sy/system/sys-email","/sy/system/nosm/sys-email"})
public class SysEmailController<ID extends Serializable> {

	@Autowired
	private SysEmailManager sysEmailManager;

	private Page<SysEmail> page=new Page<SysEmail>();

	//TODO  id errors
	private ID id;

	private SysEmail entity = new SysEmail();
	private List<PropertyFilter> filters=new ArrayList<PropertyFilter>();

	@RequestMapping("/input")
	public String input() throws Exception {
		return "page/diaowen-system/email-input";
	}
	
	@RequestMapping("/list")
	public String list() throws Exception {
		page=sysEmailManager.findPage(page, filters);
		return "/diaowen-system/email-list";
	}
	

	@RequestMapping("/save")
	public String save() throws Exception {
		try{
			sysEmailManager.save(entity);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/ref-parent";
	}
	

	protected void prepareModel() throws Exception {
		entity=sysEmailManager.getModel((String) id);
	}
}

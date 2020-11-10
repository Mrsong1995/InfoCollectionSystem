package com.key.dwsurvey.controller.sysuser;

import com.key.common.plugs.page.Page;
import com.key.common.plugs.page.PropertyFilter;
import com.key.dwsurvey.entity.SysDbBackup;
import com.key.dwsurvey.service.SysDbBackupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统备份 controller
 */

@Controller
@RequestMapping(value = {"/sy/system/nosm/sys-backup","/sy/system/sys-backup"})
public class SysBackupController<ID extends Serializable>{
	
	@Autowired
	private SysDbBackupManager sysDbBackupManager;

	private Page<SysDbBackup> page=new Page<SysDbBackup>();

	//TODO  id errors
	private ID id;

	private SysDbBackup entity = new SysDbBackup();
	private List<PropertyFilter> filters=new ArrayList<PropertyFilter>();

	@RequestMapping("/input")
	public String input() throws Exception {
		return "/diaowen-system/backup-input";
	}
	
	@RequestMapping("/list")
	public String list() throws Exception {
		page=sysDbBackupManager.findPage(page, filters);
		return "/diaowen-system/backup-list";
	}
	
	@RequestMapping("/save")
	public String save() throws Exception {
		sysDbBackupManager.save(entity);
		return "/ref-parent";
	}
	

	protected void prepareModel() throws Exception {
		//TODO
		entity=sysDbBackupManager.getModel((String) id);
	}
}

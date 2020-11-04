package com.key.common.plugs.security;

import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class MyDefaultWebSecurityManager extends DefaultWebSecurityManager{
	@Autowired
	private AccountManager accountManager;
	
	public String getUserId(){
		User user=accountManager.getCurUser();
		if(user!=null){
			return user.getId();
		}
		return null;
	}
	
}

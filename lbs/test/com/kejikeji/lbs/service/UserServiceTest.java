package com.kejikeji.lbs.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kejikeji.common.test.BaseSpringTestCase;
import com.kejikeji.lbs.model.LocationCatalog;
import com.kejikeji.lbs.model.User;

public class UserServiceTest extends BaseSpringTestCase {
	
	static Log log = LogFactory.getLog(UserServiceTest.class);

	protected UserService userService;
	
	public void testRegister() {
		User user=new User();
		user.setName("中文");
		user.setPasswd("haha");
		
		user.setCurLocation(new LocationCatalog("860010",""));
		
		userService.register(user);
		
		super.setComplete();
	}

	public void testLogin() {
		User user=userService.login("中文", "haha");
		log.info(user.getName());
	}

}

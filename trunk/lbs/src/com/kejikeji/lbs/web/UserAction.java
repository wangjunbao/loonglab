package com.kejikeji.lbs.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;


import com.kejikeji.common.JsonUtil;
import com.kejikeji.common.web.BaseMultiActionController;
import com.kejikeji.lbs.model.User;
import com.kejikeji.lbs.service.UserService;

public class UserAction extends BaseMultiActionController {
	
	private UserService userService;
	
	
	
	public UserService getUserService() {
		return userService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public ModelAndView login( HttpServletRequest request, HttpServletResponse response ){
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		User user=userService.login(userName, password);
		
		if(user==null){
			response.setStatus(400);
		}
		
		//TODO 10021需要定义常量
		try {
			response.getWriter().print(JsonUtil.getJsonString("result", "10021"));
			response.flushBuffer();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		return null;
		
	}
}

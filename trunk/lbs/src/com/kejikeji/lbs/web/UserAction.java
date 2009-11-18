package com.kejikeji.lbs.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.kejikeji.common.web.BaseMultiActionController;
import com.kejikeji.lbs.model.LocationCatalog;
import com.kejikeji.lbs.model.User;
import com.kejikeji.lbs.service.UserService;
import com.kejikeji.lbs.view.bean.Result;

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
		
		int resultCode=Result.SUCCESS;
		int responseStatus=200;
		
		if(user==null){
			responseStatus=400;
			resultCode=Result.E_USER_LOGIN_FAILED;			
		}
		

		response.setStatus(responseStatus);
		try {
			response.getWriter().print(new JSONObject(new Result(resultCode)).toString());
			response.flushBuffer();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		return null;
		
	}
	
	public ModelAndView register( HttpServletRequest request, HttpServletResponse response ){
		
		User user=new User();
		
		ServletRequestDataBinder binder = new ServletRequestDataBinder( user );
		binder.bind(request);
		
		String curLocationCode=request.getParameter("curLocation");
		user.setCurLocation(new LocationCatalog(curLocationCode,""));
		
//		int responseStatus=200;
		int resultCode=userService.register(user);
		

//		response.setStatus(responseStatus);
		try {
			response.getWriter().print(new JSONObject(new Result(resultCode)).toString());
			response.flushBuffer();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		return null;
		
	}
}

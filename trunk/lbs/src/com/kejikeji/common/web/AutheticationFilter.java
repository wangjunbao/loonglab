package com.kejikeji.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AutheticationFilter implements Filter {

	static Log log = LogFactory.getLog(AutheticationFilter.class);
	private FilterConfig config;
	/**
	 * 登陆页面
	 */
	private String loginPage;
	
	
	public void destroy() {
		

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {		
		
		//log.debug("==============enter authenticationFitler==================");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		
		
		Object obj=req.getSession().getAttribute("sms.user");
		if(obj==null){
			res.sendRedirect(req.getContextPath()+loginPage);
			return;
			
		}
		
		chain.doFilter(request,response);
		
		
		
	}
	

	public void init(FilterConfig arg0) throws ServletException {
		config=arg0;
		loginPage=config.getInitParameter("loginPage");
	}

}

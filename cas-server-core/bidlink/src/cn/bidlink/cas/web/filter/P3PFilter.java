package cn.bidlink.cas.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class P3PFilter implements Filter{

	Log log = LogFactory.getLog(P3PFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
		log.debug("===========enter p3pFitler==========");
		
		HttpServletResponse res=(HttpServletResponse)arg1;
		res.setHeader("P3P","CP=CAO PSA OUR");
		arg2.doFilter(arg0, res);
		
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

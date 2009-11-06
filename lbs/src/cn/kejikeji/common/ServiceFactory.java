package cn.kejikeji.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceFactory implements ApplicationContextAware{
	
	static ApplicationContext appContext;
	
	public void  setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		appContext=arg0;
		
	}
	
	public static Object getBean(String type){
		return appContext.getBean(type);
	}

}

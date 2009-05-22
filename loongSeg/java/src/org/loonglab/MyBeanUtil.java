package org.loonglab;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;



public class MyBeanUtil {
	public static String getPropertyAsString(Object obj,String propertyName){
		try {
			return BeanUtils.getProperty(obj, propertyName);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static Object getProperty(Object obj,String propertyname){
		try {
			return PropertyUtils.getProperty(obj, propertyname);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static void setProperty(Object obj,String propertyName,Object propertyValue){
		try {
			BeanUtils.setProperty(obj, propertyName, propertyValue);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static Object getInstance(String className){
		try {
			Class clazz=Class.forName(className);
			return clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	public static Class getClass(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public static Object cloneBean(Object obj){
		try {
			return BeanUtils.cloneBean(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}

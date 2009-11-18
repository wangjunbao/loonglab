package com.kejikeji.lbs.view.bean;

/**
 * 
 * 返回结果
 * @author liugang
 *
 */
public class Result {
	
	//TODO 确定参数名
	public static int SUCCESS=0;
	public static int UNKNOWN_ERROR=-1;
	public static int E_USER_REGISTER_USER_EXIST=10011;
	public static int E_USER_REGISTER_EMAIL_EXIST=10012;
	public static int E_USER_LOGIN_FAILED=10021;
	
	private int resultCode;
	
	
	

	public Result(int resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	
}

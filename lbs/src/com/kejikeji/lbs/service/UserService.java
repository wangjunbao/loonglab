package com.kejikeji.lbs.service;

import com.kejikeji.lbs.model.User;

public interface UserService {
	/**
	 * 注册
	 * @param user
	 */
	public int register(User user);
	
	/**
	 * 登录请求
	 * @param user
	 * @return
	 */
	public User login(String username,String passwd);

}

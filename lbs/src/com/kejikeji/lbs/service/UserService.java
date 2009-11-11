package com.kejikeji.lbs.service;

import com.kejikeji.lbs.model.User;

public interface UserService {
	/**
	 * 注册
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * 登录请求
	 * @param user
	 * @return
	 */
	public boolean login(User user);

}

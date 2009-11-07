package com.kejikeji.lbs.service;

import com.kejikeji.lbs.model.User;

public interface UserService {
	/**
	 * ×¢²á
	 * @param user
	 */
	public void register(User user);
	
	/**
	 * µÇÂ¼ÇëÇó
	 * @param user
	 * @return
	 */
	public boolean login(User user);

}

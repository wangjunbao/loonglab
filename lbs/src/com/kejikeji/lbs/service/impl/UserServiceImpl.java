package com.kejikeji.lbs.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.kejikeji.common.dao.CommonDaoSupport;
import com.kejikeji.lbs.model.User;
import com.kejikeji.lbs.service.UserService;

public class UserServiceImpl extends CommonDaoSupport implements UserService {

	@Override
	public User login(String username,String passwd) {
		String passMd5=DigestUtils.md5Hex(passwd);
		List<User> userList=dao.find("from User u where u.name=? and u.passwd=?",username,passMd5);
		if(userList.size()>0)
			return userList.get(0);
		return null;
	}

	@Override
	public void register(User user) {
		dao.save(user);

	}

}

package com.kejikeji.lbs.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import sun.security.provider.MD5;

import com.kejikeji.common.dao.CommonDaoSupport;
import com.kejikeji.lbs.model.User;
import com.kejikeji.lbs.service.UserService;
import com.kejikeji.lbs.view.bean.Result;

public class UserServiceImpl extends CommonDaoSupport implements UserService {

	@Override
	public User login(String username,String passwd) {
		String passMd5=DigestUtils.md5Hex(passwd);
		List<User> userList=dao.find("from User u where u.userName=? and u.password=?",username,passMd5);
		if(userList.size()>0)
			return userList.get(0);
		return null;
	}

	@Override
	public int register(User user) {
		List<User> userList=dao.find("from User u where u.userName=?",user.getUserName());
		if(userList.size()>0)
			return Result.E_USER_REGISTER_USER_EXIST;
		
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		dao.save(user);
		return Result.SUCCESS;

	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("123").substring(8,24)+",202cb962ac59075b964b07152d234b70");

		
	}

}

package com.kejikeji.lbs.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import sun.security.provider.MD5;

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
		user.setPasswd(DigestUtils.md5Hex(user.getPasswd()));
		dao.save(user);

	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("123").substring(8,24)+",202cb962ac59075b964b07152d234b70");

		
	}

}

package com.yeahwap.netgame.hessian.impl;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.hessian.UserHessianService;
import com.yeahwap.netgame.hessian.pojo.UserHessian;
import com.yeahwap.netgame.service.UserService;

/**
 * Create on 2012-10-13 17:00
 * 
 * @author Harry
 *         <ul>
 *         <li>Title: UserHessianServiceImpl.java</li>
 *         <li>description: 用户远程用户的添加和修改，当hessian的client和server都在一台机器上，可以直接使用这个service或者使用userService来处理</li>
 *         </ul>
 * 
 */
@Service
public class UserHessianServiceImpl implements UserHessianService {
	@Resource
	private UserService userService;

	@Override
	public boolean add(Serializable obj) {
		System.out.println("add obj");
		UserHessian uh = (UserHessian) obj;
		User user = new User();
		user.setName(uh.getName());
		user.setPassword(uh.getPassword());
		user.setInitFromid(1);
		user.setDateline(new Date());
		user.setIsview(0);
		user.setType(0);
		userService.add(user);
		return true;
	}

	@Override
	public boolean update(Serializable obj) {
		System.out.println("update obj");
		return true;
	}
}

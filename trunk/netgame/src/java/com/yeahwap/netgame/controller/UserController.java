package com.yeahwap.netgame.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeahwap.netgame.Constants;
import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.hessian.UserHessianService;
import com.yeahwap.netgame.hessian.pojo.UserHessian;
import com.yeahwap.netgame.service.UserService;

/**
 * Create on 2012-10-13 19:16
 * 
 * @author Harry
 * 
 *         <ul>
 *         <li>Title: UserController.java</li>
 *         <li>处理游戏用户的相关操作，针对远程的add和update必须使用getService() 来处理，</li>
 *         <li>针对我们后台的管理，可以注入UserService来处理</li>
 *         </ul>
 * 
 */
@Controller
public class UserController {
	//先名称再按类型装配，这里使用到类型的装配
	@Resource
	private UserHessianService userHessianService;
	@Resource
	private UserHessianService userHessianServiceImpl;
	@Resource 
	private UserService userService;
	
	
	@RequestMapping(value = "/sdk/userRegister.do", method = RequestMethod.GET, params = {"name", "password" })
	public String userRegister(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest req) {
		
		UserHessian user = new UserHessian();
		user.setName(name);
		user.setPassword(password);
		user.setInitFromid(1);
		user.setDateline(new Date());
		user.setMobile("");
		user.setEmail("");
		user.setScore(0);
		user.setIsview(0);
		user.setType(0);
		user.setWeiboId("");
		user.setToken("");
		user.setSecret("");
		User u = getService().add(user);
		req.setAttribute("user", u);
		return "userregister";
	}

	private UserHessianService getService() {
		if ("hessian".equals(Constants.METHOD)) {
			return this.userHessianService;
		}

		if ("local".equals(Constants.METHOD)) {
			return this.userHessianServiceImpl;
		}

		return null;
	}
}

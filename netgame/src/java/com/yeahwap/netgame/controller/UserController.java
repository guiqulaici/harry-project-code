package com.yeahwap.netgame.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeahwap.netgame.hessian.UserHessianService;
import com.yeahwap.netgame.hessian.pojo.UserHessian;


@Controller
public class UserController {
	@Resource
	private UserHessianService userHessianService;
	@RequestMapping(value = "/sdk/userRegister.do", method = RequestMethod.GET, params = {"name", "password" })
	public String userRegister(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest req) {
		UserHessian user = new UserHessian();
		user.setName(name);
		user.setPassword(password);
		user.setInitFromid(1);
		user.setDateline(new Date());
		user.setIsview(0);
		user.setType(0);
		boolean flag = userHessianService.add(user);
		req.setAttribute("return", flag);
		return "userregister";
	}
}

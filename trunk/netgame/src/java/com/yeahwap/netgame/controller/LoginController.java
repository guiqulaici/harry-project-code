package com.yeahwap.netgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeahwap.netgame.domain.pojo.Operator;
import com.yeahwap.netgame.service.OperatorService;

/**
 * Create on 2012-10-10 7:52
 * 
 * @author Harry
 *         <ul>
 *         <li>Title: LoginCntroller.java</li>
 *         <li>description: 处理后台登录的控制类</li
 *         </ul>
 */

@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private OperatorService operatorService;

	@RequestMapping(value = "login.do", method = RequestMethod.POST, params = {
			"username", "password" })
	public String login(@RequestParam("username") String name,
			@RequestParam("password") String password, ModelMap modelmap) {
		System.out.println(name + ";" + password);

		// !查询数据库
		Operator operator = operatorService.get(1);

		List<String> list = new ArrayList<String>();
		list.add(operator.getName());
		list.add(operator.getPassword());
		modelmap.put("return", list);

		return "login";
	}

}

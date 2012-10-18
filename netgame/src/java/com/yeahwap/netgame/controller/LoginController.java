package com.yeahwap.netgame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView login(@RequestParam("username") String name,
			@RequestParam("password") String password, ModelMap modelmap) {
		System.out.println(name + ";" + password);

		// !查询数据库
		Operator operator = operatorService.get(1);

		List<String> list = new ArrayList<String>();
		list.add(operator.getName());
		list.add(operator.getPassword());
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("login");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("return", list);
		
		List<String> list1 = new ArrayList<String>();
		list1.add("c");
		list1.add("d");
		map.put("aaa", list1);
		mav.addAllObjects(map);
		
		
		return mav;
	}

}

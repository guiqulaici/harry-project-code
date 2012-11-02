package com.spring.mvc.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mvc.domain.SZfcard;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("login.do")
	public String login(Model model) {
		SZfcard card = new SZfcard();
		model.addAttribute(card);
		System.out.println("key=" + Conventions.getVariableName(card));
		System.out.println(Conventions.getVariableName("abc"));
		System.out.println(Conventions.getVariableName(100));
		return "login";
	}
}

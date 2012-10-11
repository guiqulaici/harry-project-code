package com.yeahwap.netgame.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.service.UserService;


@Controller
@RequestMapping("/sdk")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "userRegister.do", method = RequestMethod.GET, params = {"name", "password" })
	public String userRegister(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest req) {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setInitFromid(1);
		user.setDateline(new Date());
		user.setIsview(0);
		user.setType(0);
		userService.add(user);
		req.setAttribute("return", "success");
		return "userregister";
	}
}

//CREATE TABLE `user` (
//`id` INT(11) NOT NULL AUTO_INCREMENT,
//`name` VARCHAR(20) NOT NULL DEFAULT 'a' COMMENT '平台用户名',
//`password` VARCHAR(20) NOT NULL DEFAULT 'a' COMMENT '密码',
//`init_fromid` INT(11) NOT NULL DEFAULT '0' COMMENT '平台用户注册时的初始来源',
//`dateline` DATETIME NOT NULL DEFAULT '2012-07-01 01:01:01',
//`mobile` VARCHAR(20) NULL DEFAULT 'a',
//`email` VARCHAR(50) NULL DEFAULT 'a',
//`score` INT(11) NULL DEFAULT '0' COMMENT '账号的金额',
//`isview` INT(11) NOT NULL DEFAULT '0' COMMENT '0、启用，1、黑名单',
//`type` INT(11) NOT NULL DEFAULT '0' COMMENT '账号类型：1，本地账户，2,新浪微博，3，腾讯微博',
//`weibo_id` VARCHAR(50) NULL DEFAULT 'a',
//`token` VARCHAR(100) NULL DEFAULT 'a',
//`secret` VARCHAR(100) NULL DEFAULT 'a',
//PRIMARY KEY (`id`)
//)
//COLLATE='utf8_general_ci'
//ENGINE=InnoDB;

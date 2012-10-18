package com.yeahwap.netgame.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;// 必须引用此包

import com.yeahwap.netgame.Constants;
import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.hessian.UserHessianService;
import com.yeahwap.netgame.service.UserService;
import com.yeahwap.netgame.util.StringUtil;

/**
 * Create on 2012-10-13 19:16
 * 
 * @author Harry
 * 
 *         <ul>
 *         <li>Title: UserController.java</li>
 *         <li>处理游戏用户的相关操作，针对远程的add和update必须使用getService() 来处理，</li>
 *         <li>针对我们服务端的管理，可以注入UserService来处理</li>
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
	public String userRegister(
			@RequestParam("name") String name,
			@RequestParam("password") String password, 
			HttpServletRequest req) {
		// 查看当前注册用户是否已经存在
		User oldUser = userService.getUserByName(name);
		User u = null;

		if (oldUser == null) {
			User user = new User();
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
			u = getService().add(user);
		}

		req.setAttribute("user", u);
		return "userregister";
	}
	
	@RequestMapping(value = "/sdk/userLogin.do", method = RequestMethod.GET, params = {"name", "password" })
	public String userLogin(
			@RequestParam("name") String name,
			@RequestParam("password") String password, 
			ModelMap model) {
		User user = userService.getUserByNameAndPassword(name, password);
		model.put("user", user);
		return "userlogin";
	}
	
	@RequestMapping("/sdk/userUpdate.do")
	public ModelAndView userUpdate(
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword,
			@RequestParam("uid") String uid, 
			@RequestParam("name") String name,
			HttpServletRequest request) {
		int id = StringUtil.getInt(uid, 0);
		User olduser = userService.getUserByIdAndName(id, name, oldpassword);
		User user = null;
		
		if (olduser != null) {
			if (!oldpassword.equals(newpassword)) {
				olduser.setPassword(newpassword);
			} else {
				olduser.setPassword(oldpassword);
			}

			olduser.setInitFromid(1);
			olduser.setDateline(new Date());
			olduser.setMobile("");
			olduser.setEmail("");
			olduser.setScore(0);
			olduser.setIsview(0);
			olduser.setType(0);
			olduser.setWeiboId("");
			olduser.setToken("");
			olduser.setSecret("");
			user = getService().update(olduser);
		}

		Map<String, User> map = new HashMap<String, User>();
		map.put("user", user);
		return new ModelAndView("userupdate", map);
	}
	
	@RequestMapping(value = "/sdk/userFind.do", params = { "name", "email" }, method = RequestMethod.GET)
	public ModelAndView userFind(
			@RequestParam("name") String name,
			@RequestParam("email") String email) {
		User user = userService.getUserByNameAndEmail(name, email);
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", user);
		mav.setViewName("userfindpassword");
		return mav;
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
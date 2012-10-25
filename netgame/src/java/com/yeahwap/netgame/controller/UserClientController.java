package com.yeahwap.netgame.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yeahwap.netgame.Constants;
import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.hessian.AccessHessianService;
import com.yeahwap.netgame.hessian.UserHessianService;
import com.yeahwap.netgame.hessian.domain.AccessLogType;
import com.yeahwap.netgame.hessian.domain.pojo.AccessLog;
import com.yeahwap.netgame.service.UserService;
import com.yeahwap.netgame.util.HttpUtil;
import com.yeahwap.netgame.util.StringUtil;

/**
 * Create on 2012-10-13 19:16
 * 
 * @author Harry
 * 
 *         <ul>
 *         <li>Title: UserClientController.java</li>
 *         <li>处理游戏用户的相关操作，针对远程的add和update必须使用getService() 来处理，</li>
 *         <li>针对我们服务端的管理，可以注入UserService来处理</li>
 *         </ul>
 * 
 */
@Controller
@RequestMapping("/sdk")
public class UserClientController {
	@Resource
	private UserHessianService userHessianService;
	@Resource
	private UserHessianService userHessianServiceImpl;
	@Resource
	private AccessHessianService accessHessianService;
	@Resource
	private AccessHessianService accessHessianServiceImpl;
	
	@Resource
	private UserService userService;

	@RequestMapping(value = "/userRegister.do",method = RequestMethod.POST , params = { "name", "password",
			"initFromid" })
	public String userRegister(@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("initFromid") String initFromid,
			HttpServletRequest req, @RequestHeader("header-valid") String key) {
		// Enumeration enumeration = req.getHeaderNames();
		// while(enumeration.hasMoreElements()) {
		// String headName = (String) enumeration.nextElement();
		// System.out.println(headName + ":" + req.getHeader(headName));
		// }
		if (!Constants.HEADER_VALID.equals(key)) {
			return "redirect:error";
		}

		User oldUser = userService.getUserByName(vaild(name));
		User u = null;

		if (password == null || ("").equals(vaild(password))) {
			password = "123456";
		}

		if (oldUser == null) {
			User user = new User();
			user.setName(vaild(name));
			user.setPassword(vaild(password));
			int initFromId = StringUtil.getInt(initFromid, 0);
			user.setInitFromid(initFromId);
			user.setDateline(new Date());
			user.setPhone(req.getParameter("phone"));
			user.setEmail(req.getParameter("email"));
			user.setScore(0);
			user.setIsview(0);
			user.setType(1);
			user.setWeiboId("");
			user.setToken("");
			user.setSecret("");
			u = getService().add(user);
			AccessLog log = getAccessLog(u.getId(), AccessLogType.REGISTER, initFromId, req);
			getAccessService().add(log);
		}
		
		req.setAttribute("user", u);
		return "userregister";
	}

	@RequestMapping(value = "/userLogin.do" ,method = RequestMethod.POST, params = { "name", "password",
			"fromid" })
	public String userLogin(@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("fromid") String fromid, ModelMap model,
			@RequestHeader("header-valid") String key, HttpServletRequest request) {
		
		if (!Constants.HEADER_VALID.equals(key)) {
			return "redirect:error";
		}

		User user = userService.getUserByNameAndPassword(vaild(name), vaild(password));
		
		if (user != null) {
			AccessLog log = getAccessLog(user.getId(), AccessLogType.LOGIN, StringUtil.getInt(fromid, 0), request);
			getAccessService().add(log);
		}
		
		model.put("user", user);
		return "userlogin";
	}

	@RequestMapping(value = "/userUpdate.do", method = RequestMethod.POST)
	public ModelAndView userUpdate(@RequestParam("uid") String uid,
			@RequestParam("name") String name,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("fromid") String fromid, HttpServletRequest request,
			@RequestHeader("header-valid") String key) {

		if (!Constants.HEADER_VALID.equals(key)) {
			return new ModelAndView("error");
		}

		int id = StringUtil.getInt(uid, 0);
		User olduser = userService.getUserByIdAndName(id, vaild(name),
				vaild(oldpassword));
		User user = null;
		String newpassword = request.getParameter("newpassword");

		if (olduser != null) {
			if (newpassword != null && !("").equals(newpassword)
					&& !oldpassword.equals(newpassword)) {
				olduser.setPassword(vaild(newpassword));
			}

			olduser.setDateline(new Date());
			olduser.setPhone(request.getParameter("phone"));
			olduser.setEmail(request.getParameter("email"));
			olduser.setWeiboId("");
			olduser.setToken("");
			olduser.setSecret("");
			user = getService().update(olduser);
			
			AccessLog log = getAccessLog(user.getId(), AccessLogType.UPDATE, StringUtil.getInt(fromid, 0), request);
			getAccessService().add(log);
		}

		Map<String, User> map = new HashMap<String, User>();
		map.put("user", user);
		return new ModelAndView("userupdate", map);
	}

	@RequestMapping(value = "/userFind.do", params = { "name", "email" }, method = RequestMethod.POST)
	public ModelAndView userFind(@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("fromid") String fromid,
			@RequestHeader("header-valid") String key, HttpServletRequest request) {

		if (!Constants.HEADER_VALID.equals(key)) {
			return new ModelAndView("error");
		}

		User user = userService.getUserByNameAndEmail(vaild(name), email);
		
		if (user != null) {
			AccessLog log = getAccessLog(user.getId(), AccessLogType.FIND, StringUtil.getInt(fromid, 0), request);
			getAccessService().add(log);
		}
		
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
	
	private AccessHessianService getAccessService() {
		if ("hessian".equals(Constants.METHOD)) {
			return this.accessHessianService;
		}

		if ("local".equals(Constants.METHOD)) {
			return this.accessHessianServiceImpl;
		}
		return null;
	}

	@RequestMapping(value = "/error")
	public String returnError() {
		System.out.println("1");
		return "error";
	}
	
	private String vaild(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest.toLowerCase();
	}
	

	private AccessLog getAccessLog(int uid, byte type, int fromid, HttpServletRequest request) {
		AccessLog log = new AccessLog();
		log.setUid(uid);
		log.setFromid(fromid);
		log.setIp(HttpUtil.getRemoteIP(request));
		log.setMobile(request.getParameter("mobile"));
		log.setAccessTime(new Date());
		log.setType(type);
		return log;
	}

}

package com.yeahwap.netgame.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeahwap.netgame.domain.SzfCardType;
import com.yeahwap.netgame.domain.pojo.NgFrom;
import com.yeahwap.netgame.domain.pojo.User;
import com.yeahwap.netgame.service.NgFromService;
import com.yeahwap.netgame.service.UserService;
import com.yeahwap.netgame.util.StringUtil;


/**
 * 客户端调用支付页面
 * 
 * @author Harry
 * @version 1.0 2012/10/25
 * @since JDK1.6
 */
@Controller
@RequestMapping("/pay")
public class PayController {
	@Resource
	private UserService userService;
	
	@Autowired
	@Qualifier("ngFromService")
	private NgFromService ngFromService;
	
	@RequestMapping(value = "/pay.do", method = RequestMethod.GET, params = {"uid", "fromid" })
	public String validPay(@RequestParam("uid") String uid, @RequestParam("fromid") String fromid, Model model) {
		if ("".equals(uid) || uid == null || "".equals(fromid) || fromid == null) {
			// 当前路径:http://localhost:8080/netgame/pay
			return "redirect:1007/error";
		}
		
		// 验证用户是否存在
		int id = StringUtil.getInt(uid, 0);
		User user = userService.get(id);
		if (user == null) {
			return "redirect:1006/error";
		}
		
		// 验证fromid是否存在
		int fromID = StringUtil.getInt(fromid, 0);
		NgFrom nf = ngFromService.get(fromID);
		if (nf == null) {
			return "redirect:1008/error";
		}
		
		model.addAttribute("uid", uid);
		model.addAttribute("fromid", fromid);
		model.addAttribute("yidong", SzfCardType.YIDONG);
		model.addAttribute("liantong", SzfCardType.LIANTONG);
		model.addAttribute("dianxin", SzfCardType.DIANXING);
		
		return "/pay/pay";
	}
	
	@RequestMapping("{errorCode}/error")
	public String Error(@PathVariable("errorCode") String code, Model model) {
		model.addAttribute("return", code);
		
		return "pay/error/error";
	}
}

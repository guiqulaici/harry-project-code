package com.yeahwap.netgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeahwap.netgame.domain.SzfCard;
import com.yeahwap.netgame.domain.SzfCardType;

/**
 * The docking interface for szf!
 * 
 * @author Harry
 * @version 1.0 2012/10/25
 * @since JDK1.6
 */
@Controller
@RequestMapping("/szf")
public class PaySzfController {
	@RequestMapping(value="{uid}/{fromid}/yidong.do")
	public String sendYiDong(@PathVariable("uid") String uid, @PathVariable("fromid") String fromid, ModelMap modelMap) {
		System.out.println(uid + ";" + fromid);
		SzfCard szfCard = new SzfCard();
		szfCard.setCardType(SzfCardType.YIDONG);
		modelMap.addAttribute("card", szfCard);
		return "pay/szfsend";
	} 
	
	@RequestMapping(value="{fromid}/{uid}/payYidong.do")
	public String payYiDong(@PathVariable("uid") String uid, @PathVariable("fromid") String fromid, @ModelAttribute("yidongCardInfo") SzfCard szfCard) {
		System.out.println(uid + ";" + fromid);
		System.out.println(szfCard.toString());
		return "";
	}
}

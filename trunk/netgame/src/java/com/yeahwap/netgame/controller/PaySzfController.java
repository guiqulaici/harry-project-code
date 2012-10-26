package com.yeahwap.netgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeahwap.netgame.domain.SzfCardType;
import com.yeahwap.netgame.domain.pojo.SzfOrder;

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
	public String sendPay(@PathVariable("uid") String uid, @PathVariable("fromid") String fromid, ModelMap modelMap) {
		System.out.println(uid + ";" + fromid);
		SzfOrder szfOrder = new SzfOrder();
		szfOrder.setCardTypeCombine(SzfCardType.YIDONG);
		modelMap.put("so", szfOrder);
		return "pay/szfsend";
	} 
	
	// @RequestMapping(value="")
}

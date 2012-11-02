package com.yeahwap.netgame.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwap.netgame.domain.MerInfo;
import com.yeahwap.netgame.domain.OrderType;
import com.yeahwap.netgame.domain.SzfCard;
import com.yeahwap.netgame.domain.SzfErrorCode;
import com.yeahwap.netgame.domain.pojo.Merchant;
import com.yeahwap.netgame.domain.pojo.Order;
import com.yeahwap.netgame.domain.pojo.SzfOrder;
import com.yeahwap.netgame.service.MerchantService;
import com.yeahwap.netgame.service.OrderService;
import com.yeahwap.netgame.service.SzfOrderService;
import com.yeahwap.netgame.szf.ServerConnSzxUtils;
import com.yeahwap.netgame.util.StringUtil;

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
	public static final SimpleDateFormat DAYFORMAT = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	@Qualifier("merchantService")
	private MerchantService merchantService;
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@Autowired
	@Qualifier("szfOrderService")
	private SzfOrderService szfOrderService;
	
	@RequestMapping(value="{uid}/{fromid}/{cardType}/szfSend.do")
	public String szfSend(@PathVariable("uid") String uid, @PathVariable("fromid") String fromid, @PathVariable("cardType") byte cardType, ModelMap modelMap) {
		SzfCard szfCard = new SzfCard();
		szfCard.setCardType(cardType);
		modelMap.addAttribute("szfCard", szfCard);
		modelMap.put("uid", uid);
		modelMap.put("fromid", fromid);
		// System.out.println("uid=" + uid + ";fromid=" + fromid);
		return "pay/szfsend";
	}

	@RequestMapping(value="{fromid}/{uid}/szfValid.do")
	public String szfValid(@PathVariable("uid") int uid, @PathVariable("fromid") int fromid, @Valid SzfCard szfCard , BindingResult br, ModelMap modelMap) {
		// System.out.println(szfCard.toString());
		modelMap.put("uid", uid);
		modelMap.put("fromid", fromid);
		
		if (br.hasErrors()) {
			return "pay/szfsend";
		}
		
		// 跳转到确认页面
		modelMap.put("szfCard", szfCard);
		return "pay/szfsendpay";
	}
	
	@RequestMapping("/ajax/{fromid}/{uid}/{cardType}/{cardSN}/{cardMoney}/{cardPassword}/szfpay.do")
	@ResponseBody
	public Object payAjax(@PathVariable("fromid") int fromid,
			@PathVariable("uid") int uid,
			@PathVariable("cardType") byte cardType,
			@PathVariable("cardSN") String cardSN,
			@PathVariable("cardMoney") int cardMoney,
			@PathVariable("cardPassword") String cardPassword) {
		SzfCard szfCard = new SzfCard();
		szfCard.setCardType(cardType);
		szfCard.setCardSN(cardSN);
		szfCard.setCardPassword(cardPassword);
		szfCard.setCardMoney(cardMoney);
		
		System.out.println(szfCard.toString());
		System.out.println("uid=" + uid + ";fromid=" + fromid);
		
		// 获取商户信息
		Merchant mer = merchantService.get(MerInfo.SZFINFO);
		System.out.println("商户信息:" + mer);
		SzfOrder szfOrder = addSzfOrder(uid, fromid, szfCard, mer);
		int id = szfOrderService.add(szfOrder);
		
		return szfOrder.getOrderId();
	}
	
	@RequestMapping("/ajax/szfpay.do")
	@ResponseBody
	public Object payAjax(HttpServletRequest request) {
		String szfOrderId = request.getParameter("data");
		if (("").equals(szfOrderId) || szfOrderId == null) {
			return "订单号格式出错，充值失败";
		}
		
		SzfOrder szfOrder = szfOrderService.get(szfOrderId);
		Merchant mer = merchantService.get(MerInfo.SZFINFO);
		String code = "-1"; 
		
		if (szfOrder != null) {
			code = accessingURL(szfOrder.getId(), mer);
			
			if (!code.equals("200")) {
				closeOrder(szfOrderId);
			}
		}
		
		return SzfErrorCode.getValidCode(code);
	}

	// 新建本地订单
	private int addOrder(int uid, int fromid, int money) {
		Order order = new Order();
		order.setUid(uid);
		order.setFromid(fromid);
		order.setStatus(OrderType.WAITPAY);
		order.setDateTime(new Date());
		order.setPayMoney(money);
		int id = orderService.add(order);
		return id;
	}
	
	// 封装神州付订单
	private SzfOrder addSzfOrder(int uid , int fromid, SzfCard card, Merchant mer) {
		SzfOrder szfOrder = new SzfOrder();
		int orderId = addOrder(uid, fromid, card.getCardMoney() * 100);
		
		szfOrder.setVersion(mer.getVersion());
		szfOrder.setMerId(mer.getMerId());
		// 全额付款，支付金额必须为0
		szfOrder.setPayMoney(0);
		
		// 拼接神州付订单号
		String szfOrderId = DAYFORMAT.format(new Date()) + "-" + mer.getMerId() + "-" + orderId;
		szfOrder.setOrderId(szfOrderId);
		
		szfOrder.setReturnUrl(mer.getMerReturnurl());
		
		// TODO 充值卡加密信息,mer.getDesKey()流程需要找神州付获取
		String cardInfo = ServerConnSzxUtils.getDesEncryptBase64String(card.getCardMoney()*100 + "", card.getCardSN(), card.getCardPassword(), mer.getDesKey());
		szfOrder.setCardInfo(cardInfo);
		
		szfOrder.setMerUserName(mer.getMerName());
		szfOrder.setMerUserMail(mer.getMerEmail());
		
		// TODO 私有数据,需要问神州付工作人员
		String privateField = uid + "_" + fromid + "_" + orderId;
		szfOrder.setPrivateField(privateField);
		
		szfOrder.setVerifyType(mer.getVerifyType());
		szfOrder.setCardTypeCombine(card.getCardType());
		
		// TODO Md5校验字符串,需要问神州付工作人员
		String combineString = mer.getVersion() + mer.getMerId() + 0 + szfOrderId + mer.getMerReturnurl() + cardInfo + privateField + mer.getVerifyType() + mer.getPrivateKey();
		String md5String = DigestUtils.md5Hex(combineString); //md5加密串
		szfOrder.setMd5String(md5String);
		
		// TODO signString,因为是MD5校验，因此不需要传递证书
		szfOrder.setSignString("");
		
		szfOrder.setUid(uid);
		szfOrder.setFromid(fromid);
		szfOrder.setDateTime(new Date());
		szfOrder.setStatus(OrderType.WAITPAY);
		
		return szfOrder;
	}
	
	// 访问神州付完成支付
	private String accessingURL(int id, Merchant mer) {
		// 构建支付URL
		SzfOrder szfOrder = szfOrderService.get(id);
		try {
			String urlRequestData = mer.getUrl() + "?version="
					+ szfOrder.getVersion() + "&merId=" + mer.getMerId()
					+ "&payMoney=" + szfOrder.getPayMoney() + "&orderId="
					+ szfOrder.getOrderId() + "&returnUrl="
					+ mer.getMerReturnurl() + "&cardInfo="
					+ URLEncoder.encode(szfOrder.getCardInfo(), "utf-8")
					+ "&merUserName=" + mer.getMerName() + "&merUserMail="
					+ mer.getMerEmail() + "&privateField="
					+ szfOrder.getPrivateField() + "&verifyType="
					+ szfOrder.getVerifyType() + "&cardTypeCombine="
					+ szfOrder.getCardTypeCombine() + "&md5String="
					+ szfOrder.getMd5String() + "&signString="
					+ szfOrder.getSignString();
			System.out.println("构造 url 请求数据：" + urlRequestData);

			// 服务器建立连接
			int szfResponseCode;
			URL url = new URL(urlRequestData);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			int code = httpConnection.getResponseCode();
			System.out.println("连接神州付服务器：" + mer.getUrl() + "，HTTP响应代码：" + code);
			if (code == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"UTF-8"));
				// 当正确响应时处理数据
				szfResponseCode = httpConnection.getHeaderFieldInt("szfResponseCode", 0);
				System.out.println("连接神州付服务器：" + mer.getUrl() + "，SZF响应代码：" + szfResponseCode);
				return szfResponseCode + "";
			}
		} catch (Exception e) {
			System.out.println("连接神州付服务器：" + mer.getUrl() + "异常，e=" + e);
			return  "-1";
		}

		return "-1";
	}
	
	private void closeOrder(String szfOrderId) {
		SzfOrder szfOrder = szfOrderService.get(szfOrderId);
		szfOrder.setStatus(OrderType.CLOSEPAY);
		szfOrderService.update(szfOrder);
		
		String orderIdStr = szfOrderId.substring(szfOrderId.lastIndexOf("-") + 1, szfOrderId.length());
		int orderId = StringUtil.getInt(orderIdStr, 0);
		Order order = orderService.get(orderId);
		order.setStatus(OrderType.CLOSEPAY);
		orderService.update(order);
	}

}




























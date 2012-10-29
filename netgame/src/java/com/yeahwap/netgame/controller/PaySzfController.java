package com.yeahwap.netgame.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeahwap.netgame.domain.MerInfo;
import com.yeahwap.netgame.domain.OrderType;
import com.yeahwap.netgame.domain.SzfCard;
import com.yeahwap.netgame.domain.SzfCardType;
import com.yeahwap.netgame.domain.pojo.Merchant;
import com.yeahwap.netgame.domain.pojo.Order;
import com.yeahwap.netgame.domain.pojo.SzfOrder;
import com.yeahwap.netgame.service.MerchantService;
import com.yeahwap.netgame.service.OrderService;
import com.yeahwap.netgame.szf.ServerConnSzxUtils;

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
	public static final SimpleDateFormat DAYFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	@Qualifier("merchantService")
	private MerchantService merchantService;
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@RequestMapping(value="{uid}/{fromid}/yidong.do")
	public String sendYiDong(@PathVariable("uid") String uid, @PathVariable("fromid") String fromid, ModelMap modelMap) {
		SzfCard szfCard = new SzfCard();
		szfCard.setCardType(SzfCardType.YIDONG);
		modelMap.addAttribute("szfCard", szfCard);
		modelMap.put("uid", uid);
		modelMap.put("fromid", fromid);
		System.out.println("uid=" + uid + ";fromid=" + fromid);
		return "pay/szfsend";
	} 

	@RequestMapping(value="{fromid}/{uid}/payYiDong.do")
	public String payYiDong(@PathVariable("uid") int uid, @PathVariable("fromid") int fromid, @Valid SzfCard szfCard , BindingResult br, ModelMap modelMap) {
		System.out.println(szfCard.toString());
		
		if (br.hasErrors()) {
			modelMap.put("uid", uid);
			modelMap.put("fromid", fromid);
			return "pay/szfsend";
		}
		
		SzfOrder order = addSzfOrder(uid, fromid, szfCard);
		
		return "";
	}

	// 新建本地订单
	private int addOrder(int uid, int fromid) {
		Order order = new Order();
		order.setUid(uid);
		order.setFromid(fromid);
		order.setStatus(OrderType.WAITPAY);
		order.setDateTime(new Date());
		int id = orderService.add(order);
		return id;
	}
	
	// 新建神州付订单
	private SzfOrder addSzfOrder(int uid , int fromid, SzfCard card) {
		SzfOrder szfOrder = new SzfOrder();
		int orderId = addOrder(uid, fromid);
		// 获取商户信息
		Merchant mer = merchantService.get(MerInfo.SZFINFO);
		
		String szfOrderId = DAYFORMAT.format(new Date()) + "-" + mer.getMerId() + "-" + orderId;
		szfOrder.setOrderId(szfOrderId);
		
		szfOrder.setVersion(mer.getVersion());
		szfOrder.setMerId(mer.getMerId());
		szfOrder.setPayMoney(card.getCardMoney());
		szfOrder.setReturnUrl(mer.getMerReturnurl());
		// TODO 充值卡加密信息
		String cardInfo = ServerConnSzxUtils.getDesEncryptBase64String(card.getCardMoney() + "", card.getCardSN(), card.getCardPassword(), mer.getDesKey());
		szfOrder.setCardInfo(cardInfo);
		szfOrder.setMerUserName(mer.getMerName());
		szfOrder.setMerUserMail(mer.getMerEmail());
		// TODO 私有数据
		String privateField = uid + "|" + fromid;
		szfOrder.setPrivateField(privateField);
		szfOrder.setVerifyType(mer.getVerifyType());
		szfOrder.setCardTypeCombine(card.getCardType());
		// TODO Md5校验字符串
		String privateKey = "xxx";
		String combineString = mer.getVersion() + mer.getMerId() + card.getCardMoney() + szfOrderId + mer.getMerReturnurl() + cardInfo + privateField + mer.getVerifyType() + privateKey;
		String md5String = DigestUtils.md5Hex(combineString); //md5加密串
		szfOrder.setMd5String(md5String);
		// TODO signString
		
		return szfOrder;
	}
	
	
	// 访问神州付完成支付
	private void accessingURL(SzfOrder order, Merchant mer) {
		// 构建支付URL
		String urlRequestData = "";
		try {
			urlRequestData = mer.getUrl() 
					+ "?version=" + order.getVersion() 
					+ "&merId=" + mer.getMerId()
					+ "&payMoney=" + order.getPayMoney()
					+ "&orderId=" + order.getOrderId()
					+ "&returnUrl=" + mer.getMerReturnurl()
					+ "&cardInfo=" + URLEncoder.encode(order.getCardInfo(), "utf-8")
					+ "&merUserName=" + mer.getMerName()
					+ "&merUserMail=" + mer.getMerEmail()
					+ "&privateField=" + order.getPrivateField()
					+ "&verifyType=" + order.getVerifyType()
					+ "&cardTypeCombine=" + order.getCardTypeCombine()
					+ "&md5String=" + order.getMd5String()
					+ "&signString=" + order.getSignString();
			System.out.println("构造 url 请求数据：" + urlRequestData);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//服务器建立连接
	    HttpURLConnection httpConnection;
	    URL url;
	    int code;
	    int szfResponseCode;
	    try {
	        url = new URL(urlRequestData);
	        httpConnection = (HttpURLConnection) url.openConnection();
	        httpConnection.setRequestMethod("GET");
	        httpConnection.setDoOutput(true);
	        httpConnection.setDoInput(true);
	        code = httpConnection.getResponseCode();
	        System.out.println("连接神州付服务器：" + mer.getUrl() + "，HTTP响应代码：" + code);
	        if (code == HttpURLConnection.HTTP_OK) {
	            try {
	                String strCurrentLine;
	                BufferedReader reader;
	                reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
	                //当正确响应时处理数据
	                szfResponseCode = httpConnection.getHeaderFieldInt("szfResponseCode", 0);
	                System.out.println("连接神州付服务器：" + mer.getUrl() + "，SZF响应代码：" + szfResponseCode);
	                //数据通过校验
	                if (szfResponseCode == 200) {
	                    /*out.print("<html>\n" +
	                            "<head><title></title>\n" +
	                            "</head>\n" +
	                            "<body>\n" +
	                            "支付处理中,请稍候...\n" +
	                            "</body>\n" +
	                            "</html>");*/
	                	
	                }
	            } catch (IOException e) {
	                System.out.println("连接神州付服务器：" + mer.getUrl() + "异常，e=" + e);
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("连接神州付服务器：" + mer.getUrl() + "异常，e=" + e);
	    }
	}
}




























package com.yeahwap.netgame.domain.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "szf_order")
public class SzfOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	// 神州付的订单号订单号的格式：yyyyMMdd-merId-SN;SN：有商户自定义的商品标识串
	@Column(name = "order_id", length = 50) 
	private String orderId;
	
	// 神州付当前使用接口的版本号
	@Column(name = "version", length = 5) 
	private String version = "3";
	
	// 商户ID（神州付定义）
	@Column(name = "mer_id", length = 10) 
	private int merId = 1001;
	
	// 充值金额，单位分;采用全面值支付
	@Column(name = "pay_money", length = 10) 
	private int payMoney;
	
	// 商户接收神州付 支付平台的服务器返回地址（绝对地址），长度 1-255 位 之间。
	@Column(name = "return_url", length = 254) 
	private String returnUrl = "http://121.101.219.28:8080/netgamesdk/szfreturn.jsp";
	
	// 充值卡加密信息;格式:神州充值卡面值(单位:元)@充值卡序列号@充值卡密码@密钥(神州付定义)然后将此字符串加密
	@Column(name = "card_info", length = 254) 
	private String cardInfo;
	
	// 商户名
	@Column(name = "mer_user_name", length = 50)
	private String merUserName = "深圳东润技术咨询有限公司";
	
	// 商户邮箱
	@Column(name = "mer_user_mail", length = 20)
	private String merUserMail = "service@yeahwap.com";
	
	// 商户私有数据
	@Column(name = "private_field", length = 254)
	private String privateField;
	
	// 校验方式(只能是MD5)
	@Column(name = "verify_type", length = 4)
	private byte verifyType = 1;
	
	// 充值卡类型:0：移动；1：联通；2：电信
	@Column(name = "card_type_combien", length = 4)
	private byte cardTypeCombine;
	
	// md5字符串校验
	@Column(name = "md5_string", length = 254)
	private String md5String;
	
	// 证书校验
	@Column(name = "sign_string", length = 254)
	private String signString;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getMerId() {
		return merId;
	}

	public void setMerId(int merId) {
		this.merId = merId;
	}

	public int getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	public String getMerUserName() {
		return merUserName;
	}

	public void setMerUserName(String merUserName) {
		this.merUserName = merUserName;
	}

	public String getMerUserMail() {
		return merUserMail;
	}

	public void setMerUserMail(String merUserMail) {
		this.merUserMail = merUserMail;
	}

	public String getPrivateField() {
		return privateField;
	}

	public void setPrivateField(String privateField) {
		this.privateField = privateField;
	}

	public byte getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(byte verifyType) {
		this.verifyType = verifyType;
	}

	public byte getCardTypeCombine() {
		return cardTypeCombine;
	}

	public void setCardTypeCombine(byte cardTypeCombine) {
		this.cardTypeCombine = cardTypeCombine;
	}

	public String getMd5String() {
		return md5String;
	}

	public void setMd5String(String md5String) {
		this.md5String = md5String;
	}

	public String getSignString() {
		return signString;
	}

	public void setSignString(String signString) {
		this.signString = signString;
	}
}

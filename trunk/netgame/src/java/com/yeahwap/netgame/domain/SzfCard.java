package com.yeahwap.netgame.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 封装神州付的，移动卡，联通卡，电信卡
 * 
 * @author Harry Ye
 * @version 1.0; 2012/10/26
 * @since JDK1.6
 * 
 */
public class SzfCard {
	@Pattern (regexp="(^[0-9]{17}$)|(^[0-9]{15}$)", message="序列号格式无效")  
	private String cardSN;
	@NotNull (message="请选择您充值卡的金额!")
	private Integer cardMoney;
	@Pattern (regexp="(^[0-9]{19}$)|(^[0-9]{18}$)", message="密码格式无效")
	private String cardPassword;
	private byte cardType = SzfCardType.YIDONG;

	public String getCardSN() {
		return cardSN;
	}

	public void setCardSN(String cardSN) {
		this.cardSN = cardSN;
	}

	public Integer getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(Integer cardMoney) {
		this.cardMoney = cardMoney;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public byte getCardType() {
		return cardType;
	}

	public void setCardType(byte cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("cardSN:" + this.cardSN + ";");
		sb.append("cardMoney:" + this.cardMoney + ";");
		sb.append("cardPassword:" + this.cardPassword + ";");
		sb.append("cardType:" + this.cardType);
		return sb.toString();
	}
}

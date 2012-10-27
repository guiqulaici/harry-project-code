package com.yeahwap.netgame.domain;

/**
 * 封装神州付的，移动卡，联通卡，电信卡
 * 
 * @author Harry Ye
 * @version 1.0; 2012/10/26
 * @since JDK1.6
 * 
 */
public class SzfCard {
	private String cardSN;
	private String cardMoney;
	private String cardPassword;
	private byte cardType = SzfCardType.YIDONG;

	public String getCardSN() {
		return cardSN;
	}

	public void setCardSN(String cardSN) {
		this.cardSN = cardSN;
	}

	public String getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(String cardMoney) {
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
		return super.toString();
	}
}

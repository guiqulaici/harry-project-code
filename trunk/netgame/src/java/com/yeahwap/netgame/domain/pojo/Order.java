package com.yeahwap.netgame.domain.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 神州付，易宝支付，支付宝的订单记录
 * 
 * @author Harry
 * 
 */
@Entity
@Table(name = "account")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "uid", nullable = false)
	private int uid;
	
	@Column(name = "fromid", nullable = false)
	private int fromid;
	
	@Column(name = "status", nullable = false)
	private byte status; // 1.等待支付， 2.支付成功,3.调用支付失败,4.支付失败
	
	@Column(name = "pay_money", nullable = false)
	private int payMoney;
	
	@Column(name = "balanceday", nullable = false)
	private Date balanceday;
	
	@Column(name = "type", nullable = false)
	private byte type; // 1.神州付入账单，2.易宝入账单， 3.支付宝入账单
	
	@Column(name = "date_time", nullable = false)
	private Date dateTime;

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Date getBalanceday() {
		return balanceday;
	}

	public void setBalanceday(Date balanceday) {
		this.balanceday = balanceday;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public int getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(int payMoney) {
		this.payMoney = payMoney;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("uid=" + this.uid + ";");
		sb.append("fromid=" + this.fromid + ";");
		sb.append("status=" + this.status + ";");
		sb.append("payMoney=" + this.payMoney + ";");
		sb.append("dateTime=" + this.dateTime);
		return sb.toString();
	}

}

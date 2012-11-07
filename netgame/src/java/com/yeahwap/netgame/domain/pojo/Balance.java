package com.yeahwap.netgame.domain.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 记录用户的账号金额
 * @author Harry Ye
 *
 */
@Entity
@Table(name = "balance")
public class Balance implements java.io.Serializable {
	
	private int uid;
	private int balance; // 账号余额
	private int paid; // 已支出金额
	private int freeze; // 冻结金额

	public Balance() {
	}

	public Balance(int uid, int balance, int paid, int freeze) {
		this.uid = uid;
		this.balance = balance;
		this.paid = paid;
		this.freeze = freeze;
	}

	@Id
	@Column(name = "uid", nullable = false)
	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Column(name = "balance", nullable = false)
	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Column(name = "paid", nullable = false)
	public int getPaid() {
		return this.paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	@Column(name = "freeze", nullable = false)
	public int getFreeze() {
		return this.freeze;
	}

	public void setFreeze(int freeze) {
		this.freeze = freeze;
	}

}

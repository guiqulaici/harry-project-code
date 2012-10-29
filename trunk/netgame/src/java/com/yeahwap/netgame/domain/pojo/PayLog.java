package com.yeahwap.netgame.domain.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Harry
 * @version 1.0 2012/10/29
 * @since JDK1.6
 */
@Entity
@Table(name = "pay_log")
public class PayLog implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column(name = "user_id", nullable = false, length = 10)
	private int userId;
	@Column(name = "from_id", nullable = false, length = 10)
	private int fromId;
	@Column(name = "pay_id", nullable = false, length = 10)
	private int payId;
	@Column(name = "momey", nullable = false, length = 10)
	private int momey;
	@Column(name = "status", nullable = false, length = 10)
	private int status;
	@Column(name = "dateline", nullable = false)
	private Date dateline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public int getMomey() {
		return momey;
	}

	public void setMomey(int momey) {
		this.momey = momey;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDateline() {
		return dateline;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}
}

package com.yeahwap.netgame.domain.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Create on 2012-10-10 16:03
 * 
 * @author Harry
 * 
 */
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column(name = "cp_id", nullable = false, length = 10)
	private int cpId;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "isview", nullable = false, length = 4)
	private int isview;
	@Column(name = "dateline", nullable = false)
	private Date dateline;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpId() {
		return cpId;
	}

	public void setCpId(int cpId) {
		this.cpId = cpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsview() {
		return isview;
	}

	public void setIsview(int isview) {
		this.isview = isview;
	}

	public Date getDateline() {
		return dateline;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}

}

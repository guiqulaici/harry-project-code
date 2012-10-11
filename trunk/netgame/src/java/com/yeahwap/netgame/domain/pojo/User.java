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
@Table(name = "user")
public class User implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "init_fromid", nullable = false, length = 10)
	private int initFromid;

	@Column(name = "dateline", nullable = false)
	private Date dateline;

	@Column(name = "mobile", nullable = true, length = 50)
	private String mobile;

	@Column(name = "email", nullable = true, length = 100)
	private String email;

	@Column(name = "score", nullable = true, length = 200)
	private int score;

	@Column(name = "isview", nullable = false, length = 4)
	private int isview;

	@Column(name = "type", nullable = false, length = 4)
	private int type;

	@Column(name = "weibo_id", nullable = true, length = 50)
	private String weiboId;

	@Column(name = "token", nullable = true, length = 50)
	private String token;

	@Column(name = "secret", nullable = true, length = 50)
	private String secret;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInitFromid() {
		return initFromid;
	}

	public void setInitFromid(int initFromid) {
		this.initFromid = initFromid;
	}

	public Date getDateline() {
		return dateline;
	}

	public void setDateline(Date dateline) {
		this.dateline = dateline;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getIsview() {
		return isview;
	}

	public void setIsview(int isview) {
		this.isview = isview;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}

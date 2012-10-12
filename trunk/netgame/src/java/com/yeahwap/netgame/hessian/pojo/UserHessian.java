package com.yeahwap.netgame.hessian.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Create on 2012-10-12 13:47
 * 
 * @author Harry
 *         <ul>
 *         <li>Title: UserHessian.java</li>
 *         <li>version: 1.0</li>
 *         <li>description: 远程调用时封装User,同步User.java</li>
 *         </ul>
 * 
 */
public class UserHessian implements Serializable {
	private static final long serialVersionUID = 7630019807197784411L;
	private int id;
	private String name;
	private String password;
	private int initFromid;
	private Date dateline;
	private String mobile;
	private String email;
	private int score;
	private int isview;
	private int type;
	private String weiboId;
	private String token;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

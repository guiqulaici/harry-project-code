package com.yeahwap.netgame.domain.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * Create on 2012-10-10 16:03
 * 
 * @author Harry
 * 
 */
@Entity
@Table(name = "auth_operator", uniqueConstraints = @UniqueConstraint(columnNames = { "login" }))
public class Operator implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column(nullable = false, length = 50)
	private String name;
	@Column(nullable = false, length = 50)
	private String login;
	@Column(nullable = false, length = 50)
	private String password;
	@Column(nullable = true, length = 20)
	private String qq;
	@Column(nullable = true, length = 20)
	private String email;
	@Column(nullable = false)
	private byte status;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		Operator o = (Operator) obj;
		return o.getId() == this.getId();
	}
}

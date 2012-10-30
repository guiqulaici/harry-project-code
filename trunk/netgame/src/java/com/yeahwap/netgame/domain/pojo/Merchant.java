package com.yeahwap.netgame.domain.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <ul>
 * <li>Title: SzfMer.java</li>
 * <li>description: 封装商户信息</li>
 * </ul>
 * 
 * @author Harry
 * @version 1.0 2012/10/29
 * @since JDK1.6
 */
@Entity
@Table(name = "merchant")
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "mer_id", nullable = false)
	private String merId;
	@Column(name = "mer_name", nullable = false)
	private String merName;
	@Column(name = "mer_returnurl", nullable = false)
	private String merReturnurl;
	@Column(name = "mer_email", nullable = false)
	private String merEmail;
	@Column(name = "version", nullable = false)
	private String version;
	@Column(name = "verify_type", nullable = false)
	private byte verifyType;
	@Column(name = "des_key", nullable = false)
	private String desKey;
	@Column(name = "url", nullable = false)
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesKey() {
		return desKey;
	}

	public void setDesKey(String desKey) {
		this.desKey = desKey;
	}

	public byte getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(byte verifyType) {
		this.verifyType = verifyType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getMerReturnurl() {
		return merReturnurl;
	}

	public void setMerReturnurl(String merReturnurl) {
		this.merReturnurl = merReturnurl;
	}

	public String getMerEmail() {
		return merEmail;
	}

	public void setMerEmail(String merEmail) {
		this.merEmail = merEmail;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("merId=" + this.merId + ";");
		sb.append("merName=" + this.merName + ";");
		sb.append("merReturnurl=" + this.merReturnurl + ";");
		sb.append("merEmail=" + this.merEmail + ";");
		sb.append("version=" + this.version + ";");
		sb.append("verifyType=" + this.verifyType + ";");
		sb.append("desKey=" + this.desKey + ";");
		sb.append("url=" + this.url + ";");
		return sb.toString();
	}

}

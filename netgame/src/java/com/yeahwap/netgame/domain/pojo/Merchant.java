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

	@Column(name = "version", nullable = false)
	private String version; // 接口版本号

	@Column(name = "mer_id", nullable = false)
	private String merId; // 商户ID

	@Column(name = "return_url", nullable = false)
	private String returnUrl; // 服务器返回地址

	@Column(name = "verify_type", nullable = false)
	private byte verifyType; // 数据校验方式

	@Column(name = "url", nullable = false)
	private String url; // 支付接口地址

	@Column(name = "private_key", nullable = false)
	private String privateKey; // md5加密密钥

	@Column(name = "des_key", nullable = false)
	private String desKey; // 信息加密密钥

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public byte getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(byte verifyType) {
		this.verifyType = verifyType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getDesKey() {
		return desKey;
	}

	public void setDesKey(String desKey) {
		this.desKey = desKey;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("version=" + this.version + ";");
		sb.append("merId=" + this.merId + ";");
		sb.append("returnUrl=" + this.returnUrl + ";");
		sb.append("verifyType=" + this.verifyType + ";");
		sb.append("url=" + this.url + ";");
		sb.append("privateKey=" + this.privateKey + ";");
		sb.append("desKey=" + this.desKey);
		return sb.toString();
	}

}

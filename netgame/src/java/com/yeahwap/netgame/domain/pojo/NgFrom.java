package com.yeahwap.netgame.domain.pojo;

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
@Table(name = "ng_from")
public class NgFrom {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column(name = "name", nullable = true, length = 50)
	private String name;
	
	@Column(name = "product_id", nullable = false, length = 4)
	private int productId;
	
	@Column(name = "channel_id", nullable = false, length = 4)
	private int channelId;
	
	@Column(name = "frominfo", nullable = true, length = 200)
	private String frominfo;
	
	@Column(name = "isview", nullable = false, length = 4)
	private int isview;

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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getFrominfo() {
		return frominfo;
	}

	public void setFrominfo(String frominfo) {
		this.frominfo = frominfo;
	}

	public int getIsview() {
		return isview;
	}

	public void setIsview(int isview) {
		this.isview = isview;
	}
}

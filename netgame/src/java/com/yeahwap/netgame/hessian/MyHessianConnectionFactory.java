package com.yeahwap.netgame.hessian;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.apache.log4j.Logger;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.client.HessianURLConnectionFactory;
import com.yeahwap.netgame.util.CipherUtil;

public class MyHessianConnectionFactory extends HessianURLConnectionFactory {
	
	private static final Logger log = Logger.getLogger(MyHessianConnectionFactory.class);
	//private HessianProxyFactory factory;
	private String key;
	private String ip;

//	@Override
//	public void setHessianProxyFactory(HessianProxyFactory factory) {
//		super.setHessianProxyFactory(factory);
//	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public HessianConnection open(URL arg0) throws IOException {
		System.out.println("HARRY:" + arg0 + ";" + ip + ";" + key);
		HessianConnection conn = super.open(arg0);
		Date now = new Date();
		String validStr = getValidStr(now);
		conn.addHeader("valid-str", validStr);
		conn.addHeader("valid-time", "" + now.getTime());
		if (log.isDebugEnabled()) {
			log.debug("valid-str:" + validStr);
			log.debug("valid-time:" + now.getTime());
			log.debug("client-ip:" + ip);
		}
		return conn;
	}
	

	private String getValidStr(Date time) {
		String text = time.getTime() + ip + key;
		System.out.println(text);
		return CipherUtil.getMD5Str(text);
	}

}

package com.harry.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnection {
	
	public static void main(String[] args) {
		
		try {
			String url1 = "http://121.101.219.28:8080/netgamesdk/sdk/userRegister.do?";
			String url2 = "http://localhost:8080/netgamesdk/sdk/userLogin.do?name=HarryYe&password=123456&fromid=1";
			String url3 = "http://localhost:8080/netgamesdk/sdk/userUpdate.do?name=HarryYe&oldpassword=123456&newpassword=654321&uid=1&fromid=10&mobile=AAABBB&phone=13512765966";
			String url4 = "http://localhost:8080/netgamesdk/sdk/userFind.do?name=HarryYe&email=harry@yeahwap.com&fromid=1";
			URL url = new URL(url1);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestProperty("header-valid", "yeahwap");
			conn.setRequestMethod("POST");
			
			conn.setDoOutput(true);
			StringBuffer params = new StringBuffer();
			// name=lichao1&password=123456&mobile=MAC300234345313452334&initFromid=1
			params.append("name").append("=").append("lichao1").append("&").append("password").append("=").append("123456").append("&mobile=MAC300234345313452334&initFromid=1");        
			byte[] bypes = params.toString().getBytes();        
			conn.getOutputStream().write(bypes);// 输入参数
			
			InputStream is = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = is.read(buf)) != -1) {
				out.write(buf, 0, bytesRead);
			}
			
			System.out.println(out.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package com.harry.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnection {
	
	public static void main(String[] args) {
		
		try {
			String url1 = "http://localhost:8080/netgamesdk/sdk/userRegister.do?name=HarryLei&password=123456&initFromid=1&mobile=AAAABBBBB&phone=13631375979";
			String url2 = "http://localhost:8080/netgamesdk/sdk/userLogin.do?name=HarryYe&password=123456&fromid=1";
			String url3 = "http://localhost:8080/netgamesdk/sdk/userUpdate.do?name=HarryYe&oldpassword=123456&newpassword=654321&uid=1&fromid=10&mobile=AAABBB&phone=13512765966";
			String url4 = "http://localhost:8080/netgamesdk/sdk/userFind.do?name=HarryYe&email=harry@yeahwap.com&fromid=1";
			URL url = new URL(url4);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestProperty("header-valid", "yeahwap");
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

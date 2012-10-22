package com.harry.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnection {
	
	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://localhost:8080/netgamesdk/sdk/userLogin.do?name=HarryYe&password=654321&fromid=1");
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

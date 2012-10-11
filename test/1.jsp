<%@page contentType="text/html;charset=gb2312"%>
<%@ page import="java.io.*,java.util.*,java.net.*,java.sql.*"%>
<%!
	public String getInfo(String url){
        String temp = "";
        try {
            InputStream stream;
            URL l_url = new URL(url);
            HttpURLConnection connec = (HttpURLConnection) l_url.openConnection();
            connec.setConnectTimeout(3000);
            connec.setReadTimeout(3000); 
	    connec.connect();
            stream = connec.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String strLine = "";
            while ((strLine = breader.readLine()) != null) {
                	temp = temp + strLine.trim();
            } 
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return temp;
    }
%>


<%
	String info  = this.getInfo("http://121.101.219.28:8080/2.jsp");
	if(info.indexOf("abc") == -1){
		Thread.sleep(3000);
		info  = this.getInfo("http://121.101.219.28:8080/2.jsp");
		if(info.indexOf("abc") == -1){
			System.out.println(new java.util.Date()+"--->问题出现"+info.trim());
			try{
				Process process8 = Runtime.getRuntime().exec("/usr/local/apps/stop.sh");
				process8.waitFor();
				System.out.println("关闭第一次");
				Thread.sleep(5000);
				Process process7 = Runtime.getRuntime().exec("/usr/local/apps/stop.sh");
				process7.waitFor();
				System.out.println("关闭第二次");
				Process process88 = Runtime.getRuntime().exec("/usr/local/apps/stop.sh");
				process88.waitFor();
				System.out.println("关闭第三次"); 

				Process process1 = Runtime.getRuntime().exec("/usr/local/apps/start.sh");
				process1.waitFor();
				System.out.println("启动");

			 } catch (Exception e) {}
		}
	} else {
		System.out.println(new java.util.Date()+"--->ok!");
	}
%>

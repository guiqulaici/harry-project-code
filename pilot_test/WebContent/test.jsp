<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!public static String getIpAddr(HttpServletRequest request) {
	String ip = request.getHeader("x-forwarded-for");

	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		System.out.println("1");
		ip = request.getHeader("Proxy-Client-IP");
	}

	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		System.out.println("2");
		ip = request.getHeader("WL-Proxy-Client-IP");
	}

	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		System.out.println("3");
		ip = request.getRemoteAddr();
	}

	return ip;

}%>

<%
	Enumeration headerNames = request.getHeaderNames();

	while (headerNames.hasMoreElements()) {

		String headerName = (String) headerNames.nextElement();
		Enumeration values = request.getHeaders(headerName);

		while (values.hasMoreElements()) {

			out.print(headerName + " : "
					+ (String) values.nextElement() + "<br>");

		}

	}
	
	// out.println(getIpAddr(request));
	out.println("ip=" + request.getRemoteAddr());
%>
</body>
aaa
</html>
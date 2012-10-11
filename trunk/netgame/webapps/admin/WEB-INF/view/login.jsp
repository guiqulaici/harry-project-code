<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线网游后台</title>
</head>
<body>
	${return}
	<form action="<%=request.getContextPath()%>/login/login.html" id="loginForm" name="loginForm" method="post">
		用户:<input type="text" id="username" name="username" value=""/>
		密码:<input type="text" id="password" name="password" value=""/>
		<input type="submit"/>
	</form>
	
	<table>
		<c:forEach items="${return}" var="r">
			<tr>
				<td>
					<%-- <c:out value="${rr}"></c:out> --%>
					${r}
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
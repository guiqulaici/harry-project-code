<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- http://localhost:8080/netgamesdk/sdk/userUpdate.do?name=Harry Ye&oldpassword=123456&newpassword=654321&uid=5&fromid=1 -->
{<br/>
	<c:choose>
		<c:when test="${user != null}">
			"id" : "${user.id}",<br/>
			"name" : "${user.name}",<br/>
			"password" : "${user.password}",<br/>
			"initFromid" : "${user.initFromid}",<br/>
			"dateline" : "${user.dateline}",<br/>
			"phone" : "${user.phone}",<br/>
			"email" : "${user.email}",<br/>
			"score" : "${user.score}",<br/>
			"isview" : "${user.isview}",<br/>
			"type" : "${user.type}",<br/>
			"weiboId" : "${user.weiboId}",<br/>
			"token" : "${user.token}",<br/>
			"secret" : "${user.secret}"<br/>
		</c:when>
		<c:otherwise>
			"return" : "1005"					
		</c:otherwise>
	</c:choose>
}<br/>
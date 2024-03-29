<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
{
	<c:choose>
		<c:when test="${user != null}">
			"id" : "${user.id}",
			"name" : "${user.name}",
			"password" : "${user.password}",
			"initFromid" : "${user.initFromid}",
			"dateline" : "${user.dateline}",
			"phone" : "${user.phone}",
			"email" : "${user.email}",
			"score" : "${user.score}",
			"isview" : "${user.isview}",
			"type" : "${user.type}",
			"weiboId" : "${user.weiboId}",
			"token" : "${user.token}",
			"secret" : "${user.secret}"
		</c:when>
		<c:otherwise>
			"return" : "1003"				
		</c:otherwise>
	</c:choose>
}
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<spring:url value="/startGame" var="startGame" htmlEscape="true"/>
	<a href="${startGame}">Start Game</a>
	
	

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.maistruk.battleship.service.TableCreater"%>
<%@page import="com.maistruk.battleship.service.FieldChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<spring:url value="/resources/styles.css" var="stylesCss" />
<link href="${stylesCss}" rel="stylesheet">
<spring:url value="/resources/bootstrap.min.css" var="bootstrapsCss" />
<link href="${bootstrapsCss}" rel="stylesheet">
<spring:url value="/resources/bootstrap.min.js" var="bootstrapsJs" />
<script src="${bootstrapsJs}"></script>
<spring:url value="/resources/jquery-3.3.1.min.js" var="jquery" />
<script src="${jquery}"></script>

</head>
<body>

	<jsp:include page="components/navigationBar.jsp"></jsp:include>


	<%
		FieldChecker fieldChecker = (FieldChecker)session.getAttribute("fieldChecker");
		TableCreater tableCreater = (TableCreater)session.getAttribute("tableCreater");
	%>
	
	<form action="startGame" method="POST">
		<div class="tbl">
	<table>
	<%
 	    for(int i = 0; i <= 10; i++){
		    out.print("<tr>");
		    for(int j = 0; j <=10; j++){
		        if(i == 0 || j == 0){
		            out.print("<td>" + tableCreater.createFrame(i, j) +"</td>");
		        } else {
		        String fieldName = tableCreater.getLatter(i) + j;
		        String[] isChecked = new String[] {"blueMark", "", "oragneMark"};
		        out.print("<td>" + tableCreater.getFirstTablePart(i, j, isChecked) +"</td>"); 
		        }
		    }
		    out.print("<tr>");
		} 
	%>
	</table>
	</div>
	<input type="submit" class="btn btn-success btn-lg shotBtn" value="Start Game">
	</form>
<% 
	String exception = (String) session.getAttribute("exception");
	if(exception != null){
	    out.print("<p>" + exception + "</p>");
	}%>
</body>
</html>
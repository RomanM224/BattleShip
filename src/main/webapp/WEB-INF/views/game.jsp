<%@page import="com.maistruk.battleship.service.TableCreater"%>
<%@page import="com.maistruk.battleship.service.FieldChecker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.maistruk.battleship.model.Fleet"%>
<%@ page import="com.maistruk.battleship.model.Field"%>
<%@ page import="com.maistruk.battleship.service.ComputerAI"%>

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
	    Fleet myFleet = (Fleet) session.getAttribute("myFleet");
				Fleet enemyFleet = (Fleet) session.getAttribute("enemyFleet");
				FieldChecker fieldChecker = (FieldChecker) session.getAttribute("fieldChecker");
				TableCreater tableCreater = (TableCreater) session.getAttribute("tableCreater");
				List<Field> enemyShots = (List<Field>) session.getAttribute("enemyShots");
				List<Field> myShipsFields = (List<Field>) session.getAttribute("myShipsFields");
				List<Field> myShots = (List<Field>) session.getAttribute("myShots");
				List<Field> enemyShipsFields = (List<Field>) session.getAttribute("enemyShipsFields");
	%>
	<div class ="container-fluid mt-5 ml-5">
	<div class ="row">
	 <div class="col-2 ml-5 border border-warning">
	 <h3>My Ships</h3>
	 <%for(int i = 1; i <=5; i++){
	     int shipSize = myFleet.getShipFieldsBySize(i);
		 if(shipSize != 0){
	     	out.print("<span class=\"text-success\">Ship size: " + i + " | Ship health: " + shipSize + " blocks</span><br>");
		 } else {
		     out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
		 }
	}
	%>
	</div>
	<div class="col-1"></div>
	<div class="col-2 border border-info">
	 <h3>Enemy Ships</h3>
	 <%for(int i = 1; i <=5; i++){
	     int shipSize = enemyFleet.getShipFieldsBySize(i);
		 if(shipSize != 0){
	     	out.print("<span class=\"text-success\">Ship size: " + i + " | Ship health: " + shipSize + " blocks</span><br>");
		 } else {
		     out.print("<span class=\"text-danger\">Ship with size " + i + " is destroyed " + "</span><br>");
		 }
	}
	%>

	
	</div>
	</div>
	</div>
	
	<form action="game" method="POST">

		<div class="tbl">
		<b>My Ships</b>
			<table>
				<%
				    for (int i = 0; i <= 10; i++) {
								out.print("<tr>");
								for (int j = 0; j <= 10; j++) {
									if (i == 0 || j == 0) {
										out.print("<td>" + tableCreater.createFrame(i, j) + "</td>");
									} else {
										String fieldName = tableCreater.getLatter(i) + j;
										String[] isChecked = fieldChecker.ifExistFieldMyFleet(fieldName, enemyShots, myShipsFields);
										out.print("<td>" + tableCreater.getMyTablePart(i, j, isChecked) + "</td>");
									}
								}
								out.print("<tr>");
							}
				%>
			</table>
		</div>

		<div class="tbl">
		<b>Enemy Ships</b>
			<table >
				<%
				    for (int i = 0; i <= 10; i++) {
								out.print("<tr>");
								for (int j = 0; j <= 10; j++) {
									if (i == 0 || j == 0) {
										out.print("<td>" + tableCreater.createFrame(i, j) + "</td>");
									} else {
										String fieldName = tableCreater.getLatter(i) + j;
										String[] isChecked = fieldChecker.ifExistFieldEnemyFleet(fieldName, myShots, enemyShipsFields);
										out.print("<td>" + tableCreater.getEnemyTablePart(i, j, isChecked) + "</td>");
									}
								}
								out.print("<tr>");
							}
				%>
			</table>
		</div>

<%--  		<div class="tbl">
			<table>
				<%
				    for (int i = 0; i <= 10; i++) {
								out.print("<tr>");
								for (int j = 0; j <= 10; j++) {
									if (i == 0 || j == 0) {
										out.print("<td>" + tableCreater.createFrame(i, j) + "</td>");
									} else {
										String fieldName = tableCreater.getLatter(i) + j;
										String[] isChecked = fieldChecker.showEnemyFleet(fieldName, myShots, enemyShipsFields);
										out.print("<td>" + tableCreater.showEnemyShipsTablePart(i, j, isChecked) + "</td>");
									}
								}
								out.print("<tr>");
							}
				%>
			</table>
		</div>  --%>

		<input type="submit" class="btn btn-warning btn-lg shotBtn" value="Shot">
	</form>

	<script type="text/javascript">
    $('.product-list').on('change', function() {
        $('.product-list').not(this).prop('checked', false);  
    });
    $(".unclicable").click(function() { return false; });
  </script>

</body>
</html>
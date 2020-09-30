<%@page import="com.maistruk.battleship.model.TableCreater"%>
<%@page import="com.maistruk.battleship.model.FieldChecker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<%@ page import="java.util.List"%>
<%@ page import="com.maistruk.battleship.model.Fleet"%>
<%@ page import="com.maistruk.battleship.model.Field"%>
<%@ page import="com.maistruk.battleship.model.ComputerAI"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<spring:url value="/resources/styles.css" var="stylesCss" />
<link href="${stylesCss}" rel="stylesheet">
</head>
<body>
	<%
	    Fleet myFleet =(Fleet) session.getAttribute("myFleet");
		Fleet enemyFleet =(Fleet) session.getAttribute("enemyFleet");
		List<Field> myHist = (List<Field>) session.getAttribute("myHits");
		ComputerAI computerAI = (ComputerAI) session.getAttribute("computerAI");
		FieldChecker fieldChecker = (FieldChecker)session.getAttribute("myFieldChecker");
		TableCreater myTableCreater = (TableCreater)session.getAttribute("myTableCreater");
		TableCreater enemyTableCreater = (TableCreater)session.getAttribute("enemyTableCreater");
		
		int num = 0;
	%>
	<h1>Enemy fields: <% out.print(enemyFleet.getAllFields().size() + " Ships: " + enemyFleet.getShips().size()); %></h1>
	<h1>My fields: <% out.print(myFleet.getAllFields().size() + " Ships: " + myFleet.getShips().size()); %></h1>
	
	<form action="game" method="POST">
	
	<div class="tbl">
	<table border="1" cellpadding="2">
	<%
	    for(int i = 0; i <= 7; i++){
		    out.print("<tr>");
		    for(int j = 0; j <=7; j++){
		        out.print("<td>" + myTableCreater.getMyTablePart(i, j) +"</td>");
		    }
		    out.print("<tr>");
		}
	%>
	</table>
	</div>
	
	<div class="tbl">
	<table border="1" cellpadding="2">
	<%
	    for(int i = 0; i <= 7; i++){
		    out.print("<tr>");
		    for(int j = 0; j <=7; j++){
		        out.print("<td>" + enemyTableCreater.getEnemyTablePart(i, j) +"</td>");
		    }
		    out.print("<tr>");
		}
	%>
	</table>
	</div>
	
	
<%-- 	<div class="tbl">
	<table border="1" cellpadding="2">
	Enemy Hits
	<tr>
	<td></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td>
	</tr>
	<tr>
	<td>A</td><td><input type="checkbox" name="my_a1" <% out.print(computerAI.ifExistField("enemyHit_a1")); %>></td><td><input type="checkbox" name="my_a2" <%  out.print(computerAI.ifExistField("enemyHit_a2")); %>></td><td><input type="checkbox" name="my_a3" <%  out.print(computerAI.ifExistField("enemyHit_a3")); %>></td><td><input type="checkbox" name="my_a4" <%  out.print(computerAI.ifExistField("enemyHit_a4")); %>></td><td><input type="checkbox" name="my_a5" <%  out.print(computerAI.ifExistField("enemyHit_a5")); %>></td><td><input type="checkbox" name="my_a6" <%  out.print(computerAI.ifExistField("enemyHit_a6")); %>></td><td><input type="checkbox" name="my_a7" <%  out.print(computerAI.ifExistField("enemyHit_a7")); %>></td>
	</tr>
	<tr>
	<td>B</td><td><input type="checkbox" name="my_b1" <%  out.print(computerAI.ifExistField("enemyHit_b1")); %>></td><td><input type="checkbox" name="my_b2" <%  out.print(computerAI.ifExistField("enemyHit_b2")); %>></td><td><input type="checkbox" name="my_b3"<%  out.print(computerAI.ifExistField("enemyHit_b3")); %>></td><td><input type="checkbox" name="my_b4" <%  out.print(computerAI.ifExistField("enemyHit_b4")); %>></td><td><input type="checkbox" name="my_b5" <%  out.print(computerAI.ifExistField("enemyHit_b5")); %>></td><td><input type="checkbox" name="my_b6" <%  out.print(computerAI.ifExistField("enemyHit_b6")); %>></td><td><input type="checkbox" name="my_b7" <%  out.print(computerAI.ifExistField("enemyHit_b7")); %>></td>
	</tr>
	<tr>
	<td>C</td><td><input type="checkbox" name="my_c1" <% out.print(computerAI.ifExistField("enemyHit_c1")); %>></td><td><input type="checkbox" name="my_c2" <% out.print(computerAI.ifExistField("enemyHit_c2")); %>></td><td><input type="checkbox" name="my_c3" <% out.print(computerAI.ifExistField("enemyHit_c3")); %>></td><td><input type="checkbox" name="my_c4" <% out.print(computerAI.ifExistField("enemyHit_c4")); %>></td><td><input type="checkbox" name="my_c5" <% out.print(computerAI.ifExistField("enemyHit_c5")); %>></td><td><input type="checkbox" name="my_c6" <% out.print(computerAI.ifExistField("enemyHit_c6")); %>></td><td><input type="checkbox" name="my_c7" <% out.print(computerAI.ifExistField("enemyHit_c7")); %>></td>
	</tr>
	<tr>
	<td>D</td><td><input type="checkbox" name="my_d1" <% out.print(computerAI.ifExistField("enemyHit_d1")); %>></td><td><input type="checkbox" name="my_d2" <% out.print(computerAI.ifExistField("enemyHit_d2")); %>></td><td><input type="checkbox" name="my_d3" <% out.print(computerAI.ifExistField("enemyHit_d3")); %>></td><td><input type="checkbox" name="my_d4" <% out.print(computerAI.ifExistField("enemyHit_d4")); %>></td><td><input type="checkbox" name="my_d5" <% out.print(computerAI.ifExistField("enemyHit_d5")); %>></td><td><input type="checkbox" name="my_d6" <% out.print(computerAI.ifExistField("enemyHit_d6")); %>></td><td><input type="checkbox" name="my_d7" <% out.print(computerAI.ifExistField("enemyHit_d7")); %>></td>
	</tr>
	<tr>
	<td>E</td><td><input type="checkbox" name="my_e1" <% out.print(computerAI.ifExistField("enemyHit_e1")); %>></td><td><input type="checkbox" name="my_e2" <% out.print(computerAI.ifExistField("enemyHit_e2")); %>></td><td><input type="checkbox" name="my_e3" <% out.print(computerAI.ifExistField("enemyHit_e3")); %>></td><td><input type="checkbox" name="my_e4" <% out.print(computerAI.ifExistField("enemyHit_e4")); %>></td><td><input type="checkbox" name="my_e5" <% out.print(computerAI.ifExistField("enemyHit_e5")); %>></td><td><input type="checkbox" name="my_e6" <% out.print(computerAI.ifExistField("enemyHit_e6")); %>></td><td><input type="checkbox" name="my_e7" <% out.print(computerAI.ifExistField("enemyHit_e7")); %>></td>
	</tr>
	<tr>
	<td>F</td><td><input type="checkbox" name="my_f1" <% out.print(computerAI.ifExistField("enemyHit_f1")); %>></td><td><input type="checkbox" name="my_f2" <% out.print(computerAI.ifExistField("enemyHit_f2")); %>></td><td><input type="checkbox" name="my_f3" <% out.print(computerAI.ifExistField("enemyHit_f3")); %>></td><td><input type="checkbox" name="my_f4" <% out.print(computerAI.ifExistField("enemyHit_f4")); %>></td><td><input type="checkbox" name="my_f5" <% out.print(computerAI.ifExistField("enemyHit_f5")); %>></td><td><input type="checkbox" name="my_f6" <% out.print(computerAI.ifExistField("enemyHit_f6")); %>></td><td><input type="checkbox" name="my_f7" <% out.print(computerAI.ifExistField("enemyHit_f7")); %>></td>
	</tr>
	<tr>
	<td>G</td><td><input type="checkbox" name="my_g1" <% out.print(computerAI.ifExistField("enemyHit_g1")); %>></td><td><input type="checkbox" name="my_g2" <% out.print(computerAI.ifExistField("enemyHit_g2")); %>></td><td><input type="checkbox" name="my_g3" <% out.print(computerAI.ifExistField("enemyHit_g3")); %>></td><td><input type="checkbox" name="my_g4" <% out.print(computerAI.ifExistField("enemyHit_g4")); %>></td><td><input type="checkbox" name="my_g5" <% out.print(computerAI.ifExistField("enemyHit_g5")); %>></td><td><input type="checkbox" name="my_g6" <% out.print(computerAI.ifExistField("enemyHit_g6")); %>></td><td><input type="checkbox" name="my_g7" <% out.print(computerAI.ifExistField("enemyHit_g7")); %>></td>
	</tr>
	</table>
	</div> --%>
	
	

	
	<input type="submit" value="Hit">
	</form>

</body>
</html>
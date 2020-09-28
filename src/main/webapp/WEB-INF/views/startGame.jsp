<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<spring:url value="/resources/styles.css" var="stylesCss" />
<link href="${stylesCss}" rel="stylesheet">

</head>
<body>
	
	<form action="startGame" method="POST">
	<table border="1" cellpadding="2">
	<tr>
	<td></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td>
	</tr>
	<tr>
	<td>A</td><td><input type="checkbox" name="my_a1"></td><td><input type="checkbox" name="my_a2"></td><td><input type="checkbox" name="my_a3"></td><td><input type="checkbox" name="my_a4"></td><td><input type="checkbox" name="my_a5"></td><td><input type="checkbox" name="my_a6"></td><td><input type="checkbox" name="my_a7"></td>
	</tr>
	<tr>
	<td>B</td><td><input type="checkbox" name="my_b1"></td><td><input type="checkbox" name="my_b2"></td><td><input type="checkbox" name="my_b3"></td><td><input type="checkbox" name="my_b4"></td><td><input type="checkbox" name="my_b5"></td><td><input type="checkbox" name="my_b6"></td><td><input type="checkbox" name="my_b7"></td>
	</tr>
	<tr>
	<td>C</td><td><input type="checkbox" name="my_c1"></td><td><input type="checkbox" name="my_c2"></td><td><input type="checkbox" name="my_c3"></td><td><input type="checkbox" name="my_c4"></td><td><input type="checkbox" name="my_c5"></td><td><input type="checkbox" name="my_c6"></td><td><input type="checkbox" name="my_c7"></td>
	</tr>
	<tr>
	<td>D</td><td><input type="checkbox" name="my_d1"></td><td><input type="checkbox" name="my_d2"></td><td><input type="checkbox" name="my_d3"></td><td><input type="checkbox" name="my_d4"></td><td><input type="checkbox" name="my_d5"></td><td><input type="checkbox" name="my_d6"></td><td><input type="checkbox" name="my_d7"></td>
	</tr>
	<tr>
	<td>E</td><td><input type="checkbox" name="my_e1"></td><td><input type="checkbox" name="my_e2"></td><td><input type="checkbox" name="my_e3"></td><td><input type="checkbox" name="my_e4"></td><td><input type="checkbox" name="my_e5"></td><td><input type="checkbox" name="my_e6"></td><td><input type="checkbox" name="my_e7"></td>
	</tr>
	<tr>
	<td>F</td><td><input type="checkbox" name="my_f1"></td><td><input type="checkbox" name="my_f2"></td><td><input type="checkbox" name="my_f3"></td><td><input type="checkbox" name="my_f4"></td><td><input type="checkbox" name="my_f5"></td><td><input type="checkbox" name="my_f6"></td><td><input type="checkbox" name="my_f7"></td>
	</tr>
	<tr>
	<td>G</td><td><input type="checkbox" name="my_g1"></td><td><input type="checkbox" name="my_g2"></td><td><input type="checkbox" name="my_g3"></td><td><input type="checkbox" name="my_g4"></td><td><input type="checkbox" name="my_g5"></td><td><input type="checkbox" name="my_g6"></td><td><input type="checkbox" name="my_g7"></td>
	</tr>
	
	
	</table>
	<input type="submit" value="Start Game">
	</form>

</body>
</html>
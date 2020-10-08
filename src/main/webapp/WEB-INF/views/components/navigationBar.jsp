<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>JavaSpringWebApp</title>
    
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

<nav class="navbar navbar-expand-lg navbar-light bg-light navBar">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-5">
    <li class="nav-item">
    	<spring:url value="/" var="homeUrl" htmlEscape="true"/>
        <a class="nav-link" href="${homeUrl}">Home</a>
      </li>
      <li class="nav-item active">
      	<spring:url value="/create" var="create" htmlEscape="true"/>
        <a class="nav-link" href="${create}">Create</a>
      </li>
      <li class="nav-item active">
      	<spring:url value="/update" var="update" htmlEscape="true"/>
        <a class="nav-link" href="${update}">Update</a>
      </li>
      <li class="nav-item">
      	<spring:url value="/showAll" var="showAll" htmlEscape="true"/>
        <a class="nav-link" href="${showAll}">Show All</a>
      </li>
      <li class="nav-item">
      	<spring:url value="/getById" var="getById" htmlEscape="true"/>
        <a class="nav-link" href="${getById}">Get By Id</a>
      </li>
      <li class="nav-item">
      	<spring:url value="/delete" var="delete" htmlEscape="true"/>
        <a class="nav-link" href="${delete}">Delete</a>
      </li>
    </ul>
  </div>
</nav>
</body>
</html>

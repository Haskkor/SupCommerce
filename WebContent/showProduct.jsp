<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Show Product</title>
		
	</head>
	
	<header>
	
		<jsp:include page="header.jsp" />
	
	</header>
	
	<body>
		<h1> ${name} </h1>
		<h1> ${content} </h1>
		<h1> ${price} </h1>
	</body>
	
	<footer>
		<jsp:include page="/footer.jsp" />
	</footer>
	
</html>


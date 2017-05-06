<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.supinfo.sun.supcommerce.bo.SupProduct" %>
<%@ page import="java.util.List" %>
<%@ page import="com.supinfo.sun.supcommerce.doa.SupProductDao" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>List Products</title>
		
	</head>
	
	<header>
		<jsp:include page="header.jsp" />
	</header>
	
	<body>
	
		<table>
		
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
			</tr>
			
		<c:forEach items="${list}" var="l">
			<tr>
				<td><a href="showProduct?id=${l.id}">${l.name}</a></td>
				<td>${l.id}</td>
				<td>${l.name}</td>
				<td>${l.content}</td>
				<td>${l.price}</td>
				<c:if test="${not empty username}">
				<td>	
					<form action="auth/removeProduct" method="post">
						<input type="hidden" value="${l.id}" name="id" />
						<input type="submit" value="Remove" />
					</form>
				</td>	
				</c:if>	
			</tr>				
		</c:forEach>
		</table>
	</body>
	
	<footer>
		<jsp:include page="/footer.jsp" />
	</footer>
</html>
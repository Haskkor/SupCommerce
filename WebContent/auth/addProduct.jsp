<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		
	</head>
	
	<header>
	
		<%@ include file="/header.jsp" %>
	
	</header>
	
	<body>
		<form action="addProduct" method="post">
			<div>
				<label for="username">Name :</label>
				<input type="text" name="name" id="name" />
				<label for="content">Content :</label>
				<input type="text" name="content" id="content" />
				<label for="price">Price :</label>
				<input type="text" name="price" id="price" />
			</div>
			<div>
				<input type="submit" value="Submit" />
			</div>
		</form>
	</body>
	
	<footer>
		<%@ include file="/footer.jsp" %>
	</footer>
	
</html>
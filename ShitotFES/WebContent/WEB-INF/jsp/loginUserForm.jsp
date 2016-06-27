<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<form action="loginUserAction" method="post">

		Enter login name:<input type="text" name="name" required/><br>
		Enter password:<input type="password" name="password" required/><br> 
		<input type="submit" value="Log In" /> <br> 
		 ${logError}<br> <br> <br>
	</form>
	<a href="home">cancel</a>
</body>
</html>
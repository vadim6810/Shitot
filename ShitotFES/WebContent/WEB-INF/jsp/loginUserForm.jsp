<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
	<form action="loginUserAction" method="post">
	<dl>
	<dt>Enter login name:</dt>
	<dd><input type="text" name="name" required/></dd>
	</dl>
	<dl>
	<dt>Enter password:</dt>
	<dd><input type="password" name="password" required/></dd>
	</dl>
	 <p>${logError}</p>
	<button type="submit">Log In</button> 
	</form>
	<br>
	${cancel}
</body>
</html>
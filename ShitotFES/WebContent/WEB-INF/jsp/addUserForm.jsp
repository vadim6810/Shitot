<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new user</title>
</head>
<body>
<form action="addUserAction" method="post">

Enter user login:<input type="text" name="name" required/><br>
Enter user password:<input type="password" name="password" required/><br>

<input type="submit" value="Add user"/>
<br>
<br>
<br>
<br>
</form>
<a href="home">cancel</a>
</body>
</html>
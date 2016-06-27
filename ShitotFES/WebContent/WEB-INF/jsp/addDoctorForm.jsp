<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addDoctorAction">

Enter doctor login name:<input type="text" name="nameLogin" required/><br>
Enter doctor password:<input type="text" name="password" required/><br>
Enter doctor tel number:<input type="text" name="telNumber"/><br>
Enter doctor email:<input type="text" name="email"/><br>
Enter doctor address:<input type="text" name="address"/><br>
Enter doctor tel house:<input type="text" name="telHouse"/><br>
Enter doctor specialty:<input type="text" name="specialty"/><br>
Enter doctor target Audience:<input type="text" name="targetAudience"/><br>
Enter doctor other Specialty:<input type="text" name="otherSpecialty"/><br>
Enter doctor preferential:<input type="text" name="preferential"/><br>
Enter doctor expert:<input type="text" name="expert"/><br>
Enter doctor certification:<input type="text" name="certification"/><br>
Enter doctor lectors:<input type="text" name="lectors"/><br>
Enter doctor comments:<input type="text" name="comments"/><br>

<input type="submit" value="Add doctor"/>
<br>
<br>
<br>
<br>
</form>
<a href="home">cancel</a>
</body>
</html>
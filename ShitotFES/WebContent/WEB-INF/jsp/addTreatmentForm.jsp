<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create treatment</title>
</head>
<body>
<form action="addTreatmentAction">

Enter name source:<input type="text" name="infSourse"/><br>
Enter date meeting:<input type="date" name="dateMeetingStr"/><br>
Enter patient id:<input type="number" name="patientId"/><br>
Enter interval id:<input type="number" name="intervalId"/><br>
Enter is alternative doctor:<input type="checkbox" name="isAlternativeDoctor"/><br><br>


<input type="submit" value="Add teratment"/>
<br>
<br>
<br>
<br>
</form>
<a href="home">cancel</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updatePatientAction">

Enter treatment id:<input type="text" name="treatmentIdStr"/><br>
Enter name patient:<input type="text" name="patientName"/><br>
Enter date payment:<input type="text" name="datePaymentStr"/><br>
Enter sum payment:<input type="text" name="paymentStr"/><br>
Enter check number:<input type="text" name="checkNumber"/><br>
Enter description:<input type="text" name="description"/><br>

<input type="submit" value="Update patient"/>
<br>
<br>
<br>
<br>
</form>
<a href="home">cancel</a>
</body>
</html>
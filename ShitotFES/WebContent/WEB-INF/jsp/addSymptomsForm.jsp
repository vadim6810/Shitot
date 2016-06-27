<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new symptom</title>
</head>
<body>
<form action="addSymptom">
Symptom: <input type="text" name="symptomStr" required><button type="submit">Add</button>${result}<br>
</form>
<a href="home">Cancel</a>
</body>
</html>
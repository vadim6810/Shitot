<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add problem</title>
</head>
<body>
<h2>Create problem</h2>
	<form method="get" action="addProblemAction">
		<dl>
			<dt>Problem:</dt>
			<dd>
				<input type="text" name="problemName" required>
			</dd>
		</dl>
		<dl>
			<dt>Description:</dt>
			<dd>
				<input type="text" name="description">
			</dd>
		</dl>
		<dl>
			<dt>Symptoms:</dt>
			<dd>
				<c:forEach items="${symptoms}" var="s">
					<jsp:useBean id="s" scope="page" type="com.shitot.to.Symptom" />
					<input type="checkbox" name="${s.id}" id="${s.id}">
					<label for="${s.id}">${s.name}</label><br>
				</c:forEach>
				<a href="addSymptomForm">Add symptoms</a>
			</dd>
		</dl>
		<button type="submit">Add problem</button> ${resAddProbl}
	</form>
	<br>
	<a href="home">Cancel</a>
</body>
</html>
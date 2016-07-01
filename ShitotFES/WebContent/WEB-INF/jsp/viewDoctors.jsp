<%@page import="com.shitot.to.Doctor"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Doctors</title>
</head>
<body>
	<h2>Doctors</h2>
	<table border="1" cellpadding="8" cellspacing="0">
		<thead>
			<tr>
				<th>Name</th>
				<th>Specialties</th>
				<th>Tel number</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${doctorsList}" var="doctor">
				<jsp:useBean id="doctor" scope="page" type="com.shitot.to.Doctor" />
				<tr>
					<td>${doctor.name}</td>
					<td>${doctor.specialty}, ${doctor.otherSpecialty}</td>
					<td>${doctor.telNumber}</td>
					<td><a href="addDoctorForm?action=update&id=${doctor.id}">Update</a>
					<td><a href="deleteDoctor?id=${doctor.id}">Delete</a></td>
			</c:forEach>
		</tbody>
	</table>
	<!-- <a href="home">Home</a> -->
</body>
</html>
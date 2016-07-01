<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add doctor form</title>
</head>
<body>
	<h2>Add new doctor</h2>
	<hr>
	<jsp:useBean id="doctor" type="com.shitot.to.Doctor" scope="request"/>
	<form action="addDoctorAction" method="post">
	<input type="hidden" name="id" value="${doctor.id}">
		<dl>
			<dt>Enter doctor name:</dt>
			<dd>
				<input type="text" name="doctorName" required value="${doctor.name}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor password:</dt>
			<dd>
				<input type="text" name="password" required value="${doctor.password}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor email:</dt>
			<dd>
				<input type="email" name="email" value="${doctor.email}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor tel number:</dt>
			<dd>
				<input type="text" name="telNumber" value="${doctor.telNumber}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor home tel:</dt>
			<dd>
				<input type="text" name="telHouse" value="${doctor.telHouse}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor address:</dt>
			<dd>
				<input type="text" name="address" value="${doctor.address}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor specialty:</dt>
			<dd>
				<select name="specialty" id="problem" value="${doctor.specialty}">
					<option></option>
					<c:forEach items="${problems}" var="p">
						<option ${p.equals(doctor.specialty)?'selected':''}>${p}</option>
					</c:forEach>
				</select> <a href="addProblemForm">Add specialty</a>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor optional specialty:</dt>
			<dd>
				<select name="otherSpecialty" id="problemOpt" value="${doctor.otherSpecialty}">
					<option></option>
					<c:forEach items="${problems}" var="p">
						<option ${p.equals(doctor.otherSpecialty)?'selected':''}>${p}</option>
					</c:forEach>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor target Audience:</dt>
			<dd>
				From age:<input type="number" min="0" value="0" style="width: 50px;"name="targetFromAge" value="${doctor.targetFromAge}"> 
				To age:<input type="number"	min="0" value="0" style="width: 50px;" name="targetToAge" value="${doctor.targetToAge}"> 
				<input type="checkbox" name="targetMale" value="1" ${doctor.targetGender%2==1?'checked':''}>M 
				<input type="checkbox" name="targetFemale" value="2" ${doctor.targetGender>1?'checked':''}>F
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor preferential:</dt>
			<dd>
				<input type="text" name="preferential" value="${doctor.preferential}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor expert:</dt>
			<dd>
				<input type="text" name="expert" value="${doctor.expert}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor certification:</dt>
			<dd>
				<input type="text" name="certification" value="${doctor.certification}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor lectors:</dt>
			<dd>
				<input type="text" name="lectors" value="${doctor.lectors}"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor comments:</dt>
			<dd>
				<input type="text" name="comments" value="${doctor.comments}"/>
			</dd>
		</dl>
		<button type="submit">Save</button>
	</form>
	<a href="home">cancel</a>
</body>
</html>
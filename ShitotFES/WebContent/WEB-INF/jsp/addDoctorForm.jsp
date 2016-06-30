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
	<form action="addDoctorAction" method="post">
		<dl>
			<dt>Enter doctor name:</dt>
			<dd>
				<input type="text" name="doctorName" required />
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor password:</dt>
			<dd>
				<input type="text" name="password" required />
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor email:</dt>
			<dd>
				<input type="email" name="email"/>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor tel number:</dt>
			<dd>
				<input type="text" name="telNumber" />
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor home tel:</dt>
			<dd>
				<input type="text" name="telHouse" />
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor address:</dt>
			<dd>
				<input type="text" name="address" />
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor specialty:</dt>
			<dd>
				<select name="specialty" id="problem">
				<c:forEach items="${problems}" var="p">
				<option>${p}				
				</c:forEach>
				</select>
				<a href="addProblemForm">Add specialty</a>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor optional specialty:</dt>
			<dd>
				<select name="otherSpecialty" id="problemOpt">
				<c:forEach items="${problems}" var="p">
				<option>${p}				
				</c:forEach>
				</select>
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor target Audience:</dt>
			<dd>
				From age:<input type="number" style="width: 40px;" name="targetFromAge">
				To age:<input type="number" style="width: 40px;" name="targetToAge"> 
				<input type="checkbox" name="targetMale" value=1>M
				<input type="checkbox" name="targetFemale" value=2>F
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor preferential:</dt>
			<dd>
				<input type="text" name="preferential" />
			</dd>
		</dl>
		<dl>
			<dt>Enter doctor expert:</dt>
			<dd>
				<input type="text" name="expert" />
			</dd>
		</dl>
		<dl>
			<dt>
				Enter doctor certification:</dt>
			<dd>
				<input type="text" name="certification" />
			</dd>
		</dl>
		<dl>
			<dt>
				Enter doctor lectors:</dt>
			<dd>
				<input type="text" name="lectors" />
			</dd>
		</dl>
		<dl>
			<dt>
				Enter doctor comments:</dt>
			<dd>
				<input type="text" name="comments" />
			</dd>
		</dl>
		<button type="submit">Add doctor</button>
	</form>
	<a href="home">cancel</a>
	<script>
	var sel1 = document.getElementById("problem");
	var sel2 = document.getElementById("problemOpt");
	var pList = JSON.parse('${problemsList}');
	for (var p in pList) {
		var option = document.createElement("option");
		option.innerHTML = pList[p];
		sel1.appendChild(option);
		option = document.createElement("option");
		option.innerHTML = pList[p];
		sel2.appendChild(option);
	}
	</script>
</body>
</html>
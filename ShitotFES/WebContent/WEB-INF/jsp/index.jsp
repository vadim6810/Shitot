<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<title>Shitot</title>
<style>
body {
	font-family: Arial;
}

dt {
	display: inline-block;
	width: 250px;
}

dd {
	display: inline-block;
	margin-left: 8px;
	vertical-align: top;
}

a {
	text-decoration: none;
}
.content{
	background-color: #eeffff;
}
</style>
</head>
<body>
	<div class="container">
		<h1>
			<a href="home">SHITOT ORG</a>
		</h1>
	</div>
	<hr>
	<div class="container wrapper">
		<div class="row">
				<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> Add <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="addPatientForm">Create new patient</a></li>
								<li><a href="addDoctorForm">Create new doctor</a></li>
								<li><a href="addProblemForm">Create specialty</a></li>
								<li><a href="addSymptomForm">Create new symptom</a></li>
								<li class="divider"></li>
								<li><a href="addUserForm">Create new user</a></li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> Get <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="getAllPatientsAction">Get all patients</a></li>
								<li><a href="getAllDoctorsAction">Get all doctors</a></li>
								<li><a href="#">Get doctors by clinic city</a></li>
								<li><a href="#">Get doctors by specialization</a></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="navbar-text">${loggedUser}</li>
						<li class=""><a href="logout">logout</a></li>
					</ul>
				</div>
				</nav>
		</div>
		<div class="row content">
			<jsp:include page="${page}.jsp" />
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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
			<a href="home">SHITOT ORG - Doctor page</a>
		</h1>
		<h2>Doctor ${loggedDoctor}</h2>
	</div>
	<hr>
	<div class="container wrapper">
		<div class="row">
			<jsp:include page="${page}.jsp"/>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
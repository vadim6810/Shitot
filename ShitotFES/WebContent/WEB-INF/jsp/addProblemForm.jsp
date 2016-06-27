<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add problem</title>
<style>
dt {
	display: inline-block;
	width: 170px;
}
dd {
	display: inline-block;
	margin-left: 8px;
	vertical-align: top;
}
</style>
</head>
<body>
	<form method="post" action="addProblemAction">
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
			<dt>Symptoms</dt>
			<dd>
				<select id="symptoms" name="symptoms" multiple></select>
			</dd>
		</dl>
		<button type="submit">Add problem</button>
	</form>
	<script type="text/javascript">
		var select = document.getElementById("symptoms");
		var sList = JSON.parse('${symptomsList}');
		for ( var s in sList) {
			var option = document.createElement("option");
			option.setAttribute("value",sList[s].id);
			option.innerHTML = sList[s].nameSymptom;
			select.appendChild(option);
		}
	</script>
</body>
</html>
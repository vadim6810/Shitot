<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add clinic</title>
<style>
tr {
	text-align: left;
}

table td:FIRST-CHILD {
	width: 100px;
	text-align: left;
}
</style>
</head>
<body>
	<form action="addClinicAction">
		<dl>
			<dt>Clinic city:</dt>
			<dd>
				<input type="text" name="city" required />
			</dd>
		</dl>
		<dl>
			<dt>Clinic address:</dt>
			<dd>
				<input type="text" name="addressClinic" required />
			</dd>
		</dl>
		<h3>Schedule</h3>
		<table>
			<th>Day</th>
			<th>from</th>
			<th>to</th>
			<th>Day off</th>
			<tr>
				<td>SUN</td>
				<td><input type="time" name="sunFrom"></td>
				<td><input type="time" name="sunTo"></td>
				<td><input type="checkbox" name="sunOff"></td>
			</tr>
			<tr>
				<td>MON</td>
				<td><input type="time" name="monFrom"></td>
				<td><input type="time" name="monTo"></td>
				<td><input type="checkbox" name="monOff"></td>
			</tr>
			<tr>
				<td>TUE</td>
				<td><input type="time" name="wedFrom"></td>
				<td><input type="time" name="wedTo"></td>
				<td><input type="checkbox" name="tueOff"></td>
			</tr>
			<tr>
				<td>WED</td>
				<td><input type="time" name="thuFrom"></td>
				<td><input type="time" name="thuTo"></td>
				<td><input type="checkbox" name="wedOff"></td>
			</tr>
			<tr>
				<td>THU</td>
				<td><input type="time" name="thuFrom"></td>
				<td><input type="time" name="thuTo"></td>
				<td><input type="checkbox" name="thuOff"></td>
			</tr>
			<tr>
				<td>FRI</td>
				<td><input type="time" name="friFrom"></td>
				<td><input type="time" name="friTo"></td>
				<td><input type="checkbox" name="friOff"></td>
			</tr>
			<tr>
				<td>SAT</td>
				<td><input type="time" name="satFrom"></td>
				<td><input type="time" name="satTo"></td>
				<td><input type="checkbox" name="satOff"></td>
			</tr>
		</table>

		<input type="submit" value="Add clinic" /> <br> <br> <br>
		<br>
	</form>
	<a href="home">cancel</a>
</body>
</html>
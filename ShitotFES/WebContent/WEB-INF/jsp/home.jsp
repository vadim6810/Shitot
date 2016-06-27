<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shitot</title>
</head>
<body>
	<a href="loginUserForm">1. Login </a>/
	<a href="logout"> logout</a> --> ${loggedUser}
	<br>
	<a href="addPatientForm">2. Create new patient</a>
	<br>
	<a href="addDoctorForm">3. Create new doctor</a>
	<br>
	<a href="addProblemForm">4. Add problem</a>
	<br>
	<a href="getAllPatientForm">5. Get all patients</a>
	<br>
	<a href="getAllDoctorForm">6. Get all doctors</a>
	<br>
	<a href="getAllDoctorByCityForm">7. Get doctors by clinic city</a>
	<br>
	<a href="getAllDoctorBySpecializationForm">8. Get doctors by
		specialization</a>
	<br>
	<a href="getPatientNotPaymentForm">9. Get patient not payment</a>
	<br>
	<a href="addUserForm">10. Create new user</a>
	<br>
	<a href="addTreatmentForm">11. Create new treatment</a>
	<br>
	<a href="addSymptomsForm">12. Add new symptom</a><br>
	${logError}
</body>
</html>
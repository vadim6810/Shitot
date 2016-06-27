<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addPatientAction">

Enter patient name:<input type="text" name="name"/><br>
Enter patient tel number:<input type="text" name="telNumber"/><br>
Enter patient age:<input type="text" name="ageStr"/><br>
Enter patient doctor name:<input type="text" name="doctorName"/><br>
Enter patient date applicarion:<input type="text" name="dateApplication"/><br>
Enter patient date meeting:<input type="text" name="dateMeeting"/><br>
<br><br>
Problem
Enter patient problem name:<input type="text" name="nameProblem"/><br>
ב. התנהגות<input type="radio" name="bBehavior"/>
קשב<input type="radio" name="attention"/>
חרדה<input type="radio" name="anxiety"/>
דיכאון<input type="radio" name="depression"/>
ערך עצמי<input type="radio" name="selfWorth"/>
מיומנויות חברתיות<input type="radio" name="socialSkills"/>
טיפול זוגי<input type="radio" name="treatmentPaired"/>
אכילה<input type="radio" name="eating"/>
ה.אישיות<input type="radio" name="personality"/>
טראומה<input type="radio" name="trauma"/>
הדרכת הורים<input type="radio" name="parentalGuidance"/>
מ.לחץ<input type="radio" name="pressure"/>
PDD<input type="radio" name="pdd"/>
עבודה<input type="radio" name="work"/>

<input type="submit" value="Add patient"/>
<br>
<br>
<br>
<br>
</form>
<a href="home">cancel</a>
</body>
</html>
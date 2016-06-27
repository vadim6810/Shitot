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

Enter patient name:<input type="text" name="name" required/><br>
Enter patient tel number:<input type="text" name="telNumber"/><br>
Enter patient age:<input type="number" name="ageStr"/><br>
Enter patient description:<input type="text" name="description"/><br>

<br><br>
<!-- Problem
Enter patient problem name:<input type="text" name="nameProblem"/><br>
ב. התנהגות bBehavior<input type="radio" name="bBehavior"/>
קשב attention<input type="radio" name="attention"/>
 חרדה anxiety<input type="radio" name="anxiety"/><br>
 דיכאון depression<input type="radio" name="depression"/>
selfWorth ערך עצמי<input type="radio" name="selfWorth"/>
socialSkill sמיומנויות חברתיות<input type="radio" name="socialSkills"/><br>
 טיפול זוגי treatmentPaired<input type="radio" name="treatmentPaired"/>
  אכילה eating<input type="radio" name="eating"/>
personality ה.אישיות<input type="radio" name="personality"/><br>
trauma טראומה<input type="radio" name="trauma"/>
הדרכת הורים parentalGuidance<input type="radio" name="parentalGuidance"/>
 pressure מ.לחץ<input type="radio" name="pressure"/><br>
PDD<input type="radio" name="pdd"/>
עבודה work<input type="radio" name="work"/><br> -->

<input type="submit" value="Add patient"/>
<br>
<br>
<br>
<br>
</form>
<a href="home">cancel</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Seating Plan Development</h1>
<form method="get" action="http://localhost:8089/SeatArrangement/classList.jsp">
Enter capacity of each exam hall<br>
<input type="radio" name="capacity" value=20>20 students<br>
<input type="radio" name="capacity" value=30>30 students <br>
<input type="radio" name="capacity"value=40>40 students <br><br>
<input type="submit" value="Submit">
</form>
</body>
</html>
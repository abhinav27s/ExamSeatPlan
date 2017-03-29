<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Select all those classes whose exam is to be held during same time-</h2>
<form method="post" action="http://localhost:8089/SeatArrangement/Trial">
<% String ar[]={"CE","IT","ECE","EIC","EL"};
  int c=Integer.parseInt(request.getParameter("capacity"));
for(int i=0;i<ar.length;i++)
{%>
<h3><%=ar[i] %></h3><br>
<% for(int j=1;j<=4;j++)
   { %>
    <input type="checkbox" name="chk" value=<%=ar[i]+"."+j %>><%=ar[i] %><%=j %><br>
<% }
}%>
<input type="hidden" name="capacity" value=<%=c %> >
<input type="submit" value="Submit">//
</form>
</body>
</html>
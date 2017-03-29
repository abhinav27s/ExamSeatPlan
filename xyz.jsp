<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.sql.*" %>
    <%@page import="java.util.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body><table>
<% //List<String> a=new ArrayList<>(5);
  String[] a=(String[])request.getAttribute("abc");
  for(int i=0;a[i]!=null;i++)
  { String x=a[i];
  String[] y=x.split("[.]");
  %><tr><td><%=y[0] %></td><td><%=y[1] %></td><td><%=y[2] %></td></tr>
  <% 	  
  }
  %>
</table>  
</body>
</html>
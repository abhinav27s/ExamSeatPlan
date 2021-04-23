<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%@page import="java.util.*" %>
<%  Connection con=null;
    Statement stmt=null,stmt2=null;
    ResultSet rs=null;
    int x;
try { 
   	Class.forName("oracle.jdbc.driver.OracleDriver"); 
   	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
   	 stmt=con.createStatement();
   	stmt2=con.createStatement();
   	 
   } catch (Exception e) {
   	// TODO Auto-generated catch block
   	e.printStackTrace();
   }

%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<th>S.No.</th>
<th>Starting R.No.</th>
<th>Ending R.No.</th>
<th>Exam Hall No.</th>
</tr>
<% 
try{ 
rs=stmt.executeQuery("select * from tempdata");
while(rs.next())
   {%><tr>
     <td><%=rs.getInt(1) %></td>
     <td><%=rs.getString(2) %></td>
     <td><%=rs.getString(3) %></td>
     <td><%=rs.getInt(4) %></td>
     </tr>
 <% }
   stmt2.execute("drop table tempdata"); 
}
catch(Exception e)
{ e.printStackTrace(); }
try{
	rs.close();
	stmt.close();
	con.close();
}
catch(Exception e)
{ }
%>
</table>
</body>
</html>
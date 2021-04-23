package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Table
 */
@WebServlet("/Table")
public class Table extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	Statement stmt1=null,stmt2=null,stmt3=null,stmt=null;
    ResultSet rs=null,rs2=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Table() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 try {
		    	Class.forName("oracle.jdbc.driver.OracleDriver"); 
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
				 stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 stmt1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 stmt2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				 stmt3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    public void destroy() {
		// TODO Auto-generated method stub
		try{
			rs.close();
			stmt.close();
			stmt1.close();
			stmt2.close();
			stmt3.close();
			con.close();
		}
		catch(Exception e)
		{ }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String[] ar=(String[])request.getAttribute("checkarr"); 
	int[] x=(int[])request.getAttribute("roomarr"); 
	int c=(int)request.getAttribute("capacity");
	int cnt=0,n=0;
	try{
		stmt1.execute("create table tempdata(nodeNo number(4),startNo varchar(20),endNo varchar(20),roomNo number(4))");
	    for(int i=0;i<ar.length;i++)
	    { String[] s=ar[i].split("[.]");
	    rs=stmt.executeQuery("select rollno from ymca_db where stream='"+s[0]+"' and year="+Integer.parseInt(s[1])+"order by rollno");
	    rs2=stmt2.executeQuery("select rollno from ymca_db where stream='"+s[0]+"' and year="+Integer.parseInt(s[1])+"order by rollno");
	    rs2.last();
	    while(rs.next())
	    {	
	     cnt=1;
	      n++;
	      String start=rs.getString(1);
	      while(cnt<c/2 && rs.next()) 
	      { 
	    	 cnt++;
	        if(rs.getString(1).equals(rs2.getString(1)))//checking if last row is reached.. 
	         break; 	
	      }
	      if(rs==null)
	  	    rs.previous();
	      String end=rs.getString("RollNo");
	      stmt3.execute("insert into tempdata values("+n+",'"+start+"','"+end+"',"+x[n]+")");
	    }
	    rs.first();
	    }
	    n=0;
	    RequestDispatcher rd=request.getRequestDispatcher("result.jsp");
	    rd.forward(request,response);
	}
	catch(Exception e)
	{e.printStackTrace(); }
	}

}

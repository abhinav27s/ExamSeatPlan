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
 * Servlet implementation class classList
 */
@WebServlet("/classList")
public class classList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	 Statement stmt=null;
     ResultSet rs=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public classList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 try {
		    	Class.forName("oracle.jdbc.driver.OracleDriver"); 
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
				 stmt=con.createStatement();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	int c=Integer.parseInt(request.getParameter("capacity"));
	String[] ar=(request.getParameterValues("chk"));
	String[] a=new String[10];
	try{
	for(int i=0;i<ar.length;i++)
	{ String[] x=ar[i].split("[.]");
	  rs=stmt.executeQuery("select * from collgstrength where stream='"+x[0]+"' and year="+Integer.parseInt(x[1]));
	  rs.next();
	  a[i]=rs.getString(1)+"."+rs.getInt(2)+"."+rs.getInt(3);
	}
	RequestDispatcher rd=request.getRequestDispatcher("xyz.jsp");
	request.setAttribute("abc", a);
	request.setAttribute("capacity", c);
	rd.forward(request,response);
	}
	catch(Exception e)
	{ e.printStackTrace(); }
  }

}

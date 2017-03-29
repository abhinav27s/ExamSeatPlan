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
@WebServlet("/Trial")
public class Trial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	 Statement stmt=null,stmt2=null;
     ResultSet rs=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Trial() {
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
				 stmt2=con.createStatement();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	int c=Integer.parseInt(request.getParameter("capacity"));
	String[] ar=(request.getParameterValues("chk"));
	int[][] G=new int[200][200];
	for(int p=1;p<200;p++)
	     for(int q=1;q<200;q++)
	       G[p][q]=0;
	int n,k=0;
	try{
	for(int i=0;i<ar.length;i++)
	{ String[] x=ar[i].split("[.]");
	  rs=stmt.executeQuery("select count(*) from ymca_db where stream='"+x[0]+"' and year="+Integer.parseInt(x[1]));
	  rs.next();
	  if((rs.getInt(1))%(c/2)==0)
	  n=rs.getInt(1)/(c/2);
	  else
	  n=(int)(rs.getInt(1)/(c/2))+1;	  
	  for(int p=k+1;p<=k+n;p++)
	   for(int q=k+1;q<=k+n;q++)	  
	    if(p!=q)
		 G[p][q]=1;
	  k=k+n;
	}	 
	/*for(int j=11;j<=71;j++)
	stmt2.execute("insert into ymca_db values('"+j+"','CE',1)");*/
	RequestDispatcher rd=request.getRequestDispatcher("mColoring");
	request.setAttribute("adjmatrix", G);
	request.setAttribute("capacity", c);
	request.setAttribute("nodes", k);
	request.setAttribute("checkarr", ar);
	rd.forward(request,response);
	}
	catch(Exception e)
	{ e.printStackTrace(); }
  }

}

package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class mColoring
 */
@WebServlet("/mColoring")
public class mColoring extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int[] x=new int[500]; 
	int[] count=new int[500]; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mColoring() {
        super();
        // TODO Auto-generated constructor stub
    }
	boolean isValid(int k,int c,int n,int G[][])
	{ for(int i=1;i<=n;i++)
	   {if((G[k][i]==1 && x[i]==c) || count[c]==2)//as each room can have at max 2 nodes/groups
	     return false;
	   }
	   return true;
	}
	boolean mColor(int k,int n,int G[][])
	{ for(int c=1;;c++)
	   if(isValid(k,c,n,G))
	   {x[k]=c;
	    count[c]++;
	    if(k==n)
	     {  
	        return true;
	     }
	    else if(mColor(k+1,n,G))
	    return true;
	   }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int[][]G=(int[][])request.getAttribute("adjmatrix");
	int c=(int)request.getAttribute("capacity");
	int n=(int)request.getAttribute("nodes"); 
	String[] ar=(String[])request.getAttribute("checkarr"); 
	 mColor(1,n,G);
	 /*for(int y=1;y<=n;y++)
	  System.out.println("Node"+y+"-> Room"+x[y]);*/
	 for(int i=0;i<=n;i++)
       count[i]=0;
	RequestDispatcher rd=request.getRequestDispatcher("Table");
	request.setAttribute("checkarr", ar);
	request.setAttribute("roomarr", x);
	request.setAttribute("capacity", c);
	rd.forward(request, response);
	}

}

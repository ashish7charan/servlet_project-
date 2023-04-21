package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TransferDAO;

@SuppressWarnings("serial")
@WebServlet("/tr")
public class TransferServlet extends HttpServlet {
	 float amt=0;
     long accno=0;
     int pin=0;
     public void doPost(HttpServletRequest req,HttpServletResponse res)
 			throws ServletException,IOException 
	{     
    	 System.out.println("do post method of transfer");
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		HttpSession hs=req.getSession(false);
		if(hs==null)
		{
			pw.println("<center><h2>SESSION IS EXPIRED PLS LOG IN AGAIN..</h2></center>"+"<br>");	
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		else
		{
		    int c=0;
					try {
						 amt=Float.parseFloat(req.getParameter("amt"));
						 accno=Long.parseLong(req.getParameter("acno"));
						 pin=Integer.parseInt(req.getParameter("pin"));
						 
						}
						catch(Exception e)
						{
							  ++c;
							  System.out.println(c);
							  pw.println("<center><h2>PLS ENTER NUMBER...</h2></center>"+"<br>");
							RequestDispatcher rd=req.getRequestDispatcher("trans.html");
							rd.include(req, res);
						
						}
					
					if(c==0)
					{
						if(amt>100000)
						{
						
							pw.println("<center><h2>YOU CAN WITHDRAWL TRANSFER 100000 AT A TIME...</h2></center>"+"<br>");
							RequestDispatcher rd=req.getRequestDispatcher("trans.html");
							rd.include(req, res);
						}
						else if(amt<=99)
						{
							pw.println("<center><h2>YOU CAN TRANSFER MINIMUM 100 AT A TIME...</h2></center>"+"<br>");
							RequestDispatcher rd=req.getRequestDispatcher("trans.html");
							rd.include(req, res);
						}
						else
						{
						String s=new TransferDAO().Deposit(req);
						pw.println("<center><h2>"+s+"</h2></center>"+"<br>");
						RequestDispatcher rd=req.getRequestDispatcher("home.html");
						rd.include(req, res);
					}
					}
			
			
		}

}
}

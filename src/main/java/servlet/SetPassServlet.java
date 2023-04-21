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

import dao.SetPassword_DAO;
@SuppressWarnings("serial")
@WebServlet("/set")
public class SetPassServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException
	{  
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		if(req.getParameter("pass").equals(req.getParameter("pass1")))
		{
		  try {
			int k=new SetPassword_DAO().setPassword(req) ;
			if(k>0)
			{
				
				pw.println("<center><h2>PASSWORD CHANGED...</h2></center>"+"<br>");
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, res);
			}
			else
			{
				pw.println("<center><h2>TRANSACTION FAILED...</h2></center>"+"<br>");
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, res);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}else
		{
			pw.println("<center><h2>PASSWORD IS NOT MATHCED...</h2></center>"+"<br>");
			RequestDispatcher rd=req.getRequestDispatcher("set.html");
			rd.include(req, res);
		}

	}
 }

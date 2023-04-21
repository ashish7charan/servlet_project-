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

@SuppressWarnings("serial")
@WebServlet("/exit")
public class ExitServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException
			{
		          PrintWriter pw=res.getWriter();
		          res.setContentType("text/html");
		          HttpSession hs=req.getSession(false);
		          if(hs==null)
		          {
		        	 pw.println("Session is Expired...");
		  			RequestDispatcher rd1=req.getRequestDispatcher("login.html");
		  			rd1.include(req, res);
		          }
		          else
		          {
		        	  pw.println("<html>");
		        	  pw.println("<head><title>My Servlet</title></head>");
		        	  
		        	  pw.println("<cenetr><body>");
		        	  pw.println("<font color='red'><h4>Logout Sucssefully..</h4></font>");
		        	  pw.println("</body></cenetr></html>");
		        	  hs.invalidate();
		        	  ViewBalance_Servlet.m=0;
		        	  ViewBalance_Servlet.common=0;
		        	  LoginServlet.n=0;
		        	  With_servlet.block=0;
		        	  Deposite_Servlet.block=0;
		        
		  			RequestDispatcher rd1=req.getRequestDispatcher("login.html");
		  			rd1.include(req, res);
		        	  
		          }
			}

}

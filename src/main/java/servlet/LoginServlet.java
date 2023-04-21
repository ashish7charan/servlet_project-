package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
@SuppressWarnings("serial")
@WebServlet("/log")
public class LoginServlet extends HttpServlet {
	
	public static int n=0;
	 ArrayList<String> al=new ArrayList<>();
	public void init(ServletConfig servletconfig)
		    throws ServletException
		{
		   System.out.println("inti method");
		}
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	
	throws ServletException,IOException
	{   System.out.println("dopost of n");
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		try {
			al = new LoginDAO().Login(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			 pw.println("<center><h2>PLS ENTER NUMBER ONLY..</h2></center>"+"<br>");
	
		}
		if(al.get(0)==null||al.get(1)==null)
		{   
			if(al.get(0)==null) {
			pw.println("<center><h2>INVALID MOBILE NUMBER OR PASSWORD..</h2></center>"+"<br>");
			
			RequestDispatcher rd= req.getRequestDispatcher("login.html");
			rd.include(req, res);
			}
			else
				
			pw.println("<center><h2>YOUR CARD IS BLOCKED WAIT PLS WAIT SOME TIME...</h2></center>"+"<br>");
			
				
		}
		else
		{
			 n=0;
			pw.println("<center><h2> WELCOME IN MINI BANK..</h2></center>"+"<br>");
			HttpSession hs=req.getSession();
			hs.setAttribute("mno", req.getParameter("mno"));
			System.out.println("Log in servlet mno"+req.getParameter("mno"));
			RequestDispatcher rd= req.getRequestDispatcher("home.html");
			rd.include(req, res);
		}
}
}

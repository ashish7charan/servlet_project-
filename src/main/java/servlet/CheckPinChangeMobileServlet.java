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

import dao.ChangePinDAO;
@SuppressWarnings("serial")
@WebServlet("/change")
public class CheckPinChangeMobileServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException
	{  
		PrintWriter pw=res.getWriter();
		System.out.println("change");
		res.setContentType("text/html");
		HttpSession hs=req.getSession(false);
		if(hs==null)
		{
			
			 pw.println("<center><h2>SESSION IS EXPIRED PLS LOG IN AGAIN..</h2></center>"+"<br>");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);			
		}
		else
		{       long mobile=0;
			System.out.println("Chnage not null");
			int c=0;
			try
			{
				
				mobile=Long.parseLong(req.getParameter("mno"));
			}
			catch(Exception e)
			{
				++c;
				
				 pw.println("<center><h2>PLS ENTER NUMBER ONLY..</h2></center>"+"<br>");
				
				RequestDispatcher rd=req.getRequestDispatcher("change.html");
				rd.include(req, res);
				
			}
			if(c==0)
			{
				
				String m=(String) hs.getAttribute("mno");
				long mno=Long.parseLong(m);
						
				 if(mno==mobile) {
				try {
					String s=new ChangePinDAO().changePin(req);
					pw.println("<center><h2>"+s+"</h2></center>"+"<br>");
					RequestDispatcher rd=req.getRequestDispatcher("home.html");
					rd.include(req, res);
				} catch (Exception e) {
					e.printStackTrace();
				 System.out.println("hs"+mno);
				 System.out.println("req"+mobile);
				}
			}
			
			else
			{
				
				 pw.println("<center><h2>INVALID MOBILE NUMBER..</h2></center>"+"<br>");

				RequestDispatcher rd=req.getRequestDispatcher("change.html");
				rd.include(req, res);
				
			}
			
			
		}
		
	}
}}
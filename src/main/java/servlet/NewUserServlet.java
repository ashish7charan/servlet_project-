package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewRegisterationDAO;
@SuppressWarnings("serial")
@WebServlet("/new")
public class NewUserServlet extends HttpServlet {

		protected void doPost(HttpServletRequest req,HttpServletResponse res)
		throws ServletException,IOException
		{
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			String result=NewRegisterationDAO.custReg(req);
			if(result==null)
			{
				
				 pw.println("<center><h2>REGISTRATION FAILED...</h2></center>"+"<br>");
					
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.include(req, res);
		    }
			else
			{
				
				 pw.println("<center><h2>REGISTRATION SUCCESFULL...</h2></center>"+"<br>");
				 pw.println("<center><h2>DETAILS ARE...</h2></center>"+"<br>");
				 pw.println("<center><h2>YOUR GENERATED AC NUMBER IS..."+req.getAttribute("acno")+"</h2></center>"+"<br>");
				 pw.println("<center><h2>YOUR GENERATED ATM NUMBER IS..."+req.getAttribute("atm")+"</h2></center>"+"<br>");
				 pw.println("<center><h2>ACCOUNT CREATION DATE IS..."+req.getAttribute("creation")+"</h2></center>"+"<br>");
				 pw.println("<center><h2>ATM CARD EXPIRY DATE IS..."+req.getAttribute("expiry")+"</h2></center>"+"<br>");
				 pw.println("<center><h2>ATM PIN..."+req.getAttribute("pin")+"</h2></center>"+"<br>");
				 RequestDispatcher rd=req.getRequestDispatcher("login.html");
					rd.include(req, res);
			}
}
	}

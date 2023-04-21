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

import dao.CheckNo_DAO;
@SuppressWarnings("serial")
@WebServlet("/mobile")
public class CheckMobileServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException
			{  
				PrintWriter pw=res.getWriter();
				res.setContentType("text/html");
				
				   try {
					String status=new CheckNo_DAO().checkMno(req);
					if(status==null)
					{
						 pw.println("<center><h2>INVALID NUMBER</h2></center>"+"<br>");
						RequestDispatcher rd=req.getRequestDispatcher("forget.html");
						rd.include(req, res);
					}
					else
					{
					     HttpSession hs=req.getSession();
					     hs.setAttribute("mno", req.getParameter("mno"));
						RequestDispatcher rd=req.getRequestDispatcher("set.html");
						rd.include(req, res);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}


	
}
package servlet;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.PassBook;
import dao.ViewpassBookDAO;
@SuppressWarnings("serial")
@WebServlet("/passbook")
public class ViewPassBokkServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException
	{  
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		HttpSession hs=req.getSession(false);
		if(hs==null)
		{
			pw.println("SESSION IS EXPIRED..PLS LOGIN");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		else
		{
		      try {
				ArrayList<PassBook> al= new ViewpassBookDAO().viewPass(req);
				if(al.size()==0)
				{
					pw.println("NO ENTRY AVAILABLE..");
				}
				else
				{
					pw.println("YOUR HISTROY..<br>");
					Iterator<PassBook> it=al.iterator();
					pw.println("<table>");
					pw.println("<tr><th>Sno.</th><th>Type</th><th>Date</th><th>Time</th><th>Amount</th>  <th>Remaning balance</th</tr>");
					
					
					while(it.hasNext())
					{
						PassBook pb=(PassBook)it.next();
						
						pw.println("<tr>");
					    pw.println("<td>" + pb.getSno() + "</td>");
					    pw.println("<td>" + pb.gettType() + "</td>");
					    pw.println("<td>" + pb.gettDate() + "</td>");
					    pw.println("<td>" + pb.gettTime() + "</td>");
					    pw.println("<td>" + pb.getAmount() + "</td>");
					    pw.println("<td>" + pb.getrBalance() + "</td>");
					    pw.println("</tr>");
					
//						pw.println("<html><body><table><tr><td>"+pb.getSno()+"</td>"+"<td>"+pb.gettType()+"</td>"+
//						"<td>"+pb.gettDate()+"</td>"+"<td>"+pb.gettTime()+"</td>"+"<td>"+pb.getAmount()+"<td>"+"<td>"+pb.getrBalance()+"</td></tr></table></body></html>");	
					}
					
					pw.println("</table>");
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
 }

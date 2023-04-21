package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CradManageDAO;
import dao.DAO_Balance;
@SuppressWarnings("serial")
@WebServlet("/vbal")
public class ViewBalance_Servlet extends HttpServlet {
	  public static  int m=0;
	  public static int common=0;
	   
	  public void destroy()
	  {
		  System.out.println("destroy");
	  }
	  
	  public void init(ServletConfig servletconfig)
			    throws ServletException
			{
			   System.out.println("init");
			}
	  
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException {
		System.out.println("Do post");
		// TODO Auto-generated method stu
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		String bal=null;
		HttpSession hs=req.getSession(false);
		if(hs!=null) {
		
		try {
			bal = new DAO_Balance().ViewBalance(req);
		} catch (Exception e) {
			
			pw.println("<center><h6>INVALID PIN..</h6></center>");
		}
		
		if(common==3)
		{
			System.out.println("commons is :"+common);
		}
		
		if(bal!=null)
		{
		  
		   RequestDispatcher rd=req.getRequestDispatcher("home.html");
			rd.include(req, res);
		   pw.println("<center><h6>"+bal+"</h6></center>"+"<br>");
		     m=0;
		     common=0;
			With_servlet.block=0;
			Deposite_Servlet.block=0;
		     System.out.println("commons is :"+common);
		   System.out.println("balance is not null"+m);
		}
		else if(bal==null&&m!=3)
		{
			
			pw.println("<center><h6>INVALID PIN..</h6></center>"+"<br>");
			RequestDispatcher rd=req.getRequestDispatcher("home.html");
			rd.include(req, res);
			m++;
			common++;
			System.out.println("commons is :"+common);
			System.out.println("balance is  null "+ m);
		
			
		}
		else if(m>2&&bal==null)
			{     m=0;
			    common=0;
			 
				With_servlet.block=0;
				Deposite_Servlet.block=0;
				
			    System.out.println("commons is :"+common);
			  System.out.println("balance is  null and m is 3 "+ m);
			     
				pw.println("<center><h6>YOUR CARD IS BLOCKED FOR 24 HOURS..</h6></center>"+"<br>");
				try {
					//HttpSession hs=req.getSession();
//					hs.setAttribute("atnpin", req.getAttribute("pin"));
//					System.out.println("for checking"+req.getAttribute("pin"));
					new CradManageDAO().checkCardStatus(req);
					System.out.println("try block after");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				RequestDispatcher rd1=req.getRequestDispatcher("login.html");
				rd1.include(req, res);
			}
	}
		else
		{
			pw.println("<center><h6>SESSION IS EXPIRED..</h6></center>");
			RequestDispatcher rd1=req.getRequestDispatcher("login.html");
			rd1.include(req, res);
			
		}
	}
	

}

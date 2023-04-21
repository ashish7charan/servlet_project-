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

import dao.CradManageDAO;
import dao.DAO_With;
@SuppressWarnings("serial")
@WebServlet("/with")
public class With_servlet extends HttpServlet {
	 public static int block=0;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{      int amt=0;
	       int pin=0;
	      
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		HttpSession hs=req.getSession(false);
		if(hs==null)
		{
			pw.println("session is expired");
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
		else
		{
			     int c=0;
			try {
				 amt=Integer.parseInt(req.getParameter("amt"));
				 pin=Integer.parseInt(req.getParameter("apin"));
				 
				}
				catch(Exception e)
				{
					  ++c;
					  System.out.println(c);
					pw.println("PLS ENTER NUMBER");
					RequestDispatcher rd=req.getRequestDispatcher("with.html");
					rd.include(req, res);
				
				}
			
			if(ViewBalance_Servlet.common==4)
			{
				System.out.println("Commons in with "+ViewBalance_Servlet.common);
			}
			if(c==0)
			{
				
			 if(amt%100!=0)
			{
				
				pw.println("<center><h2>PLS ENTER VALID AMOUNT..</h2></center>"+"<br>");
				RequestDispatcher rd=req.getRequestDispatcher("with.html");
				rd.include(req, res);
				
			
			}
			else if(amt>10000)
			{
				pw.println("YOU CAN WITHDRAWL MAXIMUM 10000 AT A TIME..");
				RequestDispatcher rd=req.getRequestDispatcher("with.html");
				rd.include(req, res);
			}
			else if(amt<=99)
			{
				pw.println("YOU CAN WITHDRAWL MINIMUM 100 AT A TIME..");
				RequestDispatcher rd=req.getRequestDispatcher("with.html");
				rd.include(req, res);
			}
			else
			{
			    String result= new DAO_With().withdrwal(req);
			    if(result!=null)
			    {    block=0;
			   
			    ViewBalance_Servlet.m=0;
				With_servlet.block=0;
				Deposite_Servlet.block=0;
				ViewBalance_Servlet.common=0;
			    System.out.println("Commons in with "+ViewBalance_Servlet.common);
			    	   pw.println(result);
					    RequestDispatcher rd=req.getRequestDispatcher("home.html");
						rd.include(req, res);
			    }
			    
			    else if(result==null&&block!=3)
			    {
			    	pw.println("<center><h2>INVALID PIN..</h2></center>"+"<br>");
					RequestDispatcher rd=req.getRequestDispatcher("home.html");
					rd.include(req, res);
					block++;
					ViewBalance_Servlet.common++;
					System.out.println("Commons in with "+ViewBalance_Servlet.common);
					System.out.println("result is  null "+ block);
			    }
			    else if(block>2&&result==null)
				{    block=0;
				 ViewBalance_Servlet.m=0;
				 With_servlet.block=0;
				 Deposite_Servlet.block=0;
				 ViewBalance_Servlet.common=0;
				System.out.println("Commons in with "+ViewBalance_Servlet.common);
				  System.out.println("balance is  null and m is 3 "+ block);
				     
					pw.println("<center><h2>YOUR CARD IS BLOCKED FOR 24 HOURS..</h2></center>"+"<br>");
					try {
						//HttpSession hs=req.getSession();
//						hs.setAttribute("atnpin", req.getAttribute("pin"));
//						System.out.println("for checking"+req.getAttribute("pin"));
						new CradManageDAO().checkCardStatus(req);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					RequestDispatcher rd1=req.getRequestDispatcher("login.html");
					rd1.include(req, res);
				}
		}
			    
			   
			}
		}
			
		}
				
				
				
				
		
		
	}

	
	
		
	




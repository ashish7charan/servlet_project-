package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.servlet.ServletRequest;

import dbconnection.DBcon;
import servlet.Deposite_Servlet;
import servlet.ViewBalance_Servlet;
import servlet.With_servlet;
public class LoginDAO {
	private static final String SELECT_QURY="SELECT * FROM CustomerDetail WHERE MOBILENO=? AND PASSWORD=?";
	  private static final String CARD_QURY="SELECT * FROM ATMBLOCK WHERE PIN=?";
	  private static final String CARD_DELETE="DELETE FROM ATMBLOCK WHERE PIN=?";
	   public String result=null;
	   public String result1=null;
	   int pin=0;
	   ArrayList<String> ar=new ArrayList<>();
	   public static String al=null;
	   
	   
	public   ArrayList<String> Login(ServletRequest req) throws Exception
	{   try
	{
		Connection con=DBcon.getCon();
		PreparedStatement ps=con.prepareStatement(SELECT_QURY);
		ps.setLong(1, Long.parseLong(req.getParameter("mno")));
		ps.setString(2, req.getParameter("pass"));
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			ViewBalance_Servlet.m=0;
			With_servlet.block=0;
			Deposite_Servlet.block=0;
			ViewBalance_Servlet.common=0;
			System.out.println("Commons in dao login "+ViewBalance_Servlet.common);
			result="ok";
			pin=rs.getInt(10);
			PreparedStatement ps1=con.prepareStatement(CARD_QURY);
			ps1.setInt(1, pin);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next())
			{
				System.out.println("inside crad block query");
				LocalDate d1=LocalDate.now();
				  Date d2=rs1.getDate(3);
				  LocalDate d3=d2.toLocalDate();
				  
				  LocalTime now=LocalTime.now();
				  LocalTime ut=LocalTime.parse(rs1.getString(5));
				  
				  
				  if(d1.compareTo(d3)>=0)
				  {
					  System.out.println("Inside first if condtion");
					  if(d1.compareTo(d3)==0)
					  {
						  System.out.println("inside ==");
						  if(now.compareTo(ut)>0||now.compareTo(ut)==0)
						  {
							  System.out.println("inside second if ");
							  PreparedStatement ps3=con.prepareStatement(CARD_DELETE);
								 ps3.setInt(1, pin);
								 ps3.executeUpdate();
								 System.out.println("card delete");
								  result1="OK";
						  }
						  else
						  {
							  result1=null; 
						  }
						  
					  }
					  else if(d1.compareTo(d3)>0)
					  {
						  System.out.println("inside greater then");
						  PreparedStatement ps3=con.prepareStatement(CARD_DELETE);
							 ps3.setInt(1, pin);
							 ps3.executeUpdate();
							 System.out.println("card delete");
							  result1="OK";
					  }
					
				  }
				  else
				  {
					 
					 
					  result1=null;
				  }
				
			}
			else
			{
				
				
				result1="OK";
			}
			
		}
		ar.add(result);
		ar.add(result1);
		
   System.out.println("Lnegth of ar is"+ar.size());
	System.out.println("Ar is"+ar);
	}
	
	catch(Exception e)
	{
		throw new IllegalArgumentException();
	}
		
		
		return ar;
		
	}
}
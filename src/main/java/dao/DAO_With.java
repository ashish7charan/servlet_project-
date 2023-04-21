package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dbconnection.DBcon;
public class DAO_With {
	
	private static final String CHECK_BALANCE="SELECT * FROM CUSTOMERBANK WHERE PIN=?";

	private static final String UPDATE_BALANCE="UPDATE CUSTOMERBANK SET BALANCE=BALANCE-? WHERE PIN=?";
	
	private static final String INSERT_PASS="INSERT INTO PASSBOOK VALUES(PASS_SQNO.NEXTVAL,?,?,?,?,?,?)";
	
	
	static String s;
	public  String withdrwal(HttpServletRequest req)
	{
		try
		{
			
			Connection con=DBcon.getCon();
			PreparedStatement ps=con.prepareStatement(CHECK_BALANCE);
			HttpSession hs=req.getSession(false);
			String mobile=(String) hs.getAttribute("mno");
			long mno=Long.parseLong(mobile);
			int amt=Integer.parseInt(req.getParameter("amt"));
			int pin=Integer.parseInt(req.getParameter("apin"));
			ps.setInt(1, pin);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("First rs if");
				float balance=rs.getFloat(5);
				if(amt<=balance)
				{
					PreparedStatement ps1=con.prepareStatement(UPDATE_BALANCE);
					ps1.setFloat(1, amt);
					ps1.setInt(2, pin);
					int k=ps1.executeUpdate();
					if(k>0)
					{
						System.out.println("inside the update ");
						s="Pls Collect your cash";
						LocalDate date=LocalDate.now();
			            LocalTime time=LocalTime.now();
			            String ti=""+time;
						PreparedStatement ps2=con.prepareStatement(INSERT_PASS);
						ps2.setString(1, "DEBIT");
						ps2.setDate(2, Date.valueOf(date));
						ps2.setString(3, ti);
						ps2.setInt(4, amt);
						ps2.setFloat(5 ,balance-amt);
						ps2.setLong(6, mno);
						int z=ps2.executeUpdate();
						System.out.println("passbook update..");
					}
					    	
				}
				else
				{
					System.out.println("rs inside else");
					s="Insufficient fund..";
				}
			}
			else
			{
				s=null;
			    System.out.println("First rs else");	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return s;
		
   }
 }

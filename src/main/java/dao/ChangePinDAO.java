package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import dbconnection.DBcon;

public class ChangePinDAO {
	 
	private static final String SELECT_QURY="SELECT PIN FROM CUSTOMERDETAIL WHERE MOBILENO =?";
	
	private static final String UPDATE_QURY1="UPDATE CUSTOMERGEN SET PIN=? WHERE PIN=?";
	
	private static final String UPDATE_QURY2="UPDATE CUSTOMERBANK SET PIN=? WHERE PIN=?";
	
	private static final String UPDATE_QURY3="UPDATE CUSTOMERDETAIL SET PIN=? WHERE PIN=?";

    String status=null;;
    int pin=0;
    static int newPin=0;
  
public  String changePin(HttpServletRequest req) throws Exception
{
	try
	{
		Connection con=DBcon.getCon();
		PreparedStatement ps=con.prepareStatement(SELECT_QURY);
		con.setAutoCommit(false);
		Savepoint s1=con.setSavepoint();
		long mobile=Long.parseLong(req.getParameter("mno"));
		System.out.println(mobile);
		ps.setLong(1, mobile);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			System.out.println("in first rs");
			pin=rs.getInt(1);
			Random rand=new Random();
			 newPin=rand.nextInt(9000)+1000;
			System.out.println("After random");
			PreparedStatement ps1=con.prepareStatement(UPDATE_QURY1);
			System.out.println(newPin);
			ps1.setInt(1, newPin);
			ps1.setInt(2, pin);
			System.out.println("After setting value");
			int a=ps1.executeUpdate();
			System.out.println(a);
			if(a>0)
			{
				System.out.println("in A");
				PreparedStatement ps2=con.prepareStatement(UPDATE_QURY2);
				ps2.setInt(1, newPin);
				ps2.setInt(2, pin);
				int b=ps2.executeUpdate();
				if(b>0)
				{
					System.out.println("in B");
					PreparedStatement ps3=con.prepareStatement(UPDATE_QURY3);
					ps3.setInt(1, newPin);
					ps3.setInt(2, pin);
					int c=ps3.executeUpdate();
					if(c>0)
					{
						System.out.println("in c");
						status="PIN CHANGED..\n NEW PIN:"+newPin;
						con.commit();
				     }
					else
					{
						con.rollback();
						System.out.println("in rollback");
					}
					  
					  
			     }
					else
					{
						System.out.println("Third if fail");
						
					}
					
					
				}
			else
			{
				System.out.println("Second if fail");
			}
		
				
			}
		else
		{
			System.out.println("Main if fail");
		}
			
		
		
		
	}catch(Exception e) {e.printStackTrace();
	System.out.println("In exception");}
	return status;
}




}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import javax.servlet.ServletRequest;

import dbconnection.DBcon;

public class TransferDAO {
	private static final String CHECK_BALANCE="SELECT * FROM CUSTOMERBANK WHERE PIN=?";
	
	private static final String UPDATE_BALANCE1="UPDATE CUSTOMERBANK SET BALANCE=BALANCE-? WHERE PIN=?";

	private static final String UPDATE_BALANCE2="UPDATE CUSTOMERBANK SET BALANCE=BALANCE+? WHERE ACNO=?";
	
	
	
	
	 static String s="";
	 float balance=0.0f;
	  
     public String Deposit(ServletRequest req)
     {
    	 try
    	 {
    		 Connection con=DBcon.getCon();
    		 int pin=Integer.parseInt(req.getParameter("pin"));
    		 float amt=Float.parseFloat(req.getParameter("amt"));
    	     long accno=Long.parseLong(req.getParameter("acno"));
    		 con.setAutoCommit(false);
    		 Savepoint s1=con.setSavepoint();
    		 PreparedStatement ps=con.prepareStatement(CHECK_BALANCE);
    		 ps.setInt(1, pin );
    		 ResultSet rs=ps.executeQuery();
    		 if(rs.next())
    		 {
    			balance=rs.getFloat(5);
    			if(balance>=amt)
    			{
    				PreparedStatement ps1=con.prepareStatement(UPDATE_BALANCE1);
    				ps1.setFloat(1, amt);
    				ps1.setInt(2, pin);
    				int k=ps1.executeUpdate();
    				if(k>0)
    				{
    					PreparedStatement ps2=con.prepareStatement(UPDATE_BALANCE2);
    					ps2.setFloat(1, amt);
    					ps2.setLong(2, accno);
    					int l=ps2.executeUpdate();
    					if(l>0)
    					{
    						s="TRANSACTION SUCCEFULL...";
    						con.commit();
    					}
    					else
    					{
    						s="INVALID ACCOUNT NUMBER";
    						con.rollback(s1);
    					}
    					
    				}
    				else
    				{
    					con.rollback(s1);
    				}
    			}
    			else
    			{
    				s="INSUFFICIENT FUND..";
    			}
    		 }
    		 else
    		 {
    			 s="INAVLID PIN";
    		 }
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return s;
     }

}

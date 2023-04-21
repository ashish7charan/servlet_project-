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

public class DepositDAO {

	private static final String CHECK_BALANCE="SELECT * FROM CUSTOMERBANK WHERE PIN=?";

	private static final String UPDATE_BALANCE="UPDATE CUSTOMERBANK SET BALANCE=BALANCE+? WHERE PIN=?";
	
	private static final String INSERT_PASS="INSERT INTO PASSBOOK VALUES(PASS_SQNO.NEXTVAL,?,?,?,?,?,?)";
	
	 static String s="";
	  
     public String Deposit(HttpServletRequest req)
     {
    	 try
    	 {
    		 Connection con=DBcon.getCon();
    		 HttpSession hs=req.getSession(false);
 			String mobile=(String) hs.getAttribute("mno");
 			 long mno=Long.parseLong(mobile);
    		 int pin=Integer.parseInt(req.getParameter("dpin"));
    		 float balance=Float.parseFloat(req.getParameter("damt"));
    		 float rBalance=0.0f;
    		 PreparedStatement ps=con.prepareStatement(CHECK_BALANCE);
    		 ps.setInt(1, pin );
    		 ResultSet rs=ps.executeQuery();
    		 if(rs.next())
    		 {    
    			 rBalance=rs.getFloat(5);
    			 PreparedStatement ps1=con.prepareStatement(UPDATE_BALANCE);
 
    			 ps1.setFloat(1, balance);
    			 ps1.setInt(2, pin);
    			 ps1.execute();
    			 s="DEPOSIT SUCESSEFUL..";
    			 LocalDate date=LocalDate.now();
		            LocalTime time=LocalTime.now();
		            String ti=""+time;
					PreparedStatement ps2=con.prepareStatement(INSERT_PASS);
					ps2.setString(1, "CREDIT");
					ps2.setDate(2, Date.valueOf(date));
					ps2.setString(3, ti);
					ps2.setFloat(4, balance);
					ps2.setFloat(5 ,rBalance+balance);
					ps2.setLong(6, mno);
					int z=ps2.executeUpdate();
					System.out.println("passbook update..");
			
    		 }
    		 else
    		 {
    			 s=null;
    		 }
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return s;
     }
	
}

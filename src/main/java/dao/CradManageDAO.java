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

public class CradManageDAO {
	private final static String STATUS_CHECK="INSERT INTO  ATMBLOCK VALUES (?,?,?,?,?)"; 
	private final static String GET_PIN="SELECT PIN from CustomerDetail WHERE MOBILENO=?  "; 
	public int checkCardStatus(HttpServletRequest req)throws Exception
	{
		Connection con=DBcon.getCon();
		PreparedStatement ps=con.prepareStatement(STATUS_CHECK);
		HttpSession hs=req.getSession(false);
		System.out.println("In dao"+ hs.getAttribute("mno"));
		String mno=(String) hs.getAttribute("mno");
		Long mobile=Long.parseLong(mno);
		
		PreparedStatement ps1=con.prepareStatement(GET_PIN);
		ps1.setLong(1,mobile );
		
		ResultSet rs=ps1.executeQuery();
		int pin=0;
		if(rs.next())
		{
			System.out.println(rs.getLong(1));
			
	          pin=rs.getInt(1);
		}
	
	
  
		LocalDate bDate=LocalDate.now();
		LocalDate uDate=LocalDate.now().plusDays(1);
		
		LocalTime time1 = LocalTime.now();
		LocalTime time2 = LocalTime.now().plusHours(24);
		
		String s1=""+time1;
		String s2=""+time2;
		
		ps.setInt(1, pin);
		ps.setDate(2, Date.valueOf(bDate));
		ps.setDate(3, Date.valueOf(uDate));
		ps.setString(4, s1);
		ps.setString(5, s2);
		int k=ps.executeUpdate();
		System.out.println("card block..");
		return k;
	}
	
}

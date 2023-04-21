package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import dbconnection.DBcon;
public class CheckNo_DAO {
	private static final String SELECT_QURY="SELECT * FROM 	CUSTOMERDETAIL WHERE MOBILENO=?";
	    String status=null;;
	  
	public  String checkMno(HttpServletRequest req) throws Exception
	{
		try
		{
			Connection con=DBcon.getCon();
			PreparedStatement ps=con.prepareStatement(SELECT_QURY);
			ps.setLong(1, (Long.parseLong(req.getParameter("mno"))));
			System.out.println(Long.parseLong(req.getParameter("mno")));
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println("Inside rs");
				status="valid";
			}
		}catch(Exception e) {e.printStackTrace();}
		return status;
	}

}

package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import dbconnection.DBcon;
public class DAO_Balance {
	private static String VIEW_BALANCE="SELECT * FROM  CUSTOMERBANK WHERE PIN=?";
	    String balance;
	  
	public  String ViewBalance(HttpServletRequest req) throws Exception
	{
		try
		{
			Connection con=DBcon.getCon();
			PreparedStatement ps=con.prepareStatement(VIEW_BALANCE);
			ps.setInt(1, (Integer.parseInt(req.getParameter("pin"))));
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				
				balance="YOUR BALANCE IS:"+rs.getFloat(5);
			}
		}catch(Exception e) {throw new IllegalArgumentException();}
		return balance;
	}

}

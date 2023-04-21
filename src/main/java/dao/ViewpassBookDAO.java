package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.PassBook;
import dbconnection.DBcon;
public class ViewpassBookDAO {
	private static final String SELECT_QURY="SELECT * FROM PASSBOOK WHERE MOBILENO=?";
	   ArrayList<PassBook> al=new ArrayList<>();
	  
	public  ArrayList<PassBook> viewPass(HttpServletRequest req) throws Exception
	{
		try
		{
			Connection con=DBcon.getCon();
			HttpSession hs=req.getSession(false);
			String number=(String)hs.getAttribute("mno");
			long mobile=Long.parseLong(number);
			PreparedStatement ps=con.prepareStatement(SELECT_QURY);
			ps.setLong(1, mobile);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				PassBook pb=new PassBook();
				pb.setSno(rs.getInt(1));
				pb.settType(rs.getString(2));
				pb.settDate(rs.getDate(3));
				pb.settTime(rs.getString(4));
				pb.setAmount(rs.getInt(5));
				pb.setrBalance(rs.getFloat(6));
				al.add(pb);
				
			}
		}catch(Exception e) {e.printStackTrace();}
		return al;
	}

}

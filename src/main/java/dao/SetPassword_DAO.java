package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dbconnection.DBcon;
public class SetPassword_DAO {
	private static final String SELECT_QURY="UPDATE CUSTOMERDETAIL SET PASSWORD=? WHERE MOBILENO=?";
	    int k=0;
	  
	public  int setPassword(HttpServletRequest req) throws Exception
	{
		try
		{
			Connection con=DBcon.getCon();
			PreparedStatement ps=con.prepareStatement(SELECT_QURY);
		    HttpSession hs=req.getSession(false);
		    String mno=(String) hs.getAttribute("mno");
		    Long mobile=Long.parseLong(mno);
		    ps.setString(1,req.getParameter("pass"));
		    ps.setLong(2, mobile);
			 k=ps.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		return k;
	}

}

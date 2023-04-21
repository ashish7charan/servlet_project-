package dbconnection;
import java.sql.*;
public class DBcon {
	public static Connection con=null;
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
	  		  con=DriverManager.getConnection
	  				 ("jdbc:oracle:thin:@localhost:1521:orcl","system","abhi");
	
		}catch(Exception e) {e.printStackTrace();}
	}
	public static Connection getCon()
	{
		return con;
	}
	

}

package dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import javax.servlet.http.*;
import dbconnection.DBcon;

public class NewRegisterationDAO {
	public static Random r=new Random();
	private static final String DETAIL_CUST="INSERT INTO CUSTOMERDETAIL VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GEN_CUST ="INSERT INTO CUSTOMERGEN VALUES(?,?,?,?,?,?)";
	private static final String BANK_CUST ="INSERT INTO CUSTOMERBANK VALUES(?,?,?,?,?)";
	
	public static  String custReg(HttpServletRequest req)
	{
		String s=null;
		try
		{
			Connection con=DBcon.getCon();
			PreparedStatement ps=con.prepareStatement(DETAIL_CUST);
			PreparedStatement ps1=con.prepareStatement(GEN_CUST);
			PreparedStatement ps2=con.prepareStatement(BANK_CUST);
	        con.setAutoCommit(false);
	        Savepoint sp=con.setSavepoint();
	        int pin=r.nextInt(9000) + 1000;
			ps.setString(1, req.getParameter("name"));
			ps.setInt(2, Integer.parseInt(req.getParameter("age")));
			ps.setInt(3, Integer.parseInt(req.getParameter("adno")));
			ps.setString(4, (req.getParameter("pno")));
			ps.setString(5, req.getParameter("st"));
			ps.setString(6, req.getParameter("dt"));
			ps.setString(7, req.getParameter("vi"));
			ps.setString(8, req.getParameter("ms"));
			ps.setString(9, req.getParameter("atype"));
			ps.setInt(10, pin);
			ps.setLong(11, Long.parseLong(req.getParameter("mno")));
			ps.setString(12, req.getParameter("pass"));
			int k=ps.executeUpdate();
			if(k>0)
			{
			
				long a=r.nextLong(1000000000);
				int b=r.nextInt(100);
				String acno=a+""+b+"";
				long atm=r.nextLong(1000000000);
				LocalDate creation=LocalDate.now();
				LocalDate expiry=creation .plusYears(3);
				ps1.setString(1, req.getParameter("name"));
				ps1.setString(2,acno);
				ps1.setInt(3, pin);
				ps1.setLong(4,atm);
				ps1.setDate(5, Date.valueOf(expiry));
				ps1.setDate(6, Date.valueOf(creation));
				int m=ps1.executeUpdate();
				if(m>0)
				{
					ps2.setString(1, req.getParameter("name"));
					ps2.setString(2,acno);
					ps2.setInt(3, pin);
					ps2.setString(4,req.getParameter("atype"));
					ps2.setFloat(5,0.0f);
					int q=ps2.executeUpdate();
					if(q>0&&m>0&k>0)
					{
						s="done";
                        System.out.println("All ok");
                        req.setAttribute("acno", acno);
                       req.setAttribute("pin", pin);
                        req.setAttribute("atm", atm);
                       req.setAttribute("creation", creation);
                        req.setAttribute("expiry", expiry);
 
						con.commit();
					}else
					{
						System.out.println("last else");
						s=null;
						   con.rollback(sp);	
					}
						
					
					
				}
				else
				{
					s=null;
					   con.rollback(sp);	
					   System.out.println("Second last else");
				}
				
			} else
			{
				s=null;
				   con.rollback(sp);
				   System.out.println("third last ");
			} 
			
		}
		catch(Exception e)
		{
		   s=null;
		   System.out.println("Exception");
		}
		return s;
		
	}
}

package hk.dbtask;
import java.sql.*; //for database connecttion

public interface DbListener
{
 private  static Connection createConnection()
 {
	Connection con=null;
	 try
	 {
		Class.forName("com.mysql.cj.jdbc.Driver") ; //load the driver class
	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/housekeepingdb","root","root");
	 //con=DriverManager.getConnection("jdbc:mysql://localhost:3307/housekeepingdb","root","precursor");
	 }
	 catch(ClassNotFoundException|SQLException cse)
	 {
	     cse.printStackTrace();	 
	 }
	 return con;
 }
 public static Connection openConnection()
 {
	Connection con= createConnection();
	return con;
	
	
 }
 
 public static void  closeConnection(Connection con)
 {
	if(con!=null) 
	{
		try
		{
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
 }
 
}

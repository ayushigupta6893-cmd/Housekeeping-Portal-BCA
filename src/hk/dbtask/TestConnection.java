package hk.dbtask;
import java.sql.*;

public class TestConnection implements DbListener
{
public static void main(String[] args)
{
	Connection dbconnection=DbListener.openConnection();
	System.out.println(dbconnection);
}
}

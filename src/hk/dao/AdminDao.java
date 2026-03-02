package hk.dao;

import hk.dbtask.DbListener;
import hk.models.Admin;
import hk.models.ContactUs;

import hk.models.Users;
import java.sql.*;
import java.util.ArrayList;

public class AdminDao implements DbListener

{
		private Connection con;
		Admin admin;
		Users user=null;
		ContactUs cs;
		
		
public Admin getAdmin() {
			return admin;
		}





		public void setAdmin(Admin admin) {
			this.admin = admin;
		}





		public Users getUser() {
			return user;
		}





		public void setUser(Users user) {
			this.user = user;
		}





		public ContactUs getCs() {
			return cs;
		}





		public void setCs(ContactUs cs) {
			this.cs = cs;
		}

ArrayList<ContactUs> contactuslist=new ArrayList<ContactUs>();
		
		public ArrayList<ContactUs> viewcontactus()
		{
			con=DbListener.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(!contactuslist.isEmpty())
				contactuslist.clear();
			try
			{
			String strsql="select * from contactus"	;
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			
			
			
			while(rs.next())
			{
			
				String name=rs.getString("name");
				
				
				String yourtext=rs.getString("yourquery");
				Date date=rs.getDate("date");
				
				cs=new ContactUs( name,yourtext, date);
				contactuslist.add(cs);
				
			}
				
				
			}
			catch(SQLException se)
			{
				
				
			}
			finally
			{
			
				if(ps!=null)
					try
				{
						ps.close();
						
					}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				DbListener.closeConnection(con);
				}
			
			return contactuslist;
			
			
		}
		
		ArrayList<Users> userlist=new ArrayList<Users>();
		
		public ArrayList<Users> viewAllUsers()
		{
			con=DbListener.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			if(!userlist.isEmpty())
				userlist.clear();
			try
			{
			String strsql="select * from users"	;
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			
			
			
			while(rs.next())
			{
				String id=rs.getString("id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String city=rs.getString("city");
				String usertype=rs.getString("usertype");
				user=new Users(id, name, email, phone, city, usertype);
				userlist.add(user);
				
			}
				
				
			}
			catch(SQLException se)
			{
				
				
			}
			finally
			{
			
				if(ps!=null)
					try
				{
						ps.close();
						
					}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				DbListener.closeConnection(con);
				}
			
			return userlist;
			
			
		}
		
		
		
	
	
 	public Admin login(String id,String pass)
	{
		/* String status="invalid"; */
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
		
		String strsql="select * from admin where adminid=? and adminpass=?"; //?is known as placeholder
		ps=con.prepareStatement(strsql); //passes to Rdbms-> parser parse the query and compile the query->stored in buffer->reference is assigned to ps
		ps.setString(1, id);
		ps.setString(2, pass);
		System.out.println(ps);
		ps.executeQuery();
		rs=ps.executeQuery();
		if(rs.next()) //check whether data exist or not
		{
			
			String adminname=rs.getString("name");
			String adminphone=rs.getString("phone");
			String email=rs.getString("email");
			String address=rs.getString("address");
			
			admin=new Admin(adminname,adminphone,email,address);
		}
		
		
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
		
			if(ps!=null)
				try
			{
					ps.close();
					
				}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			DbListener.closeConnection(con);
			}
		
		return admin;
	}

}

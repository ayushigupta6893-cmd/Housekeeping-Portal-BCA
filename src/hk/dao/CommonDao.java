package hk.dao;

import java.sql.*;
import java.util.ArrayList;

import hk.dbtask.DbListener;
import hk.models.Users;
import hk.models.CommonDetails;


public class CommonDao implements DbListener {
	private Connection con;
	
	public ArrayList<CommonDetails>nearbylist=new ArrayList<CommonDetails>();

	public ArrayList<CommonDetails> nearBySearch(float currentlatitude,float currentlongitude)
	{
		
		
		
		
		PreparedStatement ps=null;
		ResultSet rs=null;

		//String status="invalid";
		con=DbListener.openConnection();
		
	try {
		

		//String strsql="select s.shopid,s.shopname,s.email,s.phone,s.ownername,s.address,u.picname,u.id from shopdetails s,users u where u.id=s.shopownid and u.usertype='sa'";
		
		/*
		 * System.out.println(currentlatitude); float lt=currentlatitude;
		 * System.out.println(currentlongitude);
		 */
		//String strsql="Select shopname,111.111*DEGREES(ACOS(LEAST(1.0, COS(RADIANS(?))* COS(RADIANS(Latitude))* COS(RADIANS(?- longitude))+SIN(RADIANS(?))* SIN(RADIANS(Latitude))))) AS distance_in_km  FROM shopdetails HAVING distance_in_km <5.0  ORDER BY distance_in_km DESC";
		
		String strsql="select s.companyownerid,s.comanyname,s.email,s.phone,s.address,s.emptype,u.picname,u.id,111.111*DEGREES(ACOS(LEAST(1.0, COS(RADIANS(?))* COS(RADIANS(latitude))* COS(RADIANS(?- longitude))+SIN(RADIANS(?))* SIN(RADIANS(latitude))))) AS distance_in_km  FROM cmpdetail s,users u where  u.id=s.companyownerid and u.usertype='company'  HAVING distance_in_km <20.0  ORDER BY distance_in_km DESC";
		
//		"select s.companyownerid,s.comanyname,s.email,s.phone,s.address,u.picname,u.id ,u.name from cmpdetail s,users u where u.id=s.companyownerid and u.usertype='company'";
		
		
		ps=con.prepareStatement(strsql);
		ps.setFloat(1, currentlatitude);
		ps.setFloat(2, currentlongitude);
		ps.setFloat(3,currentlatitude);
		System.out.println(ps);
		rs=ps.executeQuery();

		while(rs.next())//check whether data exists or not
		{
			
				String companyname=rs.getString("comanyname");
				System.out.println(companyname);
				
				
				
				
				  String email=rs.getString("email"); 
				  String name=rs.getString("name"); 
				  String address=rs.getString("address");
				  String picname=rs.getString("picname"); 
				  String phone=rs.getString("phone");
				  String id=rs.getString("id"); 
				  String companyownerid=rs.getString("companyownerid");
				  System.out.println(id);
				  System.out.println(picname);
				  System.out.println(email);
				 
				
				
				
				  CommonDetails cd=new CommonDetails(name, email, companyname, address,
				  picname, phone, id, companyownerid);
				  
				  nearbylist.add(cd);
				 

		
		}
	}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		




	finally {
		
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		DbListener.closeConnection(con);
	}

	return nearbylist;

	}//end near by methods



	 
	
	
	
	
	ArrayList<CommonDetails>commonlist=new ArrayList<CommonDetails>();

	public ArrayList<CommonDetails> searchDetails()
	{
		
		
	//select  s.shopname,s.email,s.phone,s.ownername,s.address,u.picname from shopdetails s,users u where u.id=s.shopownid and u.usertype='sa';
		
		
		PreparedStatement ps=null;
		ResultSet rs=null;

		//String status="invalid";
		con=DbListener.openConnection();
		
	try {
		

		String strsql="select s.companyownerid,s.comanyname,s.email,s.phone,s.address,u.picname,u.id ,u.name from cmpdetail s,users u where u.id=s.companyownerid and u.usertype='company'";
			
		ps=con.prepareStatement(strsql);
		
		rs=ps.executeQuery();

		while(rs.next())//check whether data exists or not
		{
			
				String companyownerid=rs.getString("companyownerid");
				String companyname=rs.getString("comanyname");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				String picname=rs.getString("picname");
				
				String userid=rs.getString("id");
				String username=rs.getString("name");
				
				
				CommonDetails cd=new CommonDetails(username, email, companyname, address, picname, phone, userid, companyownerid);
				commonlist.add(cd);


		
		}
	}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		




	finally {
		
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		DbListener.closeConnection(con);
	}

	return commonlist;
		
		}

		

	
	
	

	public Users login(String id, String pass) {

		/* String status="invalid"; */
		con = DbListener.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Users users = null;
		try {

			String strsql = "select * from users where id=? and password=? and status='true'"; // ?is known as
																								// placeholder
			ps = con.prepareStatement(strsql); // passes to Rdbms-> parser parse the query and compile the query->stored
												// in buffer->reference is assigned to ps
			ps.setString(1, id);
			ps.setString(2, pass);
			System.out.println(ps);
			ps.executeQuery();
			rs = ps.executeQuery();
			if (rs.next()) // check whether data exist or not
			{

				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");

				String city = rs.getString("city");
				String picname = rs.getString("picname");
				java.sql.Date date = rs.getDate("date");
				String usertype = rs.getString("usertype");
				users = new Users();
				users.setCity(city);
				users.setName(name);
				users.setDate(date);
				users.setPicname(picname);
				users.setEmail(email);
				users.setUsertype(usertype);
				users.setPhone(phone);
				users.setId(id);
				users.setPass(pass);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			if (ps != null)
				try {
					ps.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return users;
	}

}

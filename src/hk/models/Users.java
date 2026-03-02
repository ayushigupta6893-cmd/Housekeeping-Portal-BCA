package hk.models;

import java.sql.Date;

public class Users
{

	private String id,pass,name,email,phone,city,usertype,picname,status;
	private Date date;
	
	
	public Users(String id, String pass, String name, String email, String phone, String city, String usertype,
			String status, Date date) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.usertype = usertype;
		this.status = status;
		this.date = date;
	}


	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Users(String id, String name, String email, String phone, String city, String usertype) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.usertype = usertype;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getUsertype() 
	{
		return usertype;
	}
	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}
	public String getPicname()
	{
		return picname;
	}
	public void setPicname(String picname)
	{
		this.picname = picname;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public Date getDate() 
	{
		return date;
	}
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	
	
}

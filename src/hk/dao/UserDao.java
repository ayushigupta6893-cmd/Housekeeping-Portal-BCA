package hk.dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import hk.dbtask.DbListener;
import hk.models.Companydetail;
import hk.models.Complaint;
import hk.models.ContactUs;
import hk.models.Employee;
import hk.models.EmployeeAssignment;
import hk.models.EmployeeRequest;
import hk.models.Feedback;
import hk.models.Message;
import hk.models.Users;
public class UserDao implements DbListener
{
	Connection con;
	Users user;
	Message  message ;
	Complaint complaint ;
	Companydetail pt;
	
	
	public int createContactus(ContactUs cs)
	{
		
		int status=0;
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		
		try {
			String strinsert="insert into contactus(  name, email, phone, yourquery, date)values(?,?,?,?,?)";
			ps=con.prepareStatement(strinsert);
			
			ps.setString(1, cs.getName());
			
			ps.setString(2, cs.getEmail());
			ps.setString(3, cs.getPhone());
			ps.setString(4, cs.getYourquery());
			ps.setDate(5, cs.getDate());
			
			
			System.out.println(ps);
			
			status=ps.executeUpdate();
			
			
		}
		catch(SQLException se)
		{
			
			se.printStackTrace();
		}
		//finally block
		
		return status;
		
		
	}


	
	
	public int employeerequest(EmployeeRequest rp)
	{
		
		int status=0;
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		
		try {
			String strinsert="insert into employerequest( companyid, emptype, noofemployee, duration, requeststatus, answer, requestdate, answerdate,userid)values(?,?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(strinsert);
			ps.setInt(1, rp.getCompanyid());
			ps.setString(2, rp.getEmptype());
			ps.setInt(3, rp.getNoofemployee());
			ps.setString(4, rp.getDuration());
			ps.setString(5, "pending");
			ps.setString(6, rp.getAnswer());
			ps.setDate(7, rp.getRequestdate());
			ps.setDate(8, rp.getAnswerdate());
			ps.setString(9, rp.getUserid());
			
			System.out.println(ps);
			
			status=ps.executeUpdate();
			
			
		}
		catch(SQLException se)
		{
			
			se.printStackTrace();
		}
		//finally block
		
		return status;
		
		
	}



	
	ArrayList<Companydetail> typelist=new ArrayList<Companydetail>();
	public ArrayList<Companydetail> viewcompany()
	{
		
	con=DbListener.openConnection();
	PreparedStatement ps=null;
	ResultSet rs=null;
	if(!typelist.isEmpty())
	{
		typelist.clear();
	}

	try {
		String strsql="select * from cmpdetail";
		ps=con.prepareStatement(strsql);
		
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			
			
			int typeid=rs.getInt("companyid");
			String typename=rs.getString("comanyname");
			
			pt=new Companydetail(typename, typeid);
			typelist.add(pt);
		
			}
		
		
		
		
	}
	catch(SQLException se)	
	{
		se.printStackTrace();
	}
	finally
	{
		if(ps!=null)
			try {
				ps.close();
			} 
		catch (SQLException e) 
		{
				
				e.printStackTrace();
			}
		if(rs!=null)
		
			try 
			{
				rs.close();
			} catch (SQLException e)
			{
				
				e.printStackTrace();
			}
			DbListener.closeConnection(con);
		
	}
	return typelist;
	}//method closed
	
	public boolean checkUserId(String userid) 
	
	{
			boolean status=false;
			
			con=DbListener.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				
				String strsql="select * from users where id=?";
			
				ps=con.prepareStatement(strsql);
				ps.setString(1, userid);
				System.out.println(ps);
				
				rs=ps.executeQuery();
				if(rs.next())
				{
			//userid exists in the table

				status=true;
			

				}
	
			
			
	}
	catch(SQLException se)
	{
		se.printStackTrace();
		
		
	}
	
	finally 
	{
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DbListener.closeConnection(con);
		
	}
	
	
	
			return status;
	}
	
	
	public boolean uploadDocs(InputStream is,File f, String filename)
	{
		try {
			int size=is.available();//total number of bytes
			byte[]data=new byte[size];
			
			is.read(data);
			System.out.println(data.length);
//scott@123/doromon.png
			//FileOutputStream fos=new FileOutputStream(f+"//"+filename);
//			swasti@123\abc.jpg
			FileOutputStream fos=new FileOutputStream(f+File.separator+filename);
			
			fos.write(data);//image is geeting  written in the file with this code
			fos.close();
			System.out.println("pic uploaded ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

		
	}









public int updateProfilePic(String filename,String id)
	{
		int status=0;
		PreparedStatement ps=null;
		String strupdate="update users set picname=? where id=?";
		try {
			
			con=DbListener.openConnection();
			
	 ps=con.prepareStatement(strupdate);
			ps.setString(1, filename);
			ps.setString(2, id);
		int rowupdate=	ps.executeUpdate();
		if(rowupdate>0)
		
			 status=1;
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		finally {
			try {
			if(ps!=null)
				ps.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			DbListener.closeConnection(con);
		}
		return status;
		
	}
	
	


	
	
public Message viewMessage(String messageid)
	{
		con=DbListener.openConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		try
		{
			
			String strsql="select * from message where messageid=?";
			int msg_id=Integer.parseInt(messageid);
			ps=con.prepareStatement(strsql);
			ps.setInt(1, msg_id);
			System.out.println(ps);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int id= rs.getInt("messageid");  
				
				
				String senderid=rs.getString("senderid");
		
				
				  
				  
				
				String subject=rs.getString("subject");
				Date date=rs.getDate("date");
				
				  String rstatus=rs.getString("rstatus"); 
				  
				  String sstatus=rs.getString("sstatus"); 
				  
				  String     messagetext=rs.getString("messagetext");
				
				  message=new Message(senderid,subject,date);
					
				  message.setMessageid(id);
				  
					
					  message.setMessagetext(messagetext);
					  message.setRstatus(rstatus);
					  message.setRstatus(sstatus);
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
				
		return message;
	}
	
	
	
	
	ArrayList<Message> messagelist=new ArrayList<Message>();
	
	public ArrayList<Message> viewinbox(String recieveId)
	{
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!messagelist.isEmpty())
			messagelist.clear();
		try
		{
		String strsql="select * from message where recieveid=? and rstatus='true'";
		
		ps=con.prepareStatement(strsql);
		
		ps.setString(1,recieveId );
		
		
		
		
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
			int id= rs.getInt("messageid"); 
			
			
			String senderid=rs.getString("senderid");
			String subject=rs.getString("subject");
			Date date=rs.getDate("date");
			
			  String rstatus=rs.getString("rstatus"); 
			  
			  String sstatus=rs.getString("sstatus"); 
			  
			  String messagetext=rs.getString("messagetext");
			
			
		
			
		message=new Message(senderid,subject,date);
		
		  message.setMessageid(id);
		  
			
			  message.setMessagetext(messagetext);
			  message.setRstatus(rstatus);
			  message.setRstatus(sstatus);
			  
			 
		 
			messagelist.add(message);
			
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
		
		return messagelist;
		
	}	
	
	
	
	
	
	
	public int registration(Users user)
	{
		
		int status=0;
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		
		try
		{
			
			String strsql="insert into users(id, password, name, email, phone, city, usertype, date,  status) values(?,?,?,?,?,?,?,?,?)";
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, user.getId());
		    ps.setString(2, user.getPass());
		    ps.setString(3, user.getName());
		    ps.setString(4, user.getEmail());
		    ps.setString(5, user.getPhone());
		    ps.setString(6, user.getCity());
		    ps.setString(7, user.getUsertype());
		    ps.setDate(8, user.getDate());
		    ps.setString(9, user.getStatus());
		    
		    System.out.println(ps);
		   status= ps.executeUpdate(); //1
		    
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
			catch (SQLException e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		DbListener.closeConnection(con);
		
		}
		return status;
		
}

	public int PostFeedback(Feedback f)
	{
	
		int status=0;
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		try
		{
			
			String strsql="insert into feedback(senderid,receiveid, feedbacktext, rating, date, subject) values(?,?,?,?,?,?)";
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, f.getSenderid());
		    ps.setString(2, f.getReceiveid());
		    ps.setString(3, f.getFeedback());
		    ps.setInt(4, f.getRating());
		    ps.setDate(5, f.getDate());
		    ps.setString(6, f.getSubject());
		    
		    
		    
		    System.out.println(ps);
		   status= ps.executeUpdate(); //1
		    
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
			catch (SQLException e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return status ;
	
	}
	
	public int PostComplaint(Complaint c)
	{
	
		int status=0;
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		try
		{
			
			String strsql="insert into complaint( senderid, companyid, subject, complainttext, date, status) values(?,?,?,?,?,?)";
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, c.getSenderid());
		    ps.setString(2, c.getCompanyid());
		    ps.setString(3, c.getSubject());
		    ps.setString(4, c.getComplainttext());
		    ps.setDate(5, c.getDate());
		    ps.setString(6, c.getStatus());
		    
		    
		    
		    System.out.println(ps);
		   status= ps.executeUpdate(); //1
		    
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
			catch (SQLException e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return status ;
	
	}
	
	
	
	
	
	
	
	
	
	public int editProfile( String email, String phone, String city, String userid)
	{
		String strupdate="update users set email=?,phone=?,city=? where id=?";
	    
		int status=0;
		PreparedStatement ps =null;
		
		con=DbListener.openConnection();
		
		
		
		try
		{
			 ps=con.prepareStatement(strupdate);
			ps.setString(1, email);
			ps.setString(2, phone);
			ps.setString(3, city);
			ps.setString(4, userid);
			System.out.println(ps);
			ps.executeUpdate();
			
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
			catch (SQLException e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	     return status;
	}
	
	public int composemessage(Message m)
	{
		int status=0;
		
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		try
		{
			
			String strsql="insert into message( senderid, recieveid, subject, messagetext, date, rstatus, sstatus) values(?,?,?,?,?,?,?)";
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, m.getSenderid());
		    ps.setString(2, m.getRecieveid());
		    ps.setString(3, m.getSubject());
		    ps.setString(4, m.getMessagetext());
		    ps.setDate(5, m.getDate());
		    ps.setString(6, m.getRstatus());
		    ps.setString(7, m.getSstatus());
		    
		    
		    
	       
		    
		    
		    System.out.println(ps);
		   status= ps.executeUpdate(); //1
		    
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
			catch (SQLException e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return status;
	}


//view response
	
	EmployeeRequest employeerequest;
	Companydetail companydetail;
	EmployeeAssignment empAssignment;
	
	ArrayList<EmployeeRequest>responselist=new ArrayList<EmployeeRequest>();
	public ArrayList<EmployeeRequest> viewResponse(String userid)
	{
		
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!responselist.isEmpty())
			responselist.clear();
		try
		{
		String strsql="select er.answer,er.companyid,er.emptype,er.answerdate,ea.employeeid, cd.comanyname,cd.companyownerid from employerequest er ,cmpdetail cd,employee_assignment ea where er.userid=? and er.requeststatus='confirm' and er.requestid=ea.requestid and er.companyid=cd.companyid";
		
		ps=con.prepareStatement(strsql);
		
		ps.setString(1, userid);
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
		
			String answer=rs.getString("answer");
			
			int companyid=rs.getInt("companyid");
		
			String emptype=rs.getString("emptype");
			String employeeid=rs.getString("employeeid");
			String companyname=rs.getString("comanyname");
//			System.out.println(emptype+duration+companyname+userid);
			
			Date date=rs.getDate("answerdate");
		companydetail=new Companydetail();
		companydetail.setComanyname(companyname);
		companydetail.setCompanyownerid(rs.getString("companyownerid"));
		employeerequest=new EmployeeRequest();
		employeerequest.setAnswer(answer);
	
		employeerequest.setUserid(userid);
		employeerequest.setEmptype(emptype);
		employeerequest.setAnswerdate(date);
		employeerequest.setCompanyid(companyid);
		
		empAssignment=new EmployeeAssignment();
		empAssignment.setEmployeeid(employeeid);
		employeerequest.setEmployeeAssignment(empAssignment);
		employeerequest.setCompanydetail(companydetail);
		responselist.add(employeerequest);
						
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
		
		return responselist;
		
		
		
	}
	Employee emp;

public Users getUser() {
		return user;
	}




	public void setUser(Users user) {
		this.user = user;
	}




	public Message getMessage() {
		return message;
	}




	public void setMessage(Message message) {
		this.message = message;
	}




	public Complaint getComplaint() {
		return complaint;
	}




	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}




	public Companydetail getPt() {
		return pt;
	}




	public void setPt(Companydetail pt) {
		this.pt = pt;
	}




	public Companydetail getCompanydetail() {
		return companydetail;
	}




	public void setCompanydetail(Companydetail companydetail) {
		this.companydetail = companydetail;
	}




	public EmployeeAssignment getEmpAssignment() {
		return empAssignment;
	}




	public void setEmpAssignment(EmployeeAssignment empAssignment) {
		this.empAssignment = empAssignment;
	}




	public Employee getEmp() {
		return emp;
	}




	public void setEmp(Employee emp) {
		this.emp = emp;
	}




public Employee viewEmployee(String empid)
{
	
	con=DbListener.openConnection();
	PreparedStatement ps = null;
	ResultSet rs=null;
	try
	{
		
		String strsql="select * from employee where employeeid=?";
		
		ps=con.prepareStatement(strsql);
		ps.setString(1, empid);
		System.out.println(ps);
		rs=ps.executeQuery();
		if(rs.next())
		{
			
			
			
			String name=rs.getString("name");
	
			
			  
			  
			
			String email=rs.getString("email");
			
			
			  String phone=rs.getString("phone"); 
			  
			  String experience=rs.getString("experience"); 
			  
			  	emp=new Employee();
			  	emp.setName(name);
			  	emp.setExperience(experience);
			  	emp.setPhone(phone);
			  	emp.setEmail(email);
			  
				


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
			
	return emp;


}

	
	
	}
	
	
 


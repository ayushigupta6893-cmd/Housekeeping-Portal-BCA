package hk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hk.dbtask.DbListener;
import hk.models.Companydetail;
import hk.models.Complaint;
import hk.models.Employee;
import hk.models.EmployeeAssignment;
import hk.models.EmployeeRequest;
import hk.models.Feedback;
import hk.models.Message;
import hk.models.PostJob;
import hk.models.Users;

public class CompanyDao implements DbListener
{
	 private Connection con;
	
	public Companydetail getCd() {
		return cd;
	}








	public void setCd(Companydetail cd) {
		this.cd = cd;
	}


	/*
	 * Feedback feedback;
	 */
	 Users user;
	Feedback  feedback ;
	Complaint complaint;
	PostJob job; 
	
ArrayList<PostJob> joblist=new ArrayList<PostJob>();
	
	public ArrayList<PostJob> viewJobs( )
	{
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!joblist.isEmpty())
			joblist.clear();
		try
		{
		String strsql="select * from job  ";
		
		ps=con.prepareStatement(strsql);
		
		
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
			
			String jobtitle=rs.getString("jobtitle");
			
			
			int noofpost=rs.getInt("noofpost");
			String email=rs.getString("email");
			String agelimit=rs.getString("agelimit");
			String eligibility=rs.getString("eligibility");
			String companyid=rs.getString("companyid");
			
			job=new PostJob(noofpost, companyid, jobtitle, email, agelimit, eligibility);
			
			
			
	
			
			
	joblist.add(job);
			
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
		
		return joblist;
		
	}	
	
	
	
	
	
	
	
	
	public int postJob( PostJob job )
	{
		int status=0;
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		
		try 
		{
			String strsql="insert into job(  companyid, jobtitle, noofpost, email, agelimit, eligibility, description, date) values(?,?,?,?,?,?,?,?)";
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, job.getCompanyid());
		    ps.setString(2, job.getJobtitle());
		    ps.setInt(3, job.getNoofpost());
		    ps.setString(4, job.getEmail());
		    ps.setString(5, job.getAgelimit());
		    ps.setString(6, job.getEligibility());
		    ps.setString(7, job.getDescription());
		    ps.setDate(8, job.getDate());
		   
		   
		    
		   
		    System.out.println(ps);
			  status= ps.executeUpdate(); 
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		
		
		
		return status;
	}
	
	
	
	
	public int resolveComplaint(String[] complaintids) 
	{
		int status=1;
		con=DbListener.openConnection();
		
		PreparedStatement ps=null;
		
		try {
			
			//con.setAutoCommit(false);//from java connection you table will not be updated
			String strdelete="update from complaint where status=?";
			
		ps=con.prepareStatement(strdelete);
		for(int i=0;i<complaintids.length;i++)
		{
			

			 ps.setString(1,"Resolved"); 
			 ps.setString(2,complaintids[i]);
			 ps.addBatch();//all the data
			
			 		}
	//batch processing  is successfull when all records are deleted/updated/inserted
		
		int []arr=ps.executeBatch();//it will carry all the data to the RDBMS
		int flag=0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]<0)
			{
				flag=1;
				status=0;
				break;
			}
		}
		
		if(flag==0)
			//con.commit();//for permanent change
			return status;
		}
		catch(SQLException se)
		{
			try {
				con.rollback();//10 record -8 record->rollback->restore
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			se.printStackTrace();
		}
		
		
		
		finally {
			
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			DbListener.closeConnection(con);
		}
		
		
		
	return status;
	}
	
	
	public int addcompanyDetails(Companydetail rp)
	{
		
		int status=0;
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		
		try {
			String strinsert="insert into cmpdetail(companyownerid, comanyname, email, address, phone, founder, landmark, latitude, longitude, registrationnumber, companydetail, emptype)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(strinsert);
			ps.setString(1, rp.getCompanyownerid());
			
			ps.setString(2, rp.getComanyname());
			ps.setString(3, rp.getEmail());
			ps.setString(4, rp.getAddress());
			ps.setString(5, rp.getPhone());
			ps.setString(6, rp.getFounder());
			ps.setString(7, rp.getLandmark());
			ps.setDouble(8, rp.getLatitude());
			ps.setDouble(9, rp.getLongitude());
			ps.setString(10, rp.getRegistrationnumber());
			ps.setString(11, rp.getCompanydetail());
			ps.setString(12, rp.getEmptype());
			
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



	public int deleteFeedBack(String[] feedbackids) 
	{
		int status=1;
		con=DbListener.openConnection();
		
		PreparedStatement ps=null;
		
		try {
			
			//con.setAutoCommit(false);//from java connection your table will not be updated
			String strdelete="delete from feedback where id=?";
			
		ps=con.prepareStatement(strdelete);
		for(int i=0;i<feedbackids.length;i++)
		{
			

			 ps.setInt(1, Integer.parseInt(feedbackids[i])); 
			 ps.addBatch();//all the data
			
			 		}
	//batch processing  is successfull when all records are deleted/updated/inserted
		
		int []arr=ps.executeBatch();//it will carry all the data to the RDBMS
		int flag=0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]<0)
			{
				flag=1;
				status=0;
				break;
			}
		}
		
		if(flag==0)
			//con.commit();//for permanent change
			return status;
		}
		catch(SQLException se)
		{
			try {
				con.rollback();//10 record -8 record->rollback->restore
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			se.printStackTrace();
		}
		
		
		
		finally {
			
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			DbListener.closeConnection(con);
		}
		
		
		
	return status;
	}
	

ArrayList<Complaint> complaintlist=new ArrayList<Complaint>();
	
	public ArrayList<Complaint> viewcomplaint( )
	{
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!complaintlist.isEmpty())
			complaintlist.clear();
		try
		{
		String strsql="select * from complaint where status='resolved' ";
		
		ps=con.prepareStatement(strsql);
		
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
			
			String senderid=rs.getString("senderid");
			
			
		
			String subject=rs.getString("subject");
			Date date=rs.getDate("date");
			String complaintext=rs.getString("complainttext");
	
			
			complaint =new Complaint();
			complaint.setSenderid(senderid);
			complaint.setSubject(subject);
			complaint.setDate(date);
			complaint.setComplainttext(complaintext);
			
			
			
	
			
			
	complaintlist.add(complaint);
			
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
		
		return complaintlist;
		
	}	
	
	ArrayList<Feedback> feedbacklist=new ArrayList<Feedback>();
	
	public ArrayList<Feedback> viewfeedback( String recieveId)
	{
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!feedbacklist.isEmpty())
			feedbacklist.clear();
		try
		{
		String strsql="select * from feedback where receiveId=? ";
		
		ps=con.prepareStatement(strsql);
		
		ps.setString(1, recieveId);
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
			int id= rs.getInt("id");
			String senderid=rs.getString("senderid");
			
			
			String receiveid=rs.getString("receiveid");
			String subject=rs.getString("subject");
			Date date=rs.getDate("date");
			String feedbacktext=rs.getString("feedbacktext");
			int rating=rs.getInt("rating");
			
			feedback=new Feedback(senderid,subject,date);
			feedback.setId(id);
			feedback.setReceiveid(receiveid);
			feedback.setFeedback(feedbacktext);
			feedback.setRating(rating);
			
			
	
			
			
	feedbacklist.add(feedback);
			
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
		
		return feedbacklist;
		
	}	
	
	
	
	
	
	
	
	
	public int registration(Users user)
	{
		
		int status=0;
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		
		try
		{
			
			String strsql="insert into users(id, password, name, email, phone, city, usertype, date, status) values(?,?,?,?,?,?,?,?,?)";
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, user.getId());
		    ps.setString(2, user.getPass());
		    ps.setString(3, user.getName());
		    ps.setString(4, user.getEmail());
		    ps.setString(5, user.getPhone());
		    ps.setString(6, user.getCity());
		    ps.setString(7, user.getUsertype());
		    ps.setDate(8,   user.getDate());
		    ps.setString(9, user.getStatus());
			/*
			 * ps.setString(10, user.getPicname());
			 */
		    
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
				try {
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
	
	/*employee adding*/
	public int addemployee(Employee employee )
	{
		int status=0;
		PreparedStatement ps=null;
		con=DbListener.openConnection();
		
		try 
		{
			//String strsql="insert into employee( companyId, employeeid, name, email, phone, gender, experience, address, type,status) values(?,?,?,?,?,?,?,?,?,?)";
			String strsql="insert into employee(employeeid, name, email, phone, gender, experience, address, type, status, companyownerid, companyid)values(?,?,?,?,?,?,?,?,?,?,?)";
			
		    ps=con.prepareStatement(strsql);
		    ps.setString(1, employee.getEmployeeid());
		   // ps.setString(2, employee.getEmployeeid());
		    ps.setString(2, employee.getName());
		    ps.setString(3, employee.getEmail());
		    ps.setString(4, employee.getPhone());
		    ps.setString(5, employee.getGender());
		    ps.setString(6, employee.getExperience());
		    ps.setString(7, employee.getAddress());
		    ps.setString(8, employee.getType());
		    ps.setString(9,employee.getStatus());
		    ps.setString(10,employee.getCompanyid());
		    ps.setInt(11,employee.getCid());
		    
		   
		    System.out.println(ps);
			  status= ps.executeUpdate(); 
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
	//new methods
	
	EmployeeRequest employeerequest;
	Companydetail companydetail;
	
	ArrayList<EmployeeRequest>requestlist=new ArrayList<EmployeeRequest>();
	public ArrayList<EmployeeRequest> viewRequest(String companyownerid)
	{
		
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!requestlist.isEmpty())
			requestlist.clear();
		try
		{
		String strsql="select er.requestid,er.companyid,er.requestdate,er.userid,er.emptype,er.noofemployee,er.duration,cd.comanyname from employerequest er ,cmpdetail cd where er.companyid=cd.companyid and cd.companyownerid=? and er.requeststatus='pending'";
		
		ps=con.prepareStatement(strsql);
		
		ps.setString(1, companyownerid);
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
			int no_of_employee= rs.getInt("noofemployee");

			int requestid= rs.getInt("requestid");
			String userid=rs.getString("userid");
			
			int companyid=rs.getInt("companyid");
		
			String emptype=rs.getString("emptype");
			String duration=rs.getString("duration");
			String companyname=rs.getString("comanyname");
			System.out.println(emptype+duration+companyname+userid);
			
			Date date=rs.getDate("requestdate");
		companydetail=new Companydetail();
		companydetail.setComanyname(companyname);
		employeerequest=new EmployeeRequest();
		employeerequest.setRequestid(requestid);
		employeerequest.setNoofemployee(no_of_employee);
		employeerequest.setEmptype(emptype);
		employeerequest.setUserid(userid);
		employeerequest.setDuration(duration);
		employeerequest.setRequestdate(date);
		employeerequest.setCompanyid(companyid);
		employeerequest.setCompanydetail(companydetail);
		
		requestlist.add(employeerequest);
						
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
		
		return requestlist;
		
		
		
	}
	
	
	
	//view employee companywise
	Employee emp;
	public Users getUser() {
		return user;
	}








	public void setUser(Users user) {
		this.user = user;
	}








	public Feedback getFeedback() {
		return feedback;
	}








	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}








	public Complaint getComplaint() {
		return complaint;
	}








	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}








	public PostJob getJob() {
		return job;
	}








	public void setJob(PostJob job) {
		this.job = job;
	}








	public Companydetail getCompanydetail() {
		return companydetail;
	}








	public void setCompanydetail(Companydetail companydetail) {
		this.companydetail = companydetail;
	}








	public Employee getEmp() {
		return emp;
	}








	public void setEmp(Employee emp) {
		this.emp = emp;
	}


	ArrayList<Employee> employeelist=new ArrayList<Employee>();
	
	public ArrayList<Employee> viewEmployee(String companyownerid,int cid)
	{
		
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!employeelist.isEmpty())
			employeelist.clear();
		try
		{
		String strsql="select *  from employee where companyid=? and companyownerid=?";
		ps=con.prepareStatement(strsql);
		
		ps.setInt(1, cid);
		ps.setString(2,companyownerid);
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
		
			String employeeid=rs.getString("employeeid");
			
			
		
			String emptype=rs.getString("type");
			String name=rs.getString("name");
			String phone=rs.getString("phone");
			//String companyname=rs.getString("comanyname");
			String experience=rs.getString("experience");
		
			
			
			/*
			 * companydetail=new Companydetail(); companydetail.setComanyname(companyname);
			 */
		emp=new Employee();
		emp.setName(name);
		emp.setPhone(phone);
		emp.setType(emptype);
		emp.setExperience(experience);
		emp.setEmployeeid(employeeid);
		//emp.setCompanydetail(companydetail);
		employeelist.add(emp);
						
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
		
		return employeelist;
		
		
		
	}
	
	
	
	
ArrayList<Companydetail> companylist=new ArrayList<Companydetail>();
	Companydetail cd;	
	public ArrayList<Companydetail> viewCompany( String ownerid)
	{
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!companylist.isEmpty())
			companylist.clear();
		try
		{
		String strsql="select * from cmpdetail where companyownerid=?";
		
		ps=con.prepareStatement(strsql);
		ps.setString(1,ownerid );
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
			
			int companyid=rs.getInt("companyid");
			
			
		
			String companyname=rs.getString("comanyname");
						
			cd=new Companydetail();
			cd.setCompanyid(companyid);
			cd.setComanyname(companyname);
			
	
			
			
			companylist.add(cd);
			
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
		
		return companylist;
		
	}	
	
	
	
	//find employee
	
	public ArrayList<Employee> findEmployee(String companyownerid,String type,int cid)
	{
		
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!employeelist.isEmpty())
			employeelist.clear();
		try
		{
		String strsql="select *  from employee where companyid=? and companyownerid=? and type=? and status='free'";
		ps=con.prepareStatement(strsql);
		
		ps.setInt(1, cid);
		ps.setString(2,companyownerid);
		ps.setString(3, type);
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
		
			String employeeid=rs.getString("employeeid");
			
			
		
			String emptype=rs.getString("type");
			String name=rs.getString("name");
			String phone=rs.getString("phone");
			//String companyname=rs.getString("comanyname");
			String experience=rs.getString("experience");
			
			
			
			/*
			 * companydetail=new Companydetail(); companydetail.setComanyname(companyname);
			 */
		emp=new Employee();
		emp.setName(name);
		emp.setPhone(phone);
		emp.setType(emptype);
		emp.setExperience(experience);
		emp.setEmployeeid(employeeid);
		emp.setCid(cid);
		
		//emp.setCompanydetail(companydetail);
		employeelist.add(emp);
						
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
		
		return employeelist;
		
		
		
	}








	public int assignEmployee(String[] empdata, String requestid, String answer,Date dt)
	{

		int reqid=Integer.parseInt(requestid);
		
	
		
		con=DbListener.openConnection();
		
		
		PreparedStatement psemployee,psassign,psrequest=null;
		
		try {
			String strupdate="update employee set status=? where employeeid=?";
			String strupdate_request="update employerequest set answer=?,answerdate=?,requeststatus=? where requestid=?";
			String strinsert="insert into employee_assignment(requestid, employeeid, date)values(?,?,?)";
			psemployee=con.prepareStatement(strupdate);
			psrequest=con.prepareStatement(strupdate_request);
			psassign=con.prepareStatement(strinsert);
		for(int i=0;i<empdata.length;i++)
		{
			
			psemployee.setString(1, "busy");
			psemployee.setString(2 ,empdata[i]);
			psemployee.addBatch();
			
			psassign.setInt(1,reqid);
			psassign.setString(2, empdata[i]);
			psassign.setDate(3, dt);
			psassign.addBatch();
			psrequest.setString(1, answer);
			psrequest.setDate(2, dt);
			psrequest.setString(3, "confirm");
			psrequest.setInt(4, reqid);
			psrequest.addBatch();
			System.out.println(psrequest);
			System.out.println(psassign);
			System.out.println(psemployee);
			
			
		}
			
		psassign.executeBatch();
		psrequest.executeBatch();
		psemployee.executeBatch();
			
			
		}
		
		
		catch(SQLException se)
		{
			
			se.printStackTrace();
		}
		
		
		return 1;
	}
	
	//view busy employee
	
	
	public ArrayList<Employee> viewBusyEmployee(String companyownerid,int cid)
	{
		
		con=DbListener.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		if(!employeelist.isEmpty())
			employeelist.clear();
		try
		{
		String strsql="select *  from employee where companyid=? and companyownerid=? and status='busy'";
		ps=con.prepareStatement(strsql);
		
		ps.setInt(1, cid);
		ps.setString(2,companyownerid);
	
		System.out.println(ps);
		
		rs=ps.executeQuery();
		
		
		
		while(rs.next())
		{
			
		
			String employeeid=rs.getString("employeeid");
			
			
		
			String emptype=rs.getString("type");
			String name=rs.getString("name");
			String phone=rs.getString("phone");
			//String companyname=rs.getString("comanyname");
			String experience=rs.getString("experience");
		
			
			
			/*
			 * companydetail=new Companydetail(); companydetail.setComanyname(companyname);
			 */
		emp=new Employee();
		emp.setName(name);
		emp.setPhone(phone);
		emp.setType(emptype);
		emp.setExperience(experience);
		emp.setEmployeeid(employeeid);
		//emp.setCompanydetail(companydetail);
		employeelist.add(emp);
						
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
		
		return employeelist;
		
		
		
	}







	public int changeEmployeeStatus(String[] empids)
	{
		int status=1;
		con=DbListener.openConnection();
		
		PreparedStatement ps=null;
		
		try {
			
			//con.setAutoCommit(false);//from java connection your table will not be updated
			String strdelete="update employee set status=?  where employeeid=?";
			
		ps=con.prepareStatement(strdelete);
		for(int i=0;i<empids.length;i++)
		{
			
			ps.setString(1, "free");
			 ps.setString(2, empids[i]); 
			 ps.addBatch();//all the data
			
			 		}
	//batch processing  is successfull when all records are deleted/updated/inserted
		
		int []arr=ps.executeBatch();//it will carry all the data to the RDBMS
		int flag=0;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]<0)
			{
				flag=1;
				status=0;
				break;
			}
		}
		
		if(flag==0)
			//con.commit();//for permanent change
			return status;
		}
		catch(SQLException se)
		{
			try {
				con.rollback();//10 record -8 record->rollback->restore
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			se.printStackTrace();
		}
		
		
		
		finally {
			
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			DbListener.closeConnection(con);
		}
		
		
		
	return status;
	}
	


}

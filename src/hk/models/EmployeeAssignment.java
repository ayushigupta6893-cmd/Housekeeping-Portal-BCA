package hk.models;

import java.sql.Date;

//assignid, requestid, employeeid, date
public class EmployeeAssignment {
	
	private int assignid,requestid;
	private String employeeid;
	private Date date;
	public int getAssignid() {
		return assignid;
	}
	public void setAssignid(int assignid) {
		this.assignid = assignid;
	}
	public int getRequestid() {
		return requestid;
	}
	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public EmployeeAssignment(int requestid, String employeeid, Date date) {
		super();
		this.requestid = requestid;
		this.employeeid = employeeid;
		this.date = date;
	}
	public EmployeeAssignment(int assignid, int requestid, String employeeid, Date date) {
		super();
		this.assignid = assignid;
		this.requestid = requestid;
		this.employeeid = employeeid;
		this.date = date;
	}
	public EmployeeAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}

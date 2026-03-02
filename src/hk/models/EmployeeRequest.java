package hk.models;

import java.sql.Date;



public class EmployeeRequest
{
	/*
	 * requestid, companyid, emptype, no of employee, duration, requeststatus,
	 * answer, requestdate, answerdate
	 */
	private int requestid, companyid,noofemployee;
	private String 	emptype,duration, requeststatus,answer;
	private Date requestdate, answerdate;
	private String userid;
	private Companydetail companydetail;
private EmployeeAssignment employeeAssignment;
	public EmployeeAssignment getEmployeeAssignment() {
	return employeeAssignment;
}
public void setEmployeeAssignment(EmployeeAssignment employeeAssignment) {
	this.employeeAssignment = employeeAssignment;
}
	public Companydetail getCompanydetail() {
		return companydetail;
	}
	public void setCompanydetail(Companydetail companydetail) {
		this.companydetail = companydetail;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getRequestid() {
		return requestid;
	}
	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public int getNoofemployee() {
		return noofemployee;
	}
	public void setNoofemployee(int noofemployee) {
		this.noofemployee = noofemployee;
	}
	public String getEmptype() {
		return emptype;
	}
	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getRequeststatus() {
		return requeststatus;
	}
	public void setRequeststatus(String requeststatus) {
		this.requeststatus = requeststatus;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}
	public Date getAnswerdate() {
		return answerdate;
	}
	public void setAnswerdate(Date answerdate) {
		this.answerdate = answerdate;
	}
	public EmployeeRequest(int requestid, int companyid, int noofemployee, String emptype, String duration,
			String requeststatus, String answer, Date requestdate, Date answerdate) {
		super();
		this.requestid = requestid;
		this.companyid = companyid;
		this.noofemployee = noofemployee;
		this.emptype = emptype;
		this.duration = duration;
		this.requeststatus = requeststatus;
		this.answer = answer;
		this.requestdate = requestdate;
		this.answerdate = answerdate;
	}
	public EmployeeRequest(int companyid, int noofemployee, String emptype, String duration, String requeststatus,
			String answer, Date requestdate, Date answerdate) {
		super();
		this.companyid = companyid;
		this.noofemployee = noofemployee;
		this.emptype = emptype;
		this.duration = duration;
		this.requeststatus = requeststatus;
		this.answer = answer;
		this.requestdate = requestdate;
		this.answerdate = answerdate;
	}
	
	public EmployeeRequest(int companyid, int noofemployee, String emptype, String duration) {
		super();
		this.companyid = companyid;
		this.noofemployee = noofemployee;
		this.emptype = emptype;
		this.duration = duration;
		
	}
	
	
	public EmployeeRequest(int companyid, int noofemployee, String emptype, String duration, String requeststatus,
			Date requestdate) {
		super();
		this.companyid = companyid;
		this.noofemployee = noofemployee;
		this.emptype = emptype;
		this.duration = duration;
		this.requeststatus = requeststatus;
		this.requestdate = requestdate;
	}
	public EmployeeRequest(int noofemployee, String emptype, String duration) {
		super();
		this.noofemployee = noofemployee;
		this.emptype = emptype;
		this.duration = duration;
	}
	
	public EmployeeRequest(int companyid, int noofemployee, String emptype, String duration, Date requestdate) {
		super();
		this.companyid = companyid;
		this.noofemployee = noofemployee;
		this.emptype = emptype;
		this.duration = duration;
		this.requestdate = requestdate;
	}
	public EmployeeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

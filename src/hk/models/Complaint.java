package hk.models;

import java.sql.Date;

import hk.dbtask.DbListener;

public class Complaint  implements DbListener
{

	 private String senderid,companyid,subject, complainttext,status;
	private int complaintid ;
	private Date  date ;
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getComplainttext() {
		return complainttext;
	}
	public void setComplainttext(String complainttext) {
		this.complainttext = complainttext;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public int getComplaintid() {
		return complaintid;
	}
	public void setComplaintid(int complaintid) {
		this.complaintid = complaintid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	public Complaint(String senderid, String companyid, String subject, String complainttext, String status,
			Date date) {
		super();
		this.senderid = senderid;
		this.companyid = companyid;
		this.subject = subject;
		this.complainttext = complainttext;
		this.status = status;
		this.date = date;
	}
	public Complaint(String subject, String complainttext, String companyid) {
		super();
		this.subject = subject;
		this.complainttext = complainttext;
		this.companyid = companyid;
	}
	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	
	
}

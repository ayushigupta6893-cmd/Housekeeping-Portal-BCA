package hk.models;

import java.sql.Date;

public class Feedback 
{

	private String senderid ,receiveid, subject ;
	private int rating ,id;
	private Date date;
	private String feedback;
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Feedback(String senderid, String receiveid, String subject, int rating, Date date, String feedback) {
		super();
		this.senderid = senderid;
		this.receiveid = receiveid;
		this.subject = subject;
		this.rating = rating;
		this.date = date;
		this.feedback = feedback;
	}

	public Feedback(String senderid, String subject, Date date) {
		super();
		this.senderid = senderid;
		this.subject = subject;
		this.date = date;
	}
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
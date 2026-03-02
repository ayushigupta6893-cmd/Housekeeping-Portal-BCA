package hk.models;

import java.sql.Date;

public class Message
{
 private String senderid,recieveid,subject,messagetext,rstatus,sstatus;
 private Date date;
 private int messageid;

public String getSenderid() {
	return senderid;
}

public void setSenderid(String senderid) {
	this.senderid = senderid;
}

public String getRecieveid() {
	return recieveid;
}

public void setRecieveid(String recieveid) {
	this.recieveid = recieveid;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getMessagetext() {
	return messagetext;
}

public void setMessagetext(String messagetext) {
	this.messagetext = messagetext;
}

public String getRstatus() {
	return rstatus;
}

public void setRstatus(String rstatus) {
	this.rstatus = rstatus;
}

public String getSstatus() {
	return sstatus;
}

public void setSstatus(String sstatus) {
	this.sstatus = sstatus;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public int getMessageid() {
	return messageid;
}

public void setMessageid(int messageid) {
	this.messageid = messageid;
}

public Message(String senderid, String recieveid, String subject, String messagetext, String rstatus, String sstatus,
		Date date) {
	super();
	this.senderid = senderid;
	this.recieveid = recieveid;
	this.subject = subject;
	this.messagetext = messagetext;
	this.rstatus = rstatus;
	this.sstatus = sstatus;
	this.date = date;
}


public Message(String recieveid, String subject, String rstatus, Date date) {
	super();
	this.recieveid = recieveid;
	this.subject = subject;
	this.rstatus = rstatus;
	this.date = date;
}



public Message(String senderid, String subject, Date date) {
	super();
	this.senderid = senderid;
	this.subject = subject;
	this.date = date;
}

public Message() {
	super();
	// TODO Auto-generated constructor stub
}


}

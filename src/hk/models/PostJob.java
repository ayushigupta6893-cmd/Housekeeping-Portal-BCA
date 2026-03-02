package hk.models;

import java.sql.Date;

public class PostJob
{
	/*
	 * jobid, companyid, jobtitle, noofpost, email, agelimit, eligibility,
	 * description, date
	 */

 private int  jobid , noofpost;

 private String companyid, jobtitle,  email, agelimit, eligibility,
  description; 
 
 Date date;

public int getJobid() {
	return jobid;
}

public void setJobid(int jobid) {
	this.jobid = jobid;
}

public int getNoofpost() {
	return noofpost;
}

public void setNoofpost(int noofpost) {
	this.noofpost = noofpost;
}

public String getCompanyid() {
	return companyid;
}

public void setCompanyid(String companyid) {
	this.companyid = companyid;
}

public String getJobtitle() {
	return jobtitle;
}

public void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAgelimit() {
	return agelimit;
}

public void setAgelimit(String agelimit) {
	this.agelimit = agelimit;
}

public String getEligibility() {
	return eligibility;
}

public void setEligibility(String eligibility) {
	this.eligibility = eligibility;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public PostJob(int noofpost, String companyid, String jobtitle, String email, String agelimit, String eligibility,
		String description, Date date) {
	super();
	this.noofpost = noofpost;
	this.companyid = companyid;
	this.jobtitle = jobtitle;
	this.email = email;
	this.agelimit = agelimit;
	this.eligibility = eligibility;
	this.description = description;
	this.date = date;
}

public PostJob(int noofpost, String jobtitle, String email, String agelimit, String eligibility, String description,
		Date date) {
	super();
	this.noofpost = noofpost;
	this.jobtitle = jobtitle;
	this.email = email;
	this.agelimit = agelimit;
	this.eligibility = eligibility;
	this.description = description;
	this.date = date;
}

public PostJob(int noofpost, String companyid, String jobtitle, String email, String agelimit, String eligibility) {
	super();
	this.noofpost = noofpost;
	this.companyid = companyid;
	this.jobtitle = jobtitle;
	this.email = email;
	this.agelimit = agelimit;
	this.eligibility = eligibility;
}







}

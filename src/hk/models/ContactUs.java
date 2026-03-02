package hk.models;

import java.sql.Date;

public class ContactUs 
{
	/*
	 * contactid, name, email, phone, yourquery, date
	 */

int contactid;
String name,email,phone,yourquery;
Date date;
public int getContactid() {
	return contactid;
}
public void setContactid(int contactid) {
	this.contactid = contactid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getYourquery() {
	return yourquery;
}
public void setYourquery(String yourquery) {
	this.yourquery = yourquery;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public ContactUs(String name, String email, String phone, String yourquery, Date date) 
{
	super();
	this.name = name;
	this.email = email;
	this.phone = phone;
	this.yourquery = yourquery;
	this.date = date;
}
public ContactUs(String name, String yourquery, Date date) {
	super();
	this.name = name;
	this.yourquery = yourquery;
	this.date = date;
}









}

package hk.models;

public class Admin 
{
private String adminId,adminpass,name, pass,email,phone;

public String getAdminId() {
	return adminId;
}

public void setAdminId(String adminId) {
	this.adminId = adminId;
}

public String getAdminpass() {
	return adminpass;
}

public void setAdminpass(String adminpass) 
{
	this.adminpass = adminpass;
}

public String getName() 
{
	return name;
}

public void setName(String name)
{
	this.name = name;
}

public String getPass()
{
	return pass;
}

public void setPass(String pass) 
{
	this.pass = pass;
}

public String getEmail() 
{
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

public Admin(String adminId, String adminpass) {
	super();
	this.adminId = adminId;
	this.adminpass = adminpass;
}

public Admin(String adminId, String name, String email, String phone)
{
	super();
	this.adminId = adminId;
	this.name = name;
	this.email = email;
	this.phone = phone;
}

public Admin()
{
	super();
	// TODO Auto-generated constructor stub
}


}

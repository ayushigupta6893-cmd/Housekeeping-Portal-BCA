package hk.models;

public class Employee
{
 private String employeeid, companyid,name,experience;
 private String email,phone,gender;
 private String address,type;
 private String status;
 private Companydetail companydetail;
 private EmployeeAssignment empassign;
 public EmployeeAssignment getEmpassign() {
	return empassign;
}
public void setEmpassign(EmployeeAssignment empassign) {
	this.empassign = empassign;
}
private int cid;
 
 
 
public Employee(String employeeid, String companyid, String name, String experience, String email, String phone,
		String gender, String address, String type, String status, int cid) {
	super();
	this.employeeid = employeeid;
	this.companyid = companyid;
	this.name = name;
	this.experience = experience;
	this.email = email;
	this.phone = phone;
	this.gender = gender;
	this.address = address;
	this.type = type;
	this.status = status;

	this.cid = cid;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public Companydetail getCompanydetail() {
	return companydetail;
}
public void setCompanydetail(Companydetail companydetail) {
	this.companydetail = companydetail;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getEmployeeid() {
	return employeeid;
}
public void setEmployeeid(String employeeid) {
	this.employeeid = employeeid;
}
public String getCompanyid() {
	return companyid;
}
public void setCompanyid(String companyid) {
	this.companyid = companyid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
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
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public Employee(String companyid, String employeeid, String name, String experience, String email, String phone,
		String gender, String address, String type) {
	super();
	this.companyid = companyid;
	this.employeeid = employeeid;
	this.name = name;
	this.experience = experience;
	this.email = email;
	this.phone = phone;
	this.gender = gender;
	this.address = address;
	this.type = type;
	
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}

}
package hk.models;

public class CommonDetails
{
	
	private String name, email, companyname,address,picname,phone,id;
	private String companyownerid;
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
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicname() {
		return picname;
	}
	public void setPicname(String picname) {
		this.picname = picname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyownerid() {
		return companyownerid;
	}
	public void setCompanyownerid(String companyownerid) {
		this.companyownerid = companyownerid;
	}
	public CommonDetails(String name, String email, String companyname, String address, String picname, String phone,
			String id, String companyownerid) {
		super();
		this.name = name;
		this.email = email;
		this.companyname = companyname;
		this.address = address;
		this.picname = picname;
		this.phone = phone;
		this.id = id;
		this.companyownerid = companyownerid;
	}
	
	
	
	
	
	
	
	

}

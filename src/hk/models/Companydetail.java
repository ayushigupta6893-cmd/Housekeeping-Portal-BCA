package hk.models;

public class Companydetail 
{
	/*
	 * companyownerid, companyid, comanyname, email, address, phone, founder,
	 * landmark, latitude, longitude, registrationnumber, companydetail, emptype
	 * 
	 */
	
	private String  companyownerid,comanyname, email, address, phone, founder;
	private String landmark,registrationnumber, companydetail;
	 private String emptype;
	private int companyid;
	private Double latitude, longitude;
	public String getCompanyownerid() {
		return companyownerid;
	}
	public void setCompanyownerid(String companyownerid) {
		this.companyownerid = companyownerid;
	}
	public String getComanyname() {
		return comanyname;
	}
	public void setComanyname(String comanyname) {
		this.comanyname = comanyname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getRegistrationnumber() {
		return registrationnumber;
	}
	public void setRegistrationnumber(String registrationnumber) {
		this.registrationnumber = registrationnumber;
	}
	public String getCompanydetail() {
		return companydetail;
	}
	public void setCompanydetail(String companydetail) {
		this.companydetail = companydetail;
	}
	public  String getEmptype() {
		return emptype;
	}
	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Companydetail(String companyownerid, String comanyname, String email, String address, String phone,
			String founder, String landmark,Double latitude, Double longitude ,String registrationnumber, String companydetail, String emptype
			) {
		super();
		this.companyownerid = companyownerid;
		this.comanyname = comanyname;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.founder = founder;
		this.landmark = landmark;
		this.latitude = latitude;
		this.longitude = longitude;
		this.registrationnumber = registrationnumber;
		this.companydetail = companydetail;
		this.emptype = emptype;
		
	}
	
	public Companydetail(String comanyname, int companyid)
	{
		super();
		this.comanyname = comanyname;
		this.companyid = companyid;
	}
	public Companydetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}


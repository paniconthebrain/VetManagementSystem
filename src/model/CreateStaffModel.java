package model;

public class CreateStaffModel {

	private Integer staffId;
	private String fullName;
	private String gender;
	private String contactNo;
	private String staffType;
	
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	
	public CreateStaffModel(Integer staffId, String fullName, String gender, String contactNo, String staffType) {
		this.staffId = staffId;
		this.fullName = fullName;
		this.gender = gender;
		this.contactNo = contactNo;
		this.staffType = staffType;
	}
	
	
	
	
	
	
	
}

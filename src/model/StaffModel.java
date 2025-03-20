package model;

public class StaffModel {
	
	
	private int staffId;
	private String fullName;
	private String Gender;
	private String contactNo;
	private String staffType;

	//Parameterized Constructor
	public StaffModel(int staffId, String fullName, String gender, String contactNo, String staffType) {
		this.staffId = staffId;
		this.fullName = fullName;
		this.Gender = gender;
		this.contactNo = contactNo;
		this.staffType = staffType;
	}

	//Default Constructor
	public StaffModel() {
		this.staffId = 0;
		this.fullName = "";
		this.Gender = "";
		this.contactNo = "";
		this.staffType = "";
	}
	
	
	public StaffModel(StaffModel staffModel) {
		this.staffId = staffModel.staffId;
		this.fullName = staffModel.fullName;
		this.Gender = staffModel.Gender;
		this.contactNo = staffModel.contactNo;
		this.staffType = staffModel.staffType;
	}

	//getter and setters
	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
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
	
	
	

}

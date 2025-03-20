package model;

public class UserManagementModel {

	private int userId;
	private String fullName;
	private String contactNo;
	private String password;

	// Default Constructor
	public UserManagementModel() {
		this.userId = 0;
		this.fullName = "";
		this.contactNo = "";
		this.password = "";
	}

	// Parameterized Constructor
	public UserManagementModel(int userId, String fullName, String contactNo, String password) {
		this.userId = userId;
		this.fullName = fullName;
		this.contactNo = contactNo;
		this.password = password;
	}
	
	public UserManagementModel(UserManagementModel userManagement) {
		this.userId = userManagement.userId;
		this.fullName = userManagement.fullName;
		this.contactNo = userManagement.contactNo;
		this.password = userManagement.password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserManagement [userId=" + userId + ", fullName=" + fullName + ", contactNo=" + contactNo
				+ ", password=" + password + "]";
	}
	
	
	
	

}

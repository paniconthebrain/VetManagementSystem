package model;

public class UserManagementModel {

	private int userId;
	private int assignedId;
	private String username;
	private String password;
	private String fullName;
	private String userType;

	private static UserManagementModel instance;

	public static UserManagementModel getInstance() {
		if (instance == null) {
			instance = new UserManagementModel();
		}
		return instance;
	}

	public void clearSession() {
		instance = null; // Clear session when logging out
	}

	// Getters and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAssignedId() {
		return assignedId;
	}

	public void setAssignedId(int assignedId) {
		this.assignedId = assignedId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public UserManagementModel(int userId, int assignedId, String username, String password, String fullName,
			String userType) {
		this.userId = userId;
		this.assignedId = assignedId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.userType = userType;
	}

	public UserManagementModel() {
		this.userId = 0;
		this.assignedId = 0;
		this.username = "";
		this.password = "";
		this.fullName = "";
		this.userType = "";
	}

	public void setUserDetails(int userId, String username, String userType) {
		this.userId = userId;
		this.username = username;
		this.userType = userType;
	}

}

package model;

public class LoginModel {
	
	private String userName;
	private String Password;
	
	
	public String getUserName() {
		return userName;
	}	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	//Parameterized Constructor
	public LoginModel(String userName, String password) {		
		this.userName = userName;
		Password = password;
	}
	//Default Constructor
	public LoginModel() {
		this.userName = "";
		Password = "";
	}
	//Copy Constructor
	public LoginModel(LoginModel userLogin) {
		this.userName = userLogin.userName;
		Password = userLogin.Password;
	}	

}

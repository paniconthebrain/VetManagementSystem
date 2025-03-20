package interfaces;

import model.LoginModel;

public interface InfLogin {
	
	public LoginModel login(String fullName, String password);
}

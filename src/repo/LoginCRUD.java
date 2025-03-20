package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import interfaces.InfLogin;
import library.DbConnection;
import model.LoginModel;

public class LoginCRUD extends DbConnection implements InfLogin {

	@Override
	public LoginModel login(String fullName, String password) {
		PreparedStatement pStat;
		Connection conn;
		
		return null;
		
	}

	
}

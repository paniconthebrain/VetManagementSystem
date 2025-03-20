package interfaces;

import java.util.List;

import model.UserManagementModel;

public interface InfUserManagementCRUD {

	public boolean Insert(UserManagementModel user);
	public UserManagementModel Search(int userId);
	public boolean Update(UserManagementModel user);
	List All();
	
}

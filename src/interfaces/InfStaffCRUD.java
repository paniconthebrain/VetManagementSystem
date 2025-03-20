package interfaces;

import java.util.List;

import model.StaffModel;

public interface InfStaffCRUD {
	
	public boolean Insert(StaffModel staffModel);
	public StaffModel search();
	public boolean Update(StaffModel staffmodel);
	List All();

	
}

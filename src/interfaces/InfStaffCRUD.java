package interfaces;

import java.util.List;

import model.StaffModel;

public interface InfStaffCRUD {

	public boolean Insert(StaffModel staffModel);
	public StaffModel search();
	StaffModel searchByName(String staffName); // Add the method with int parameter
	StaffModel searchByID(Integer staffID); // Add the method with int parameter
	public boolean Update(StaffModel staffmodel);

	List All();

}

package interfaces;

import model.CompanySetupModel;


public interface InfCompanyCRUD {
	
	public boolean Insert(CompanySetupModel company);
	public CompanySetupModel search(int companyId);
	

}

package interfaces;

import java.util.List;

import model.OwnerModel;

public interface InfOwnerCRUD {
	public boolean Insert(OwnerModel ownerModel);
	public OwnerModel search();
	public boolean Update(OwnerModel ownerModel);
	List All();

}

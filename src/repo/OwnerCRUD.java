package repo;

import java.util.List;

import interfaces.InfOwnerCRUD;
import library.DbConnection;
import model.OwnerModel;

public class OwnerCRUD extends DbConnection implements InfOwnerCRUD {

	@Override
	public boolean Insert(OwnerModel ownerModel) {
		
		return false;
	}

	@Override
	public OwnerModel search() {
		return null;
	}

	@Override
	public boolean Update(OwnerModel ownerModel) {
		return false;
	}

	@Override
	public List All() {
		return null;
	}

	
}

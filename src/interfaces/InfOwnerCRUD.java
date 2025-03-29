package interfaces;

import java.util.List;
import model.OwnerModel;

public interface InfOwnerCRUD {
    boolean Insert(OwnerModel ownerModel);
    OwnerModel searchByName(String ownerName); // Add the method with int parameter
    boolean Update(OwnerModel ownerName);
    boolean delete(int ownerName); // Add delete method
    List<OwnerModel> All();
    
    OwnerModel getOwnerbyId(int ownerId);
}
package interfaces;

import java.util.List;
import model.OwnerModel;

public interface InfOwnerCRUD {
    boolean Insert(OwnerModel ownerModel);
    OwnerModel searchByName(int ownerName); // Add the method with int parameter
    boolean Update(OwnerModel ownerName);
    boolean delete(int ownerName); // Add delete method
    List<OwnerModel> All();
}
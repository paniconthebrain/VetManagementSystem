package interfaces;

import java.util.List;
import model.OwnerModel;

public interface InfOwnerCRUD {
    boolean Insert(OwnerModel ownerModel);
    OwnerModel search(int ownerId); // Add the method with int parameter
    boolean Update(OwnerModel ownerModel);
    boolean delete(int ownerId); // Add delete method
    List<OwnerModel> All();
}
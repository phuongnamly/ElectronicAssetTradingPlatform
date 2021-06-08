package client.model.mockInterface;

//import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.exception.InventoryException;

import java.util.ArrayList;

public interface InventoryDataSource {
    boolean create(Inventory inventory) throws InventoryException;
    boolean edit(Inventory inventory) throws InventoryException;
    boolean delete(String id);
    ArrayList<Inventory> get(String id);
    ArrayList<Inventory> getAll();
    boolean deleteAll();
}

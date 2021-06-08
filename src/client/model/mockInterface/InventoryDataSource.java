package client.model.mockInterface;

import client.model.entity.Asset;
import client.model.entity.Inventory;

import java.util.ArrayList;

public interface InventoryDataSource {
    boolean create(Inventory inventory);
    boolean edit(Inventory inventory);
    boolean delete(String id);
    ArrayList<Inventory> get(String id);
    ArrayList<Inventory> getAll();
    boolean deleteAll();
}

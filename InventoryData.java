package client.model.mockDatabase;
import client.model.entity.Inventory;
import client.model.exception.InventoryException;
import client.model.mockInterface.InventoryDataSource;

import java.util.ArrayList;

public class InventoryData implements InventoryDataSource {
    ArrayList<Inventory> inventories;
    int id;

    public InventoryData(){
        inventories = new ArrayList<>();
        id = 0;
    }

    @Override
    public boolean create(Inventory inventory) throws InventoryException {
        if (InventoryException.validate("create", inventory)){
            id++;
            inventory.setInventoryID(Integer.toString(id));
            inventories.add(inventory);

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean edit(Inventory inventory) throws InventoryException {
        String func = "editID";
        if (InventoryException.validate(func, inventory)){
            int inventoryID = Integer.parseInt(inventory.getInventoryID())-1;
            Inventory prevInventory = inventories.get(inventoryID);
            if (inventory.getAssetID() == null){
                inventory.setAssetID(prevInventory.getAssetID());
            }
            if (inventory.getOrganisationID() == null){
                inventory.setOrganisationID(prevInventory.getOrganisationID());
            }
            if (inventory.getQuantity() == null){
                inventory.setQuantity(prevInventory.getQuantity());
            }
            inventories.set(inventoryID, inventory);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            inventories.remove(Integer.parseInt(id)-1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Inventory> get(String id) {
        Inventory inventory = inventories.get(Integer.parseInt(id)-1);
        ArrayList<Inventory> tempInventory = new ArrayList<>();
        tempInventory.add(inventory);
        return tempInventory;
    }

    @Override
    public ArrayList<Inventory> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        inventories.clear();
        id = 0;
        return true;
    }
}

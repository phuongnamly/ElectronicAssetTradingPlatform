package client.model.UnitTest.mockTest;

import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.exception.AssetException;
import client.model.exception.InventoryException;
import client.model.mockDatabase.InventoryData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the class for testing mock inventory CRUD functions
 */
public class mockInventoryTest {
    InventoryData inventorydata;

    @BeforeEach
    void setup() {
        inventorydata = new InventoryData();
    }

    @AfterEach
    void tearDown() {
        inventorydata.deleteAll();
    }

    //    Test get asset

    @Test
    void TestGetInventoryFromGetInventory() throws InventoryException {
        Inventory inventory_set = new Inventory("1","2","3", "10000");
        inventorydata.create(inventory_set);
        ArrayList<Inventory> inventory_get = inventorydata.get("1");
        assertEquals(inventory_set.toStringAllFields(), inventory_get.get(0).toStringAllFields());
    }

    //    Test create asset
    @Test
    void TestBooleanCreateInventory() throws InventoryException {
        assertTrue(inventorydata.create(new Inventory("1","2","3", "100000")));
    }

    @Test
    void TestGetInventoryFromCreateInventory() throws InventoryException {
        Inventory inventory_new = new Inventory("1","2","3", "100000");
        inventorydata.create(inventory_new);
        ArrayList<Inventory> inventory_get = inventorydata.get("1");
        assertEquals(inventory_new.toStringAllFields(), inventory_get.get(0).toStringAllFields());
    }
    //    Test Edit Asset
    @Test
    void TestBooleanInventoryFromEditInventory() throws InventoryException{
        inventorydata.create(new Inventory("1","2","3", "100000"));
        assertTrue(inventorydata.edit(new Inventory("1","2","3", null)));
    }

    @Test
    void TestGetInventoryFromEditInventory() throws InventoryException {
        Inventory inventory_set_orig = new Inventory("1","2","3", "100000");
        Inventory inventory_set_new = new Inventory("1","2","5", null);
        Inventory inventory_expect = new Inventory("1","2","5", "100000");
        inventorydata.create(inventory_set_orig);
        inventorydata.edit(inventory_set_new);
        ArrayList<Inventory> inventory_get = inventorydata.get("1");

        assertEquals(inventory_get.get(0).toStringAllFields(),inventory_expect.toStringAllFields());
    }

    //    Test delete asset
    @Test
    void TestBooleanInventoryFromDeleteInventory() throws InventoryException{
        inventorydata.create(new Inventory("1","2","3", "100000"));
        assertTrue(inventorydata.delete("1"));
    }

//    @Test
//    void TestGetOrganisationFromDeleteOrganisation() throws OrganisationException, IndexOutOfBoundsException {
//        Organisation organisation_set_orig = new Organisation("1","Thomas Company", "100000");
////        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
////        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        organisationdata.create(organisation_set_orig);
//        organisationdata.delete("1");
////        userdata.edit(user_set_new);
////        ArrayList<User> user_get = userdata.get("1");
//
//        assertNull(organisationdata.get("1"));
//    }
}

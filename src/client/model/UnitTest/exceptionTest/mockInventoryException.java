package client.model.UnitTest.exceptionTest;

import client.model.entity.Inventory;
import client.model.entity.User;
import client.model.exception.InventoryException;
import client.model.exception.UserException;
import client.model.mockDatabase.InventoryData;
import client.model.mockDatabase.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class mockInventoryException {
    InventoryData inventorydata;
    @BeforeEach
    void setup() {
        inventorydata = new InventoryData();
    }

    @AfterEach
    void tearDown() {
        inventorydata.deleteAll();
    }

    /* Test out of range/ non integer phone number*/
    @Test
    void TestNonIntegerQuantity() throws InventoryException {
        Assertions.assertThrows(InventoryException.class, () -> {
            inventorydata.create(new Inventory("1","2","3","hfffj"));
        });
    }

    /*Test out of range id/ non integer/ non positive for edit*/
    @Test
    void TestWrongInventoryIDFormat() throws InventoryException {
        Assertions.assertThrows(InventoryException.class, () -> {
            inventorydata.edit(new Inventory("-1","2","3","10000"));
            inventorydata.edit(new Inventory(null,"2","3","10000"));
            inventorydata.edit(new Inventory("10000000000000","2","3","10000"));
        });
    }
    /*Test get empty database*/
    @Test
    void TestGetEmptyDatabase() throws InventoryException {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            inventorydata.get("1");
        });
    }

}

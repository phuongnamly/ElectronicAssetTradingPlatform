package client.model.exception;
import client.model.entity.Inventory;

import static utils.tryParse.TryParse.TryParseInt;

/**
 * This is the class for creating specific exceptions for inventory
 */
public class InventoryException extends Exception {
    public InventoryException(){super();}
    public InventoryException(String message){super(message);}
    public static boolean validate(String func, Inventory inventory) throws InventoryException{
        boolean success = false;

        if (inventory.getQuantity() == null || TryParseInt(inventory.getQuantity()) != null){
            success = true;
        }
        else{
            throw new InventoryException("quantity needs to be an int and within integer limit");
        }
        if (inventory.getAssetID() == null || TryParseInt(inventory.getAssetID()) != null){
            success = true;
        }
        else{
            throw new InventoryException("AssetID needs to be an int and within integer limit");
        }
        if (inventory.getOrganisationID() == null || TryParseInt(inventory.getOrganisationID()) != null){
            success = true;
        }
        else{
            throw new InventoryException("Org ID needs to be an int and within integer limit");
        }
        switch(func) {
//            case("create"):{
//                /*Test out of range/ non integer Credit*/

//            }
//            break;

            case("editID"):{
                /*Test out of range id/ non integer/ non positive*/
                if (inventory.getInventoryID() != null) {
                    if (TryParseInt(inventory.getInventoryID()) != null && TryParseInt(inventory.getInventoryID()) > 0) {
                        success = true;
                    } else {
                        throw new InventoryException("Inventory ID needs to be a positive integer and within integer limit");
                    }
                }

                /*Test userID is null when create.*/
                else {
                    throw new InventoryException("Inventory ID can't be null");
                }
            }
            break;
        }


        /*
         *
         *
         * Exception cases
         * create 3 cases
         * edit 1 case ID null
         * get 1 case ID null
         *
         * */
        return success;
    }
}

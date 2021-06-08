package client.model.exception;
import client.model.entity.Inventory;

import static utils.tryParse.TryParse.TryParseInt;

public class InventoryException extends Exception {
    public InventoryException(){super();}
    public InventoryException(String message){super(message);}
    public static boolean validate(String func, Inventory inventory) throws InventoryException{
        boolean success = false;

        switch(func) {
//            case("create"):{
//                /*Test out of range/ non integer Credit*/
//                if (TryParseInt(organisation.getCredits()) != null){
//                    success = true;
//                }
//                else{
//                    throw new OrganisationException("Credits needs to be an int and within integer limit");
//                }
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

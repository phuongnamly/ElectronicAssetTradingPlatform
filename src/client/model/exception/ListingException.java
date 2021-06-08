package client.model.exception;

import client.model.entity.Listing;

import static utils.tryParse.TryParse.TryParseInt;

/**
 * This is the class for creating specific exceptions for listing
 */
public class ListingException extends Exception{
    public ListingException(){super();}
    public ListingException(String message){super(message);}

    public static boolean validate(String func, Listing listing) throws ListingException{
        boolean success = false;
        /*Test out of range/ non integer Credit*/
        if (listing.getPrice() == null || TryParseInt(listing.getPrice()) != null){
            success = true;
        }
        else{
            throw new ListingException("Prices needs to be an int and within integer limit");
        }
        if (listing.getQuantity() == null || TryParseInt(listing.getQuantity()) != null){
            success = true;
        }
        else{
            throw new ListingException("Quantity needs to be an int and within integer limit");
        }
        if (listing.getInventoryID() == null || TryParseInt(listing.getInventoryID()) != null){
            success = true;
        }
        else{
            throw new ListingException("InventoryID needs to be an int and within integer limit");
        }
        /*Test out of range/ non integer Credit*/
        if (listing.getPrice() == null || TryParseInt(listing.getPrice()) != null){
            success = true;
        }
        else{
            throw new ListingException("Prices needs to be an int and within integer limit");
        }
        if (listing.getQuantity() == null || TryParseInt(listing.getQuantity()) != null){
            success = true;
        }
        else{
            throw new ListingException("Quantity needs to be an int and within integer limit");
        }
        if (listing.getUserID() == null || TryParseInt(listing.getUserID()) != null){
            success = true;
        }
        else{
            throw new ListingException("UserID needs to be an int and within integer limit");
        }
        /*Test out of range/ non integer Credit*/
        if (listing.getPrice() == null || TryParseInt(listing.getPrice()) != null){
            success = true;
        }
        else{
            throw new ListingException("Prices needs to be an int and within integer limit");
        }
        if (listing.getQuantity() == null || TryParseInt(listing.getQuantity()) != null){
            success = true;
        }
        else{
            throw new ListingException("Quantity needs to be an int and within integer limit");
        }

        switch(func) {
//            case("create"):{

//            }
//            break;

            case("editID"):{
                /*Test out of range id/ non integer/ non positive*/
                if (listing.getListingID() != null) {
                    if (TryParseInt(listing.getListingID()) != null && TryParseInt(listing.getListingID()) > 0) {
                        success = true;
                    } else {
                        throw new ListingException("Listing ID needs to be a positive integer and within integer limit");
                    }
                }

                /*Test userID is null when create.*/
                else {
                    throw new ListingException("Organisation ID can't be null");
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

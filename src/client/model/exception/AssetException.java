package client.model.exception;

import client.model.entity.Asset;

import static utils.tryParse.TryParse.TryParseInt;

/**
 * This is the class for creating specific exceptions for asset
 */
public class AssetException extends Exception{
    public AssetException(){super();}
    public AssetException(String message){super(message);}

    public static boolean validate(String func, Asset asset) throws AssetException{
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
                if (asset.getAssetID() != null) {
                    if (TryParseInt(asset.getAssetID()) != null && TryParseInt(asset.getAssetID()) > 0) {
                        success = true;
                    } else {
                        throw new AssetException("Asset ID needs to be a positive integer and within integer limit");
                    }
                }

                /*Test userID is null when create.*/
                else {
                    throw new AssetException("Asset ID can't be null");
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

package client.model.exception;

import com.sun.org.apache.xpath.internal.operations.Or;

import static utils.tryParse.TryParse.TryParseInt;

public class OrganisationException extends Exception{
    public OrganisationException(){super();}
    public OrganisationException(String message){super(message);}

    public static boolean validate(String organisationID, String organisationName, String credits) throws OrganisationException{
        boolean success = false;

        switch(func) {
            case("create"):{
                /*Test out of range/ non integer Credit*/
                if (TryParseInt(credits) != null){
                    success = true;
                }
                else{
                    throw new OrganisationException("Credits needs to be an int and within integer limit");
                }
            }
            break;

            case("editID"):{
                /*Test out of range id/ non integer/ non positive*/
                if (organisationID != null) {
                    if (TryParseInt(organisationID) != null && TryParseInt(organisationID) > 0) {
                        success = true;
                    } else {
                        throw new OrganisationException("Organisation ID needs to be a positive integer and within integer limit");
                    }
                }

                /*Test userID is null when create.*/
                else {
                    throw new OrganisationException("Organisation ID can't be null");
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

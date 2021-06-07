package client.model.exception;

import client.model.entity.Organisation;
//import com.sun.org.apache.xpath.internal.operations.Or;

import static utils.tryParse.TryParse.TryParseInt;

public class OrganisationException extends Exception{
    public OrganisationException(){super();}
    public OrganisationException(String message){super(message);}

    public static boolean validate(String func, Organisation organisation) throws OrganisationException{
        boolean success = false;

        switch(func) {
            case("create"):{
                /*Test out of range/ non integer Credit*/
                if (organisation.getCredits()==null || TryParseInt(organisation.getCredits()) != null){
                    success = true;
                }
                else{
                    throw new OrganisationException("Credits needs to be an int and within integer limit");
                }
            }
            break;

            case("editID"):{
                /*Test out of range id/ non integer/ non positive*/
                if (organisation.getOrganisationID() != null) {
                    if (TryParseInt(organisation.getOrganisationID()) != null && TryParseInt(organisation.getOrganisationID()) > 0) {
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

package client.model.exception;

import static utils.tryParse.TryParse.TryParseInt;

//https://www.javatpoint.com/custom-exception
public class UserException extends Exception{
    public UserException(){super();}
    public UserException(String message){super(message);}

    public static boolean validate(String organisationID, String username, String password, String email, String phoneNum, String address, String accountType) throws UserException {
        boolean success = false;

        if (TryParseInt(phoneNum) != null){
            success = true;
        }
        else{
            throw new UserException("Phone number needs to be an int and within integer limit");
        }

        return success;
    }
}
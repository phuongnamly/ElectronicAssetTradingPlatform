package client.model.exception;

import client.model.entity.User;

import static utils.tryParse.TryParse.TryParseInt;

//https://www.javatpoint.com/custom-exception
public class UserException extends Exception{
    public UserException(){super();}
    public UserException(String message){super(message);}

    public static boolean validate(String func, User user) throws UserException {
        boolean success = false;

        switch(func) {
            case("create"):{
                /*Test out of range/ non integer phone number*/
                if (user.getPhoneNum()==null || TryParseInt(user.getPhoneNum()) != null){
                    success = true;
                }
                else{
                    throw new UserException("Phone number needs to be an int and within integer limit");
                }
            }
            break;

            case("editID"):{
                /*Test out of range id/ non integer/ non positive*/
                if (user.getUserID() != null) {
                    if (TryParseInt(user.getUserID()) != null && TryParseInt(user.getUserID()) > 0) {
                        success = true;
                    } else {
                        throw new UserException("User ID needs to be a positive integer and within integer limit");
                    }
                }
                /*Test userID is null when create.*/
                else {
                    throw new UserException("User ID can't be null");
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

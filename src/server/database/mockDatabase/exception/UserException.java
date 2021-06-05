package server.database.mockDatabase.exception;

//https://www.javatpoint.com/custom-exception
public class UserException extends Exception{
    public UserException(){super();}
    public UserException(String message){super(message);}

    public static boolean validate() throws UserException{
        throw new UserException();
    }
}

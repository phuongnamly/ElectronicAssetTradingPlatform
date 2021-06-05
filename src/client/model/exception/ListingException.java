package client.model.exception;

public class ListingException extends Exception{
    public ListingException(){super();}
    public ListingException(String message){super(message);}

    public static boolean validate() throws ListingException{
        throw new ListingException();
    }
}

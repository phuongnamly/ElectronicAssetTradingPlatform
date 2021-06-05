package client.model.exception;

public class AssetException extends Exception{
    public AssetException(){super();}
    public AssetException(String message){super(message);}

    public static boolean validate() throws AssetException{
        throw new AssetException();
    }
}

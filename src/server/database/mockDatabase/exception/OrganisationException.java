package server.database.mockDatabase.exception;

public class OrganisationException extends Exception{
    public OrganisationException(){super();}
    public OrganisationException(String message){super(message);}

    public static boolean validate() throws OrganisationException{
        throw new OrganisationException();
    }
}

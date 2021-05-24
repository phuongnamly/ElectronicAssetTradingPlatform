package server.database;

import server.database.JBDCDataSource.Entity.User;
import server.database.JBDCDataSource.JBDCUserDataSource;

public class testDatabase {


    static private JBDCUserDataSource userDatabase;

    public static void main(String[] args){
        User uh = new User("3213213","Nam", "sddasd", "dsasdasd","huytran@gmail.com", "0412339968", "Kelvin Grove", "admin");
        User um = new User("3213213","Minh123", "sddasd", "dsasdasd","huytran@gmail.com", "0412339968", "Kelvin Grove", "admin");
        userDatabase = new JBDCUserDataSource();
        userDatabase.create(uh) ;
        userDatabase.create(um) ;
    }
}

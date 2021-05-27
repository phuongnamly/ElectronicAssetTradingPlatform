package server.database;

import server.database.JBDCDataSource.Entity.User;
import server.database.JBDCDataSource.JBDCOrganisationDataSource;
import server.database.JBDCDataSource.JBDCUserDataSource;

import java.util.List;

public class testDatabase {


    static private JBDCUserDataSource userDatabase;
    static private JBDCOrganisationDataSource organisationDatabase;

    public static void main(String[] args){
//        User uh = new User("3213213","Nam", "sddasd", "dsasdasd","huytran@gmail.com", "0412339968", "Kelvin Grove", "admin");
//        User um = new User("3213213","Minh123", "sddasd", "dsasdasd","huytran@gmail.com", "0412339968", "Kelvin Grove", "admin");
        userDatabase = new JBDCUserDataSource();
        organisationDatabase = new JBDCOrganisationDataSource();

//        userDatabase.create(uh) ;
//        userDatabase.create(um) ;

        List<User> users = userDatabase.get("Nam");
        System.out.println(users.get(0).getUsername());

        organisationDatabase.delete("3");
    }
}

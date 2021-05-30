package server.database;

import server.database.JBDCDataSource.Entity.Organisation;
import server.database.JBDCDataSource.Entity.User;
import server.database.JBDCDataSource.JBDCOrganisationDataSource;
import server.database.JBDCDataSource.JBDCUserDataSource;

import java.util.List;

public class testDatabase {


    static private JBDCUserDataSource userDatabase;
    static private JBDCOrganisationDataSource organisationDatabase;

    public static void main(String[] args){
        organisationDatabase = new JBDCOrganisationDataSource();
        userDatabase = new JBDCUserDataSource();

        mockUpOrganisation();
        mockUpUser();
    }

    public static void mockUpUser(){
        createMockUser();
//        getMockUser();
    }

    public static void createMockUser(){
        userDatabase.create(new User("1","admin", "admin", "dsasdasd","admin@gmail.com", "0123456789", "Garden Point", "admin"));
        userDatabase.create(new User("2","Nam", "123123", "dsasdasd","nam@gmail.com", "0123456789", "Garden Point", "leader")) ;
        userDatabase.create(new User("3","Thomas", "123123", "dsasdasd","thomas@gmail.com", "0123456789", "Kelvin Grove", "leader")) ;
        userDatabase.create(new User("4","Jason", "12123", "dsasdasd","jason@gmail.com", "0123456789", "Garden Point", "leader")) ;
        userDatabase.create(new User("5","Selwyn", "123123", "dsasdasd","selwyn@gmail.com", "0123456789", "Garden Point", "leader")) ;
    }

    public static void getMockUser(){
        List<User> users = userDatabase.get("Nam");
        System.out.println(users.get(0).getUsername());
    }

    public static void mockUpOrganisation(){
        createMockOrganisation();
//        editMockOrganisation();
//        deleteMockOrganisation();
    }

    public static void createMockOrganisation(){
        organisationDatabase.create(new Organisation("","Electronic Asset Trading Company", "0"));
        organisationDatabase.create(new Organisation("","Nam's Company", "25"));
        organisationDatabase.create(new Organisation("","Thomas's Company", "50"));
        organisationDatabase.create(new Organisation("","Jason's Company", "75"));
        organisationDatabase.create(new Organisation("","Selwyn's Company", "100"));
    }

    public static void editMockOrganisation(){
        organisationDatabase.edit(new Organisation("2","Nam's Company", "999"));
        organisationDatabase.edit(new Organisation("3","Thomas's Company", "666"));
        organisationDatabase.edit(new Organisation("4","Jason's Company", "555"));
        organisationDatabase.edit(new Organisation("5","Selwyn's Company", "1111"));
    }

    public static void deleteMockOrganisation(){
        //        organisationDatabase.delete("3");
    }
}

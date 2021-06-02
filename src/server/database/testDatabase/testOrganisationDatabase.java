//package server.database.testDatabase;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testng.annotations.AfterTest;
//import server.database.JBDCDataSource.DBConnection;
//import server.database.JBDCDataSource.Entity.Organisation;
//import server.database.JBDCDataSource.JBDCOrganisationDataSource;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class testOrganisationDatabase {
//    static private JBDCOrganisationDataSource organisationDatabase;
//
//    @BeforeEach
//    public void initDatabase() {
//        organisationDatabase = new JBDCOrganisationDataSource();
//    }
//
//    @Test
//    public void testCreate()  {
//        ArrayList<Organisation> organisations = ArrayList<>();
//        organisations.add(new Organisation("","Electronic Asset Trading Company", "0"));
//        organisations.add(new Organisation("","Electronic Asset Trading Company", "0"));
//        organisations.add(new Organisation("","Electronic Asset Trading Company", "0"));
//        organisations.add(new Organisation("","Electronic Asset Trading Company", "0"));
//        organisations.add(new Organisation("","Electronic Asset Trading Company", "0"));
//
//        organisationDatabase.create();
//        organisationDatabase.create(new Organisation("","Nam's Company", "25"));
//        organisationDatabase.create(new Organisation("","Thomas's Company", "50"));
//        organisationDatabase.create(new Organisation("","Jason's Company", "75"));
//        organisationDatabase.create(new Organisation("","Selwyn's Company", "100"));
//    }
//
//    @Test
//    public void testEdit()  {
//    }
//
//    @Test
//    public void testDelete()  {
//    }
//
//    @Test
//    public void testGetOne()  {
//    }
//
//    @Test
//    public void testGetAll()  {
//    }
//}
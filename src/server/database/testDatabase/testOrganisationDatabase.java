package server.database.testDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.database.mockDatabase.entity.Organisation;
import server.database.schema.JBDCOrganisationDataSource;

import static org.junit.Assert.assertTrue;

class testOrganisationDatabase {
    static private JBDCOrganisationDataSource organisationDatabase;

    @BeforeEach
    public void initDatabase() {
        organisationDatabase = new JBDCOrganisationDataSource();
    }

//    @Test
//    public void testCreate()  {
//        ArrayList<Organisation>  organisations = new ArrayList<>();
//        organisations.add(new Organisation("","Electronic Asset Trading Company", "0"));
//        organisations.add(new Organisation("","Nam's Company", "25"));
//        organisations.add(new Organisation("","Thomas's Company", "50"));
//        organisations.add(new Organisation("","Jason's Company", "75"));
//        organisations.add(new Organisation("","Selwyn's Company", "100"));
//
//        for (Organisation organisation : organisations)
//        {
//            boolean success = organisationDatabase.create(organisation);
//            assertTrue(success);
//        }
//    }

    @Test
    public void testCreate()  {
        assertTrue(organisationDatabase.create(new Organisation("","Electronic Asset Trading Company", "0")));
        // getOne
    }

    @Test
    public void testEdit()  {
    }

    @Test
    public void testDelete()  {
    }

    @Test
    public void testGetOne()  {
        //
    }

    @Test
    public void testGetAll()  {
    }
}
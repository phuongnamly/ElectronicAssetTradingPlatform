package server.database.testDatabase;

import client.model.entity.Organisation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import server.database.schema.JBDCOrganisationDataSource;

import static org.junit.Assert.assertTrue;

class testOrganisationDatabase {
    static private JBDCOrganisationDataSource organisationDatabase;

    @BeforeEach
    public void setup() {
        organisationDatabase = new JBDCOrganisationDataSource();
    }

    @AfterEach
    public void teardown() {
        organisationDatabase.deleteAll();
    }


    @Test
    public void testBooleanCreate()  {
        assertTrue(organisationDatabase.create(new Organisation("","Electronic Asset Trading Company", "0")));
        // getOne
    }

    @Test
    public void testEdit()  {
    }

    @Test
    public void testDelete()  {
        assertTrue(organisationDatabase.deleteAll());
    }

    @Test
    public void testGetOne()  {
        //
    }

    @Test
    public void testGetAll()  {
    }
}
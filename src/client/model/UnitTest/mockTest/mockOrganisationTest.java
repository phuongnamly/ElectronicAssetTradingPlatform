package client.model.UnitTest.mockTest;

import client.model.entity.Organisation;
import client.model.exception.OrganisationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.model.mockDatabase.OrganisationData;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class mockOrganisationTest {
    OrganisationData organisationdata;

    @BeforeEach
    void setup() {
        organisationdata = new OrganisationData();
    }

    @AfterEach
    void tearDown() {
        organisationdata.deleteAll();
    }

    //    Test get organisation

    @Test
    void TestGetOrganisationFromGetOrganisation() throws OrganisationException {
        Organisation organisation_set = new Organisation("1","Thomas Company", "100000");
        organisationdata.create(organisation_set);
        ArrayList<Organisation> organisation_get = organisationdata.get("1");
        assertEquals(organisation_set.toStringAllFields(), organisation_get.get(0).toStringAllFields());
    }

    //    Test create organisation
    @Test
    void TestBooleanCreateOrganisation() throws OrganisationException {
        assertTrue(organisationdata.create(new Organisation("1","Thomas Company", "100000")));
    }

    @Test
    void TestGetOrganisationFromCreateOrganisation() throws OrganisationException {
        Organisation organisation_set = new Organisation("1","Thomas Company", "100000");
        organisationdata.create(organisation_set);
        ArrayList<Organisation> organisation_get = organisationdata.get("1");
        assertEquals(organisation_set.toStringAllFields(), organisation_get.get(0).toStringAllFields());
    }
    //    Test Edit Organisation
    @Test
    void TestBooleanOrganisationFromEditOrganisation() throws OrganisationException{
        organisationdata.create(new Organisation("1","Thomas Company", "100000"));
        assertTrue(organisationdata.edit(new Organisation("1","Thomas Company 2 ", "20000")));
    }

    @Test
    void TestGetOrganisationFromEditOrganisation() throws OrganisationException {
        Organisation organisation_set_orig = new Organisation("1","Thomas Company", "100000");
        Organisation organisation_set_new = new Organisation("1","Thomas Company 2 ", "20000");
        Organisation organisation_expect = new Organisation("1","Thomas Company 2 ", "20000");
        organisationdata.create(organisation_set_orig);
        organisationdata.edit(organisation_set_new);
        ArrayList<Organisation> organisation_get = organisationdata.get("1");

        assertEquals(organisation_get.get(0).toStringAllFields(),organisation_expect.toStringAllFields());
    }

    //    Test delete organisatoin
    @Test
    void TestBooleanOrganisationFromDeleteOrganisation() throws OrganisationException{
        organisationdata.create(new Organisation("1","Thomas Company", "100000"));
        assertTrue(organisationdata.delete("1"));
    }

//    @Test
//    void TestGetOrganisationFromDeleteOrganisation() throws OrganisationException, IndexOutOfBoundsException {
//        Organisation organisation_set_orig = new Organisation("1","Thomas Company", "100000");
////        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
////        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        organisationdata.create(organisation_set_orig);
//        organisationdata.delete("1");
////        userdata.edit(user_set_new);
////        ArrayList<User> user_get = userdata.get("1");
//
//        assertNull(organisationdata.get("1"));
//    }
}

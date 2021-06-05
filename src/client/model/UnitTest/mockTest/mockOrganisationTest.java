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
        assertEquals(organisation_set.toString(), organisation_get.get(0).toString());
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
        assertEquals(organisation_set.toString(), organisation_get.get(0).toString());
    }
//    //    Test Edit User
//    @Test
//    void TestBooleanUserFromEditUser() throws UserException{
//        organisationdata.create(new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin"));
//        assertTrue(organisationdata.edit(new User("1",null,"admin", "admin", "admin@gmail.com", "0198765432", "KG", "admin")));
//    }
//
//    @Test
//    void TestGetUserFromEditUser() throws UserException {
//        User user_set_orig = new User("1","1","admin", "admin", "admin@gmail.com", "987654321", "KG", "admin");
//        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        userdata.create(user_set_orig);
//        userdata.edit(user_set_new);
//        ArrayList<User> user_get = userdata.get("1");
//
//        assertEquals(user_get.get(0).toString(),user_expect.toString());
//    }
//
//    //    Test delete user
//    @Test
//    void TestBooleanUserFromDeleteUser() throws UserException{
//        userdata.create(new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin"));
//        assertTrue(userdata.delete("1"));
//    }
//
//    @Test
//    void TestGetUserFromDeleteUser() throws UserException, IndexOutOfBoundsException {
//        User user_set_orig = new User("1","1","admin", "admin", "admin@gmail.com", "987654321", "KG", "admin");
////        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
////        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        userdata.create(user_set_orig);
//        userdata.delete("1");
////        userdata.edit(user_set_new);
////        ArrayList<User> user_get = userdata.get("1");
//
//        assertNull(userdata.get("1"));
//    }
}

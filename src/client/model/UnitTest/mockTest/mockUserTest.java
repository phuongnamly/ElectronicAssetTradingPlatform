package client.model.UnitTest.mockTest;

import client.model.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.model.exception.UserException;
import client.model.mockDatabase.UserData;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This is the class for testing mock user CRUD functions
 */
public class mockUserTest {
    UserData userdata;

    @BeforeEach
    void setup() {
        userdata = new UserData();
    }

    @AfterEach
    void tearDown() {
        userdata.deleteAll();
    }

//    @Test
//    void Test(){
//    }


    //    Test get user

    @Test
    void TestGetUserFromGetUser() throws UserException {
        User user_set = new User("1",null,"admin", "admin", "admin","admin@gmail.com", "0123456789", "Garden Point");
        userdata.create(user_set);
        ArrayList<User> user_get = userdata.get("1");
        assertEquals(user_set.toStringAllFields(), user_get.get(0).toStringAllFields());
    }

    //    Test create user
    @Test
    void TestBooleanCreateUser() throws UserException {
        assertTrue(userdata.create(new User("1",null,"admin", "admin","admin", "admin@gmail.com", "0123456789", "Garden Point")));
    }

    @Test
    void TestGetUserFromCreateUser() throws UserException {
        User user_set = new User("1",null,"admin", "admin", "admin","admin@gmail.com", "0123456789", "Garden Point");
        userdata.create(user_set);
        ArrayList<User> user_get = userdata.get("1");
        assertEquals(user_set.toStringAllFields(), user_get.get(0).toStringAllFields());
    }

//    Test Edit User
    @Test
    void TestBooleanUserFromEditUser() throws UserException{
        userdata.create(new User("1",null,"admin", "admin", "admin","admin@gmail.com", "0123456789", "Garden Point"));
        assertTrue(userdata.edit(new User("1",null,"admin", "admin", "admin","admin@gmail.com", "0198765432", "KG")));
    }

    @Test
    void TestGetUserFromEditUser() throws UserException {
        User user_set_orig = new User("1","1","admin", "admin","admin", "admin@gmail.com", "987654321", "KG");
        User user_set_new = new User("1",null,"admin", "admin","admin", "admin@gmail.com", "0123456789", "Garden Point");
        User user_expect = new User("1","1","admin", "admin","admin", "admin@gmail.com", "0123456789", "Garden Point");
        userdata.create(user_set_orig);
        userdata.edit(user_set_new);
        ArrayList<User> user_get = userdata.get("1");

        assertEquals(user_get.get(0).toStringAllFields(),user_expect.toStringAllFields());
    }

//    Test delete user
    @Test
    void TestBooleanUserFromDeleteUser() throws UserException{
        userdata.create(new User("1",null,"admin", "admin","admin", "admin@gmail.com", "0123456789", "Garden Point"));
        assertTrue(userdata.delete("1"));
    }

//    @Test
//    void TestGetUserFromDeleteUser() throws UserException, IndexOutOfBoundsException {
//        User user_set_orig = new User("1","1","admin", "admin", "admin", "admin@gmail.com", "12345678", "admin");
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

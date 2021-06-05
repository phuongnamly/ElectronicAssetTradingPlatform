package client.model.UnitTest.mockTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.model.entity.User;
import client.model.exception.UserException;
import client.model.mockDatabase.UserData;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
        User user_set = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
        userdata.create(user_set);
        ArrayList<User> user_get = userdata.get("1");
        assertEquals(user_set.toString(), user_get.get(0).toString());
    }

    //    Test create user
    @Test
    void TestBooleanCreateUser() throws UserException {
        assertTrue(userdata.create(new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin")));
    }

    @Test
    void TestGetUserFromCreateUser() throws UserException {
        User user_set = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
        userdata.create(user_set);
        ArrayList<User> user_get = userdata.get("1");
        assertEquals(user_set.toString(), user_get.get(0).toString());
    }

//    Test Edit User
    @Test
    void TestBooleanUserFromEditUser() throws UserException{
        userdata.create(new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin"));
        assertTrue(userdata.edit(new User("1",null,"admin", "admin", "admin@gmail.com", "0198765432", "KG", "admin")));
    }

    @Test
    void TestGetUserFromEditUser() throws UserException {
        User user_set_orig = new User("1","1","admin", "admin", "admin@gmail.com", "987654321", "KG", "admin");
        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
        userdata.create(user_set_orig);
        userdata.edit(user_set_new);
        ArrayList<User> user_get = userdata.get("1");

        assertEquals(user_get.get(0).toString(),user_expect.toString());
    }

//    Test delete user
    @Test
    void TestBooleanUserFromDeleteUser() throws UserException{
        userdata.create(new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin"));
        assertTrue(userdata.delete("1"));
    }

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

package server.database.model.UnitTest.mockTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.database.model.entity.User;
import server.database.model.exception.UserException;
import server.database.model.mockDatabase.UserData;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    void TestBooleanCreateUser() throws UserException {
        assertTrue(userdata.create(new User(null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin")));
    }

    @Test
    void TestGetUserFromCreateUser() throws UserException {
        User user_set = new User(null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
        userdata.create(user_set);
        ArrayList<User> user_get = userdata.get("0");
        assertEquals(user_set, user_get.get(0));
    }
}

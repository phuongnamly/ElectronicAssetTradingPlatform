package client.model.UnitTest.exceptionTest;

import client.model.entity.User;
import client.model.exception.UserException;
import client.model.mockDatabase.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the class for testing mock user exception
 */
public class mockUserException {
    UserData userdata;
    @BeforeEach
    void setup() {
        userdata = new UserData();
    }

    @AfterEach
    void tearDown() {
        userdata.deleteAll();
    }

       /* Test out of range/ non integer phone number*/
    @Test
    void TestNonIntegerPhoneNumber() throws UserException {
        Assertions.assertThrows(UserException.class, () -> {
            userdata.create(new User("1",null,"admin", "admin","admin", "admin@gmail.com", "sadsads", "Garden Point"));
        });
    }

        /*Test out of range id/ non integer/ non positive for edit*/
    @Test
    void TestWrongUserIDFormat() throws UserException {
        Assertions.assertThrows(UserException.class, () -> {
            userdata.edit(new User("-1",null,"admin", "admin","admin", "admin@gmail.com", "123456789", "Garden Point"));
            userdata.edit(new User(null,null,"admin", "admin","admin", "admin@gmail.com", "1236789", "Garden Point"));
            userdata.edit(new User("128901290567890",null,"admin", "admin","admin", "admin@gmail.com", "123456789", "Garden Point"));
        });
    }
        /*Test get empty database*/
    @Test
    void TestGetEmptyDatabase() throws UserException {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
          userdata.get("1");
        });
    }


}

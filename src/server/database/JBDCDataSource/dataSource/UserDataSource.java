package server.database.JBDCDataSource.dataSource;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDataSource {
    void createUser(User user);
    void editUser(int user_id, User user);
    void deleteUser(int user_id);
    User getUser(String username);
    ArrayList<User> getAllUsers();
}

package server.database.mockDatabase;

import server.database.mockDatabase.entity.User;
import server.database.mockDatabase.mockInterface.UserDataSource;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserData implements UserDataSource {
    ArrayList<User> users;
    int id;

    UserData(){
        users = new ArrayList<>();
        id = 0;
    }

    @Override
    public boolean create(User user) {
        try {
            user.setUserID(Integer.toString(id));
            users.add(user);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean edit(User User) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ArrayList<User> get(String id) {
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}

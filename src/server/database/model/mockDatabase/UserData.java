package server.database.model.mockDatabase;

import server.database.model.entity.User;
import server.database.model.mockInterface.UserDataSource;

import java.util.ArrayList;

public class UserData implements UserDataSource {
    ArrayList<User> users;
    int id;

    public UserData(){
        users = new ArrayList<>();
        id = 0;
    }

    @Override
    public boolean create(User user) {
        try {
            id++;
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
        User user = users.get(Integer.parseInt(id));
        ArrayList<User> tempUser = new ArrayList<>();
        tempUser.add(user);
        return tempUser;
    }

    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        users.clear();
        id = 0;
        return true;
    }
}

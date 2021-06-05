package client.model.mockDatabase;

import client.model.entity.User;
import client.model.mockInterface.UserDataSource;

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
    public boolean edit(User user) {
        try {
            int userID = Integer.parseInt(user.getUserID())-1;
            User prevUser = users.get(userID);
            if (user.getUsername() == null){
                user.setUsername(prevUser.getUsername());
            }
            if (user.getAddress() == null){
                user.setAddress(prevUser.getAddress());
            }
            if (user.getEmail()==null){
                user.setEmail(prevUser.getEmail());
            }
            if (user.getOrganisationID()== null){
                user.setOrganisationID(prevUser.getOrganisationID());
            }
            if (user.getAccountType() == null){
                user.setAccountType(prevUser.getAccountType());
            }
            if (user.getPassword() == null){
                user.setPassword(prevUser.getPassword());
            }
            if (user.getPhoneNum() == null){
                user.setPhoneNum(prevUser.getPhoneNum());
            }
            users.set(userID, user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws IndexOutOfBoundsException{
        try {

            users.remove(Integer.parseInt(id)-1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<User> get(String id) {
        User user = users.get(Integer.parseInt(id)-1);
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

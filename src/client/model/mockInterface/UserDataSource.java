package client.model.mockInterface;

import client.model.entity.User;
import client.model.exception.UserException;

import java.util.ArrayList;


public interface UserDataSource {
    boolean create(User User) throws UserException;
    boolean edit(User User) throws UserException;
    boolean delete(String id);
    ArrayList<User> get(String id);
    ArrayList<User> getAll();
    boolean deleteAll();
}

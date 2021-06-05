package server.database.model.mockInterface;

import server.database.model.entity.User;
import java.util.ArrayList;


public interface UserDataSource {
    boolean create(User User);
    boolean edit(User User);
    boolean delete(String id);
    ArrayList<User> get(String id);
    ArrayList<User> getAll();
    boolean deleteAll();
}

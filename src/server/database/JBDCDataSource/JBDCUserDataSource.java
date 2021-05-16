package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.dataSource.AccountType;
import server.database.JBDCDataSource.dataSource.User;
import server.database.JBDCDataSource.dataSource.UserDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class JBDCUserDataSource implements UserDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `user_id` INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" + // from https://stackoverflow.com/a/41028314
                    "  `username` VARCHAR(50),\n" +
                    "  `password` VARCHAR(255),\n" +
                    "  `account_type` ENUM('admin',leader','member'),\n" +
                    "  `organisation_id` INT(11),\n" +
                    "  PRIMARY KEY (`user_id`)\n" +
                    ");";

    private static final String CREATE_USER = "REPLACE INTO user (username, password, account_type) VALUES (?, ?, ?);";

    private static final String EDIT_USER = "UPDATE user SET username = ?, password = ?, account_type = ? WHERE user_id = ?";

    private static final String DELETE_USER = "DELETE FROM user WHERE user_id=?";

    private static final String GET_USER = "SELECT * FROM address WHERE user_id=?";

    private static final String GET_ALL_USERS = "SELECT * FROM address user";

    private Connection connection;

    private PreparedStatement createUser;

    private PreparedStatement editUser;

    private PreparedStatement deleteUser;

    private PreparedStatement getUser;

    private PreparedStatement getAllUsers;


    public JBDCUserDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            createUser = connection.prepareStatement(CREATE_USER);
            editUser = connection.prepareStatement(EDIT_USER);
            deleteUser = connection.prepareStatement(DELETE_USER);
            getUser = connection.prepareStatement(GET_USER);
            getAllUsers = connection.prepareStatement(GET_ALL_USERS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createUser(User user) {
        try {
            createUser.setString(1, user.getUsername());
            createUser.setString(2, user.getPassword());
            createUser.setString(3, user.getAccountType().name());
            createUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void editUser(int user_id, User user) {
        try {
            editUser.setString(1, user.getUsername());
            editUser.setString(2, user.getPassword());
            editUser.setString(3, user.getAccountType().name());
            editUser.setInt(4, user_id);
            editUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int user_id) {
        try {
            getUser.setInt(1, user_id);
            deleteUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User getUser(String username) {
        User user = new User();
        ResultSet rs = null;
        try {
            getUser.setString(1, username);
            int index = 0;
            rs = getUser.executeQuery();
            if(rs.next()){
                user.setUsername(rs.getString("user_id"));
                user.setPassword(rs.getString("password"));
                user.setAccountType(AccountType.valueOf(rs.getString("account_type")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<HashMap<String, User>> users = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getUser.executeQuery();
            int index = 0;
            rs.next();
            while(rs.next()){
                users.add(new HashMap<>());
                users.get(index).put("user_id" , AccountType.valueof());
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.Entity.Enum.AccountType;
import server.database.JBDCDataSource.Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class JBDCUserDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE `user` (\n" +
                    "  `user_id`  INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" + // from https://stackoverflow.com/a/41028314
                    "  `organisation_id` INTEGER,\n" +
                    "  `username` VARCHAR(50) NOT NULL,\n" +
                    "  `password` VARCHAR(255) NOT NULL,\n" +
                    "  `salt` VARCHAR(255) NOT NULL,\n" +
                    "  `account_type` ENUM('admin',leader','member') NOT NULL,\n" +
                    "  `email` VARCHAR(255) NOT NULL,\n" +
                    "  `phone` INT(10) NOT NULL,\n" +
                    "  `address` VARCHAR(255) NOT NULL,\n" +
                    "  PRIMARY KEY (`user_id`) NOT NULL,\n" +
                    "  UNIQUE KEY (user_name) NOT NULL,\n" +
                    "  FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id))\n" +
                    ");";

    private static final String CREATE_USER = "REPLACE INTO user (organisation_id, username, password, salt, account_type, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String EDIT_USER = "UPDATE user SET organisation_id = ?, username = ?, password = ?, salt = ?, account_type = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";

    private static final String DELETE_USER = "DELETE FROM user WHERE user_id=?";

    private static final String GET_USER = "SELECT * FROM user WHERE user_id=?";

    private static final String GET_ALL_USERS = "SELECT * FROM user";

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

    public void create(User user) {
        // "REPLACE INTO user (organisation_id, username, password, salt, account_type, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            createUser.setInt(1, user.getOrganisationID());
            createUser.setString(2, user.getUsername());
            createUser.setString(3, user.getPassword());
            createUser.setString(4, user.getSalt());
            createUser.setString(5, user.getAccountType().name());
            createUser.setString(6, user.getEmail());
            createUser.setInt(7, user.getPhoneNum());
            createUser.setString(8, user.getPassword());
            createUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void edit(int user_id, User user) {
        // "UPDATE user SET organisation_id = ?, username = ?, password = ?, salt = ?, account_type = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";
        try {
            editUser.setInt(1, user.getOrganisationID());
            editUser.setString(2, user.getUsername());
            editUser.setString(3, user.getPassword());
            editUser.setString(4, user.getSalt());
            editUser.setString(5, user.getAccountType().name());
            editUser.setString(6, user.getEmail());
            editUser.setInt(7, user.getPhoneNum());
            editUser.setString(8, user.getPassword());
            editUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int user_id) {
        try {
            getUser.setInt(1, user_id);
            deleteUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public User get(String username) {
        User user = new User();
        ResultSet rs = null;
        try {
            getUser.setString(1, username);
            int index = 0;
            rs = getUser.executeQuery();
            if(rs.next()){
                user.setOrganisationID(rs.getInt("organisation_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setAccountType(AccountType.valueOf(rs.getString("account_type")));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum((rs.getInt("phone_number")));
                user.setAddress(rs.getString("address"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public ArrayList<User> getAll() {
        ArrayList<User>  users = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getUser.executeQuery();;
            rs.next();
            while(rs.next()){
                User user = new User();
                user.setOrganisationID(rs.getInt("organisation_id"));
                user.setUsername(rs.getString("user_id"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setAccountType(AccountType.valueOf(rs.getString("account_type")));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum((rs.getInt("phone_number")));
                user.setAddress(rs.getString("address"));

                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }
}

package server.database.schema;

import client.model.entity.User;
import client.model.mockInterface.UserDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class JBDCUserDataSource implements UserDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `user_id`  INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" + // from https://stackoverflow.com/a/41028314
                    "  `organisation_id` INTEGER,\n" +
                    "  `username` VARCHAR(50) NOT NULL,\n" +
                    "  `password` VARCHAR(255) NOT NULL,\n" +
                    "  `account_type` ENUM('admin','member'),\n" +
                    "  `email` VARCHAR(255) NOT NULL,\n" +
                    "  `phone` INT(10) NOT NULL,\n" +
                    "  `address` VARCHAR(255) NOT NULL,\n" +
                    "  UNIQUE (`username`),\n" +
                    "  PRIMARY KEY (`user_id`),\n" +
                    "  FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`)\n" +
                    ");";

    private static final String CREATE_USER = "REPLACE INTO user (organisation_id, username, password, account_type, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String EDIT_USER = "UPDATE user SET organisation_id = ?, username = ?, password = ?, account_type = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";

    private static final String DELETE_USER = "DELETE FROM user WHERE user_id=?";

    private static final String GET_USER = "SELECT * FROM user WHERE username=?";

    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id=?";

    private static final String GET_ALL_USERS = "SELECT * FROM user";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM user";

    private static final String GET_NAMES = "SELECT username FROM user";

    private static final String DELETE_USERS = "DELETE FROM user";

    private Connection connection;

    private PreparedStatement create;

    private PreparedStatement edit;

    private PreparedStatement delete;

    private PreparedStatement get;

    private PreparedStatement getUserByID;

    private PreparedStatement getAll;

    private PreparedStatement deleteAll;

    private PreparedStatement rowCount;

    private PreparedStatement getNameList;

    public JBDCUserDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            create = connection.prepareStatement(CREATE_USER);
            edit = connection.prepareStatement(EDIT_USER);
            delete = connection.prepareStatement(DELETE_USER);
            get = connection.prepareStatement(GET_USER);
            getUserByID = connection.prepareStatement(GET_USER_BY_ID);
            getAll = connection.prepareStatement(GET_ALL_USERS);
            rowCount = connection.prepareStatement(COUNT_ROWS);
            getNameList = connection.prepareStatement(GET_NAMES);
            deleteAll = connection.prepareStatement(DELETE_USERS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean VerifyLogIn(String username_key, String password_key){
        ArrayList<User> users = get(username_key);

        if(users.size() == 0){
            return false;
        }
        else{
            String password = users.get(0).getPassword();

            if (password.equals(password_key))
            {
                return true;
            }
        }

        return false;
    }

    public boolean create(User user) {
        // "REPLACE INTO user (organisation_id, username, password, account_type, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            create.setInt(1, Integer.parseInt(user.getOrganisationID()));
            create.setString(2, user.getUsername());
            create.setString(3, user.getPassword());
            create.setString(4, user.getAccountType());
            create.setString(5, user.getEmail());
            create.setInt(6, Integer.parseInt(user.getPhoneNum()));
            create.setString(7, user.getAddress());

            int rowsCount = create.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean edit(User user) {
        // "UPDATE user SET organisation_id = ?, username = ?, password = ?, account_type = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";

        ArrayList<User> users = getById(user.getUserID());

        User prevUser = users.get(0);
        if (user.getUsername().isEmpty()){
            user.setUsername(prevUser.getUsername());
        }
        if (user.getAddress().isEmpty()){
            user.setAddress(prevUser.getAddress());
        }
        if (user.getEmail().isEmpty()){
            user.setEmail(prevUser.getEmail());
        }
        if (user.getOrganisationID().isEmpty()){
            user.setOrganisationID(prevUser.getOrganisationID());
        }
        if (user.getAccountType().isEmpty()){
            user.setAccountType(prevUser.getAccountType());
        }
        if (user.getPassword().isEmpty()){
            user.setPassword(prevUser.getPassword());
        }
        if (user.getPhoneNum().isEmpty()){
            user.setPhoneNum(prevUser.getPhoneNum());
        }

        if(users.size() == 0){
            return false;
        }
        else{
            try {
                edit.setInt(1, Integer.parseInt(user.getOrganisationID()));
                edit.setString(2, user.getUsername());
                edit.setString(3, user.getPassword());
                edit.setString(4, user.getAccountType());
                edit.setString(5, user.getEmail());
                edit.setInt(6, Integer.parseInt(user.getPhoneNum()));
                edit.setString(7, user.getAddress());
                edit.setInt(8, Integer.parseInt(user.getUserID()));

                int rowsCount = edit.executeUpdate();
                return (rowsCount>0);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    public boolean delete(String user_id) {
        try {
            get.setInt(1,  Integer.parseInt(user_id));
            int rowsCount = delete.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> get(String username) {
        ArrayList<User>  users = new ArrayList<>();
        ResultSet rs = null;
        try {
            get.setString(1, username);
            int index = 0;
            rs = get.executeQuery();

            if(rs.next()){
                User user = new User();
                user.setUserID(rs.getString("user_id"));
                user.setOrganisationID(rs.getString("organisation_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAccountType(rs.getString("account_type"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum((rs.getString("phone")));
                user.setAddress(rs.getString("address"));

                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> getById(String id) {
        ArrayList<User>  users = new ArrayList<>();
        ResultSet rs = null;
        try {
            getUserByID.setInt(1, Integer.parseInt(id));
            int index = 0;
            rs = getUserByID.executeQuery();

            if(rs.next()){
                User user = new User();
                user.setUserID(rs.getString("user_id"));
                user.setOrganisationID(rs.getString("organisation_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAccountType(rs.getString("account_type"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum((rs.getString("phone")));
                user.setAddress(rs.getString("address"));

                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> getAll() {
        ArrayList<User>  users = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getAll.executeQuery();;

            while(rs.next()){
                User user = new User();
                user.setUserID(rs.getString("user_id"));
                user.setOrganisationID(rs.getString("organisation_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAccountType(rs.getString("account_type"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum((rs.getString("phone")));
                user.setAddress(rs.getString("address"));

                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean deleteAll() {
        try {
            int rowsCount = deleteAll.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getSize() {
        ResultSet rs = null;
        int rows = 0;

        /* BEGIN MISSING CODE */
        try {
            rs = rowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /* END MISSING CODE */

        return rows;
    }

    public Set<String> nameSet() {
        Set<String> names = new TreeSet<String>();
        ResultSet rs = null;

        /* BEGIN MISSING CODE */
        try {
            rs = getNameList.executeQuery();
            while (rs.next()) {
                names.add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /* END MISSING CODE */

        return names;
    }
}

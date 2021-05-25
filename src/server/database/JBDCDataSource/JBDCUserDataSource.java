package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.Entity.Enum.AccountType;
import server.database.JBDCDataSource.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class JBDCUserDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `user_id`  INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" + // from https://stackoverflow.com/a/41028314
                    "  `organisation_id` INTEGER,\n" +
                    "  `username` VARCHAR(50) NOT NULL,\n" +
                    "  `password` VARCHAR(255) NOT NULL,\n" +
                    "  `salt` VARCHAR(255) NOT NULL,\n" +
                    "  `account_type` ENUM('admin','leader','member'),\n" +
                    "  `email` VARCHAR(255) NOT NULL,\n" +
                    "  `phone` INT(10) NOT NULL,\n" +
                    "  `address` VARCHAR(255) NOT NULL,\n" +
                    "  PRIMARY KEY (`user_id`)\n" +
//                    "  PRIMARY KEY (`user_id`),\n" +
//                    "  FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id)\n" +
                    ");";

    private static final String CREATE_USER = "REPLACE INTO user (organisation_id, username, password, salt, account_type, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String EDIT_USER = "UPDATE user SET organisation_id = ?, username = ?, password = ?, salt = ?, account_type = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";

    private static final String DELETE_USER = "DELETE FROM user WHERE user_id=?";

    private static final String GET_USER = "SELECT * FROM user WHERE username=?";

    private static final String GET_ALL_USERS = "SELECT * FROM user";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM user";

    private static final String GET_NAMES = "SELECT username FROM user";

    private Connection connection;

    private PreparedStatement createUser;

    private PreparedStatement editUser;

    private PreparedStatement deleteUser;

    private PreparedStatement getUser;

    private PreparedStatement getAllUsers;

    private PreparedStatement rowCount;

    private PreparedStatement getNameList;

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
            rowCount = connection.prepareStatement(COUNT_ROWS);
            getNameList = connection.prepareStatement(GET_NAMES);

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

    public void create(User user) {
        // "REPLACE INTO user (organisation_id, username, password, salt, account_type, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            createUser.setInt(1, Integer.parseInt(user.getOrganisationID()));
            createUser.setString(2, user.getUsername());
            createUser.setString(3, user.getPassword());
            createUser.setString(4, user.getSalt());
            createUser.setString(5, user.getAccountType());
            createUser.setString(6, user.getEmail());
            createUser.setInt(7, Integer.parseInt(user.getPhoneNum()));
            createUser.setString(8, user.getPassword());
            createUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void edit(User user) {
        // "UPDATE user SET organisation_id = ?, username = ?, password = ?, salt = ?, account_type = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";
        try {
            editUser.setInt(1, Integer.parseInt(user.getOrganisationID()));
            editUser.setString(2, user.getUsername());
            editUser.setString(3, user.getPassword());
            editUser.setString(4, user.getSalt());
            editUser.setString(5, user.getAccountType());
            editUser.setString(6, user.getEmail());
            editUser.setInt(7, Integer.parseInt(user.getPhoneNum()));
            editUser.setString(8, user.getAddress());
            editUser.setInt(9, Integer.parseInt(user.getUserID()));
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

    public ArrayList<User> get(String username) {
        ArrayList<User>  users = new ArrayList<>();
        ResultSet rs = null;
        try {
            getUser.setString(1, username);
            int index = 0;
            rs = getUser.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setOrganisationID(rs.getString("organisation_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
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
            rs = getUser.executeQuery();;
            rs.next();
            while(rs.next()){
                User user = new User();
                user.setOrganisationID(rs.getString("organisation_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
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

package server.database.schema;

import client.model.entity.Organisation;
import client.model.mockInterface.OrganisationDataSource;

import java.sql.*;
import java.util.ArrayList;

public class JBDCOrganisationDataSource implements OrganisationDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `organisation` (\n" +
                    "  `organisation_id` INTEGER  /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `organisation_name` VARCHAR(100),\n" +
                    "  `credits` INTEGER ,\n" +
                    "  UNIQUE (`organisation_name`),\n" +
                    "  PRIMARY KEY (`organisation_id`)\n" +
                    ");";


    private static final String CREATE_ORGANISATION = "REPLACE INTO organisation (organisation_name, credits) VALUES (?, ?);";

    private static final String EDIT_ORGANISATION = "UPDATE organisation SET organisation_name = ?, credits = ? WHERE organisation_id=? = ?";

    private static final String DELETE_ORGANISATION = "DELETE FROM organisation WHERE organisation_id=?";

    private static final String GET_ORGANISATION = "SELECT * FROM organisation WHERE organisation_id=?";

    private static final String GET_ALL_ORGANISATIONS = "SELECT * FROM organisation";

    private static final String DELETE_ALL_ORGANISATIONS = "DELETE FROM organisation";

    private Connection connection;

    private PreparedStatement create;

    private PreparedStatement edit;

    private PreparedStatement delete;

    private PreparedStatement get;

    private PreparedStatement getAll;

    private PreparedStatement deleteAll;


    public JBDCOrganisationDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            create = connection.prepareStatement(CREATE_ORGANISATION);
            edit = connection.prepareStatement(EDIT_ORGANISATION);
            delete = connection.prepareStatement(DELETE_ORGANISATION);
            get = connection.prepareStatement(GET_ORGANISATION);
            getAll = connection.prepareStatement(GET_ALL_ORGANISATIONS);
            deleteAll = connection.prepareStatement(DELETE_ALL_ORGANISATIONS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(Organisation organisation) {
        try {
            create.setString(1, organisation.getOrganisationName());
            create.setInt(2, Integer.parseInt(organisation.getCredits()));

            int rowsCount = create.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean edit(Organisation organisation) {
        try {
            edit.setString(1, organisation.getOrganisationName());
            edit.setInt(2, Integer.parseInt(organisation.getCredits()));
            edit.setInt(3, Integer.parseInt(organisation.getOrganisationID()));
            int rowsCount = edit.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(String organisation_id) {
        try {
            delete.setInt(1, Integer.parseInt(organisation_id));
            int rowsCount = delete.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Organisation> get(String organisation_id) {
        ArrayList<Organisation>  organisations = new ArrayList<>();
        ResultSet rs = null;
        try {
            get.setInt(1,  Integer.parseInt(organisation_id));
            int index = 0;
            rs = get.executeQuery();
            if(rs.next()){
                Organisation organisation = new Organisation();
                organisation.setOrganisationID(rs.getString("organisation_id"));
                organisation.setOrganisationName(rs.getString("organisation_name"));
                organisation.setCredits(rs.getString("credits"));
                organisations.add(organisation);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return organisations;
    }

    public ArrayList<Organisation> getAll() {
        ArrayList<Organisation>  organisations = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getAll.executeQuery();
            int index = 0;

            while(rs.next()){
                Organisation organisation = new Organisation();
                organisation.setOrganisationID(rs.getString("organisation_id"));
                organisation.setOrganisationName(rs.getString("organisation_name"));
                organisation.setCredits(rs.getString("credits"));
                organisations.add(organisation);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return organisations;
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


}

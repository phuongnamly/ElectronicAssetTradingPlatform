package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.Entity.Organisation;

import java.sql.*;
import java.util.ArrayList;

public class JBDCOrganisationDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `organisation` (\n" +
                    "  `organisation_id` INTEGER  /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `organisation_name` VARCHAR(100),\n" +
                    "  `credits` INTEGER ,\n" +
                    "  PRIMARY KEY (`organisation_id`)\n" +
                    ");";


    private static final String CREATE_ORGANISATION = "REPLACE INTO organisation (organisation_name, credits) VALUES (?, ?);";

    private static final String EDIT_ORGANISATION = "UPDATE organisation SET organisation_name = ?, credits = ? WHERE organisation_id=? = ?";

    private static final String DELETE_ORGANISATION = "DELETE FROM organisation WHERE organisation_id=?";

    private static final String GET_ORGANISATION = "SELECT * FROM organisation WHERE organisation_id=?";

    private static final String GET_ALL_ORGANISATIONS = "SELECT * FROM organisation";

    private Connection connection;

    private PreparedStatement createOrganisation;

    private PreparedStatement editOrganisation;

    private PreparedStatement deleteOrganisation;

    private PreparedStatement getOrganisation;

    private PreparedStatement getAllOrganisations;


    public JBDCOrganisationDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            createOrganisation = connection.prepareStatement(CREATE_ORGANISATION);
            editOrganisation = connection.prepareStatement(EDIT_ORGANISATION);
            deleteOrganisation = connection.prepareStatement(DELETE_ORGANISATION);
            getOrganisation = connection.prepareStatement(GET_ORGANISATION);
            getAllOrganisations = connection.prepareStatement(GET_ALL_ORGANISATIONS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(Organisation organisation) {
        try {
            createOrganisation.setString(1, organisation.getOrganisationName());
            createOrganisation.setInt(2, Integer.parseInt(organisation.getCredits()));
            return createOrganisation.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public void edit(int organisation_id, Organisation organisation) {
        try {
            editOrganisation.setString(1, organisation.getOrganisationName());
            editOrganisation.setInt(2, Integer.parseInt(organisation.getCredits()));
            editOrganisation.setInt(3, organisation_id);
            editOrganisation.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean delete(String organisation_id) {
        try {
            deleteOrganisation.setInt(1, Integer.parseInt(organisation_id));
            deleteOrganisation.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Organisation> get(Integer organisation_id) {
        ArrayList<Organisation>  organisations = new ArrayList<>();
        ResultSet rs = null;
        try {
            getOrganisation.setInt(1, organisation_id);
            int index = 0;
            rs = getOrganisation.executeQuery();
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
            rs = getOrganisation.executeQuery();
            int index = 0;
            rs.next();
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
}

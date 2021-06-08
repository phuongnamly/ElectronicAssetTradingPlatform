package server.database.schema;

import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.entity.Listing;
import client.model.mockInterface.InventoryDataSource;

import java.sql.*;
import java.util.ArrayList;

public class JBDCInventoryDataSource implements InventoryDataSource {
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `inventory` (\n" +
                    "  `inventory_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `organisation_id` INTEGER,\n" +
                    "  `asset_id` INTEGER,\n" +
                    "  `quantity` INTEGER,\n" +
                    "  PRIMARY KEY (`inventory_id`),\n" +
                    "  FOREIGN KEY (`organisation_id`) REFERENCES `organisation`(`organisation_id`),\n" +
                    "  FOREIGN KEY (`asset_id`) REFERENCES `asset`(`asset_id`)\n" +
                    ");";

    private static final String CREATE = "REPLACE INTO inventory (organisation_id, asset_id, quantity) VALUES (?, ?, ?);";

    private static final String EDIT = "UPDATE inventory SET quantity = ? WHERE inventory_id = ?";

    private static final String DELETE = "DELETE FROM inventory WHERE inventory_id=?";

    private static final String GET = "SELECT * FROM inventory WHERE inventory_id=?";

    private static final String GET_ALL = "SELECT * FROM inventory";

    private static final String GET_ALL_BY_ORGANISATION_ID = "SELECT * FROM inventory WHERE organisation_id=?";

    private static final String DELETE_ALL = "DELETE FROM inventory";

    private static final String GET_ASSET_NAME = "SELECT * FROM inventory INNER JOIN asset ON inventory.asset_id = asset.asset_id";

    private Connection connection;

    private PreparedStatement create;

    private PreparedStatement edit;

    private PreparedStatement delete;

    private PreparedStatement get;

    private PreparedStatement getAll;

    private PreparedStatement getAllByOrganisationID;

    private PreparedStatement getAssetName;

    private PreparedStatement deleteAll;

    public JBDCInventoryDataSource(){
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            create = connection.prepareStatement(CREATE);
            edit = connection.prepareStatement(EDIT);
            delete = connection.prepareStatement(DELETE);
            get = connection.prepareStatement(GET);
            getAll = connection.prepareStatement(GET_ALL);
            getAllByOrganisationID = connection.prepareStatement(GET_ALL_BY_ORGANISATION_ID);
            getAssetName = connection.prepareStatement(GET_ALL_BY_ORGANISATION_ID);
            deleteAll = connection.prepareStatement(GET_ASSET_NAME);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean create(Inventory inventory) {
        try {
            create.setInt(1, Integer.parseInt(inventory.getOrganisationID()));
            create.setInt(2, Integer.parseInt(inventory.getAssetID()));
            create.setInt(3, Integer.parseInt(inventory.getQuantity()));
            int rowsCount = create.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(Inventory inventory) {
        try {
            edit.setInt(1, Integer.parseInt(inventory.getQuantity()));
            edit.setInt(5,  Integer.parseInt(inventory.getInventoryID()));
            int rowsCount = edit.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            delete.setInt(1, Integer.parseInt(id));
            int rowsCount = delete.executeUpdate();
            return (rowsCount>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Inventory> get(String id) {
        ArrayList<Inventory> inventories = new ArrayList<>();
        ResultSet rs = null;
        try {
            get.setString(1, id);
            int index = 0;
            rs = get.executeQuery();

            if(rs.next()){
                Inventory inventory = new Inventory();
                inventory.setInventoryID(rs.getString("inventory_id"));
                inventory.setOrganisationID(rs.getString("organisation_id"));
                inventory.setAssetID(rs.getString("asset_id"));
                inventory.setQuantity(rs.getString("quantity"));
                inventories.add(inventory);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inventories;
    }

    @Override
    public ArrayList<Inventory> getAll() {
        ArrayList<Inventory> inventories = new ArrayList<>();
        ResultSet rs = null;
        try {
            int index = 0;
            rs = getAll.executeQuery();

            while(rs.next()){
                Inventory inventory = new Inventory();
                inventory.setInventoryID(rs.getString("inventory_id"));
                inventory.setOrganisationID(rs.getString("organisation_id"));
                inventory.setAssetID(rs.getString("asset_id"));
                inventory.setQuantity(rs.getString("quantity"));
                inventories.add(inventory);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inventories;
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

    public ArrayList<Inventory> getAllbyOrganisationID(String organisation_id) {
        ArrayList<Inventory> inventories = new ArrayList<>();
        ResultSet rs = null;
        try {
            getAllByOrganisationID.setString(1, organisation_id);
            int index = 0;
            rs = getAllByOrganisationID.executeQuery();
            rs = getAssetName.executeQuery();

            while(rs.next()){
                Inventory inventory = new Inventory();
                inventory.setAssetID(rs.getString("asset_name"));
                inventory.setOrganisationID(rs.getString("asset_type"));
                inventory.setQuantity(rs.getString("quantity"));
                inventories.add(inventory);
                index++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inventories;
    }
}

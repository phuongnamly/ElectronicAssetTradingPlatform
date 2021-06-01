package server.database.JBDCDataSource;

import server.database.DBConnection;
import server.database.JBDCDataSource.Entity.Asset;
import server.database.JBDCDataSource.Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class JBDCAssetDataSource {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `asset` (\n" +
                    "  `asset_id` INTEGER /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE, \n" +
                    "  `asset_type` VARCHAR(100),\n" +
                    "  `asset_name` VARCHAR(100),\n" +
                    "  PRIMARY KEY (`asset_id`)\n" +
                    ");";


    private static final String CREATE_ASSET = "REPLACE INTO asset (asset_type, asset_name) VALUES (?,?);";

    private static final String EDIT_ASSET = "UPDATE asset SET asset_type = ?, asset_name = ? WHERE asset_id = ?";

    private static final String DELETE_ASSET = "DELETE FROM asset WHERE asset_id=?";

    private static final String GET_ASSET = "SELECT * FROM asset WHERE asset_id=?";

    private static final String GET_ALL_ASSETS = "SELECT * FROM asset";

    private Connection connection;

    private PreparedStatement createAsset;

    private PreparedStatement editAsset;

    private PreparedStatement deleteAsset;

    private PreparedStatement getAsset;

    private PreparedStatement getAllAssets;


    public JBDCAssetDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            createAsset = connection.prepareStatement(CREATE_ASSET);
            editAsset = connection.prepareStatement(EDIT_ASSET);
            deleteAsset = connection.prepareStatement(DELETE_ASSET);
            getAsset = connection.prepareStatement(GET_ASSET);
            getAllAssets = connection.prepareStatement(GET_ALL_ASSETS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void create(Asset asset) {
        try {
            createAsset.setString(1, asset.getAssetType());
            createAsset.setString(2, asset.getAssetName());
            createAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void edit(Asset asset) {
        try {
            editAsset.setString(1, asset.getAssetType());
            editAsset.setString(2, asset.getAssetName());
            editAsset.setInt(3, Integer.parseInt(asset.getAssetID()));
            editAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String asset_id) {
        try {
            deleteAsset.setInt(1, Integer.parseInt(asset_id));
            deleteAsset.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Asset get(int asset_id) {
        Asset asset = new Asset();
        ResultSet rs = null;
        try {
            getAsset.setInt(1, asset_id);

            rs = getAsset.executeQuery();
            if(rs.next()){
                asset.setAssetID(rs.getString("asset_id"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setAssetName(rs.getString("asset_name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return asset;
    }

    public ArrayList<Asset> getAll() {
        ArrayList<Asset>  assets = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = getAsset.executeQuery();
            rs.next();
            while(rs.next()){
                Asset asset = new Asset();
                asset.setAssetID(rs.getString("asset_id"));
                asset.setAssetType(rs.getString("asset_type"));
                asset.setAssetName(rs.getString("asset_name"));
                assets.add(asset);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assets;
    }
}

package server.database.JBDCDataSource.Entity;

public class Asset {
    private int assetID;
    private String assetType;
    private String assetName;

    public Asset(){

    }

    public Asset(int assetID, String assetType, String assetName){
        this.assetID = assetID;
        this.assetType = assetType;
        this.assetName = assetName;
    }

    /**
     * @return the asset ID
     */
    public Integer getAssetID() { return assetID; }

    /**
     * @param assetID the asset ID to set
     */
    public void setAssetID(Integer assetID) { this.assetID = assetID; }

    /**
     * @return the asset type
     */
    public String getAssetType() { return assetType; }

    /**
     * @param assetType the asset type to set
     */
    public void setAssetType(String assetType) { this.assetType = assetType; }

    /**
     * @return the asset name
     */
    public String getAssetName() { return assetName; }

    /**
     * @param assetName the asset name to set
     */
    public void setAssetName(String assetName) { this.assetName = assetName; }
}

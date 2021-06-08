package client.model.entity;

import java.io.Serializable;


/**
 * This class is the asset class with simple get/set methods
 * */
public class Asset implements Serializable {
    private static final long serialVersionUID = 332082608397623483L;
    private String assetID;
    private String assetType;
    private String assetName;

    public Asset(){

    }
    public Asset(String assetID, String assetType, String assetName){
        this.assetID = assetID;
        this.assetType = assetType;
        this.assetName = assetName;
    }

    /**
     * @return the asset ID
     */
    public String getAssetID() { return assetID; }

    /**
     * @param assetID the asset ID to set
     */
    public void setAssetID(String assetID) { this.assetID = assetID; }

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
    public  String toStringAllFields(){
        return this.assetID+this.assetType+this.assetName;
    }

    @Override
    public String toString() {
        return this.assetName;
    }
}

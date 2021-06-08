package client.model.entity;

import java.io.Serializable;
/**
 * This class is the inventory class with simple get/set methods
 * */
public class Inventory implements Serializable {
    private static final long serialVersionUID = 332082608397623483L;
    private String inventoryID;
    private String organisationID;
    private String assetID;
    private String quantity;

    public Inventory(){

    }
    
    public Inventory(String inventoryID, String organisationID, String assetID, String quantity){
        this.inventoryID = inventoryID;
        this.organisationID = organisationID;
        this.assetID = assetID;
        this.quantity = quantity;
    }

    /**
     * @return the inventory ID
     */
    public String getInventoryID() { return inventoryID; }

    /**
     * @param inventoryID the inventory ID to set
     */
    public void setInventoryID(String inventoryID) { this.inventoryID = inventoryID; }

    /**
     * @return the organisation ID
     */
    public String getOrganisationID() { return organisationID; }

    /**
     * @param organisationID the organisation ID to set
     */
    public void setOrganisationID(String organisationID) { this.organisationID = organisationID; }

    /**
     * @return the asset ID
     */
    public String getAssetID() { return assetID; }

    /**
     * @param assetID the asset ID to set
     */
    public void setAssetID(String assetID) { this.assetID = assetID; }

    /**
     * @return the quantity
     */
    public String getQuantity() { return quantity; }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public  String toStringAllFields(){
        return this.inventoryID+this.organisationID+this.assetID+inventoryID;
    }
}

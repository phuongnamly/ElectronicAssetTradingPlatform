package server.database.JBDCDataSource.Entity;

import java.util.List;

public class Listing {
    private int organisationID;
    private int assetID;
    private int quantity;
    private int price;

    public Listing(){

    }

    public Listing(int organisationID, int assetID, int quantity, int price){
        this.organisationID = organisationID;
        this.assetID = assetID;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the organisation ID
     */
    public Integer getOrganisationID() { return organisationID; }

    /**
     * @param organisationID the organisation ID to set
     */
    public void setOrganisationID(Integer organisationID) { this.organisationID = organisationID; }

    /**
     * @return the asset ID
     */
    public Integer getAssetID() { return assetID; }

    /**
     * @param assetID the asset ID to set
     */
    public void setAssetID(Integer assetID) { this.assetID = assetID; }

    /**
     * @return the quantity
     */
    public Integer getQuantity() { return quantity; }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    /**
     * @return the price
     */
    public Integer getPrice() { return price; }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) { this.price = price; }
}

package client.model.entity;

import java.io.Serializable;


/**
 * This class is the asset class with simple get/set methods
 * */
public class Asset implements Serializable {
    private static final long serialVersionUID = 332082608397623483L;
    private String assetID;
    private String assetType;
    private String assetName;;
    private boolean currentTrade;
    private String tradeType;
    private String quantity;
    private String price;
    private String date;


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


    /**
     * @return the trade status
     */
    public boolean getCurrentTrade() { return currentTrade; }

    /**
     * @param currentTrade the trade status
     */
    public void setCurrentTrade(boolean currentTrade) { this.currentTrade = currentTrade; }

    /**
     * @return the trade type
     */
    public String getTradeType() { return tradeType; }

    /**
     * @param tradeType the trade type to set
     */
    public void setTradeType(String tradeType) { this.tradeType = tradeType; }

    /**
     * @return the quantity
     */
    public String getQuantity() { return quantity; }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) { this.quantity = quantity; }

    /**
     * @return the price
     */
    public String getPrice() { return price; }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) { this.price = price; }

    /**
     * @return the trade date
     */
    public String getDate() { return date; }

    /**
     * @param date the trade date to set
     */
    public void setDate(String date) { this.date = date; }
    public  String toStringAllFields(){
        return this.assetID+this.assetType+this.assetName;
    }

    @Override
    public String toString() {
        return this.assetName;
    }
}

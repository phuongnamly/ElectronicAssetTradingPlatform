package client.model.entity;

import java.io.Serializable;

/**
 * This class is the listing class with simple get/set methods
 * */

public class Listing implements Serializable {
    private static final long serialVersionUID = 332082608397623483L;

    private String listingID;
    private String inventoryID;
    private String userID;
    private boolean currentTrade;
    private String tradeType;
    private String quantity;
    private String price;
    private String date;

    public Listing(){

    }

    public Listing(String listingID, String inventoryID, String userID, boolean currentTrade, String tradeType, String quantity, String price, String date){
        this.listingID = listingID;
        this.inventoryID = inventoryID;
        this.userID = userID;
        this.currentTrade = currentTrade;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    /**
     * @return the organisation ID
     */
    public String getInventoryID() { return inventoryID; }

    /**
     * @param inventoryID the organisation ID to set
     */
    public void setInventoryID(String inventoryID) { this.inventoryID = inventoryID; }

    /**
     * @return the listing ID
     */
    public String getListingID() { return listingID; }

    /**
     * @param listingID the listing ID to set
     */
    public void setListingID(String listingID) { this.listingID = listingID; }

    /**
     * @return the user ID
     */
    public String getUserID() { return userID; }

    /**
     * @param userID the user ID to set
     */
    public void setUserID(String userID) { this.userID = userID; }

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
//
////    /**
////     * @return the trade date
////     */
////    public String getDate() { return getDateTime(date); }
////
////    /**
////     * @param date the trade date to set
////     */
////    public void setDate(Date date) { this.date = date; }
////
//
//    // https://stackoverflow.com/questions/6516320/datetime-datatype-in-java
//    private String getDateTime(Date date) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");;
//        return dateFormat.format(date);
//    }
    public  String toStringAllFields(){
        return this.listingID+this.inventoryID +this.userID+this.currentTrade+this.tradeType+this.quantity+this.price+this.date;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

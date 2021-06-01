package oldCode;

import server.database.JBDCDataSource.Entity.Enum.TradeType;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Trade {
    private Integer listingID;
    private Integer userID;
    private TradeType tradeType;
    private boolean currentTrade;
    private Date date;

    public Trade(){

    }

    public Trade(Integer listingID, Integer userID, TradeType tradeType, boolean currentTrade, Date date){
        this.listingID = listingID;
        this.userID = userID;
        this.tradeType = tradeType;
        this.currentTrade = currentTrade;
        this.date = date;
    }

    /**
     * @return the listing ID
     */
    public Integer getListingID() { return listingID; }

    /**
     * @param listingID the listing ID to set
     */
    public void setListingID(Integer listingID) { this.listingID = listingID; }

    /**
     * @return the user ID
     */
    public Integer getUserID() { return userID; }

    /**
     * @param userID the user ID to set
     */
    public void setUserID(Integer userID) { this.userID = userID; }

    /**
     * @return the trade type
     */
    public TradeType getTradeType() { return tradeType; }

    /**
     * @param tradeType the trade type to set
     */
    public void setTradeType(TradeType tradeType) { this.tradeType = tradeType; }

    /**
     * @return the trade status
     */
    public boolean getCurrentTrade() { return currentTrade; }

    /**
     * @param currentTrade the trade status
     */
    public void setCurrentTrade(boolean currentTrade) { this.currentTrade = currentTrade; }

    /**
     * @return the trade date
     */
    public String getDate() { return getDateTime(date); }

    /**
     * @param date the trade date to set
     */
    public void setDate(Date date) { this.date = date; }

//    /**
//     * @return the trade date
//     */
//    public String getDate() { return getDateTime(date); }
//
//    /**
//     * @param date the trade date to set
//     */
//    public void setDate(Date date) { this.date = date; }
//

    // https://stackoverflow.com/questions/6516320/datetime-datatype-in-java
    private String getDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");;
        return dateFormat.format(date);
    }
}

package client.model.mockDatabase;

import client.model.entity.Listing;
//import client.model.entity.User;
import client.model.exception.ListingException;
import client.model.mockInterface.ListingDataSource;

import java.util.ArrayList;

public class ListingData implements ListingDataSource {
    ArrayList<Listing> listings;
    int id;

    public ListingData(){
        listings = new ArrayList<>();
        id = 0;
    }
    @Override
    public boolean create(Listing listing) throws ListingException {
        String func = "create";
        if (ListingException.validate(func, listing)){
            id++;
            listing.setUserID(Integer.toString(id));
            listings.add(listing);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean edit(Listing listing) throws ListingException {
        String func = "editID";
        if (ListingException.validate(func, listing)){
            int listingID = Integer.parseInt(listing.getListingID())-1;
            Listing prevListing = listings.get(listingID);
//            if (listing.getCurrentTrade() == null){
//                listing.setCurrentTrade(prevListing.getCurrentTrade());
//            }
            if (listing.getTradeType() == null){
                listing.setTradeType(prevListing.getTradeType());
            }
            if (listing.getQuantity()==null){
                listing.setQuantity(prevListing.getQuantity());
            }
            if (listing.getInventoryID()== null){
                listing.setInventoryID(prevListing.getInventoryID());
            }
            if (listing.getUserID()== null){
                listing.setUserID(prevListing.getUserID());
            }
            if (listing.getPrice() == null){
                listing.setPrice(prevListing.getPrice());
            }
            if (listing.getDate() == null){
                listing.setDate(prevListing.getDate());
            }
            listings.set(listingID, listing);
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            listings.remove(Integer.parseInt(id)-1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Listing> get(String id) {
        Listing listing = listings.get(Integer.parseInt(id)-1);
        ArrayList<Listing> tempListing = new ArrayList<>();
        tempListing.add(listing);
        return tempListing;
    }

    @Override
    public ArrayList<Listing> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        listings.clear();
        id = 0;
        return true;
    }
}

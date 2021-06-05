package client.model.mockInterface;

import client.model.entity.Listing;

import java.util.ArrayList;

public interface ListingDataSource {
    boolean create(Listing listing);
    boolean edit(Listing listing);
    boolean delete(String id);
    ArrayList<Listing> get(String id);
    ArrayList<Listing> getAll();
    boolean deleteAll();
}

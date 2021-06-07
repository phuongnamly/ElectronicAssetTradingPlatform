package client.model.mockInterface;

import client.model.entity.Listing;
import client.model.exception.ListingException;

import java.util.ArrayList;

public interface ListingDataSource {
    boolean create(Listing listing) throws ListingException;
    boolean edit(Listing listing) throws ListingException;
    boolean delete(String id);
    ArrayList<Listing> get(String id);
    ArrayList<Listing> getAll();
    boolean deleteAll();
}

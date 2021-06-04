package server.database.mockDatabase.mockInterface;

import server.database.mockDatabase.entity.Listing;

import java.util.ArrayList;

public interface ListingDataSource {
    boolean create(Listing User);
    boolean edit(Listing User);
    boolean delete(String id);
    ArrayList<Listing> get(String id);
    ArrayList<Listing> getAll();
    boolean deleteAll();
}

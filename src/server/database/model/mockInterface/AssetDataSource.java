package server.database.model.mockInterface;

import server.database.model.entity.Asset;

import java.util.ArrayList;

public interface AssetDataSource {
    boolean create(Asset User);
    boolean edit(Asset User);
    boolean delete(String id);
    ArrayList<Asset> get(String id);
    ArrayList<Asset> getAll();
    boolean deleteAll();
}

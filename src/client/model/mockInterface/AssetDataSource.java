package client.model.mockInterface;

import client.model.entity.Asset;

import java.util.ArrayList;

public interface AssetDataSource {
    boolean create(Asset Asset);
    boolean edit(Asset Asset);
    boolean delete(String id);
    ArrayList<Asset> get(String id);
    ArrayList<Asset> getAll();
    boolean deleteAll();
}

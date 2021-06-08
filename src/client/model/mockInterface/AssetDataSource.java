package client.model.mockInterface;

import client.model.entity.Asset;
import client.model.exception.AssetException;

import java.util.ArrayList;

public interface AssetDataSource {
    boolean create(Asset Asset);
    boolean edit(Asset Asset) throws AssetException;
    boolean delete(String id);
    ArrayList<Asset> get(String id);
    ArrayList<Asset> getAll();
    boolean deleteAll();
}

package client.model.mockDatabase;

import client.model.entity.Asset;
import client.model.entity.Asset;
import client.model.exception.AssetException;
import client.model.mockInterface.AssetDataSource;

import java.util.ArrayList;

public class AssetData implements AssetDataSource {
    ArrayList<Asset> assets;
    int id;

    public AssetData(){
        assets = new ArrayList<>();
        id = 0;
    }


    @Override
    public boolean create(Asset asset) {
        try {
            id++;
            asset.setAssetID(Integer.toString(id));
            assets.add(asset);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean edit(Asset asset) throws AssetException {
        String func = "editID";
        if (AssetException.validate(func, asset)){
            int assetID = Integer.parseInt(asset.getAssetID())-1;
            Asset prevAsset = assets.get(assetID);
            if (asset.getAssetName() == null){
                asset.setAssetName(prevAsset.getAssetName());
            }
            if (asset.getAssetType() == null){
                asset.setAssetType(prevAsset.getAssetType());
            }
            assets.set(assetID, asset);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            assets.remove(Integer.parseInt(id)-1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Asset> get(String id) {
        Asset asset = assets.get(Integer.parseInt(id)-1);
        ArrayList<Asset> tempAsset = new ArrayList<>();
        tempAsset.add(asset);
        return tempAsset;
    }

    @Override
    public ArrayList<Asset> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        assets.clear();
        id = 0;
        return true;
    }
}

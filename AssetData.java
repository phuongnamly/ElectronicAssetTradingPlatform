package client.model.mockDatabase;

import client.model.entity.Asset;
import client.model.exception.AssetException;
import client.model.mockInterface.AssetDataSource;

///Detailed Simple AssetData Mock Database AssetData.Java to be integrated with Nam's code
//
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

            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean edit(Asset testData) throws AssetException {
        String func = "editID";
        if (AssetException.validate(func, testData)){
            int assetID = Integer.parseInt(testData.getAssetID())-1;
            Asset prevAsset = assets.get(assetID);
            //If test.Data getting AssetName
            if (testData.getAssetName() == null){
                testData.setAssetName(prevAsset.getAssetName());
            }
            else if (testData.getAssetType() == null){
                testData.setAssetType(prevAsset.getAssetType());
            }
            assets.set(assetID, testData);
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
        Asset assetTester = assets.get(Integer.parseInt(id)-1);
        ArrayList<Asset> tempAsset = new ArrayList<>();
        tempAsset.add(assetTester);
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
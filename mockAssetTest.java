package client.model.UnitTest.mockTest;


import client.model.entity.Asset;
import client.model.exception.AssetException;
import client.model.mockDatabase.AssetData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class mockAssetTest {
    AssetData testAssetSourcedata;

    @BeforeEach
    void setup() {
        testAssetSourcedata = new AssetData();
    }

    @AfterEach
    void tearDown() {
        testAssetSourcedata.deleteAll();
    }

  //Testing get asset with throws assetexception

    @Test
    void TestGetAssetFromGetAsset() throws AssetException {
        Asset asset_set = new Asset("1","Thomas Company", "13513");

        testAssetSourcedata.create(asset_set);
        ArrayList<Asset> asset_get = testAssetSourcedata.get("1");
        assertEquals(asset_set.toString(), asset_get.get(0).toString());
    }

    //    Test create asset
    @Test
    void TestBooleanCreateAsset() throws AssetException {
        assertTrue(testAssetSourcedata.create(new Asset("1","Jason Company", "100530")));
    }

    @Test
    void TestGetAssetFromCreateAsset() throws AssetException {
        Asset asset_set = new Asset("1","Thomas Company", "100000");
        testAssetSourcedata.create(asset_set);
        ArrayList<Asset> asset_get = testAssetSourcedata.get("1");
        assertEquals(asset_set.toString(), asset_get.get(0).toString());
    }

    @Test
    void TestBooleanAssetFromEditAsset() throws AssetException{
        testAssetSourcedata.create(new Asset("1","Jason Company", "100000"));
        assertTrue(testAssetSourcedata.edit(new Asset("1","Jason Company 2 ", null)));
    }

    @Test
    void TestGetAssetFromEditAsset() throws AssetException {
        Asset asset_set_orig = new Asset("1","Thomas Company", "100000");
        Asset asset_set_new = new Asset("1","Thomas Company 2 ", null);
        Asset asset_expect = new Asset("1","Thomas Company 2 ", "100000");
        testAssetSourcedata.create(asset_set_orig);
        testAssetSourcedata.edit(asset_set_new);
        //Removed arraylist?

        assertEquals(asset_get.get(0).toString(),asset_expect.toString());
    }

///TESTING TO SEES IF IT WORKS
    //PLEASE CHANGES LATER

    void TestBooleanAssetFromDeleteAsset() throws AssetException{
        testAssetSourcedata.create(new Asset("1","Thomas Company", "100000"));
        assertTrue(testAssetSourcedata.delete("1"));
    }


}
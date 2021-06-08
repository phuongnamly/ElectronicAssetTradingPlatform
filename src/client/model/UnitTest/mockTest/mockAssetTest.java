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

/**
 * This is the class for testing mock asset CRUD functions
 */
public class mockAssetTest {
    AssetData assetdata;

    @BeforeEach
    void setup() {
        assetdata = new AssetData();
    }

    @AfterEach
    void tearDown() {
        assetdata.deleteAll();
    }

    //    Test get asset

    @Test
    void TestGetAssetFromGetAsset() throws AssetException {
        Asset asset_set = new Asset("1","Thomas Company", "Stock");
        assetdata.create(asset_set);
        ArrayList<Asset> asset_get = assetdata.get("1");
        assertEquals(asset_set.toStringAllFields(), asset_get.get(0).toStringAllFields());
    }

    //    Test create asset
    @Test
    void TestBooleanCreateAsset() throws AssetException {
        assertTrue(assetdata.create(new Asset("1","Thomas Company", "100000")));
    }

    @Test
    void TestGetAssetFromCreateAsset() throws AssetException {
        Asset asset_set = new Asset("1","Thomas Company", "100000");
        assetdata.create(asset_set);
        ArrayList<Asset> asset_get = assetdata.get("1");
        assertEquals(asset_set.toStringAllFields(), asset_get.get(0).toStringAllFields());
    }
    //    Test Edit Asset
    @Test
    void TestBooleanAssetFromEditAsset() throws AssetException{
        assetdata.create(new Asset("1","Thomas Company", "100000"));
        assertTrue(assetdata.edit(new Asset("1","Thomas Company 2 ", null)));
    }

    @Test
    void TestGetAssetFromEditAsset() throws AssetException {
        Asset asset_set_orig = new Asset("1","Thomas Company", "100000");
        Asset asset_set_new = new Asset("1","Thomas Company 2 ", null);
        Asset asset_expect = new Asset("1","Thomas Company 2 ", "100000");
        assetdata.create(asset_set_orig);
        assetdata.edit(asset_set_new);
        ArrayList<Asset> asset_get = assetdata.get("1");

        assertEquals(asset_get.get(0).toStringAllFields(),asset_expect.toStringAllFields());
    }

    //    Test delete asset
    @Test
    void TestBooleanAssetFromDeleteAsset() throws AssetException{
        assetdata.create(new Asset("1","Thomas Company", "100000"));
        assertTrue(assetdata.delete("1"));
    }

//    @Test
//    void TestGetOrganisationFromDeleteOrganisation() throws OrganisationException, IndexOutOfBoundsException {
//        Organisation organisation_set_orig = new Organisation("1","Thomas Company", "100000");
////        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
////        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        organisationdata.create(organisation_set_orig);
//        organisationdata.delete("1");
////        userdata.edit(user_set_new);
////        ArrayList<User> user_get = userdata.get("1");
//
//        assertNull(organisationdata.get("1"));
//    }
}

package client.model.UnitTest.exceptionTest;

import client.model.entity.Asset;
import client.model.exception.AssetException;
import client.model.mockDatabase.AssetData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class mockAssetException {
    AssetData assetdata;

    @BeforeEach
    void setup() {
        assetdata = new AssetData();
    }
    @AfterEach
    void tearDown() {
        assetdata.deleteAll();
    }


    /*Test out of range id/ non integer/ non positive*/
    @Test
    void TestWrongAssetIDFormat() throws AssetException {
        Assertions.assertThrows(AssetException.class, () -> {
            assetdata.edit(new Asset(null,"Tesla","50000"));
//            organisationdata.create(new Organisation("12345673456789","Tesla","50000"));
//            organisationdata.create(new Organisation(null,"Tesla","50000"));
        });
    }
    /*Test get empty database*/
    @Test
    void TestGetEmptyDatabase() throws AssetException {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            assetdata.get("1");
        });
    }
}

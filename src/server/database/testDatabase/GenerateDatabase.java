package server.database.testDatabase;

import client.model.entity.*;
import client.model.exception.UserException;
import server.database.schema.JBDCAssetDataSource;
import server.database.schema.JBDCListingDataSource;
import server.database.schema.JBDCOrganisationDataSource;
import server.database.schema.JBDCUserDataSource;

import java.util.ArrayList;

import static utils.hash.Hash.getHashedPassword;

public class GenerateDatabase {
    static private JBDCUserDataSource userDatabase;
    static private JBDCOrganisationDataSource organisationDatabase;
    static private JBDCAssetDataSource assetDatabase;
    static private JBDCListingDataSource listingDatabase;

    public static void main(String[] args) throws UserException {
        organisationDatabase = new JBDCOrganisationDataSource();
        userDatabase = new JBDCUserDataSource();
        assetDatabase = new JBDCAssetDataSource();
        listingDatabase = new JBDCListingDataSource();

        TearDown();
        Setup();
    }

    public static void TearDown(){
        listingDatabase.deleteAll();
        assetDatabase.deleteAll();
        userDatabase.deleteAll();
        organisationDatabase.deleteAll();
    }

    public static void Setup() throws UserException {
        GenerateOrganisations();
        GenerateUsers();
        GenerateAssets();
        GenerateListings();
    }

    public static void GenerateOrganisations(){
        organisationDatabase.create(new Organisation("","Electronic Asset Trading Company", "0"));
        organisationDatabase.create(new Organisation("","Nam's Company", "25"));
        organisationDatabase.create(new Organisation("","Thomas's Company", "50"));
        organisationDatabase.create(new Organisation("","Jason's Company", "75"));
        organisationDatabase.create(new Organisation("","Selwyn's Company", "100"));
    }

    public static void GenerateUsers() throws UserException {
        ArrayList<Organisation> organisations = organisationDatabase.getAll();
        userDatabase.create(new User("",organisations.get(0).getOrganisationID(),"admin", getHashedPassword("admin"), "admin","admin@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(0).getOrganisationID(),"nam", getHashedPassword("nam"), "admin","nam@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(0).getOrganisationID(),"thomas", getHashedPassword("thomas"), "admin","thomas@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(0).getOrganisationID(),"jason", getHashedPassword("jason"), "admin","jason@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(0).getOrganisationID(),"selwyn", getHashedPassword("selwyn"), "admin","selwyn@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(1).getOrganisationID(),"elon", getHashedPassword("elon"), "member","elon@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(2).getOrganisationID(),"musk", getHashedPassword("musk"), "member","musk@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(3).getOrganisationID(),"bill", getHashedPassword("bill"), "member","bill@gmail.com", "0123456789", "Garden Point"));
        userDatabase.create(new User("",organisations.get(4).getOrganisationID(),"steve", getHashedPassword("steve"), "member","steve@gmail.com", "0123456789", "Garden Point"));
    }

    public static void GenerateAssets() {
        assetDatabase.create(new Asset("", "cryptocurrencies", "DogeCoin"));
        assetDatabase.create(new Asset("", "cryptocurrencies", "NamCoin"));
        assetDatabase.create(new Asset("", "cryptocurrencies", "ThomasCoin"));
        assetDatabase.create(new Asset("", "cryptocurrencies", "JasonCoin"));
        assetDatabase.create(new Asset("", "cryptocurrencies", "SelwynCoin"));

        assetDatabase.create(new Asset("", "cloud computing resources", "Microsoft Azure"));
        assetDatabase.create(new Asset("", "cloud computing resources", "AWS"));
        assetDatabase.create(new Asset("", "cloud computing resources", "QUT"));
        assetDatabase.create(new Asset("", "cloud computing resources", "Google Colab"));
        assetDatabase.create(new Asset("", "cloud computing resources", "IBM"));

        assetDatabase.create(new Asset("", "stock", "Stark Company"));
        assetDatabase.create(new Asset("", "stock", "Vodaphone"));
        assetDatabase.create(new Asset("", "stock", "Telstra"));
        assetDatabase.create(new Asset("", "stock", "Airbus"));
        assetDatabase.create(new Asset("", "stock", "Microsoft"));
    }

    private static void GenerateListings() {
//        ArrayList<User> users = userDatabase.getAll();
//        ArrayList<Asset> assets = assetDatabase.getAll();
//        listingDatabase.create(new Listing("",users.get(5).getOrganisationID(),users.get(5).getUserID(),assets.get(0).getAssetID(),true,"BUY","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(5).getOrganisationID(),users.get(5).getUserID(),assets.get(1).getAssetID(),true,"BUY","1","150","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(5).getOrganisationID(),users.get(5).getUserID(),assets.get(2).getAssetID(),true,"BUY","10","250","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(5).getOrganisationID(),users.get(5).getUserID(),assets.get(3).getAssetID(),true,"SELL","1","350","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(5).getOrganisationID(),users.get(5).getUserID(),assets.get(4).getAssetID(),true,"SELL","10","450","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(6).getOrganisationID(),users.get(6).getUserID(),assets.get(3).getAssetID(),true,"BUY","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(6).getOrganisationID(),users.get(6).getUserID(),assets.get(4).getAssetID(),true,"BUY","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(6).getOrganisationID(),users.get(6).getUserID(),assets.get(0).getAssetID(),true,"BUY","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(6).getOrganisationID(),users.get(6).getUserID(),assets.get(1).getAssetID(),true,"SELL","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(6).getOrganisationID(),users.get(6).getUserID(),assets.get(2).getAssetID(),true,"SELL","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(7).getOrganisationID(),users.get(7).getUserID(),assets.get(7).getAssetID(),true,"BUY","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(7).getOrganisationID(),users.get(7).getUserID(),assets.get(8).getAssetID(),true,"BUY","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(7).getOrganisationID(),users.get(7).getUserID(),assets.get(9).getAssetID(),true,"BUY","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(7).getOrganisationID(),users.get(7).getUserID(),assets.get(10).getAssetID(),true,"SELL","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(7).getOrganisationID(),users.get(7).getUserID(),assets.get(11).getAssetID(),true,"SELL","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(8).getOrganisationID(),users.get(8).getUserID(),assets.get(10).getAssetID(),true,"BUY","1","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(8).getOrganisationID(),users.get(8).getUserID(),assets.get(11).getAssetID(),true,"BUY","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(8).getOrganisationID(),users.get(8).getUserID(),assets.get(7).getAssetID(),true,"SELL","10","50","1000-01-01 00:00:00"));
//        listingDatabase.create(new Listing("",users.get(8).getOrganisationID(),users.get(8).getUserID(),assets.get(8).getAssetID(),true,"SELL","1","50","1000-01-01 00:00:00"));
    }
}

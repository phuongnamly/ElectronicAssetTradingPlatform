package client.model.UnitTest.mockTest;

import client.model.entity.Listing;
import client.model.exception.ListingException;
import client.model.mockDatabase.ListingData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the class for testing mock list CRUD functions
 */
public class mockListingTest {
    ListingData listingdata;

    @BeforeEach
    void setup() {
        listingdata = new ListingData();
    }

    @AfterEach
    void tearDown() {
        listingdata.deleteAll();
    }

//    @Test
//    void Test(){
//    }


    //    Test get Listing

    @Test
    void TestGetListingFromGetListing() throws ListingException {
        Listing listing_set = new Listing("1","2","3",true,"digital trade","100", "50000","6 June");
        listingdata.create(listing_set);
        ArrayList<Listing> listing_get = listingdata.get("1");
        assertEquals(listing_set.toStringAllFields(), listing_get.get(0).toStringAllFields());
    }

    //    Test create Listing
    @Test
    void TestBooleanCreateListing() throws ListingException {
        assertTrue(listingdata.create(new Listing("1","2","3",true,"digital trade","100", "50000","6 June")));
    }

    @Test
    void TestGetListingFromCreateListing() throws ListingException {
        Listing listing_set = new Listing("1","2","3",true,"digital trade","100", "50000","6 June");
        listingdata.create(listing_set);
        ArrayList<Listing> listing_get = listingdata.get("1");
        assertEquals(listing_set.toStringAllFields(), listing_get.get(0).toStringAllFields());
    }

    //    Test Edit Listing
    @Test
    void TestBooleanListingFromEditListing() throws ListingException{
        listingdata.create(new Listing("1","2","3",true,"digital trade","100", "50000","6 June"));
        assertTrue(listingdata.edit(new Listing("1",null,"3" ,false,"bit coin","100", "50000","7 June")));
    }

    @Test
    void TestGetListingFromEditListing() throws ListingException {
        Listing listing_set_orig = new Listing("1","2","3",true,"digital trade","100", "50000","6 June");
        Listing listing_set_new = new Listing("1",null,"3",false,"bit coin","100", "50000","7 June");
        Listing listing_expect = new Listing("1","2","3",false,"bit coin","100", "50000","7 June");
        listingdata.create(listing_set_orig);
        listingdata.edit(listing_set_new);
        ArrayList<Listing> listing_get = listingdata.get("1");

        assertEquals(listing_get.get(0).toStringAllFields(),listing_expect.toStringAllFields());
    }

    //    Test delete listing
    @Test
    void TestBooleanListingFromDeleteListing() throws ListingException{
        listingdata.create(new Listing("1",null,"3" ,false,"bit coin","100", "50000","7 June"));
        assertTrue(listingdata.delete("1"));
    }

//    @Test
//    void TestGetListingFromDeleteListing() throws ListingException, IndexOutOfBoundsException {
//        Listing listing_set_orig = new Listing("1",null,"3", null ,false,"bit coin","100", "50000","7 June");
////        User user_set_new = new User("1",null,"admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
////        User user_expect = new User("1","1","admin", "admin", "admin@gmail.com", "0123456789", "Garden Point", "admin");
//        listingdata.create(listing_set_orig);
//        listingdata.delete("1");
////        userdata.edit(user_set_new);
////        ArrayList<User> user_get = userdata.get("1");
//
//        assertNull(listingdata.get("1"));
//    }
}

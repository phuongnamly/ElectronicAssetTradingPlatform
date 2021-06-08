package client.model.UnitTest.exceptionTest;
import client.model.entity.Listing;
import client.model.exception.ListingException;
import client.model.mockDatabase.ListingData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is the class for testing mock listing exception
 */
public class mockListingException {
    ListingData listingdata;

    @BeforeEach
    void setup() {
        listingdata = new ListingData();
    }
    @AfterEach
    void tearDown() {
        listingdata.deleteAll();
    }

    /* Test out of range/ non integer price*/
    @Test
    void TestNonIntegerPrice() throws ListingException {
        Assertions.assertThrows(ListingException.class, () -> {
            listingdata.create(new Listing("1","2","3",true,"digital trade","100", "Hello","6 June"));
        });
    }
    /* Test out of range/ non integer quantity*/
    @Test
    void TestNonIntegerQuantity() throws ListingException {
        Assertions.assertThrows(ListingException.class, () -> {
            listingdata.create(new Listing("1","2","3",true,"digital trade","Hello", "1000","6 June"));
        });
    }
    /* Test out of range/ non integer organisationID*/
    @Test
    void TestNonIntegerOrganisationID() throws ListingException {
        Assertions.assertThrows(ListingException.class, () -> {
            listingdata.create(new Listing("1","Hello","3",true,"digital trade","100", "1000","6 June"));
        });
    }
    /* Test out of range/ non integer UserID*/
    @Test
    void TestNonIntegerUserID() throws ListingException {
        Assertions.assertThrows(ListingException.class, () -> {
            listingdata.create(new Listing("1","1","Hello",true,"digital trade","100", "1000","6 June"));
        });
    }

    /*Test out of range id/ non integer/ non positive*/
    @Test
    void TestWrongListingIDFormat() throws ListingException {
        Assertions.assertThrows(ListingException.class, () -> {
            listingdata.edit(new Listing("hello","2","3",true,"digital trade","100", "50000","6 June"));
//            organisationdata.create(new Organisation("12345673456789","Tesla","50000"));
//            organisationdata.create(new Organisation(null,"Tesla","50000"));
        });
    }
    /*Test get empty database*/
    @Test
    void TestGetEmptyDatabase() throws ListingException {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            listingdata.get("1");
        });
    }
}

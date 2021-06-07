package client.model.UnitTest.exceptionTest;

import client.model.entity.Organisation;
import client.model.entity.User;
import client.model.exception.OrganisationException;
import client.model.mockDatabase.OrganisationData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class mockOrganisationException {
    OrganisationData organisationdata;

    @BeforeEach
    void setup() {
        organisationdata = new OrganisationData();
    }
    @AfterEach
    void tearDown() {
        organisationdata.deleteAll();
    }

    /* Test out of range/ non integer phone number*/
    @Test
    void TestNonIntegerCredit() throws OrganisationException {
        Assertions.assertThrows(OrganisationException.class, () -> {
            organisationdata.create(new Organisation("1","Tesla","true"));
        });
    }

    /*Test out of range id/ non integer/ non positive*/
    @Test
    void TestWrongOrganisationIDFormat() throws OrganisationException {
        Assertions.assertThrows(OrganisationException.class, () -> {
            organisationdata.edit(new Organisation(null,"Tesla","50000"));
//            organisationdata.create(new Organisation("12345673456789","Tesla","50000"));
//            organisationdata.create(new Organisation(null,"Tesla","50000"));
        });
    }
    /*Test get empty database*/
    @Test
    void TestGetEmptyDatabase() throws OrganisationException {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            organisationdata.get("1");
        });
    }
}

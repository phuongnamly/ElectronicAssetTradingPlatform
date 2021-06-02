package server.database.testDatabase;

import org.junit.jupiter.api.Test;
import server.database.schema.DBConnection;

import static org.junit.jupiter.api.Assertions.*;

class TestDBConnection {
    @Test
    void testGetInstance(){
        assertNotNull(DBConnection.getInstance());
    }
}
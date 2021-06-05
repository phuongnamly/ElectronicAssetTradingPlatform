package client.model.mockInterface;

import client.model.entity.Organisation;
import java.util.ArrayList;

public interface OrganisationDataSource {
    boolean create(Organisation organisation);
    boolean edit(Organisation organisation);
    boolean delete(String id);
    ArrayList<Organisation> get(String id);
    ArrayList<Organisation> getAll();
    boolean deleteAll();
}

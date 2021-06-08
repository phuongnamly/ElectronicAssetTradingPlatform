package client.model.mockInterface;

import client.model.entity.Organisation;
import client.model.exception.OrganisationException;

import java.util.ArrayList;

public interface OrganisationDataSource {
    boolean create(Organisation organisation) throws OrganisationException;
    boolean edit(Organisation organisation) throws OrganisationException;
    boolean delete(String id);
    ArrayList<Organisation> get(String id);
    ArrayList<Organisation> getAll();
    boolean deleteAll();
}

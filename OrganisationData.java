package client.model.mockDatabase;

import client.model.entity.Organisation;
import client.model.exception.OrganisationException;
import client.model.exception.UserException;
import client.model.mockInterface.OrganisationDataSource;

import java.util.ArrayList;

public class OrganisationData implements OrganisationDataSource {
    ArrayList<Organisation> organisations;
    int id;

    public OrganisationData(){
        organisations = new ArrayList<>();
        id = 0;
    }

    @Override
    public boolean create(Organisation organisation) throws OrganisationException {
        String func = "create";
        if (OrganisationException.validate(func, organisation)){
            id++;
            organisation.setOrganisationID(Integer.toString(id));
            organisations.add(organisation);

            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean edit(Organisation organisation) throws OrganisationException {
        String func = "editID";
        if (OrganisationException.validate(func, organisation)){
            int organisationID = Integer.parseInt(organisation.getOrganisationID())-1;
            Organisation prevOrganisation = organisations.get(organisationID);
            if (organisation.getOrganisationName() == null){
                organisation.setOrganisationName(prevOrganisation.getOrganisationName());
            }
            if (organisation.getCredits() == null){
                organisation.setCredits(prevOrganisation.getCredits());
            }
            organisations.set(organisationID, organisation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {

            organisations.remove(Integer.parseInt(id)-1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Organisation> get(String id) {
        Organisation organisation = organisations.get(Integer.parseInt(id)-1);
        ArrayList<Organisation> tempOrganisation = new ArrayList<>();
        tempOrganisation.add(organisation);
        return tempOrganisation;
    }

    @Override
    public ArrayList<Organisation> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        organisations.clear();
        id = 0;
        return true;
    }
}

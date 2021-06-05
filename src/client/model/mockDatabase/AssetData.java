package client.model.mockDatabase;

import client.model.entity.Asset;
import client.model.entity.Organisation;
import client.model.mockInterface.AssetDataSource;

import java.util.ArrayList;

public class AssetData implements AssetDataSource {
    ArrayList<Asset> assets;
    int id;

    public AssetData(){
        assets = new ArrayList<>();
        id = 0;
    }


    @Override
    public boolean create(Asset Asset) {
        try {
            id++;
            organisation.setOrganisationID(Integer.toString(id));
            organisations.add(organisation);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean edit(Asset Asset) {
        try {
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
        } catch (Exception e) {
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
    public ArrayList<Asset> get(String id) {
        Organisation organisation = organisations.get(Integer.parseInt(id)-1);
        ArrayList<Organisation> tempOrganisation = new ArrayList<>();
        tempOrganisation.add(organisation);
        return tempOrganisation;
    }

    @Override
    public ArrayList<Asset> getAll() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}

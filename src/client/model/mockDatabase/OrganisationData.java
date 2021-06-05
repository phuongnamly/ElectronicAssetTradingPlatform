package client.model.mockDatabase;

import client.model.entity.Organisation;
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
    public boolean create(Organisation organisation) {
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
    public boolean edit(Organisation organisation) {
//        try {
//            int organisationID = Integer.parseInt(organisation.getOrganisationID())-1;
//            Organisation prevOrganisation = organisations.get(organisationID);
//            if (organisation.getUsername() == null){
//                organisation.setUsername(prevOrganisation.getUsername());
//            }
//            if (organisation.getAddress() == null){
//                organisation.setAddress(prevOrganisation.getAddress());
//            }
//            if (organisation.getEmail()==null){
//                organisation.setEmail(prevOrganisation.getEmail());
//            }
//            if (organisation.getOrganisationID()== null){
//                organisation.setOrganisationID(prevOrganisation.getOrganisationID());
//            }
//            if (organisation.getAccountType() == null){
//                organisation.setAccountType(prevOrganisation.getAccountType());
//            }
//            if (organisation.getPassword() == null){
//                organisation.setPassword(prevOrganisation.getPassword());
//            }
//            if (organisation.getPhoneNum() == null){
//                organisation.setPhoneNum(prevOrganisation.getPhoneNum());
//            }
//            organisations.set(organisationID, organisation);
//            return true;
//        } catch (Exception e) {
            return false;
//        }
    }

    @Override
    public boolean delete(String id) {
        return false;
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
        return false;
    }
}

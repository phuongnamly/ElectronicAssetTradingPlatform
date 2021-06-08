package client.model.entity;

import client.model.exception.OrganisationException;

import java.io.Serializable;

public class Organisation implements Serializable {
    private static final long serialVersionUID = 332082608397623483L;

    private String organisationID;
    private String organisationName;
    private String credits;

    public Organisation(){

    }

    public Organisation(String organisationID, String organisationName, String credits){
        this.organisationID = organisationID;
        this.organisationName = organisationName;
        this.credits = credits;
    }

    /**
     * @return the organisation ID
     */
    public String getOrganisationID() { return organisationID; }

    /**
     * @param organisationID the organisation ID to set
     */
    public void setOrganisationID(String organisationID) { this.organisationID = organisationID; }

    /**
     * @return the organisation name
     */
    public String getOrganisationName() { return organisationName; }

    /**
     * @param organisationName the organisation name to set
     */
    public void setOrganisationName(String organisationName) { this.organisationName = organisationName; }

    /**
     * @return the organisation credit
     */
    public String getCredits() { return credits; }

    /**
     * @param credits the organisation ID to set
     */
    public void setCredits(String credits) { this.credits = credits; }

    public  String toStringAllFields(){
        return this.organisationID+this.organisationName+this.credits;
    }

    @Override
    public String toString() {
        return this.organisationName;
    }
}

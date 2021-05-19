package server.database.JBDCDataSource.Entity;

public class Organisation {
    private int organisationID;
    private String organisationName;
    private Integer credits;

    public Organisation(){

    }

    public Organisation(int organisationID, String organisationName, Integer credits){
        this.organisationID = organisationID;
        this.organisationName = organisationName;
        this.credits = credits;
    }

    /**
     * @return the organisation ID
     */
    public Integer getOrganisationID() { return organisationID; }

    /**
     * @param organisationID the organisation ID to set
     */
    public void setOrganisationID(Integer organisationID) { this.organisationID = organisationID; }

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
    public Integer getCredits() { return credits; }

    /**
     * @param credits the organisation ID to set
     */
    public void setCredits(Integer credits) { this.credits = credits; }
}

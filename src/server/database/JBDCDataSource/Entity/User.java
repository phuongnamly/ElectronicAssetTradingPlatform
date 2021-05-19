package server.database.JBDCDataSource.Entity;

import server.database.JBDCDataSource.Entity.Enum.AccountType;

/**
 * Stores address details for a person.
 * 
 * @author Malcolm Corney
 * @version $Id: Exp $
 */
public class User implements Comparable<User> {

   private Integer organisationID;
   private String username;
   private String password;
   private String salt;
   private String email;
   private Integer phoneNum;
   private String address;
   private AccountType accountType;

   public User(){

   }

   /**
    * Constructor to set values for the User's details.
    * @param username
    * @param password
    * @param accountType
    */
   public User(Integer organisationID, String username, String password, String salt, String email, Integer phoneNum, String address, AccountType accountType) {
      this.organisationID = organisationID;
      this.username = username;
      this.password = password;
//      this.accountType = accountType.values()[accountTypeIndex];
      this.salt = salt;
      this.email = email;
      this.phoneNum = phoneNum;
      this.address = address;
      this.accountType = accountType;
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
    * @return the username
    */
   public String getUsername() {
      return username;
   }

   /**
    * @param username the name to set
    */
   public void setUsername(String username) {
      this.username = username;
   }

   /**
    * @return the password
    */
   public String getPassword() {
      return password;
   }

   /**
    * @param password the password to set
    */
   public void setPassword(String password) {
      this.password = password;
   }

   /**
    * @return the salt
    */
   public String getSalt() {
      return salt;
   }

   /**
    * @param salt the salt to set
    */
   public void setSalt(String salt) {
      this.salt = salt;
   }

   /**
    * @return the account type
    */
   public AccountType getAccountType() {
      return accountType;
   }

   /**
    * @param accountType the account type to set
    */
   public void setAccountType(AccountType accountType) {
      this.accountType = accountType;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param email the email  to set
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * @return the phone number
    */
   public int getPhoneNum() {
      return phoneNum;
   }

   /**
    * @param phoneNum the phone number to set
    */
   public void setPhoneNum(int phoneNum) { this.phoneNum = phoneNum; }

   /**
    * @return the address type
    */
   public String getAddress() {
      return address;
   }

   /**
    * @param address the address to set
    */
   public void setAddress(String address) {
      this.password = address;
   }

   /**
    * Compares this object with the specified object for order. Returns a
    * negative integer, zero, or a positive integer as this object is less than,
    * equal to, or greater than the specified object.
    * 
    * @param other The other Person object to compare against.
    * @return a negative integer, zero, or a positive integer as this object is
    *         less than, equal to, or greater than the specified object.
    * @throws ClassCastException if the specified object's type prevents it from
    *            being compared to this object.
    */
   public int compareTo(User other) {
      return this.username.compareTo(other.username);
   }
}

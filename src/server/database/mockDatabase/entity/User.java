package server.database.mockDatabase.entity;

import java.io.Serializable;

/**
 * Stores address details for a person.
 * 
 * @author Malcolm Corney
 * @version $Id: Exp $
 */
public class User implements Comparable<User>, Serializable {

   private String userID;
   private String organisationID;
   private String username;
   private String password;
   private String email;
   private String phoneNum;
   private String address;
   private String accountType;
   public User(){

   }

   /**
    * Constructor to set values for the User's details.
    * @param username
    * @param password
    * @param accountType
    */
   public User(String organisationID, String username, String password, String email, String phoneNum, String address, String accountType) {
      this.organisationID = organisationID;
      this.username = username;
      this.password = password;
//      this.accountType = accountType.values()[accountTypeIndex];
      this.email = email;
      this.phoneNum = phoneNum;
      this.address = address;
      this.accountType = accountType;
   }

   /**
    * @return the user ID
    */
   public String getUserID() { return userID; }

   /**
    * @param userID the user ID to set
    */
   public void setUserID(String userID) { this.userID = userID; }

   /**
    * @return the organisation ID
    */
   public String getOrganisationID() { return organisationID; }

   /**
    * @param organisationID the organisation ID to set
    */
   public void setOrganisationID(String organisationID) { this.organisationID = organisationID; }

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
    * @return the account type
    */
   public String getAccountType() {
      return accountType;
   }

   /**
    * @param accountType the account type to set
    */
   public void setAccountType(String accountType) {
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
   public String getPhoneNum() {
      return phoneNum;
   }

   /**
    * @param phoneNum the phone number to set
    */
   public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

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

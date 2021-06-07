package client.model.entity;

import client.model.exception.UserException;

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
   private String accountType;
   private String email;
   private String phoneNum;
   private String address;
   public User(){

   }

   /**
    * Constructor to set values for the User's details.
    * @param username
    * @param password
    * @param accountType
    */
   public User(String userID, String organisationID, String username, String password, String accountType, String email, String phoneNum, String address) throws UserException {
      if(UserException.validate(userID, organisationID, username, password, accountType, email, phoneNum, address)){
         this.userID = userID;
         this.organisationID = organisationID;
         this.username = username;
         this.password = password;
         this.accountType = accountType;
         //      this.accountType = accountType.values()[accountTypeIndex];
         this.email = email;
         this.phoneNum = phoneNum;
         this.address = address;
      }
      else{
         throw new UserException("Invalid User information");
      }
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
      this.address = address;
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

   @Override
   public  String toString(){
//      return "id: "+ this.userID+" name: " +this.username + " organisation: "+this.organisationID;
      return this.userID+this.username +this.organisationID+this.accountType+this.address+this.email+this.password+this.phoneNum;
   }

}


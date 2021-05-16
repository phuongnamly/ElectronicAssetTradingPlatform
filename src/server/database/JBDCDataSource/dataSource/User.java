package server.database.JBDCDataSource.dataSource;

import java.io.Serializable;

/**
 * Stores address details for a person.
 * 
 * @author Malcolm Corney
 * @version $Id: Exp $
 */
public class User implements Comparable<User>, Serializable {

   private static final long serialVersionUID = 332082608397623483L;

   private String username;
   private String password;
   private AccountType accountType;

   /**
    * No args constructor.
    */
   public User() {
   }

   /**
    * Constructor to set values for the User's details.
    * @param username
    * @param password
    * @param accountType
    */
   public User(String username, String password,  AccountType accountType) {
      this.username = username;
      this.password = password;
//      this.accountType = accountType.values()[accountTypeIndex];
      this.accountType = accountType;
   }

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

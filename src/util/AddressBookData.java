package util;

import server.database.JBDCDataSource.Entity.User;
import util.UserDataSource;

import javax.swing.*;

/**
 * This version uses an AddressBookDataSource and its methods to retrieve data
 */
public class AddressBookData {

   DefaultListModel listModel;

   UserDataSource addressData;

   /**
    * Constructor initializes the list model that holds names as Strings and
    * attempts to read any data saved from previous invocations of the
    * application.
    *
    */
   public AddressBookData(UserDataSource dataSource) {
      listModel = new DefaultListModel();
      addressData = dataSource;

      // add the retrieved data to the list model
      for (String name : addressData.nameSet()) {
         listModel.addElement(name);
      }
   }

   /**
    * Adds a person to the address book.
    *
    * @param u A Person to add to the address book.
    */
   public void add(User u) {

      // check to see if the person is already in the book
      // if not add to the address book and the list model
      if (!listModel.contains(u.getUsername())) {
         listModel.addElement(u.getUsername());
         addressData.addUser(u);
      }
   }

   /**
    * Based on the name of the person in the address book, delete the person.
    *
    * @param key
    */
   public void remove(Object key) {

      // remove from both list and map
      listModel.removeElement(key);
      addressData.deleteUser((String) key);
   }

   /**
    * Saves the data in the address book using a persistence
    * mechanism.
    */
   public void persist() {
      addressData.close();
   }

   /**
    * Retrieves Person details from the model.
    *
    * @param key the name to retrieve.
    * @return the Person object related to the name.
    */
   public User get(Object key) {
      return addressData.getUser((String) key);
   }

   /**
    * Accessor for the list model.
    *
    * @return the listModel to display.
    */
   public ListModel getModel() {
      return listModel;
   }

   /**
    * @return the number of names in the Address Book.
    */
   public int getSize() {
      return addressData.getSize();
   }
}

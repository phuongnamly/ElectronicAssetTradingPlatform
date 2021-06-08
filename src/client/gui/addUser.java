package client.gui;

import client.gui.clientData.NetworkDataSource;
import client.model.entity.Organisation;
import client.model.entity.User;
import client.model.exception.UserException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static utils.hash.Hash.getHashedPassword;

public class addUser extends JFrame{
    NetworkDataSource data;
    User user;

    String userField;
    String JFrameTile;
    String currentUsername;

    private JTextArea display;
    JFrame frame;

    JButton btnClose;
    JButton btnSave;

//    // JTextFields for User objects
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private String accountTypeText;
    private String organisationIDText;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JTextField addressTextField;

    public addUser() {

        // initialize database
        data = new NetworkDataSource();
        currentUsername = login.getCurrentUsernameUsername();

        if(currentUsername.isEmpty()){
            userField = "Register new user";
            JFrameTile = "Register User";
        }else{
            userField = "Change existing details, if a field is left blank, details will not be changed";
            JFrameTile = "Edit User";
            user = data.getUser(currentUsername).get(0);
        }

        frame = new JFrame(JFrameTile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        //JPanel for the whole area, with layout table properties
        JPanel orderTable = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        orderTable.setLayout(tpLayout);

        orderTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(userField),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(orderTable);
        layout.putConstraint(SpringLayout.WEST, orderTable, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, orderTable, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, orderTable, -50, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, orderTable, -10, SpringLayout.EAST, contentPane);

        btnClose = createButton("Close", this::closeButtonClicked);
        contentPane.add(btnClose);
        layout.putConstraint(SpringLayout.NORTH, btnClose, 10, SpringLayout.SOUTH, orderTable);
        layout.putConstraint(SpringLayout.SOUTH, btnClose, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, btnClose, -12, SpringLayout.EAST, contentPane);

        btnSave = new JButton("Save");
        contentPane.add(btnSave);
        layout.putConstraint(SpringLayout.NORTH, btnSave, 10, SpringLayout.SOUTH, orderTable);
        layout.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, btnSave, -10, SpringLayout.WEST, btnClose);

        JLabel getUserName = new JLabel("New Username:" );
        orderTable.add(getUserName);
        tpLayout.putConstraint(SpringLayout.WEST, getUserName, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getUserName, 5, SpringLayout.NORTH, orderTable);


        //PLEASE ENSURE THIS GETS INPUTTED INTO TOP for proper identification. Note to self please declare the JTextField elsewhere for integration purposes
        usernameTextField  = new JTextField();
        orderTable.add(usernameTextField);
        tpLayout.putConstraint(SpringLayout.WEST, usernameTextField, 10, SpringLayout.EAST, getUserName);
        tpLayout.putConstraint(SpringLayout.EAST, usernameTextField, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, usernameTextField, 5, SpringLayout.NORTH, orderTable);

        JLabel getUserPassword = new JLabel("New Password: ");
        orderTable.add(getUserPassword);
        tpLayout.putConstraint(SpringLayout.WEST, getUserPassword, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getUserPassword, 30, SpringLayout.NORTH, orderTable);


        passwordTextField = new JTextField();
        orderTable.add(passwordTextField);
        tpLayout.putConstraint(SpringLayout.WEST, passwordTextField, 10, SpringLayout.EAST, getUserPassword);
        tpLayout.putConstraint(SpringLayout.EAST, passwordTextField, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, passwordTextField, 30, SpringLayout.NORTH, orderTable);



        ///BIG NOTE TO NAM AND MYSELF, IF ADMIN ORGANSIATION DROPDOWN PANE WILL NOT BE VISIBLE.
        JLabel getAccount = new JLabel("Account Type: ");
        orderTable.add(getAccount);
        tpLayout.putConstraint(SpringLayout.WEST, getAccount, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAccount, 50, SpringLayout.NORTH, orderTable);

        //DROP DOWN PANE (INPUTED OBJECT STRING FOR TESTING PURPOSES) TO BE REMOVED DURING INTEGRATION
        //JComboBox<String> organisationList = new JComboBox<String>();
        String[] adminOrMember;
        if(currentUsername.isEmpty())
        {
            adminOrMember = new String[]{"Member"};
        }
        else{
            if(user.getAccountType().equals("Admin")){
                adminOrMember = new String[]{"Admin", "Member"};
            }
            else{
               adminOrMember = new String[]{"Member"};
            }
        }

        JComboBox getAccountType = new JComboBox<String>(adminOrMember); //Move Declaration to top
        orderTable.add(getAccountType);
        tpLayout.putConstraint(SpringLayout.WEST, getAccountType, 5, SpringLayout.EAST, getAccount);
        tpLayout.putConstraint(SpringLayout.EAST, getAccountType,-10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAccountType, 50, SpringLayout.NORTH, orderTable);
        accountTypeText = (String) getAccountType.getSelectedItem();

        JLabel getOrganisation= new JLabel("Organisation: ");
        orderTable.add(getOrganisation);
        tpLayout.putConstraint(SpringLayout.WEST, getOrganisation, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getOrganisation, 90, SpringLayout.NORTH, orderTable);

        //DROP DOWN PANE (INPUTED OBJECT STRING FOR TESTING PURPOSES) TO BE REMOVED DURING INTEGRATION
        //JComboBox<String> organisationList = new JComboBox<String>();
//        String[] listOfOrganisations = {"Bolton Clarke", "better2care", "Bluecare",
//                "myPlan Management", "Feros Care", "Anglicare", "NDIA"};
//        JComboBox organisationList = new JComboBox<String>(listOfOrganisations); //Move Declaration to top

        ArrayList<Organisation> organisations = data.getOrganisations();
        JComboBox<Organisation> organisationList = new JComboBox<Organisation>(); //Move Declaration to top
        Organisation organisation1 = organisations.get(1);
        organisationList.addItem(organisation1);
        orderTable.add(organisationList);
        Organisation selectedOrganisation = (Organisation) organisationList.getSelectedItem();
        organisationIDText = selectedOrganisation.getOrganisationID();
        
        tpLayout.putConstraint(SpringLayout.WEST, organisationList, 5, SpringLayout.EAST, getOrganisation);
        tpLayout.putConstraint(SpringLayout.EAST, organisationList,-10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, organisationList, 90, SpringLayout.NORTH, orderTable);

        JLabel getEmail = new JLabel("Email: ");
        orderTable.add(getEmail);
        tpLayout.putConstraint(SpringLayout.WEST, getEmail, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getEmail, 130, SpringLayout.NORTH, orderTable);

        emailTextField = new JTextField(); //Please change declrration
        orderTable.add(emailTextField);
        tpLayout.putConstraint(SpringLayout.WEST, emailTextField, 5, SpringLayout.EAST, getEmail);
        tpLayout.putConstraint(SpringLayout.EAST, emailTextField, -250, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, emailTextField, 130, SpringLayout.NORTH, orderTable);

        JLabel getPhone = new JLabel("Phone: ");
        orderTable.add(getPhone);
        tpLayout.putConstraint(SpringLayout.WEST, getPhone, 5, SpringLayout.EAST, emailTextField);
        tpLayout.putConstraint(SpringLayout.NORTH, getPhone, 130, SpringLayout.NORTH, orderTable);

        phoneTextField = new JTextField(); //Please Move Declarataion
        orderTable.add(phoneTextField);
        tpLayout.putConstraint(SpringLayout.WEST, phoneTextField, 5, SpringLayout.EAST, getPhone);
        tpLayout.putConstraint(SpringLayout.EAST, phoneTextField, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, phoneTextField, 130, SpringLayout.NORTH, orderTable);

        JLabel getAddress = new JLabel("Address: ");
        orderTable.add(getAddress);
        tpLayout.putConstraint(SpringLayout.WEST, getAddress, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAddress, 150, SpringLayout.NORTH, orderTable);

        addressTextField = new JTextField();
        orderTable.add(addressTextField);
        tpLayout.putConstraint(SpringLayout.WEST, addressTextField, 5, SpringLayout.EAST, getAddress);
        tpLayout.putConstraint(SpringLayout.EAST, addressTextField, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, addressTextField, 150, SpringLayout.NORTH, orderTable);

       //Button Action Listener
        addButtonListeners(new ButtonListener());

        /* show frame */
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
    }

    private void closeButtonClicked(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "Thank god ur smart u didnt' buy ",
                "THIS WILL GO THROUGH", JOptionPane.OK_OPTION);

    }

    private void unloadButtonClicked(ActionEvent actionEvent) {
        JButton btn = ((JButton) actionEvent.getSource());
        display.setText(btn.getText().trim());
    }

    private JButton createButton(String str, ActionListener listener){
        //Create the JbUTTTON
        JButton button = new JButton();
        //Set button text
        button.setText(str);
        //Add listener
        button.addActionListener(listener);
        //Return Button
        return button;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new addUser();
            }
        });
    }
    private void addButtonListeners(ActionListener listener) {
        btnSave.addActionListener(listener);
    }

    private class ButtonListener extends Component implements ActionListener {

        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e){

            JButton source = (JButton) e.getSource();

            if(source == btnSave) {
                if(currentUsername.isEmpty()){
                    try {
                        if(data.addUser(new User("", organisationIDText,usernameTextField.getText(), getHashedPassword(passwordTextField.getText()), accountTypeText,emailTextField.getText(), phoneTextField.getText(), addressTextField.getText()))){
                            JOptionPane.showMessageDialog(this,usernameTextField.getText() + " has been added to the database", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    new login();
                                    frame.dispose();
                                }
                            });
                        }
                    } catch (UserException | IOException | ClassCastException userException) {
                        JOptionPane.showMessageDialog(this,userException.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                        userException.printStackTrace();
                    }

                }else{
                    try {
                        String username = usernameTextField.getText();
                        String password = passwordTextField.getText();
                        if(!password.isEmpty())
                        {
                            password = getHashedPassword(password);
                        }
                        if(data.editUser(new User(user.getUserID(), organisationIDText,username, password, accountTypeText,emailTextField.getText(), phoneTextField.getText(), addressTextField.getText()))){
                            JOptionPane.showMessageDialog(this,"Current user has been edited", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            if(!username.isEmpty())
                            {
                                login.setCurrentUsernameUsername(username);

                            }
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    new homePage();
                                    frame.dispose();
                                }
                            });
                        }
                    } catch (UserException | IOException | ClassCastException userException) {
                        JOptionPane.showMessageDialog(this,userException.getMessage(), "WARNING", JOptionPane.WARNING_MESSAGE);
                        userException.printStackTrace();
                    }
                }
            }
        }}
}
package client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addUser extends JFrame{

    private JTextArea display;
    JFrame frame;

    JButton btnClose;
    JButton btnSave;

    public addUser() {
        frame = new JFrame("Register User");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        //JPanel for the whole area, with layout table properties
        JPanel orderTable = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        orderTable.setLayout(tpLayout);
        orderTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Register new user"),
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
        JTextField getUserNameText = new JTextField();
        orderTable.add(getUserNameText);
        tpLayout.putConstraint(SpringLayout.WEST, getUserNameText, 10, SpringLayout.EAST, getUserName);
        tpLayout.putConstraint(SpringLayout.EAST, getUserNameText, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getUserNameText, 5, SpringLayout.NORTH, orderTable);

        JLabel getUserPassword = new JLabel("New Password: ");
        orderTable.add(getUserPassword);
        tpLayout.putConstraint(SpringLayout.WEST, getUserPassword, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getUserPassword, 30, SpringLayout.NORTH, orderTable);


        JTextField getUserPasswordText = new JTextField();
        orderTable.add(getUserPasswordText);
        tpLayout.putConstraint(SpringLayout.WEST, getUserPasswordText, 10, SpringLayout.EAST, getUserPassword);
        tpLayout.putConstraint(SpringLayout.EAST, getUserPasswordText, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getUserPasswordText, 30, SpringLayout.NORTH, orderTable);



        ///BIG NOTE TO NAM AND MYSELF, IF ADMIN ORGANSIATION DROPDOWN PANE WILL NOT BE VISIBLE.
        JLabel getAccount = new JLabel("Account Type: ");
        orderTable.add(getAccount);
        tpLayout.putConstraint(SpringLayout.WEST, getAccount, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAccount, 50, SpringLayout.NORTH, orderTable);

        //DROP DOWN PANE (INPUTED OBJECT STRING FOR TESTING PURPOSES) TO BE REMOVED DURING INTEGRATION
        //JComboBox<String> organisationList = new JComboBox<String>();
        String[] adminOrMember = {"Admin", "Member"};
        JComboBox getAccountType = new JComboBox<String>(adminOrMember); //Move Declaration to top
        orderTable.add(getAccountType);
        tpLayout.putConstraint(SpringLayout.WEST, getAccountType, 5, SpringLayout.EAST, getAccount);
        tpLayout.putConstraint(SpringLayout.EAST, getAccountType,-10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAccountType, 50, SpringLayout.NORTH, orderTable);

        JLabel getOrganisation= new JLabel("Account Type: ");
        orderTable.add(getOrganisation);
        tpLayout.putConstraint(SpringLayout.WEST, getOrganisation, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getOrganisation, 90, SpringLayout.NORTH, orderTable);

        //DROP DOWN PANE (INPUTED OBJECT STRING FOR TESTING PURPOSES) TO BE REMOVED DURING INTEGRATION
        //JComboBox<String> organisationList = new JComboBox<String>();
        String[] listoforganisations = {"Bolton Clarke", "better2care", "Bluecare",
                "myPlan Management", "Feros Care", "Anglicare", "NDIA"};
        JComboBox organisationList = new JComboBox<String>(listoforganisations); //Move Declaration to top
        orderTable.add(organisationList);
        tpLayout.putConstraint(SpringLayout.WEST, organisationList, 5, SpringLayout.EAST, getOrganisation);
        tpLayout.putConstraint(SpringLayout.EAST, organisationList,-10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, organisationList, 90, SpringLayout.NORTH, orderTable);

        JLabel getEmail = new JLabel("Email: ");
        orderTable.add(getEmail);
        tpLayout.putConstraint(SpringLayout.WEST, getEmail, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getEmail, 130, SpringLayout.NORTH, orderTable);

        JTextField getEmailText = new JTextField(); //Please change declrration
        orderTable.add(getEmailText);
        tpLayout.putConstraint(SpringLayout.WEST, getEmailText, 5, SpringLayout.EAST, getEmail);
        tpLayout.putConstraint(SpringLayout.EAST, getEmailText, -250, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getEmailText, 130, SpringLayout.NORTH, orderTable);

        JLabel getPhone = new JLabel("Phone: ");
        orderTable.add(getPhone);
        tpLayout.putConstraint(SpringLayout.WEST, getPhone, 5, SpringLayout.EAST, getEmailText);
        tpLayout.putConstraint(SpringLayout.NORTH, getPhone, 130, SpringLayout.NORTH, orderTable);

        JTextField getPhoneNumber = new JTextField(); //Please Move Declarataion
        orderTable.add(getPhoneNumber);
        tpLayout.putConstraint(SpringLayout.WEST, getPhoneNumber, 5, SpringLayout.EAST, getPhone);
        tpLayout.putConstraint(SpringLayout.EAST, getPhoneNumber, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getPhoneNumber, 130, SpringLayout.NORTH, orderTable);

        JLabel getAddress = new JLabel("Home Address: ");
        orderTable.add(getAddress);
        tpLayout.putConstraint(SpringLayout.WEST, getAddress, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAddress, 150, SpringLayout.NORTH, orderTable);

        JTextField getAddressText = new JTextField();
        orderTable.add(getAddressText);
        tpLayout.putConstraint(SpringLayout.WEST, getAddressText, 5, SpringLayout.EAST, getAddress);
        tpLayout.putConstraint(SpringLayout.EAST, getAddressText, -10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getAddressText, 150, SpringLayout.NORTH, orderTable);

        JLabel getPostcode = new JLabel("Postcode: ");
        orderTable.add(getPostcode);
        tpLayout.putConstraint(SpringLayout.WEST, getPostcode, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getPostcode, 180, SpringLayout.NORTH, orderTable);

        JTextField getPostcodeText= new JTextField(); //Please change declrration
        orderTable.add(getPostcodeText);
        tpLayout.putConstraint(SpringLayout.WEST, getPostcodeText, 5, SpringLayout.EAST, getPostcode);
        tpLayout.putConstraint(SpringLayout.EAST, getPostcodeText, -290, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, getPostcodeText, 180, SpringLayout.NORTH, orderTable);

        JLabel getState = new JLabel("State: ");
        orderTable.add(getState);
        tpLayout.putConstraint(SpringLayout.WEST, getState, 5, SpringLayout.EAST, getPostcodeText);
        tpLayout.putConstraint(SpringLayout.NORTH, getState, 180, SpringLayout.NORTH, orderTable);

        String[] stateList = {"Madripoor", "Sokovia", "Westview", "New Asgard", "Wakanda"};
        JComboBox stateLister = new JComboBox<String>(stateList); //Move Declaration to top
        orderTable.add(stateLister);
        tpLayout.putConstraint(SpringLayout.WEST, stateLister, 5, SpringLayout.EAST, getState);
        tpLayout.putConstraint(SpringLayout.EAST, stateLister,-10, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, stateLister, 180, SpringLayout.NORTH, orderTable);

       //Button Action Listener
        addButtonListeners(new addUser.ButtonListener());

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

    private class ButtonListener implements ActionListener {

        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e){

            JButton source = (JButton) e.getSource();

            if(source == btnSave) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new login();
                        dispose();
                    }
                });
            }
        }}



}
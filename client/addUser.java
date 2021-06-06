package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class addUser extends JFrame{

    private JTextArea display;

    private JTextField quantityText;
    private JTextField limitText;
    JButton buyButton;
    JButton sellButton;

    JButton btnClose;
    // createUser()
    JButton btnReview;




    ////////
    ///Likely create another Java Class for the JButton declarations
    JButton btnHome;
    JButton btnOrderPad;
    JButton btnOrderHistory;
    JButton btnEdit; //Only for admin use??
    JButton btnSettings;
    JButton btnLogOut;




    public addUser() {

        JFrame frame = new JFrame("Ordering Tab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        //JPanel for the whole area, with layout table properties
        JPanel orderTable = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        orderTable.setLayout(tpLayout);
        orderTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Add User Details (ADMIN ONLY)"),
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

        btnReview = new JButton("Save");
        contentPane.add(btnReview);
        layout.putConstraint(SpringLayout.NORTH, btnReview, 10, SpringLayout.SOUTH, orderTable);
        layout.putConstraint(SpringLayout.SOUTH, btnReview, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, btnReview, -10, SpringLayout.WEST, btnClose);


        //Setting out the area for JPanel
//        JLabel assetName = new JLabel("Asset Name:");
//        orderPad.add(assetName);
//        padLayout.putConstraint(SpringLayout.WEST, assetName, 5, SpringLayout.WEST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, assetName, 5, SpringLayout.NORTH, orderPad);

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



//        JLabel get = new JLabel("Phone: ");
//        orderTable.add(getPhone);
//        tpLayout.putConstraint(SpringLayout.WEST, getPhone, 5, SpringLayout.EAST, getEmailText);
//        tpLayout.putConstraint(SpringLayout.NORTH, getPhone, 90, SpringLayout.NORTH, orderTable);
//
//        JTextField getPhoneNumber = new JTextField(); //Please Move Declarataion
//        orderTable.add(getPhoneNumber);
//        tpLayout.putConstraint(SpringLayout.WEST, getPhoneNumber, 5, SpringLayout.EAST, getPhone);
//        tpLayout.putConstraint(SpringLayout.EAST, getPhoneNumber, -10, SpringLayout.EAST, orderTable);
//        tpLayout.putConstraint(SpringLayout.NORTH, getPhoneNumber, 90, SpringLayout.NORTH, orderTable);
//
//







//        padLayout.putConstraint(SpringLayout.NORTH, quantityLabel, 40, SpringLayout.NORTH, accountListBox);


        //JTextfield for

//
//        JComboBox<String> assetListBox = new JComboBox<String>();
//        orderPad.add(assetListBox);
//        padLayout.putConstraint(SpringLayout.WEST, assetListBox, 10, SpringLayout.EAST, assetName);
//        padLayout.putConstraint(SpringLayout.EAST, assetListBox, -10, SpringLayout.EAST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, assetListBox, 3, SpringLayout.NORTH, orderPad);
//
//        //Added it as JButton
//        buyButton = new JButton("Buy");
//        orderPad.add(buyButton);
//        padLayout.putConstraint(SpringLayout.WEST, buyButton, -5, SpringLayout.WEST, assetName);
//        padLayout.putConstraint(SpringLayout.NORTH, buyButton, 2, SpringLayout.SOUTH, assetListBox);
//
//        //Added it as a JButton
//        sellButton = new JButton("Sell");
//        orderPad.add(sellButton);
//        padLayout.putConstraint(SpringLayout.WEST, sellButton, 20, SpringLayout.EAST, buyButton);
//        padLayout.putConstraint(SpringLayout.NORTH, sellButton, 2, SpringLayout.SOUTH, assetListBox);
//
//        JLabel accountLabel  = new JLabel("Account Type:");
//        orderPad.add(accountLabel);
//        padLayout.putConstraint(SpringLayout.WEST, accountLabel, 5, SpringLayout.WEST, buyButton);
//        padLayout.putConstraint(SpringLayout.NORTH, accountLabel, 40, SpringLayout.NORTH, sellButton);
//
//        JComboBox<String> accountListBox = new JComboBox<String>();
//        orderPad.add(accountListBox);
//        padLayout.putConstraint(SpringLayout.WEST, accountListBox, 10, SpringLayout.EAST, accountLabel);
//        padLayout.putConstraint(SpringLayout.EAST, accountListBox, 5, SpringLayout.EAST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, accountListBox, 70, SpringLayout.NORTH, orderPad);
//
//        JLabel quantityLabel  = new JLabel("Unit Quantity:");
//        orderPad.add(quantityLabel);
//        padLayout.putConstraint(SpringLayout.WEST, quantityLabel, 5, SpringLayout.WEST, accountLabel);
//        padLayout.putConstraint(SpringLayout.NORTH, quantityLabel, 40, SpringLayout.NORTH, accountListBox);
//
//        //Declared them as private quantitytext textfield
//        quantityText = new JTextField();
//        orderPad.add(quantityText);
//        padLayout.putConstraint(SpringLayout.WEST, quantityText, 10, SpringLayout.EAST, quantityLabel);
//        padLayout.putConstraint(SpringLayout.EAST, quantityText, 5, SpringLayout.EAST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, quantityText, 105, SpringLayout.NORTH, orderPad);
//
//        JLabel limitLabel  = new JLabel("Limit Price:");
//        orderPad.add(limitLabel);
//        padLayout.putConstraint(SpringLayout.WEST, limitLabel, 5, SpringLayout.WEST, quantityLabel);
//        padLayout.putConstraint(SpringLayout.NORTH, limitLabel, 40, SpringLayout.NORTH, quantityText);
//
//        //Declared it as a private limit textfield
//        limitText = new JTextField();
//        orderPad.add(limitText);
//        padLayout.putConstraint(SpringLayout.WEST, limitText, 10, SpringLayout.EAST, limitLabel);
//        padLayout.putConstraint(SpringLayout.EAST, limitText, 5, SpringLayout.EAST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, limitText, 145, SpringLayout.NORTH, orderPad);
//



















        /////Setting out the Panel for Main Button pANEL
        //Creating MainButtonPanel
        JPanel MainButtonPanel = new JPanel();
        SpringLayout mainLayout = new SpringLayout();
        MainButtonPanel.setLayout(mainLayout);
        MainButtonPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Main Panel"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(MainButtonPanel);
        layout.putConstraint(SpringLayout.WEST, MainButtonPanel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, MainButtonPanel, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, MainButtonPanel, -280, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, MainButtonPanel, -10, SpringLayout.EAST, contentPane);

        //Adding the Main Buttons into MainButton Panel
//        JButton btnHome;
//        JButton btnOrderPad;
//        JButton btnOrderHistory;
//        JButton btnEdit; //Only for admin use??
//        JButton btnSettings;
//        JButton btnLogOut;
//
//
        //Button for Home in Main Panel
        btnHome = new JButton("Home");
        MainButtonPanel.add(btnHome);
        mainLayout.putConstraint(SpringLayout.WEST, btnHome, 5, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnHome, 1, SpringLayout.NORTH, MainButtonPanel);

        //Button for Ordering Pad
        btnOrderPad = new JButton("Order");
        MainButtonPanel.add(btnOrderPad);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderPad, 80, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderPad, 1, SpringLayout.NORTH, MainButtonPanel);

        btnOrderHistory = new JButton("History");
        MainButtonPanel.add(btnOrderHistory);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderHistory, 160, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderHistory, 1, SpringLayout.NORTH, MainButtonPanel);

        btnEdit = new JButton("Edit");
        MainButtonPanel.add(btnEdit);
        mainLayout.putConstraint(SpringLayout.WEST, btnEdit, 250, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnEdit, 1, SpringLayout.NORTH, MainButtonPanel);

        btnSettings = new JButton("Settings");
        MainButtonPanel.add(btnSettings);
//        mainLayout.putConstraint(SpringLayout.WEST, btnSettings, 60, SpringLayout.WEST, MainButtonPanel);
//        mainLayout.putConstraint(SpringLayout.NORTH, btnSettings, 1, SpringLayout.NORTH, MainButtonPanel);
        //mainLayout.putConstraint(SpringLayout.WEST, btnSettings, 5, SpringLayout.EAST, btnEdit);
        //mainLayout.putConstraint(SpringLayout.WEST, btnLogOut, 1, SpringLayout.EAST, btnEdit);
        mainLayout.putConstraint(SpringLayout.WEST, btnSettings,  320, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnSettings, 1, SpringLayout.NORTH, MainButtonPanel);





        btnLogOut = new JButton("Logout");
        MainButtonPanel.add(btnLogOut);
        //mainLayout.putConstraint(SpringLayout.WEST, btnLogOut, 5, SpringLayout.EAST, btnSettings);
        mainLayout.putConstraint(SpringLayout.EAST, btnLogOut, 10 , SpringLayout.EAST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnLogOut, 1, SpringLayout.NORTH, MainButtonPanel);












//
//
//
//
//
//
//
//
//
//
//
//
//        //Defining the OrderPad Printout
//        JPanel orderPanel = new JPanel();
//        SpringLayout orderLayout = new SpringLayout();
//        orderPanel.setLayout(orderLayout);
//        orderPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order Description"),
//                BorderFactory.createEmptyBorder(5,5,5,5)));
//        orderTable.add(orderPanel);
//        tpLayout.putConstraint(SpringLayout.WEST,orderPanel, 20, SpringLayout.WEST, orderTable);
//        tpLayout.putConstraint(SpringLayout.NORTH, orderPanel, 20, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.SOUTH, orderPanel, 160, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.EAST, orderPanel, 250, SpringLayout.WEST, orderTable);
//
//
//
//        ///Defining the Order Pad Panel (North  West - Middle) ///////////// ORDER PAD AND BUTTONS
//        JPanel orderPad = new JPanel();
//        SpringLayout padLayout = new SpringLayout();
//        orderPad.setLayout(padLayout);
//        orderPad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order Pad"),
//                BorderFactory.createEmptyBorder(5,5,5,5)));
//        orderTable.add(orderPad);
//        tpLayout.putConstraint(SpringLayout.WEST, orderPad, 20, SpringLayout.EAST, orderPanel);
//        tpLayout.putConstraint(SpringLayout.NORTH, orderPad, 20, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.SOUTH, orderPad, 345, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.EAST, orderPad, -20, SpringLayout.EAST, orderTable);
//        //Creating widgets and buttons for Order Pad
//
//
//
//        // Defining the graph area (South West)
//        JPanel graphPanel= new JPanel();
//        SpringLayout smoLayout = new SpringLayout();
//        graphPanel.setLayout(smoLayout);
//        graphPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Graph/Table"), BorderFactory.createEmptyBorder(5,5,5,5)));
//        orderTable.add(graphPanel);
//        tpLayout.putConstraint(SpringLayout.WEST, graphPanel, 0, SpringLayout.WEST, orderPanel);
//        tpLayout.putConstraint(SpringLayout.EAST, graphPanel, 0, SpringLayout.EAST, orderPanel);
//        tpLayout.putConstraint(SpringLayout.NORTH, graphPanel, 20, SpringLayout.SOUTH, orderPanel);
//        tpLayout.putConstraint(SpringLayout.SOUTH, graphPanel, -10, SpringLayout.SOUTH, orderTable);
//        /* ADD OTHER WIDGETS */
//
//
//        //Button Action Listener
        addButtonListeners(new addUser.ButtonListener());

        /* show frame */
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
        addButtonListeners(new addUser.ButtonListener());
    }

    private JTextArea newDisplay() {

        display = new JTextArea();
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Arial",Font.BOLD, 24));
        display.setBorder(BorderFactory.createEtchedBorder());
        return display;

    }

    private void reviewButtonClicked(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "YOU MIGHT LOSE MONEY ",
                "THIS WILL GO THROUGH", JOptionPane.OK_OPTION);

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
        btnHome.addActionListener(listener);
        btnOrderHistory.addActionListener(listener);
        btnOrderPad.addActionListener(listener);
        btnEdit.addActionListener(listener);
        btnSettings.addActionListener(listener);
        //logout.addActionListener(listener);

    }

    private class ButtonListener implements ActionListener {

        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e){

            JButton source = (JButton) e.getSource();

            if(source == btnHome) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new homePage();
                        dispose();
                    }
                });
            }
            else if(source == btnSettings){
                new addUser();
                dispose();
            }
            else if(source == btnEdit){
                new editAsset();
                dispose();
            }
            else if(source == btnOrderPad){
                new orderPad();
                dispose();
            }
            else if (source == btnOrderHistory){
                new orderHistory();
                dispose();
            }
        }}



}
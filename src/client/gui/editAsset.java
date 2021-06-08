package client.gui;

////////////////////IMPORTANT!!!!!!!!!!!///////////
///Please read this
////editAsset.java contains features for BOTH editing assets and organisations

import client.gui.addUser;
import client.gui.homePage;
import client.gui.orderHistory;
import client.gui.orderPad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class editAsset extends JFrame{

    private JTextField display;

    private JTextField quantityText;
    private JTextField limitText;

    private JTextField assetNameText;
    private JTextField startPriceText;

    // use ADD_ASSET from NetworkDataSource
    JButton createAsset;
    // use EDIT_ASSET from NetworkDataSource
    JButton editAsset;
    JButton deleteAsset;
    JButton updateAsset;


    JButton saveNew;



    JButton btnClose;
    JButton btnReview;




    ///Likely create another Java Class for the JButton declarations
    JButton btnHome;
    JButton btnOrderPad;
    JButton btnOrderHistory;
    JButton btnEdit; //Only for admin use??
    JButton btnSettings;
    JButton btnLogOut;
    JFrame frame;



    public editAsset() {

        frame = new JFrame("Asset List Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        display = newDisplay();


        //JPanel for the whole area, with layout table properties
        JPanel orderTable = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        orderTable.setLayout(tpLayout);
        orderTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order System"),
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

        btnReview = createButton("Update", this::reviewButtonClicked);
        contentPane.add(btnReview);
        layout.putConstraint(SpringLayout.NORTH, btnReview, 10, SpringLayout.SOUTH, orderTable);
        layout.putConstraint(SpringLayout.SOUTH, btnReview, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, btnReview, -10, SpringLayout.WEST, btnClose);

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
//        orderLayout.putConstraint(SpringLayout.WEST, startPriceText, 10, SpringLayout.EAST, startPrice);
//        orderLayout.putConstraint(SpringLayout.EAST, startPriceText, 5, SpringLayout.EAST, orderPanel);
//        orderLayout.putConstraint(SpringLayout.NORTH, startPriceText, 40, SpringLayout.NORTH, orderPanel);
//







        ////BRAND NEW ASSET CREATION
        JPanel orderPanel = new JPanel();
        SpringLayout orderLayout = new SpringLayout();
        orderPanel.setLayout(orderLayout);
        orderPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Create New Asset"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(orderPanel);
        tpLayout.putConstraint(SpringLayout.WEST,orderPanel, 20, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, orderPanel, 20, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.SOUTH, orderPanel, 160, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.EAST, orderPanel, 250, SpringLayout.WEST, orderTable);

        JLabel newAssetName = new JLabel("Name:");
        orderPanel.add(newAssetName);
        orderLayout.putConstraint(SpringLayout.WEST, newAssetName, 5, SpringLayout.WEST, orderPanel);
        orderLayout.putConstraint(SpringLayout.NORTH, newAssetName, 5, SpringLayout.NORTH, orderPanel);

        assetNameText = new JTextField();
        orderPanel.add(assetNameText);
        orderLayout.putConstraint(SpringLayout.WEST, assetNameText, 10, SpringLayout.EAST, newAssetName);
        orderLayout.putConstraint(SpringLayout.EAST, assetNameText, -10, SpringLayout.EAST, orderPanel);
        orderLayout.putConstraint(SpringLayout.NORTH, assetNameText, 3, SpringLayout.NORTH, orderPanel);

        JLabel startPrice  = new JLabel("Start Price:");
        orderPanel.add(startPrice);
        orderLayout.putConstraint(SpringLayout.WEST, startPrice, 3, SpringLayout.WEST, newAssetName);
        orderLayout.putConstraint(SpringLayout.NORTH, startPrice, 40, SpringLayout.NORTH, assetNameText);

        //Declared it as a private limit textfield
        startPriceText = new JTextField();
        orderPanel.add(startPriceText);
        orderLayout.putConstraint(SpringLayout.WEST, startPriceText, 10, SpringLayout.EAST, startPrice);
        orderLayout.putConstraint(SpringLayout.EAST, startPriceText, 5, SpringLayout.EAST, orderPanel);
        orderLayout.putConstraint(SpringLayout.NORTH, startPriceText, 40, SpringLayout.NORTH, orderPanel);


        saveNew = createButton("Save As", this::saveAsClicked);
        orderPanel.add(saveNew);
        orderLayout.putConstraint(SpringLayout.WEST, saveNew, 110, SpringLayout.WEST, startPrice);
        orderLayout.putConstraint(SpringLayout.NORTH, saveNew, 3, SpringLayout.SOUTH, startPriceText);



        ///////////////////////////////Creating new asset

        //PART FOR CREATE NEW ORGANISATION
        JPanel editAssetPanel = new JPanel();
        SpringLayout smoLayout = new SpringLayout();
        editAssetPanel.setLayout(smoLayout);
        editAssetPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("EDIT/DELETE Asset"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(editAssetPanel);
        tpLayout.putConstraint(SpringLayout.WEST, editAssetPanel, 0, SpringLayout.WEST, orderPanel);
        tpLayout.putConstraint(SpringLayout.EAST, editAssetPanel, 0, SpringLayout.EAST, orderPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, editAssetPanel, 20, SpringLayout.SOUTH, orderPanel);
        tpLayout.putConstraint(SpringLayout.SOUTH, editAssetPanel, -10, SpringLayout.SOUTH, orderTable);

        editAsset = createButton("Edit Asset", this::editButtonClick);
        editAssetPanel.add(editAsset);
        smoLayout.putConstraint(SpringLayout.WEST, editAsset, 5, SpringLayout.WEST, editAssetPanel);
        smoLayout.putConstraint(SpringLayout.NORTH, editAsset, 5, SpringLayout.NORTH, editAssetPanel);

        deleteAsset = createButton("Delete Asset", this::deleteButtonClick);
        editAssetPanel.add(deleteAsset);
        smoLayout.putConstraint(SpringLayout.WEST, deleteAsset, 5, SpringLayout.WEST, editAssetPanel);
        smoLayout.putConstraint(SpringLayout.NORTH, deleteAsset, 35, SpringLayout.NORTH, editAssetPanel);
        
        ///Red
        JPanel createEditOrg = new JPanel();
        SpringLayout padLayout = new SpringLayout();
        createEditOrg.setLayout(padLayout);
        createEditOrg.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Organisation Details"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(createEditOrg);
        tpLayout.putConstraint(SpringLayout.WEST, createEditOrg, 20, SpringLayout.EAST, orderPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, createEditOrg, 20, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.SOUTH, createEditOrg, 280, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.EAST, createEditOrg, -20, SpringLayout.EAST, orderTable);
        //Creating widgets and buttons for Order Pad

        //Move JButton declaration to the top
        JButton editOrg = createButton("Edit Organisation", this::editOrgClick);
        createEditOrg.add(editOrg);
        padLayout.putConstraint(SpringLayout.WEST, editOrg, 5, SpringLayout.WEST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, editOrg, 165, SpringLayout.NORTH, createEditOrg);

        //Move JButton declaration to the top
        JButton deleteOrg = createButton("Delete Organisation", this::deleteOrgClick);
        createEditOrg.add(deleteOrg);
        padLayout.putConstraint(SpringLayout.WEST, deleteOrg, 5, SpringLayout.WEST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, deleteOrg, 200, SpringLayout.NORTH, createEditOrg);


        JLabel getOrganisationName = new JLabel(" New Name:" );
        createEditOrg.add(getOrganisationName);
        padLayout.putConstraint(SpringLayout.WEST, getOrganisationName, 5, SpringLayout.WEST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, getOrganisationName, 5, SpringLayout.NORTH, createEditOrg);


        //PLEASE ENSURE THIS GETS INPUTTED INTO TOP for proper identification. Note to self please declare the JTextField elsewhere for integration purposes
        JTextField getOrganisationText = new JTextField();
        createEditOrg.add(getOrganisationText);
        padLayout.putConstraint(SpringLayout.WEST, getOrganisationText, 10, SpringLayout.EAST, getOrganisationName);
        padLayout.putConstraint(SpringLayout.EAST, getOrganisationText, -10, SpringLayout.EAST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, getOrganisationText, 5, SpringLayout.NORTH, createEditOrg);

        JLabel getCredit = new JLabel("New Credit: ");
        createEditOrg.add(getCredit);
        padLayout.putConstraint(SpringLayout.WEST, getCredit, 5, SpringLayout.WEST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, getCredit, 35, SpringLayout.NORTH, createEditOrg);


        JTextField getCreditText = new JTextField();
        createEditOrg.add(getCreditText);
        padLayout.putConstraint(SpringLayout.WEST, getCreditText, 10, SpringLayout.EAST, getCredit);
        padLayout.putConstraint(SpringLayout.EAST, getCreditText, -10, SpringLayout.EAST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, getCreditText, 35, SpringLayout.NORTH, createEditOrg);


        //Declare JButton up top
        JButton btn_create = createButton("Create Organisation", this::createOrgClicked);
        createEditOrg.add(btn_create);
        padLayout.putConstraint(SpringLayout.WEST, btn_create, 5, SpringLayout.WEST, createEditOrg);
        padLayout.putConstraint(SpringLayout.NORTH, btn_create, 60, SpringLayout.NORTH, createEditOrg);




///////////////////////////////////////////////////////////For nAM TO find


        /* show frame */
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
        addButtonListeners(new ButtonListener());
    }

    private JTextField newDisplay() {

        display = new JTextField();
        display.setEditable(true);
        //display.setLineWrap(true);
        display.setFont(new Font("Arial",Font.BOLD, 13));
        // display.setBorder(BorderFactory.createEtchedBorder());
        return display;

    }

    private void reviewButtonClicked(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "Thank you for updating!", "Update", JOptionPane.OK_OPTION);
    }

    private void saveAsClicked(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "Are you sure you want to create new asset?",
                "Save As Confirm", JOptionPane.OK_CANCEL_OPTION);
    }



    private void editButtonClick (ActionEvent actionEvent) {
        Object[] assetValuesList = {"StarkCoin", "Bitconnect", "Dogecoin REEE", "NamCoin", "ThomasDollar", "SelwynPound", "Jasonuro"};
        String initAsset = "Jasonuro";
        Object SelectedAsset = JOptionPane.showInputDialog(this, "Select Asset to Edit",
                "Editing Details", JOptionPane.QUESTION_MESSAGE, null, assetValuesList, initAsset);

        JTextField editingName = new JTextField(10);
        editingName = newDisplay();
        editingName.setText(SelectedAsset.toString());
        JTextField editingStartPrice = new JTextField(10);

        JPanel editBoxPanel = new JPanel();
        editBoxPanel.add(new JLabel("Edit Name:"));
        editBoxPanel.add(editingName);
        editBoxPanel.add(Box.createVerticalStrut(15)); // a spacer
        editBoxPanel.add(new JLabel("Edit Start Price:"));
        editBoxPanel.add(editingStartPrice);
        JOptionPane.showConfirmDialog(this, editBoxPanel, "Please Edit Details", JOptionPane.OK_CANCEL_OPTION);
    }

    private void deleteButtonClick(ActionEvent actionEvent){
        Object[] assetValuesList = {"StarkCoin", "Bitconnect", "Dogecoin REEE", "NamCoin", "ThomasDollar", "SelwynPound", "Jasonuro"};
        String initAsset = "Jasonuro";
        Object SelectedAsset = JOptionPane.showInputDialog(this, "Select Asset to Delete",
                "Editing Details", JOptionPane.QUESTION_MESSAGE, null, assetValuesList, initAsset);

        JOptionPane.showMessageDialog(this, "Are you sure you want to delete?",
                "Confirm", JOptionPane.WARNING_MESSAGE);

    }

    private void editOrgClick (ActionEvent actionEvent) {
        Object[] organisationList = {"Bolton Clarke", "NDIA", "Feros Care", "myGen Physio", "better2care", "myPlan Manager", "Anglicare"};
        String initOrganisation = "NDIA";
        Object SelectedOrganisation = JOptionPane.showInputDialog(this, "Select Organisation to Edit",
                "Editing Details", JOptionPane.QUESTION_MESSAGE, null, organisationList, initOrganisation);

        JTextField editingName = new JTextField(10);
        editingName = newDisplay();
        editingName.setText(SelectedOrganisation.toString());
        JTextField editingStartPrice = new JTextField(10);

        JPanel editBoxPanel = new JPanel();
        editBoxPanel.add(new JLabel("Edit Name:"));
        editBoxPanel.add(editingName);
        editBoxPanel.add(Box.createVerticalStrut(15)); // a spacer
        editBoxPanel.add(new JLabel("Organisation Credit:"));
        editBoxPanel.add(editingStartPrice);
        JOptionPane.showConfirmDialog(this, editBoxPanel, "Please Edit Details", JOptionPane.OK_CANCEL_OPTION);
    }

    private void deleteOrgClick(ActionEvent actionEvent){
        Object[] assetValuesList = {"Bolton Clarke", "NDIA", "Feros Care", "myGen Physio", "better2care", "myPlan Manager", "Anglicare"};
        String initAsset = "NDIA";
        Object SelectedAsset = JOptionPane.showInputDialog(this, "Select organisation to delete",
                "Organisation Delete", JOptionPane.QUESTION_MESSAGE, null, assetValuesList, initAsset);

        JOptionPane.showMessageDialog(this, "Are you sure you want to delete?",
                "Confirm", JOptionPane.WARNING_MESSAGE);

    }

    private void createOrgClicked(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "Are you sure you want to create new organisation and credit balance?",
                "Confirm New Organisation", JOptionPane.OK_CANCEL_OPTION);
    }

















    private void closeButtonClicked(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "Closed",
                "Close", JOptionPane.OK_OPTION);

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
                new editAsset();
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
                        frame.dispose();
                    }
                });
            }
            else if(source == btnSettings){
                new addUser();
                frame.dispose();
            }
            else if(source == btnEdit){
                new editAsset();
                frame.dispose();
            }
            else if(source == btnOrderPad){
                new orderPad();
                frame.dispose();
            }
            else if (source == btnOrderHistory){
                new orderHistory();
                frame.dispose();
            }
        }}




}
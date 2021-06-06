package client.gui;

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
    JButton buyButton;
    JButton sellButton;


    JButton createAsset;
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



    public editAsset() {

        JFrame frame = new JFrame("Asset List Page");
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
        JPanel createOrgPanel = new JPanel();
        SpringLayout smoLayout = new SpringLayout();
        createOrgPanel.setLayout(smoLayout);
        createOrgPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Create New Organisation"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(createOrgPanel);
        tpLayout.putConstraint(SpringLayout.WEST, createOrgPanel, 0, SpringLayout.WEST, orderPanel);
        tpLayout.putConstraint(SpringLayout.EAST, createOrgPanel, 0, SpringLayout.EAST, orderPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, createOrgPanel, 20, SpringLayout.SOUTH, orderPanel);
        tpLayout.putConstraint(SpringLayout.SOUTH, createOrgPanel, -10, SpringLayout.SOUTH, orderTable);


        //Filling in the JLabels and JTextfields input createOrgPanel Finish later during integration
//        JLabel newAssetName = new JLabel("Name:");
//        orderPanel.add(newAssetName);
//        orderLayout.putConstraint(SpringLayout.WEST, newAssetName, 5, SpringLayout.WEST, orderPanel);
//        orderLayout.putConstraint(SpringLayout.NORTH, newAssetName, 5, SpringLayout.NORTH, orderPanel);
//
//
//        //Input the JTextfields at the top and implement functions given by Nam and Thomas
//        assetNameText = new JTextField();
//        orderPanel.add(assetNameText);
//        orderLayout.putConstraint(SpringLayout.WEST, assetNameText, 10, SpringLayout.EAST, newAssetName);
//        orderLayout.putConstraint(SpringLayout.EAST, assetNameText, -10, SpringLayout.EAST, orderPanel);
//        orderLayout.putConstraint(SpringLayout.NORTH, assetNameText, 3, SpringLayout.NORTH, orderPanel);
//
//        JLabel startPrice  = new JLabel("Start Price:");
//        orderPanel.add(startPrice);
//        orderLayout.putConstraint(SpringLayout.WEST, startPrice, 3, SpringLayout.WEST, newAssetName);
//        orderLayout.putConstraint(SpringLayout.NORTH, startPrice, 40, SpringLayout.NORTH, assetNameText);
//
//        //Declared it as a private limit textfield
//        startPriceText = new JTextField();
//        orderPanel.add(startPriceText);
//        orderLayout.putConstraint(SpringLayout.WEST, startPriceText, 10, SpringLayout.EAST, startPrice);
//        orderLayout.putConstraint(SpringLayout.EAST, startPriceText, 5, SpringLayout.EAST, orderPanel);
//        orderLayout.putConstraint(SpringLayout.NORTH, startPriceText, 40, SpringLayout.NORTH, orderPanel);
//
//
//        saveNew = createButton("Save As", this::saveAsClicked);
//        orderPanel.add(saveNew);
//        orderLayout.putConstraint(SpringLayout.WEST, saveNew, 110, SpringLayout.WEST, startPrice);
//        orderLayout.putConstraint(SpringLayout.NORTH, saveNew, 3, SpringLayout.SOUTH, startPriceText);
//

////////////////////////////////////Finished creating new asset












        ///Defining the Order Pad Panel (North  West - Middle) ///////////// ORDER PAD AND BUTTONS
        JPanel orderPad = new JPanel();
        SpringLayout padLayout = new SpringLayout();
        orderPad.setLayout(padLayout);
        orderPad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Asset Options"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(orderPad);
        tpLayout.putConstraint(SpringLayout.WEST, orderPad, 20, SpringLayout.EAST, orderPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, orderPad, 20, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.SOUTH, orderPad, 280, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.EAST, orderPad, -20, SpringLayout.EAST, orderTable);
        //Creating widgets and buttons for Order Pad


        //EDIT ASSET BUTTON
        editAsset = createButton("Edit Asset", this::editButtonClick);
        orderPad.add(editAsset);
        padLayout.putConstraint(SpringLayout.WEST, editAsset, 5, SpringLayout.WEST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, editAsset, 5, SpringLayout.NORTH, orderPad);

        deleteAsset = createButton("Delete Asset", this::deleteButtonClick);
        orderPad.add(deleteAsset);
        padLayout.putConstraint(SpringLayout.WEST, deleteAsset, 5, SpringLayout.WEST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, deleteAsset, 50, SpringLayout.NORTH, orderPad);




        //Button Action Listener



        /* show frame */
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
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
    }}
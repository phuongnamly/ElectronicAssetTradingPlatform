package client.gui;

import client.gui.clientData.NetworkDataSource;
import client.model.entity.Asset;
import client.model.entity.Inventory;
import client.model.entity.Organisation;
import client.model.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;

/**
 * This class is the ordering tab for user
 * */
public class orderPad extends JFrame{
    NetworkDataSource data;
    User user;
    String currentUsername;
    String assetIDText;

    private JTextArea display;

    private JTextField quantityText;
    private JTextField limitText;
    JButton buyButton;
    JButton sellButton;

    JButton btnClose;
    JButton btnReview;






    ////////
    ///Likely create another Java Class for the JButton declarations
    JButton btnHome;
    JButton btnOrderPad;
    JButton btnOrderHistory;
    JButton btnEdit; //Only for admin use??
    JButton btnSettings;
    JButton btnLogOut;

    JFrame frame;


    public orderPad() {
        // initialize database
        data = new NetworkDataSource();
        currentUsername = login.getCurrentUsernameUsername();

        frame = new JFrame("Ordering Tab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        //JPanel for the whole area, with layout table properties
        JPanel orderTable = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        orderTable.setLayout(tpLayout);
        orderTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order System"), BorderFactory.createEmptyBorder(5,5,5,5)));
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

        btnReview = createButton("Review", this::reviewButtonClicked);
        contentPane.add(btnReview);
        layout.putConstraint(SpringLayout.NORTH, btnReview, 10, SpringLayout.SOUTH, orderTable);
        layout.putConstraint(SpringLayout.SOUTH, btnReview, -10, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, btnReview, -10, SpringLayout.WEST, btnClose);



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

        btnOrderHistory = new JButton ("History");
        MainButtonPanel.add(btnOrderHistory);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderHistory, 160, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderHistory, 1, SpringLayout.NORTH, MainButtonPanel);

        btnEdit = new JButton("Edit");
        MainButtonPanel.add(btnEdit);
        mainLayout.putConstraint(SpringLayout.WEST, btnEdit, 250, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnEdit, 1, SpringLayout.NORTH, MainButtonPanel);

        btnSettings = new JButton("Settings");
        MainButtonPanel.add(btnSettings);
        mainLayout.putConstraint(SpringLayout.WEST, btnSettings,  320, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnSettings, 1, SpringLayout.NORTH, MainButtonPanel);


        btnLogOut = new JButton("Logout");
        MainButtonPanel.add(btnLogOut);
        //mainLayout.putConstraint(SpringLayout.WEST, btnLogOut, 5, SpringLayout.EAST, btnSettings);
        mainLayout.putConstraint(SpringLayout.EAST, btnLogOut, 10 , SpringLayout.EAST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnLogOut, 1, SpringLayout.NORTH, MainButtonPanel);

        //Defining the OrderPad Printout
        JPanel orderPanel = new JPanel();
        SpringLayout orderLayout = new SpringLayout();
        orderPanel.setLayout(orderLayout);
        orderPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order Description"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(orderPanel);
        tpLayout.putConstraint(SpringLayout.WEST,orderPanel, 20, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, orderPanel, 2, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.SOUTH, orderPanel, 50, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.EAST, orderPanel, 250, SpringLayout.WEST, orderTable);



        ///Defining the Order Pad Panel (North  West - Middle) ///////////// ORDER PAD AND BUTTONS
        JPanel orderPad = new JPanel();
        SpringLayout padLayout = new SpringLayout();
        orderPad.setLayout(padLayout);
        orderPad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order Pad"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(orderPad);
        tpLayout.putConstraint(SpringLayout.WEST, orderPad, 20, SpringLayout.EAST, orderPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, orderPad, 20, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.SOUTH, orderPad, 345, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.EAST, orderPad, -20, SpringLayout.EAST, orderTable);
        //Creating widgets and buttons for Order Pad

        JLabel assetName = new JLabel("Asset Name:");
        orderPad.add(assetName);
        padLayout.putConstraint(SpringLayout.WEST, assetName, 5, SpringLayout.WEST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, assetName, 5, SpringLayout.NORTH, orderPad);

//        ArrayList<Organisation> organisations = data.getOrganisations();
//        Organisation[] organisationArray = organisations.toArray(new Organisation[0]);
//        JComboBox<Organisation> organisationList = new JComboBox<Organisation>(organisationArray); //Move Declaration to top
//        orderTable.add(organisationList);
//        Organisation selectedOrganisation = (Organisation) organisationList.getSelectedItem();
//        organisationIDText = selectedOrganisation.getOrganisationID();

        ArrayList<Asset> assets = data.getAssets();
        Asset[] assetArray = assets.toArray(new Asset[0]);
        JComboBox<Asset> assetListBox = new JComboBox<Asset>(assetArray); //Move Declaration to top
        orderPad.add(assetListBox);
        Asset selectedOrganisation = (Asset) assetListBox.getSelectedItem();
        assetIDText = selectedOrganisation.getAssetID();

        padLayout.putConstraint(SpringLayout.WEST, assetListBox, 10, SpringLayout.EAST, assetName);
        padLayout.putConstraint(SpringLayout.EAST, assetListBox, -10, SpringLayout.EAST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, assetListBox, 3, SpringLayout.NORTH, orderPad);

        //Added it as JButton
        buyButton = new JButton("Buy");
        orderPad.add(buyButton);
        padLayout.putConstraint(SpringLayout.WEST, buyButton, -5, SpringLayout.WEST, assetName);
        padLayout.putConstraint(SpringLayout.NORTH, buyButton, 2, SpringLayout.SOUTH, assetListBox);

        //Added it as a JButton
        sellButton = new JButton("Sell");
        orderPad.add(sellButton);
        padLayout.putConstraint(SpringLayout.WEST, sellButton, 20, SpringLayout.EAST, buyButton);
        padLayout.putConstraint(SpringLayout.NORTH, sellButton, 2, SpringLayout.SOUTH, assetListBox);

        JLabel accountLabel  = new JLabel("Account Type:");
        orderPad.add(accountLabel);
        padLayout.putConstraint(SpringLayout.WEST, accountLabel, 5, SpringLayout.WEST, buyButton);
        padLayout.putConstraint(SpringLayout.NORTH, accountLabel, 40, SpringLayout.NORTH, sellButton);

        JComboBox<String> accountListBox = new JComboBox<String>();
        orderPad.add(accountListBox);
        padLayout.putConstraint(SpringLayout.WEST, accountListBox, 10, SpringLayout.EAST, accountLabel);
        padLayout.putConstraint(SpringLayout.EAST, accountListBox, 5, SpringLayout.EAST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, accountListBox, 70, SpringLayout.NORTH, orderPad);

        JLabel quantityLabel  = new JLabel("Unit Quantity:");
        orderPad.add(quantityLabel);
        padLayout.putConstraint(SpringLayout.WEST, quantityLabel, 5, SpringLayout.WEST, accountLabel);
        padLayout.putConstraint(SpringLayout.NORTH, quantityLabel, 40, SpringLayout.NORTH, accountListBox);

        //Declared them as private quantitytext textfield
        quantityText = new JTextField();
        orderPad.add(quantityText);
        padLayout.putConstraint(SpringLayout.WEST, quantityText, 10, SpringLayout.EAST, quantityLabel);
        padLayout.putConstraint(SpringLayout.EAST, quantityText, 5, SpringLayout.EAST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, quantityText, 105, SpringLayout.NORTH, orderPad);

        JLabel limitLabel  = new JLabel("Limit Price:");
        orderPad.add(limitLabel);
        padLayout.putConstraint(SpringLayout.WEST, limitLabel, 5, SpringLayout.WEST, quantityLabel);
        padLayout.putConstraint(SpringLayout.NORTH, limitLabel, 40, SpringLayout.NORTH, quantityText);

        //Declared it as a private limit textfield
        limitText = new JTextField();
        orderPad.add(limitText);
        padLayout.putConstraint(SpringLayout.WEST, limitText, 10, SpringLayout.EAST, limitLabel);
        padLayout.putConstraint(SpringLayout.EAST, limitText, 5, SpringLayout.EAST, orderPad);
        padLayout.putConstraint(SpringLayout.NORTH, limitText, 145, SpringLayout.NORTH, orderPad);



        //JASON CHANGED AREA SOUTH WEST ORDER PAD

        // Defining the graph area (South West)
        JPanel buyPanel= new JPanel();
        SpringLayout smoLayout = new SpringLayout();
        buyPanel.setLayout(smoLayout);
        buyPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("List of Buyers"), BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(buyPanel);
        tpLayout.putConstraint(SpringLayout.WEST, buyPanel, 0, SpringLayout.WEST, orderPanel);
        tpLayout.putConstraint(SpringLayout.EAST, buyPanel, 0, SpringLayout.EAST, orderPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, buyPanel, 5, SpringLayout.SOUTH, orderPanel);
        tpLayout.putConstraint(SpringLayout.SOUTH, buyPanel, -115, SpringLayout.SOUTH, orderTable);
        /* ADD OTHER WIDGETS */


        JPanel sellPanel = new JPanel();
        SpringLayout sellLayout = new SpringLayout();
        sellPanel.setLayout(sellLayout);
        sellPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("List of Sellers"), BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(sellPanel);
        tpLayout.putConstraint(SpringLayout.WEST, sellPanel, 0, SpringLayout.WEST, buyPanel);
        tpLayout.putConstraint(SpringLayout.EAST, sellPanel, 0, SpringLayout.EAST, buyPanel);
        tpLayout.putConstraint(SpringLayout.NORTH, sellPanel, 5, SpringLayout.SOUTH, buyPanel);
        tpLayout.putConstraint(SpringLayout.SOUTH, sellPanel, 5, SpringLayout.SOUTH, orderTable);

        String[] sellHeader = {"Price", "Quantity", "Type"};

        ArrayList<Asset> assetList = data.getListingsByAsset(new Asset());
        // https://stackoverflow.com/questions/12559287/how-to-set-a-custom-object-in-a-jtable-row
        String[][] sellOngoing = new String[assetList.size()][sellHeader.length];

        int i = 0;
        for (Asset asset : assetList) {
            String[] data = new String[sellHeader.length];
            data[0] = asset.getAssetType();
            data[1] = asset.getAssetName();
            data[2] = asset.getQuantity();
            sellOngoing[i] = data;
            i++;
        }

        String[] buyHeader = {"Name", "Price", "Quantity", "Type"};
        String[][] buyOngoing = {
                {"jshin4113", "12.32", "198", "BUY"},
                {"thomas", "14.43", "193", "BUY"},
                {"Nam", "15.23", "9", "BUY"},
                {"Cimothy", "20", "341", "BUY"},
                {"Thappell", "24", "5", "BUY"},

        };





        JTable sellOrders = new JTable(sellOngoing, sellHeader);
        JTable buyOrders = new JTable(buyOngoing, buyHeader);
        JScrollPane sellScroll = new JScrollPane(sellOrders);
        JScrollPane buyScroll = new JScrollPane(buyOrders);
        sellScroll.setPreferredSize(new Dimension(210, 85));
        buyScroll.setPreferredSize(new Dimension(210, 85));
        sellPanel.add(sellScroll);
        buyPanel.add(buyScroll);

        //Button Action Listener



        /* show frame */
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
        addButtonListeners(new ButtonListener());

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
                new orderPad();
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
}}}
package client.gui;

import com.sun.glass.ui.Accessible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * This class is for viewing history or orders
 * */
public class orderHistory extends JFrame{

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





    //Table to input all the current orders list to view successful and ongoing orders
    //Cannot delete successful orders
    //Able to delete ongoing orders
    //Able to edit current orders
    //Testing the JTable Features

    JFrame frame;

    public orderHistory() {

        frame = new JFrame("Order History");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        display = newDisplay();


        //JPanel for the actionable area where it includes the ability to view balances and butons but excludes tables
        JPanel orderTable = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        orderTable.setLayout(tpLayout);
        orderTable.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order History"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(orderTable);
        layout.putConstraint(SpringLayout.WEST, orderTable, 5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, orderTable, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, orderTable, -50, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, orderTable, -5, SpringLayout.EAST, contentPane);


        //Viewing Balance
        JLabel avaliableBalance = new JLabel("Avaliable Balance: ");
        orderTable.add(avaliableBalance);
        tpLayout.putConstraint(SpringLayout.WEST, avaliableBalance, 5, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, avaliableBalance, 5, SpringLayout.NORTH, orderTable);

        //View Balannce (Input ActionListener to check (NOTE TO DEV, please put quantityText at top
        JTextField viewAvaliable = new JTextField();
        orderTable.add(viewAvaliable);
        tpLayout.putConstraint(SpringLayout.WEST, viewAvaliable, 10, SpringLayout.EAST, avaliableBalance);
        tpLayout.putConstraint(SpringLayout.EAST, viewAvaliable, 5, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, viewAvaliable, 6, SpringLayout.NORTH, orderTable);

        JLabel totalBalance = new JLabel("Total Balance: ");
        orderTable.add(totalBalance);
        tpLayout.putConstraint(SpringLayout.WEST, totalBalance, 1, SpringLayout.WEST, avaliableBalance);
        tpLayout.putConstraint(SpringLayout.NORTH, totalBalance, 40, SpringLayout.NORTH, avaliableBalance);

        JTextField viewTotal = new JTextField();
        orderTable.add(viewTotal);
        tpLayout.putConstraint(SpringLayout.WEST, viewTotal, 10, SpringLayout.EAST, totalBalance);
        tpLayout.putConstraint(SpringLayout.EAST, viewTotal, 5, SpringLayout.EAST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, viewTotal,  45, SpringLayout.NORTH, orderTable);



        //JPanel for the order view Table and to edit
        JPanel orderListPanel = new JPanel();
        SpringLayout listLayout = new SpringLayout();
        orderListPanel.setLayout(listLayout);
        orderListPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("List of orders"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        orderTable.add(orderListPanel);

        tpLayout.putConstraint(SpringLayout.WEST, orderListPanel, 2, SpringLayout.WEST, orderTable);
        tpLayout.putConstraint(SpringLayout.NORTH, orderListPanel, 120, SpringLayout.NORTH, orderTable);
        tpLayout.putConstraint(SpringLayout.SOUTH, orderListPanel, -10, SpringLayout.SOUTH, orderTable);
        tpLayout.putConstraint(SpringLayout.EAST, orderListPanel, -2, SpringLayout.EAST, orderTable);



        //NOTE STRING VIEWORDER TO BE REPLACED DURING INTEGRATION, TEMP replacement to test JTable/ScrollPane
        String[] header = {"Username", "Asset Type", "Func", "Limit Price", "Quantity", "Status", "View"};
        String[][] viewOrder = {{"jshin4113", "jasonDollar","BUY","69", "13543", "Filled", "Edit"},
                {"ASAF","SelwynPound", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","SelwynPound", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASdasdAF","ThomasDOllar", "BUY", "1", "1223", "13245135", "Ongoing", "Edit"},
                {"ASAF","SelwynPound", "SELL", "0.01", "1213", "13245135", "Ongoing", "Edit"},
                {"ASAF","Selwyncoin", "SELL", "0.01", "12431323", "13245135", "Ongoing", "Edit"},
                {"ASAF","JasonCoin", "SELL", "1241", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","NamDollar", "SELL", "4141", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","SelwynPound", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","Namcoin", "SELL", "0.14301", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","Thomaseuro", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","NamDong", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","Selwyuan", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","JasonWon", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","Nguyencoin", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},
                {"ASAF","TuanMinhYen", "SELL", "0.01", "123", "13245135", "Ongoing", "Edit"},

        };


        JTable listOrders = new JTable(viewOrder, header);
        JScrollPane scrollOrder = new JScrollPane(listOrders);
        scrollOrder.setPreferredSize(new Dimension(480, 130));

        orderListPanel.add(scrollOrder);















        //Creating MainButtonPanel
        //This main button panel is inclusive of home, order and order history etc.
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









        //Button for Home in Main Panel
        btnHome = createButton("Home", this::homeButtonClicked);
        MainButtonPanel.add(btnHome);
        mainLayout.putConstraint(SpringLayout.WEST, btnHome, 5, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnHome, 1, SpringLayout.NORTH, MainButtonPanel);

        //Button for Ordering Pad
        btnOrderPad = createButton("Order", this::orderButtonClicked);
        MainButtonPanel.add(btnOrderPad);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderPad, 80, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderPad, 1, SpringLayout.NORTH, MainButtonPanel);

        btnOrderHistory = new JButton ("History");
        MainButtonPanel.add(btnOrderHistory);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderHistory, 160, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderHistory, 1, SpringLayout.NORTH, MainButtonPanel);

        btnEdit = createButton("Edit", this::editButtonClicked);
        MainButtonPanel.add(btnEdit);
        mainLayout.putConstraint(SpringLayout.WEST, btnEdit, 250, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnEdit, 1, SpringLayout.NORTH, MainButtonPanel);

        btnSettings = createButton("Register", this::registerButtonClicked);
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







//        ////BRAND NEW ASSET CREATION
//        JPanel orderPanel = new JPanel();
//        SpringLayout orderLayout = new SpringLayout();
//        orderPanel.setLayout(orderLayout);
//        orderPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Create New Asset"),
//                BorderFactory.createEmptyBorder(5,5,5,5)));
//        orderTable.add(orderPanel);
//        tpLayout.putConstraint(SpringLayout.WEST,orderPanel, 20, SpringLayout.WEST, orderTable);
//        tpLayout.putConstraint(SpringLayout.NORTH, orderPanel, 20, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.SOUTH, orderPanel, 160, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.EAST, orderPanel, 250, SpringLayout.WEST, orderTable);
//












        ///Commmented out unnecessary
//        JLabel newAssetName = new JLabel("Name:");
//        orderPanel.add(newAssetName);
//        orderLayout.putConstraint(SpringLayout.WEST, newAssetName, 5, SpringLayout.WEST, orderPanel);
//        orderLayout.putConstraint(SpringLayout.NORTH, newAssetName, 5, SpringLayout.NORTH, orderPanel);
//
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
//




//        ///Defining the Order Pad Panel (North  West - Middle) ///////////// ORDER PAD AND BUTTONS
//        JPanel orderPad = new JPanel();
//        SpringLayout padLayout = new SpringLayout();
//        orderPad.setLayout(padLayout);
//        orderPad.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Asset Options"),
//                BorderFactory.createEmptyBorder(5,5,5,5)));
//        orderTable.add(orderPad);
//        tpLayout.putConstraint(SpringLayout.WEST, orderPad, 20, SpringLayout.EAST, orderPanel);
//        tpLayout.putConstraint(SpringLayout.NORTH, orderPad, 20, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.SOUTH, orderPad, 280, SpringLayout.NORTH, orderTable);
//        tpLayout.putConstraint(SpringLayout.EAST, orderPad, -20, SpringLayout.EAST, orderTable);
//        //Creating widgets and buttons for Order Pad

//
//        //EDIT ASSET BUTTON
//        editAsset = createButton("Edit Asset", this::editButtonClick);
//        orderPad.add(editAsset);
//        padLayout.putConstraint(SpringLayout.WEST, editAsset, 5, SpringLayout.WEST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, editAsset, 5, SpringLayout.NORTH, orderPad);
//
//        deleteAsset = createButton("Delete Asset", this::deleteButtonClick);
//        orderPad.add(deleteAsset);
//        padLayout.putConstraint(SpringLayout.WEST, deleteAsset, 5, SpringLayout.WEST, orderPad);
//        padLayout.putConstraint(SpringLayout.NORTH, deleteAsset, 50, SpringLayout.NORTH, orderPad);
//



        //Button Action Listener



        /* show frame */
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
        addButtonListeners(new orderHistory.ButtonListener());
    }


    ///WHEN CLICKED ORDER BUTTON WILL OPEN UP ORDER PANEL (THIS.SETVISIBLE SHOULD WORK HOWEVER IT IS NOT WANTING TO CLOSE?? Will wait until final implementation to check

    //PLEASE CHECK THIS LATER TODAY


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



    private void orderButtonClicked (ActionEvent actionEvent){
        new orderPad();
        this.dispose();
    }
    private void homeButtonClicked (ActionEvent actionEvent){
        new homePage();
        this.dispose();
    }
    private void editButtonClicked (ActionEvent actionEvent){
        new editAsset();
        this.dispose();
    }
    private void registerButtonClicked (ActionEvent actionEvent){
        new addUser();
        this.dispose();
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
                new orderHistory();
            }
        });
    }    private void addButtonListeners(ActionListener listener) {
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




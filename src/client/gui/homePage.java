package client.gui;

import client.gui.clientData.NetworkDataSource;
import client.model.entity.Inventory;
import client.model.entity.Organisation;
import client.model.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class homePage extends JFrame{
    NetworkDataSource data;
    User user;
    Organisation organisation;
    ArrayList<Inventory> inventoryList;
    String userField;

    private JTextField display;
    private JLabel welcomeText;

    JTextArea adviceText; //Changed to JTextField

    ///Likely create another Java Class for the JButton declarations
    JButton btnHome;
    JButton btnOrderPad;
    JButton btnOrderHistory;
    JButton btnEdit; //Only for admin use??
    JButton btnSettings;
    JButton btnLogOut;

    JFrame frame;

    // Creating a constructor for the Homepage Window
    public homePage() {
        // initialize database
        data = new NetworkDataSource();
        String currentUsername = login.getCurrentUsernameUsername();
        user = data.getUser(currentUsername).get(0);
        organisation = data.getOrganisation(user.getOrganisationID()).get(0);
        inventoryList = data.getInventoriesByOrganisationId(user.getOrganisationID());


        // Initialising new JFrame instance
        frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        display = newDisplay();


        // Homepage JPanel
        JPanel homePanel = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        homePanel.setLayout(tpLayout);
        homePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Homepage"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(homePanel);

        // Homepage JPanel Formatting
        layout.putConstraint(SpringLayout.WEST, homePanel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, homePanel, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, homePanel, -50, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, homePanel, -10, SpringLayout.EAST, contentPane);

        ///////Setting the Homepage Panel to introduce the user and prints out the username in JTextfield
        ///////Gives a small brief introduction to how to navigate the system
        userField = user.getUsername();
//        welcomeText = new JLabel();
//        welcomeText.setText("Welcome back," + " " + userField);
//        welcomeText.setFont(new Font("Comic Sans", Font.PLAIN, 25));
//        homePanel.add(welcomeText);
//        tpLayout.putConstraint(SpringLayout.NORTH, welcomeText, 5, SpringLayout.NORTH, homePanel);

        // Creating Navigation Panel
        JPanel navPane = new JPanel();
        SpringLayout mainLayout = new SpringLayout();
        navPane.setLayout(mainLayout);
        navPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Navigation"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(navPane);
        layout.putConstraint(SpringLayout.WEST, navPane, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, navPane, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, navPane, -280, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, navPane, -10, SpringLayout.EAST, contentPane);

        //Button for Home in Main Panel
        btnHome = new JButton("Home");
        navPane.add(btnHome);
        mainLayout.putConstraint(SpringLayout.WEST, btnHome, 5, SpringLayout.WEST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnHome, 1, SpringLayout.NORTH, navPane);

        //Button for Ordering Pad
        btnOrderPad = new JButton("Order");
        navPane.add(btnOrderPad);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderPad, 80, SpringLayout.WEST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderPad, 1, SpringLayout.NORTH, navPane);

        // Button for Order History
        btnOrderHistory = new JButton("History");
        navPane.add(btnOrderHistory);
        mainLayout.putConstraint(SpringLayout.WEST, btnOrderHistory, 160, SpringLayout.WEST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnOrderHistory, 1, SpringLayout.NORTH, navPane);

        // Button for Edit
        btnEdit = new JButton("Edit");
        navPane.add(btnEdit);
        mainLayout.putConstraint(SpringLayout.WEST, btnEdit, 250, SpringLayout.WEST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnEdit, 1, SpringLayout.NORTH, navPane);

        // Button for Settings
        btnSettings = new JButton("Settings");
        navPane.add(btnSettings);
        mainLayout.putConstraint(SpringLayout.WEST, btnSettings,  320, SpringLayout.WEST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnSettings, 1, SpringLayout.NORTH, navPane);

        // Button to Log Out
        btnLogOut = new JButton("Logout");
        navPane.add(btnLogOut);
        mainLayout.putConstraint(SpringLayout.EAST, btnLogOut, 10 , SpringLayout.EAST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnLogOut, 1, SpringLayout.NORTH, navPane);

        // Section for welcome subtext
        JLabel subtextLabel = new JLabel();
        JPanel subtextPanel = new JPanel();
        SpringLayout subtextLayout = new SpringLayout();
        String subtext = "Welcome back, "+userField+ ". Your organisation have " + organisation.getCredits()+" credits.";
        subtextLabel.setText(subtext);
        subtextPanel.add(subtextLabel);
        homePanel.add(subtextPanel);


        // where to show organisation credit and assets
        //JPanel for the order view Table and to edit
        JPanel orderListPanel = new JPanel();
        SpringLayout listLayout = new SpringLayout();
        orderListPanel.setLayout(listLayout);
        orderListPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("List of assets"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        homePanel.add(orderListPanel);

        tpLayout.putConstraint(SpringLayout.WEST, orderListPanel, 2, SpringLayout.WEST, homePanel);
        tpLayout.putConstraint(SpringLayout.NORTH, orderListPanel, 120, SpringLayout.NORTH, homePanel);
        tpLayout.putConstraint(SpringLayout.SOUTH, orderListPanel, -10, SpringLayout.SOUTH, homePanel);
        tpLayout.putConstraint(SpringLayout.EAST, orderListPanel, -2, SpringLayout.EAST, homePanel);



        //NOTE STRING VIEWORDER TO BE REPLACED DURING INTEGRATION, TEMP replacement to test JTable/ScrollPane
        String[] header = {"Asset Name","Asset Type","Quantity"};

        // https://stackoverflow.com/questions/12559287/how-to-set-a-custom-object-in-a-jtable-row
        String[][] inventoryData = new String[inventoryList.size()][header.length];
        int i = 0;
        for (Inventory inventory : inventoryList) {
            String[] data = new String[header.length];
            data[0] = inventory.getAssetID();
            data[1] = inventory.getOrganisationID();
            data[2] = inventory.getQuantity();
            inventoryData[i] = data;
            i++;
        }


            //
        JTable listOrders = new JTable(inventoryData, header);
        JScrollPane scrollOrder = new JScrollPane(listOrders);
        scrollOrder.setPreferredSize(new Dimension(480, 130));

        orderListPanel.add(scrollOrder);
        // Compile frame
        frame.pack();
        frame.setSize(550,475);
        frame.setVisible(true);
        addButtonListeners(new homePage.ButtonListener());
    }

    private JTextField newDisplay() {

        display = new JTextField();
        display.setEditable(true);
        //display.setLineWrap(true);
        display.setFont(new Font("Arial",Font.BOLD, 13));
        // display.setBorder(BorderFactory.createEtchedBorder());
        return display;

    }


    // Main function to run Homepage GUI
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new homePage();
            }
        });
    }
    private void addButtonListeners(ActionListener listener) {
        btnHome.addActionListener(listener);
        btnOrderHistory.addActionListener(listener);
        btnOrderPad.addActionListener(listener);
        btnEdit.addActionListener(listener);
        btnSettings.addActionListener(listener);
        btnLogOut.addActionListener(listener);
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
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new addUser();
                        frame.dispose();
                    }
                });
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
            else if (source == btnLogOut){
                login.setCurrentUsernameUsername("");
                new login();
                frame.dispose();
            }
        }}

//    private class VstTableItemModel extends AbstractTableModel {
//
//        private List<User> users;
//
//        public VstTableItemModel(List<User> users) {
//
//            this.users = new ArrayList<User>(users);
//
//        }
//
//        @Override
//        public int getRowCount() {
//            return users.size();
//        }
//
//        @Override
//        public int getColumnCount() {
//            return 6;
//        }
//
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//
//            Object value = "??";
//            User user = users.get(rowIndex);
//            switch (columnIndex) {
//                case 0:
//                    value = user.getUserUsername();
//                    break;
//                case 1:
//                    value = user.getUserName();
//                    break;
//                case 2:
//                    value = user.getUserPhone();
//                    break;
//                case 3:
//                    value = user.getUserNic();
//                    break;
//                case 4:
//                    value = user.getUserAddress();
//                    break;
//                case 5:
//                    value = user.getUserEmail();
//                    break;
//            }
//
//            return value;
//
//        }
//
//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//            return // Return the class that best represents the column...
//        }
//
//    /* Override this if you want the values to be editable...
//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        //....
//    }
//    */
//
//        /**
//         * This will return the user at the specified row...
//         * @param row
//         * @return
//         */
//        public User getUserAt(int row) {
//            return users.get(row);
//        }
//
//    }
}
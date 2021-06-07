package client.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class homePage extends JFrame{

    private JTextField display;
    private JLabel welcomeText;

    JTextArea adviceText; //Changed to JTextField

    String userField;








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

        // Welcome Text Panel
//        JPanel welcomeTextPane = new JPanel();
//        SpringLayout welcomeLayout = new SpringLayout();
//        welcomeTextPane.setLayout(welcomeLayout);
//        contentPane.add(welcomeTextPane);
//        welcomeLayout.putConstraint(SpringLayout.WEST, homePanel, 10, SpringLayout.WEST, contentPane);
//        welcomeLayout.putConstraint(SpringLayout.NORTH, homePanel, 10, SpringLayout.NORTH, contentPane);
//        welcomeLayout.putConstraint(SpringLayout.SOUTH, homePanel, 10, SpringLayout.SOUTH, contentPane);
//        welcomeLayout.putConstraint(SpringLayout.EAST, homePanel, 10, SpringLayout.EAST, contentPane);
//





        ///////Setting the Homepage Panel to introduce the user and prints out the username in JTextfield
        ///////Gives a small brief introduction to how to navigate the system


        userField = "Jeremy Nguyen";
        welcomeText = new JLabel();
        welcomeText.setText("Welcome back," + " " + userField + "!");
        welcomeText.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        homePanel.add(welcomeText);
        tpLayout.putConstraint(SpringLayout.NORTH, welcomeText, 5, SpringLayout.NORTH, homePanel);



        adviceText = new JTextArea();
        homePanel.add(adviceText);

        adviceText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        adviceText.setText("Hello There. Welcome to our Electronic Asset Trading Platform!                              " +
                "If you are new to this, please don't be afraid, we have some info for you see!" +
                "                                                                              " +
                "                                                            " +
                "1. To place an order, please click on the 'Order' button in the Main panel. Once arrived, " +
                "please choose the asset you wish to trade, select either BUY/SELL and input your price" +
                " into 'Limit', which is the price you wish to purchase or sell the asset at." +
                "                                                                         " +
                "                                                                         " +
                "2. Click review to process. NOTE: Order may not go thru immediately" +
                "                                                                         " +
                "                                                                         " +

                "3. To view, edit and see the history of orders, head to 'Order History'. " +
                "Find ones you wish to view. " +
                "ADMIN ONLY given special access to edit and update asset in 'Edit'.      HAPPY HUNTING!"
        );
        adviceText.setEditable(false);
        adviceText.setLineWrap(true);
        adviceText.setColumns(45);
        adviceText.setRows(12);
        adviceText.setWrapStyleWord(true);

        tpLayout.putConstraint(SpringLayout.NORTH, adviceText, 40, SpringLayout.NORTH, welcomeText);
//        tpLayout.putConstraint(SpringLayout.EAST, adviceText, 1, SpringLayout.EAST, homePanel);
//        tpLayout.putConstraint(SpringLayout.WEST, adviceText, 5, SpringLayout.WEST, homePanel);
//        tpLayout.putConstraint(SpringLayout.SOUTH, adviceText, -30, SpringLayout.SOUTH, homePanel);


        //tpLayout.putConstraint(SpringLayout.WEST, adviceText, 5, SpringLayout.WEST, homePanel);











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
            else if (source == btnLogOut){
                new login();
                frame.dispose();
            }
        }}



}
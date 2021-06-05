
package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Homepage extends JFrame{

    private JTextField display;
    private JLabel welcomeText;
    private JLabel adviceText;

    // VARIABLE FOR USERNAME
    private String userField;


    ///Likely create another Java Class for the JButton declarations
    JButton btnHome;
    JButton btnOrderPad;
    JButton btnOrderHistory;
    JButton btnEdit; //Only for admin use??
    JButton btnSettings;
    JButton btnLogOut;


// Creating a constructor for the Homepage Window
    public Homepage() {

        // Initialising new JFrame instance
        JFrame frame = new JFrame("Home");
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
        JPanel welcomeTextPane = new JPanel();
        SpringLayout welcomeLayout = new SpringLayout();
        welcomeTextPane.setLayout(welcomeLayout);
        welcomeLayout.putConstraint(SpringLayout.SOUTH, welcomeTextPane, 1, SpringLayout.SOUTH, homePanel);
        welcomeLayout.putConstraint(SpringLayout.EAST, welcomeTextPane, 350, SpringLayout.EAST, homePanel);

        userField = "Jeremy";
        welcomeText = new JLabel();
        welcomeText.setText("Welcome back," + " " + userField + "!");
        welcomeText.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        welcomeTextPane.add(welcomeText);


        // Advice Text Panel
        JPanel advicePane = new JPanel();
        SpringLayout adviceLayout = new SpringLayout();
        advicePane.setLayout(adviceLayout);

        adviceText =  new JLabel();
        adviceText.setText("To get started, click a button above to take you elsewhere.");
        advicePane.add(adviceText);


        // Adding text in HomePanel
        homePanel.add(welcomeTextPane);
        homePanel.add(advicePane);


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
        mainLayout.putConstraint(SpringLayout.EAST, btnLogOut, 1 , SpringLayout.EAST, navPane);
        mainLayout.putConstraint(SpringLayout.NORTH, btnLogOut, 1, SpringLayout.NORTH, navPane);

        // Compile frame
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


    // Main function to run Homepage GUI
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage();
            }
        });
    }}
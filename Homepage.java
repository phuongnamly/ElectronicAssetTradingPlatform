
package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Homepage extends JFrame{

    private JTextField display;



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


        //JPanel for the whole area, with layout table properties
        JPanel homePanel = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        homePanel.setLayout(tpLayout);
        homePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Homepage"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(homePanel);

        layout.putConstraint(SpringLayout.WEST, homePanel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, homePanel, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, homePanel, -50, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, homePanel, -10, SpringLayout.EAST, contentPane);


        // Creating Navigation Panel
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
        mainLayout.putConstraint(SpringLayout.WEST, btnSettings,  320, SpringLayout.WEST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnSettings, 1, SpringLayout.NORTH, MainButtonPanel);

        btnLogOut = new JButton("Logout");
        MainButtonPanel.add(btnLogOut);
        //mainLayout.putConstraint(SpringLayout.WEST, btnLogOut, 5, SpringLayout.EAST, btnSettings);
        mainLayout.putConstraint(SpringLayout.EAST, btnLogOut, 10 , SpringLayout.EAST, MainButtonPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, btnLogOut, 1, SpringLayout.NORTH, MainButtonPanel);




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
                new Homepage();
            }
        });
    }}
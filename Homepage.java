package client;

import javax.swing.*;
import java.awt.*;

// This is the class which holds the landing page for the program
// after login.
public class Homepage extends JFrame {

    // Homepage content


    // Homepage Constructor
    public Homepage(){

        // Creating instance of JFrame
        super("Home");
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating Homepage JPanels
        JPanel navPanel = new JPanel();
        JPanel contentPanel = new JPanel();

        // Setting JPanel parameters
        navPanel.setBackground(Color.green);

        // Frame parameters
        frame.setSize(500, 500);

        // Add content to Homepage
        frame.add(navPanel);

        // Make Window Visible
        frame.setVisible(true);

    }

// Main to run Homepage
    public static void main(String[] args){
        new Homepage();
    }

}



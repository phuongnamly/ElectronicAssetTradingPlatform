package client;


import javax.swing.*;
import java.awt.*;


class ServerError extends JFrame{

    private JTextField display;
    private JLabel errorText;

    JTextArea errorTextArea; //Changed to JTextField

    // Creating a constructor for the Server Error Window
    public ServerError() {

        // Initialising new JFrame instance
        JFrame frame = new JFrame("Server Error");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        display = newDisplay();


        // Server Error JPanel
        JPanel homePanel = new JPanel();
        SpringLayout tpLayout = new SpringLayout();
        homePanel.setLayout(tpLayout);
        contentPane.add(homePanel);


        // Server Error JPanel Formatting
        layout.putConstraint(SpringLayout.WEST, homePanel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, homePanel, 70, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, homePanel, -50, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, homePanel, -10, SpringLayout.EAST, contentPane);


        // Creating main error message for window

        String errorMessage = ErrorRoulette();
        errorText = new JLabel();
        errorText.setText(errorMessage);
        errorText.setFont(new Font("Comic Sans", Font.PLAIN, 22));
        contentPane.add(errorText);
        tpLayout.putConstraint(SpringLayout.NORTH, errorText, 5, SpringLayout.NORTH, contentPane);
        tpLayout.putConstraint(SpringLayout.WEST, errorText, 5, SpringLayout.EAST, contentPane);


        errorTextArea = new JTextArea();
        homePanel.add(errorTextArea);

        errorTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        errorTextArea.setText("Unfortunately, a server error has occurred. This could be due to the following:\n\n" +
                "1. A poor connection\n" +
                "2. Something else\n\n" +
                "Please restart the program."
        );
        errorTextArea.setEditable(false);
        errorTextArea.setLineWrap(true);
        errorTextArea.setColumns(45);
        errorTextArea.setRows(12);
        errorTextArea.setWrapStyleWord(true);

        tpLayout.putConstraint(SpringLayout.NORTH, errorTextArea, 40, SpringLayout.NORTH, errorText);


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

    // Function to make error messages a tad more fun
    private String ErrorRoulette(){

        // Creating variables
        String errorMessage = new String();
        double randomWizard = Math.random();

        // Deciding on error message
        if (randomWizard < 0.2)
            errorMessage = "An error has occurred.";
        else if (randomWizard < 0.4 && randomWizard >= 0.2)
            errorMessage = "I'm sorry, but I don't think we have anything in common.";
        else if (randomWizard < 0.6 && randomWizard >= 0.4)
            errorMessage = "She might come back one day.";
        else if (randomWizard < 0.8 && randomWizard >= 0.6)
            errorMessage = "Do you like Huey Lewis and the News?";
        else
            errorMessage = "You're going to be fine.";

        // Returning error message
        return errorMessage;
    }


    // Main function to run Homepage GUI
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ServerError();
            }
        });
    }}
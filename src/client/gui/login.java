package client.gui;

import client.gui.clientData.NetworkDataSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.hash.Hash.getHashedPassword;

//import static utils.hash.Hash.getHashedPassword;


public class login extends JFrame{
    private static String currentUsername;

    /**
     * @return the username
     */
    public static String getCurrentUsernameUsername() {
        return currentUsername;
    }

    /**
     * @param username the name to set
     */
    public static void setCurrentUsernameUsername(String username) {
        currentUsername = username;
    }

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    JButton signIn;
    JButton signUp;

    NetworkDataSource data;

    public login (){
        super("Homepage Trading App");

        currentUsername = "";
        // if(str != null && !str.isEmpty())
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.LIGHT_GRAY);

        JMenuBar menuBar = new JMenuBar();
        JMenu trademenu = new JMenu("Trade");
        trademenu.add("Buy");
        trademenu.add("Sell");
        JMenu orderMenu = new JMenu("Orders");
        JMenu findMenu = new JMenu("Find");
        JMenu accountMenu = new JMenu("Account");
        JMenu settingMenu = new JMenu("Settings");
        menuBar.add(trademenu);
        menuBar.add(orderMenu);
        menuBar.add(findMenu);
        menuBar.add(accountMenu);
        menuBar.add(settingMenu);


        setJMenuBar(menuBar);

        JFrame signinframe = new JFrame();
        JPanel signinpanel = new JPanel();


        signinpanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        signinpanel.setBorder(BorderFactory.createTitledBorder("Signin or Register"));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,5,5,5 );

        usernameTextField = new JTextField(10);
        passwordTextField = new JTextField(10);

        signinpanel.add(new JLabel("USERNAME"), constraints);
        constraints.gridy++;

        signinpanel.add(usernameTextField, constraints);

        constraints.gridy++;


        signinpanel.add(new JLabel("Password"), constraints);

        constraints.gridy++;

        signinpanel.add(passwordTextField, constraints);

        constraints.gridy++;

        signIn = new JButton("Sign In");
        signUp = new JButton("Sign Up");

        signinpanel.add(signIn, constraints);
        constraints.gridy++;
        signinpanel.add(signUp, constraints);
        constraints.gridy++;
        constraints.gridy++;


        setLayout(new GridBagLayout());
        pack();
        add(signinpanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);


        addButtonListeners(new ButtonListener());

        pack();

        setVisible(true);

//         initialize database
        data = new NetworkDataSource();
    }

    private void signinButtonClick(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(this, "Please ensure that you have filled in it correctly!",
                "Signup Error", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Adds a listener to the new, save and delete buttons
     */
    private void addButtonListeners(ActionListener listener) {
        signIn.addActionListener(listener);
        signUp.addActionListener(listener);
    }
    private void wrongDetailsClicked(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(this, "Please enter correct details!", "WARNING",
                JOptionPane.WARNING_MESSAGE);

    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new login();
            }
        });
    }
    /**
     * Handles events for the three buttons on the UI.
     *
     * @author Malcolm Corney
     * @version $Id: Exp $
     *
     */
    private class ButtonListener extends Component implements ActionListener {

        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();

            if(source == signIn) {

                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String hashedPassword = getHashedPassword(password);

                if(data.login(username, hashedPassword)){
                    // go to home page here
                    System.out.println("Log in successful");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            setCurrentUsernameUsername(username);
                            new homePage();
                            dispose();
                        }
                    });

                }
                else{
                    JOptionPane.showMessageDialog(this,"Please ensure you have the correct details," +
                            "if not, please contact admin", "WARNING", JOptionPane.WARNING_MESSAGE);
                }

            }else if(source == signUp){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new addUser();
                        dispose();
                    }
                });
            }
        }
    }
}
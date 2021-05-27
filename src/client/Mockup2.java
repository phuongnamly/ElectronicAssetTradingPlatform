package  client;

import server.database.JBDCDataSource.Entity.User;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;


public class Mockup2 extends JFrame{
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    //
    JButton sign;
    JButton signup;

    NetworkDataSource data;

    public Mockup2 (){
        super("Homepage Trading App");

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



        //Introducing Textfields for info inptu
//        JTextField signin = new JTextField(10);
//        JTextField password = new JTextField(10);

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

//        sign = createButton("Sign In", this::signinButtonClick);
        sign = new JButton("Sign In");
        signup = createButton("Sign Up", this::signupButtonClick);

        signinpanel.add(sign, constraints);
        constraints.gridy++;
        signinpanel.add(signup, constraints);
        constraints.gridy++;
        constraints.gridy++;


        setLayout(new GridBagLayout());
        pack();
        add(signinpanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);


        addButtonListeners(new ButtonListener());

        data = new NetworkDataSource();

        pack();

        setVisible(true);

    }

    private void signinButtonClick(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(this, "Please ensure that you have filled in it correctly!",
                "Signup Error", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Adds a listener to the new, save and delete buttons
     */
    private void addButtonListeners(ActionListener listener) {
        sign.addActionListener(listener);
//        saveButton.addActionListener(listener);
//        deleteButton.addActionListener(listener);
    }


    private void signupButtonClick(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(this, "Signup message",
                "Signup Form", JOptionPane.WARNING_MESSAGE);
        JOptionPane.showInputDialog("Name: ");
        JOptionPane.showInputDialog("Address: ");
        JOptionPane.showInputDialog("Email: ");
        JOptionPane.showInputDialog("Student Number: ");
        JOptionPane.showMessageDialog(this, "Thank you for signing up, we will be in contact shortly");
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
    
    public static void main(String[] args){
        new Mockup2();
    }

    /**
     * Handles events for the three buttons on the UI.
     *
     * @author Malcolm Corney
     * @version $Id: Exp $
     *
     */
    private class ButtonListener implements ActionListener {

        /**
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
//            int size = data.getSize();

            JButton source = (JButton) e.getSource();
//            if (source == newButton) {
//                newPressed();
//            } else if (source == saveButton) {
//                savePressed();
//            } else if (source == deleteButton) {
//                deletePressed();
//            }
            if(source == sign) {
                // Organisation stuff
                String organisationName = "Stark";
                String credits = "100";


                if (data.addOrganisation(organisationName,credits)){
                    System.out.println("Mock database successful");
                }

                data.deleteOrganisation("5");

                // User stuff
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                if(data.login(username, password)){
                    // go to home page here
                    System.out.println("Log in successful");
                }
                else{
                    System.out.println("Nope");
                }
            }else if(source == signup){

            }
        }

//        /**
//         * When the new button is pressed, clear the field display, make them
//         * editable and enable the save button.
//         */
//        private void newPressed() {
//            clearFields();
//            setFieldsEditable(true);
//            saveButton.setEnabled(true);
//        }
//
//        /**
//         * When the save button is pressed, check that the name field contains
//         * something. If it does, create a new Person object and attempt to add it
//         * to the data model. Change the fields back to not editable and make the
//         * save button inactive.
//         *
//         * Check the list size to see if the delete button should be enabled.
//         */
//        private void savePressed() {
//            //    public User(String organisationID, String username, String password, String salt, String email, String phoneNum, String address, String accountType) {
//
//            if (name.getText() != null && !name.getText().equals("")) {
//                User u = new User("3213213",name.getText(), "sddasd", "dsasdasd",email.getText(), phone.getText(), street.getText() + suburb.getText(), "sdsadas");
//                data.add(u);
//            }
//            setFieldsEditable(false);
//            saveButton.setEnabled(false);
//            checkListSize();
//        }
//
//        /**
//         * When the delete button is pressed remove the selected name from the
//         * data model.
//         *
//         * Clear the fields that were displayed and check to see if the delete
//         * button should be displayed.
//         *
//         * The index here handles cases where the first element of the list is
//         * deleted.
//         */
//        private void deletePressed() {
//            int index = nameList.getSelectedIndex();
//            data.remove(nameList.getSelectedValue());
//            clearFields();
//            index--;
//            if (index == -1) {
//                if (data.getSize() != 0) {
//                    index = 0;
//                }
//            }
//            nameList.setSelectedIndex(index);
//            checkListSize();
//        }
    }



}


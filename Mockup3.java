package guiExploration;

import java.awt.Component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Mockup3 extends JFrame{
    //private JTextArea display;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    JButton sign;
    JButton signup;

    //New Declarations for JteXT AND JButton and scrolling down
    private JTextArea display;
    private JTextArea orderTextField;
    private JTextArea unitsTextField;
    private JTextArea priceTextField;

    private static final int BTN_PANEL = 10;
    JButton buy;
    JButton sell;

    public Mockup3(){
        super("Order Pad");

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setPreferredSize(new Dimension(500, 500));
//        setBackground(Color.LIGHT_GRAY);
//
//        JMenuBar menuBar = new JMenuBar();
//        JMenu trademenu = new JMenu("Trade");
//        trademenu.add("Buy");
//        trademenu.add("Sell");
//        JMenu orderMenu = new JMenu("Orders");
//        JMenu findMenu = new JMenu("Find");
//        JMenu accountMenu = new JMenu("Account");
//        JMenu settingMenu = new JMenu("Settings");
//        menuBar.add(trademenu);
//        menuBar.add(orderMenu);
//        menuBar.add(findMenu);
//        menuBar.add(accountMenu);
//        menuBar.add(settingMenu);
//        setJMenuBar(menuBar);
//        menuBar.setVisible(true);



        //Creating the Order Frame for the JFrame

        JFrame orderFrame = new JFrame("Order Frame");
        orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = orderFrame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        JPanel mainPanel  = new JPanel();
        SpringLayout mainLayout = new SpringLayout();
        mainPanel.setLayout(mainLayout);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Main Area"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        contentPane.add(mainPanel);
        layout.putConstraint(SpringLayout.WEST, mainPanel, 10, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, mainPanel, 10, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, mainPanel, -50, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.EAST, mainPanel, -10, SpringLayout.EAST, contentPane);




        //Crerating the Order Paid
        JPanel orderPadPanel = new JPanel();
        SpringLayout orderLayout = new SpringLayout();
        orderPadPanel.setLayout(orderLayout);
        orderPadPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Order Pad")
        , BorderFactory.createEmptyBorder(5,5,5,5)));

        mainPanel.add(orderPadPanel);
        mainLayout.putConstraint(SpringLayout.WEST, mainPanel, 20, SpringLayout.WEST, mainPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, mainPanel, 20, SpringLayout.NORTH, mainPanel);
        mainLayout.putConstraint(SpringLayout.SOUTH, mainPanel, 160, SpringLayout.NORTH, mainPanel);
        mainLayout.putConstraint(SpringLayout.EAST, mainPanel, 250, SpringLayout.WEST, mainPanel);

        JLabel labelFontName = new JLabel("Font name:");
        orderPadPanel.add(labelFontName);
        orderLayout.putConstraint(SpringLayout.WEST, labelFontName, 5, SpringLayout.WEST, orderPadPanel);
        orderLayout.putConstraint(SpringLayout.NORTH, labelFontName, 5, SpringLayout.NORTH, orderPadPanel);


        JPanel pTableData = new JPanel();
        SpringLayout tdLayout = new SpringLayout();
        pTableData.setLayout(tdLayout);
        pTableData.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Table Data"), BorderFactory.createEmptyBorder(5,5,5,5)));
        mainPanel.add(pTableData);
        mainLayout.putConstraint(SpringLayout.WEST, pTableData, 20, SpringLayout.EAST, orderPadPanel);
        mainLayout.putConstraint(SpringLayout.NORTH, pTableData, 20, SpringLayout.NORTH, mainPanel);
        mainLayout.putConstraint(SpringLayout.SOUTH, pTableData, 160, SpringLayout.NORTH, mainPanel);
        mainLayout.putConstraint(SpringLayout.EAST, pTableData, -20, SpringLayout.EAST, mainPanel);

        orderFrame.pack();
        orderFrame.setSize(550,475);
        //orderFrame.setLocationRelativeTo(null);
        orderFrame.setVisible(true);

    }







    ////Ignore till we JPanel correct

    private void signinButtonClick(ActionEvent actionEvent){
        JOptionPane.showMessageDialog(this, "Please ensure that you have filled in it correctly!",
                "Signup Error", JOptionPane.ERROR_MESSAGE);

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

    private void orderPadLayoutPanel(){





    }



    //The field for which "Starkcoin is displayed, once selected from the order pad. Displays asset and information about the asset
    //P
    private JTextArea assetDisplay() {

        display = new JTextArea();
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Arial",Font.BOLD, 24));
        display.setBorder(BorderFactory.createEtchedBorder()); //If possible create a pottential two borders?
        return display;

    }



    public static void main(String[] args){
        new Mockup3();


    }


}

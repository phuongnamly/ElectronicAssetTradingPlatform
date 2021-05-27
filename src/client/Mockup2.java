package guiExploration;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Mockup2 extends JFrame{
    private JTextArea display;



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
        //signinpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints constraints = new GridBagConstraints();

        signinpanel.setBorder(BorderFactory.createTitledBorder("Signin or Register"));

        constraints.gridx = 1;
        constraints.gridy = 1;

        constraints.insets = new Insets(5,5,5,5 );



        //Introducing Textfields for info inptu
        JTextField signin = new JTextField(10);
        constraints.gridx++;

        JTextField password = new JTextField(10);
        constraints.gridx++;

        signinpanel.add(new JLabel("USERNAME"), constraints);
        constraints.gridy++;
        constraints.gridx++; //added remove later

        signinpanel.add(signin, constraints);

        constraints.gridy++;
        constraints.gridx++; //added remove later


        signinpanel.add(new JLabel("Password"), constraints);

        constraints.gridy++;
        constraints.gridx++; //added remove later

        signinpanel.add(password, constraints);

        constraints.gridy++;
        constraints.gridx++; //added remove later

        JButton sign = new JButton("Sign In");

        JButton signup = new JButton("Sign Up");

        sign = createButton("Sign In", this::signinButtonClick);
        signup = createButton("Sign Up", this::signupButtonClick);

        signinpanel.add(sign, constraints);
        constraints.gridy++;
        constraints.gridx++;
        signinpanel.add(signup, constraints);
        constraints.gridy++;
        constraints.gridx++;
        constraints.gridy++;
        constraints.gridx++;



        setLayout(new GridBagLayout());
        pack();
        add(signinpanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        pack();

        setVisible(true);

    }

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


    public static void main(String[] args){
        new Mockup2();




    }


}

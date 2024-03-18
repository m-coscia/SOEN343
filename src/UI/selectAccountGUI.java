package src.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectAccountGUI extends JFrame{

    JFrame thisFrame = null;
    protected JPanel accountCreateForm;
    private JButton newAccountButton;
    private JButton logInButton;
    private JLabel loginLabel;
    private JLabel newAccountLabel;
    private JButton backButton;

    public selectAccountGUI(JFrame previousFrame){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //to go back to the previous frame
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                previousFrame.setVisible(true);
            }
        });

        //goes to login page
        //TODO complete this page
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO switch content pane OR new frame?
                accountCreateForm.setVisible(false);
                accountCreateForm.removeAll();
                JPanel Login = new JPanel();
                Login.add(new JLabel("Username") );
                Login.add(new JTextField());

                accountCreateForm.add(Login);
                accountCreateForm.setVisible(true);
            }
        });

        //goes to account creation page
        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfilesCreationFrame nextFrame = new ProfilesCreationFrame(thisFrame);
                nextFrame.setVisible(true);
                dispose();
//                accountCreateForm.setVisible(false);
//                accountCreateForm.removeAll();
//                JPanel createPanel = new ProfilePanel(thisFrame);
//                JLabel testing = new JLabel("SET UP ACCOUNTS");
//                testing.setFont(new Font(Font.SERIF, Font.BOLD, 20));
//                accountCreateForm.add(createPanel);
//                accountCreateForm.setVisible(true);
//                createPanel.setSize(900,400);


            }
        });

        add(accountCreateForm);
        setSize(600,600);
        setLocationRelativeTo(null);
        //setVisible(true);
    }

}

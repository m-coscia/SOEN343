package src.UI;

import src.UI.ProfilePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectAccountGUI {
    protected JPanel accountCreateForm;
    private JButton newAccountButton;
    private JButton logInButton;
    private JLabel loginLabel;
    private JLabel newAccountLabel;

    public selectAccountGUI(JPanel callingFrame){
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

        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountCreateForm.setVisible(false);

                accountCreateForm.removeAll();
                JPanel createPanel = new ProfilePanel();
                JLabel testing = new JLabel("SET UP ACCOUNTS");
                testing.setFont(new Font(Font.SERIF, Font.BOLD, 20));
                accountCreateForm.add(createPanel);
                accountCreateForm.setVisible(true);
                createPanel.setSize(900,400);

            }
        });
    }

}

package src.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectAccountGUI {
    protected JPanel accountCreateForm;
    private JButton newAccountButton;
    private JButton logInButton;
    private JLabel loginLabel;
    private JLabel newAccountLabel;

    public selectAccountGUI(){
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
                //TODO switch content pane OR new frame?
            }
        });
    }

}

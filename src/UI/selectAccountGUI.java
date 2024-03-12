package UI;

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

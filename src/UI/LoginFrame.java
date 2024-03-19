package src.UI;

import src.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{

    private Controller controller = new Controller();
    private JFrame previousFrame;
    private JPanel framePanel;
    private JButton backButton;
    private JPanel leftSidePanel;
    private JPanel cardsPanel;
    private JLabel noProfileLabel;

    public LoginFrame(JFrame previousFrame){
        this.previousFrame = previousFrame;

        setUpBackButton();
        //TODO fix this supposed to display that no account exist if profiles empty else show them using cardLayout
        if (controller.getProfiles().isEmpty()){
            System.out.println("No profiles yet");
            setLayout(new BorderLayout());
            noProfileLabel.setVisible(true);
            cardsPanel.add(noProfileLabel);
        }else {
            setUpBackButton();
            noProfileLabel.setVisible(false);
            framePanel.add(leftSidePanel, BorderLayout.WEST);
            framePanel.add(cardsPanel, BorderLayout.CENTER);
        }
            setContentPane(framePanel);
            setLocationRelativeTo(null);
            setSize(600,600);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void setUpBackButton() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                previousFrame.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame(null);
    }
}

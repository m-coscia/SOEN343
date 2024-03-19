package src.UI;

import src.Controller;
import src.logic.Profile;

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
    private JLabel noProfileLabel;
    private JPanel cardsPanel;

    public LoginFrame(JFrame previousFrame){
        this.previousFrame = previousFrame;

        setUpBackButton();
        //TODO fix this supposed to display that no account exist if profiles empty else show them using cardLayout

        if(controller.getProfiles().isEmpty()){
            framePanel.add(noProfileLabel);
            framePanel.add(leftSidePanel);
            add(framePanel);
        }else{
            cardsPanel = new JPanel(new CardLayout());

            for(Profile p : controller.getProfiles()){
                JPanel card = new JPanel();
                card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

                JLabel nameLabel = new JLabel("Name: " + p.getName());
                card.add(nameLabel);

                // Add profile type
                JLabel typeLabel = new JLabel("Type: " );
                card.add(typeLabel);

                // Add profile icon (you need to replace this with your own icon)
                JLabel iconLabel = new JLabel(new ImageIcon("src/UI/ProfileIcon.png"));
                card.add(iconLabel);

                // Add card to cards panel
                cardsPanel.add(card);
            }

            CardsPanel nextFrame = new CardsPanel(cardsPanel);
            nextFrame.setVisible(true);
            dispose();
//            framePanel.removeAll();
//            framePanel.setLayout(new BorderLayout());
//            framePanel.add(backButton, BorderLayout.WEST);
//            framePanel.add(cardsPanel, BorderLayout.CENTER);

        }
//        if (controller.getProfiles().isEmpty()){
//            System.out.println("No profiles yet");
//            setLayout(new BorderLayout());
//            noProfileLabel.setVisible(true);
//            //cardsPanel.add(noProfileLabel);
//        }else {
//            setUpBackButton();
//            noProfileLabel.setVisible(false);
//            framePanel.add(leftSidePanel, BorderLayout.WEST);
//            //framePanel.add(cardsPanel, BorderLayout.CENTER);
//        }
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

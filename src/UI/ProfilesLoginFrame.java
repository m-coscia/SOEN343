package src.UI;

import src.Controller;
import src.logic.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfilesLoginFrame extends JFrame {
    JFrame thisFrame = this;
    Controller controller = new Controller();
    private CardLayout layout = new CardLayout();
    private JFrame previousFrame;
    private JPanel panel1;
    private JButton backButton;
    private JButton previousProfileButton;
    private JButton selectProfileButton;
    private JButton nextProfileButton;
    private JPanel cardsPanel;
    private JPanel selectProfilePanel;
    private JPanel commandsPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel middle;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel loginPanel;
    private ArrayList<Profile> profiles;
    private int currentProfile = 0;

    public ProfilesLoginFrame(JFrame previousFrame) {
        profiles = controller.getProfiles();
        this.previousFrame = previousFrame;
        cardsPanel.setLayout(layout);

        JPanel pa = new JPanel();
        middle.add(pa.add(new JLabel("")), BorderLayout.NORTH);
        loginPanel.setVisible(false);

        for (Profile p: profiles){
            JPanel cardPanel = new JPanel();
            cardPanel.setLayout(new BorderLayout());
//            cardPanel.setAlignmentY(CENTER_ALIGNMENT);
//            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

            JLabel iconLabel = new JLabel(new ImageIcon("src/UI/smallProfile.png"));
            iconLabel.setAlignmentX(CENTER_ALIGNMENT);
            iconLabel.setAlignmentY(CENTER_ALIGNMENT);
            cardPanel.add(iconLabel,BorderLayout.CENTER);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BorderLayout());
            //infoPanel.set
            // Add profile name
            JLabel nameLabel = new JLabel("Name: " + p.getName());
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(nameLabel,BorderLayout.NORTH);

            // Add profile type
            JLabel typeLabel = new JLabel("Type of profile: " + controller.getType(p));
            typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(typeLabel,BorderLayout.CENTER);

            JLabel locationLabel = new JLabel("Location: " + controller.getLocation(p));
            locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(locationLabel, BorderLayout.SOUTH);


//            usernameLabel.setVisible(false);
//            passwordLabel.setVisible(false);
//            textField1.setVisible(false);
//            passwordField1.setVisible(false);
            //ADD INFO PANEL TO CARD PANEL
            cardPanel.add(infoPanel, BorderLayout.SOUTH);
            // Add card to cards panel
            cardsPanel.add(cardPanel);
        }
        setUpControls();
        setUpFrame();
        add(panel1);
    }

    private void setUpFrame(){
        setTitle("Pick a Profile to log in to");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        setResizable(false);
    }

    private void setUpControls(){
        nextProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.next(cardsPanel);
                currentProfile++;
            }

        });

        previousProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.previous(cardsPanel);
                currentProfile--;
            }
        });

        selectProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPanel.setVisible(true);
                commandsPanel.setVisible(false);
                passwordField1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.login(profiles.get(currentProfile % profiles.size()));
                        System.out.println(profiles.get(currentProfile % profiles.size()).getName());

                        // DashboardFrame dash = new DashboardFrame(thisFrame);
//                        Dashboard dash = new Dashboard(null, profiles.get(currentProfile % profiles.size()));
//                        dash.setLocationRelativeTo(null);
//                        dash.setVisible(true);
//                        dispose();
                        SimParameterGUI nextFrame = new SimParameterGUI(thisFrame,profiles.get(currentProfile % profiles.size()));
                        nextFrame.setLocationRelativeTo(null);
                        nextFrame.setVisible(true);
                        dispose();

                    }
                });
                //Component c = cardsPanel.getComponent(cardsPanel.getComponentCount()-1);

            }
        });
    }



    public static void main(String[] args) {
        new ProfilesLoginFrame(null);
    }
}

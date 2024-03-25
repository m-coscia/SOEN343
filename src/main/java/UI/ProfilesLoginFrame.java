package UI;

import logic.Profile;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class ProfilesLoginFrame extends JFrame {
    JFrame thisFrame = this;
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

    public ProfilesLoginFrame(JFrame previousFrame, ArrayList<Profile> profiles) {
        this.previousFrame = previousFrame;
        cardsPanel.setLayout(layout);
        for (Profile p : profiles) {
            JPanel cardPanel = new JPanel();
            cardPanel.setAlignmentY(CENTER_ALIGNMENT);
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

            JLabel iconLabel = new JLabel(new ImageIcon("src/UI/ProfileIcon.png"));
            iconLabel.setAlignmentX(CENTER_ALIGNMENT);
            iconLabel.setAlignmentY(CENTER_ALIGNMENT);
            cardPanel.add(iconLabel);

            // Add profile name
            JLabel nameLabel = new JLabel("Name: " + p.getName());
            nameLabel.setAlignmentX(CENTER_ALIGNMENT);
            nameLabel.setAlignmentY(CENTER_ALIGNMENT);
            cardPanel.add(nameLabel);

            // Add profile type
            JLabel typeLabel = new JLabel();
            typeLabel.setAlignmentX(CENTER_ALIGNMENT);
            typeLabel.setAlignmentY(CENTER_ALIGNMENT);
            cardPanel.add(typeLabel);

            JLabel locationLabel = new JLabel("Location: " + p.getLocation());
            locationLabel.setAlignmentX(CENTER_ALIGNMENT);
            locationLabel.setAlignmentY(CENTER_ALIGNMENT);
            cardPanel.add(locationLabel);

            // Add card to cards panel
            cardsPanel.add(cardPanel);
        }
        setUpControls();
        setUpFrame();
        add(panel1);
    }

    private void setUpFrame() {
        setTitle("Pick a Profile to log in to");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setResizable(false);
    }

    private void setUpControls() {
        nextProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.next(cardsPanel);
            }
        });

        previousProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.previous(cardsPanel);
            }
        });

        selectProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c = cardsPanel.getComponent(cardsPanel.getComponentCount() - 1);
                //c.get
                //addAuthentificationFields();
                //TODO how do i get the profile that is selected
                // DashboardFrame dash = new DashboardFrame(thisFrame);
                Dashboard dash = new Dashboard(null);
                dash.setLocationRelativeTo(null);
                dash.setVisible(true);
                dispose();

            }
        });
    }

    private void addAuthentificationFields() {
        // Create panel for username and password fields
        JPanel usernamePasswordPanel = new JPanel(new GridLayout(2, 2));

        // Add username label and field
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        usernamePasswordPanel.add(usernameLabel);
        usernamePasswordPanel.add(usernameField);

        // Add password label and field
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        usernamePasswordPanel.add(passwordLabel);
        usernamePasswordPanel.add(passwordField);

        // Add username and password panel to the card panel
        cardsPanel.add(usernamePasswordPanel, "UsernamePassword");

        // Show the added panel
        layout.show(cardsPanel, "UsernamePassword");

        // Repaint the frame
        revalidate();
        repaint();
        pack();
    }


}

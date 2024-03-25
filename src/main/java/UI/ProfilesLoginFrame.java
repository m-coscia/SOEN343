package UI;

import logic.Profile;
import components.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ProfilesLoginFrame extends JFrame {
    private JFrame previousFrame;
    private JPanel cardsPanel; // Panel holding the profile cards
    private ArrayList<Profile> profiles; // Profiles to display
    private int currentIndex = 0; // Index of the currently displayed profile

    public ProfilesLoginFrame(JFrame previousFrame, ArrayList<Profile> profiles) {
        this.previousFrame = previousFrame;
        this.profiles = profiles;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Select Profile");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cardsPanel = new JPanel(new CardLayout());
        fillCardsPanel();

        // Navigation buttons
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(this::showPreviousProfile);
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(this::showNextProfile);
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(this::selectProfile);

        JPanel controlPanel = new JPanel();
        controlPanel.add(prevButton);
        controlPanel.add(selectButton);
        controlPanel.add(nextButton);

        add(cardsPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void fillCardsPanel() {
        for (Profile profile : profiles) {
            JPanel profilePanel = new JPanel();
            profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
            profilePanel.add(new JLabel("Name: " + profile.getName()));
            profilePanel.add(new JLabel("Location: " + (profile.getLocation() != null ? profile.getLocation().getId() : "Not Set")));
            cardsPanel.add(profilePanel);
        }
    }

    private void showPreviousProfile(ActionEvent event) {
        CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.previous(cardsPanel);
        currentIndex = Math.floorMod(currentIndex - 1, profiles.size());
    }

    private void showNextProfile(ActionEvent event) {
        CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.next(cardsPanel);
        currentIndex = Math.floorMod(currentIndex + 1, profiles.size());
    }

    private void selectProfile(ActionEvent event) {
        Profile selectedProfile = profiles.get(currentIndex);
        System.out.println("Selected profile: " + selectedProfile.getName());
        // Further actions here, like opening the main application window with the selected profile
        // For example:
        // Dashboard dashboard = new Dashboard(selectedProfile);
        // dashboard.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        // Example usage
        SwingUtilities.invokeLater(() -> {
            ArrayList<Profile> exampleProfiles = new ArrayList<>();
            Room livingRoom = new Room(); // Assuming a constructor setting a default room
            exampleProfiles.add(new Profile("John", livingRoom));
            exampleProfiles.add(new Profile("Jane", null));
            new ProfilesLoginFrame(null, exampleProfiles);
        });
    }
}
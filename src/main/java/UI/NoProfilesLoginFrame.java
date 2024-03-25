package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NoProfilesLoginFrame extends JFrame {

    private JFrame previousFrame;

    public NoProfilesLoginFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        initializeUI();
        setUpBackButton();
        setTitle("No Profiles Available");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeUI() {
        // Main panel setup
        JPanel framePanel = new JPanel();
        framePanel.setLayout(new BorderLayout());
        framePanel.setBackground(new Color(-4931890));

        // Label to inform there are no profiles
        JLabel noProfileLabel = new JLabel("<html>No profiles have been created yet.<br>Go back to create them.</html>", SwingConstants.CENTER);
        noProfileLabel.setFont(new Font("Arial", Font.BOLD, 18));
        noProfileLabel.setForeground(new Color(-16777216));

        // Back button to return to the previous frame
        JButton backButton = new JButton("<-- Back");

        // Adding components to the main panel
        framePanel.add(noProfileLabel, BorderLayout.CENTER);
        framePanel.add(backButton, BorderLayout.PAGE_END);

        // Add action listener to back button
        backButton.addActionListener(e -> {
            dispose();
            if (previousFrame != null) {
                previousFrame.setVisible(true);
            }
        });

        // Set the main panel as the content pane
        setContentPane(framePanel);
    }

    private void setUpBackButton() {
        // This method is now empty since backButton setup is moved to initializeUI().
        // It's kept for structure consistency, could be removed or repurposed for other setup tasks.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NoProfilesLoginFrame(null));
    }
}
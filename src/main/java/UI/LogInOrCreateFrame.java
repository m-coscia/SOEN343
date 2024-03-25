package UI;

import controller.Controller;
import logic.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class LogInOrCreateFrame extends JFrame {

    private Controller controller = new Controller();
    private ArrayList<Profile> profiles = controller.getProfiles();

    // Declare buttons at the class level to make them accessible in all instance methods
    private JButton backButton;
    private JButton logInButton;
    private JButton newAccountButton;

    public LogInOrCreateFrame(JFrame previousFrame) {
        setTitle("Log in to Existing Account or Create Accounts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        initializeUI();
        attachButtonListeners(previousFrame); // Pass previousFrame to method for access
    }

    private void initializeUI() {
        JPanel accountCreateForm = new JPanel(new GridBagLayout());
        accountCreateForm.setBackground(new Color(-4931890));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginLabel = new JLabel("Log in to an existing account", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel newAccountLabel = new JLabel("Create a new account", SwingConstants.CENTER);
        newAccountLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Initialize buttons here
        logInButton = new JButton("Log in");
        newAccountButton = new JButton("Create Account");
        backButton = new JButton("<-- Back");

        gbc.insets = new Insets(20, 20, 20, 20);
        accountCreateForm.add(loginLabel, gbc);
        accountCreateForm.add(logInButton, gbc);
        accountCreateForm.add(newAccountLabel, gbc);
        accountCreateForm.add(newAccountButton, gbc);
        accountCreateForm.add(backButton, gbc);

        setContentPane(accountCreateForm);
    }

    private void attachButtonListeners(JFrame previousFrame) {
        // Access backButton, logInButton, and newAccountButton directly within this method
        backButton.addActionListener(e -> {
            dispose();
            previousFrame.setVisible(true);
        });

        logInButton.addActionListener(e -> {
            // Implement logic to handle logging in
        });

        newAccountButton.addActionListener(e -> {
            // Implement logic to navigate to account creation UI
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogInOrCreateFrame(null));
    }
}
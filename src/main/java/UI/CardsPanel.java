package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardsPanel extends JFrame {
    private JPanel profilesCardsPanel;
    private JButton button1;
    private JPanel cardsPanel;
    private JButton previousButton;
    private JButton loginButton;
    private JButton nextButton;
    private JLabel titleLabel;

    public CardsPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Cards Panel Example");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        profilesCardsPanel = new JPanel(new BorderLayout());
        profilesCardsPanel.setBackground(new Color(-4931890));

        // Create the cards panel
        cardsPanel = new JPanel(new CardLayout());
        cardsPanel.setBackground(new Color(-4931890));

        // Title label
        titleLabel = new JLabel("Navigate profiles with the buttons below");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Buttons
        button1 = new JButton("Button1 Action");
        previousButton = new JButton("Previous");
        loginButton = new JButton("Login");
        nextButton = new JButton("Next");

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(previousButton);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(nextButton);

        // Adding components to the main panel
        profilesCardsPanel.add(titleLabel, BorderLayout.NORTH);
        profilesCardsPanel.add(cardsPanel, BorderLayout.CENTER);
        profilesCardsPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(profilesCardsPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ensure the UI is created in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new CardsPanel());
    }
}

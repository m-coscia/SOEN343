package UI;

import javax.swing.*;
import java.awt.*;

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
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // Adjusts frame size to fit components
    }

    private void initializeUI() {
        profilesCardsPanel = new JPanel();
        profilesCardsPanel.setLayout(new BorderLayout());
        profilesCardsPanel.setBackground(new Color(-4931890));

        // Setting up the title label
        titleLabel = new JLabel("Navigate profiles with the buttons below");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Setting up the buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        previousButton = new JButton("Previous");
        loginButton = new JButton("Login");
        nextButton = new JButton("Next");
        buttonsPanel.add(previousButton);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(nextButton);

        // Setting up the cards panel (assuming it is intended to hold multiple cards)
        cardsPanel = new JPanel(new CardLayout());

        // Setting up the main profilesCardsPanel layout
        profilesCardsPanel.add(titleLabel, BorderLayout.NORTH);
        profilesCardsPanel.add(cardsPanel, BorderLayout.CENTER);
        profilesCardsPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Adding the profilesCardsPanel to the JFrame
        this.add(profilesCardsPanel);
    }

    public static void main(String[] args) {
        // Ensure the UI is created in the Event Dispatch Thread
        SwingUtilities.invokeLater(CardsPanel::new);
    }
}

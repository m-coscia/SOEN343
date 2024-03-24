package src.UI;

import javax.swing.*;

public class CardsPanel extends JFrame{
    private JPanel profilesCardsPanel;
    private JButton button1;
    private JPanel cardsPanel;
    private JButton previousButton;
    private JButton loginButton;
    private JButton nextButton;
    private JLabel titleLabel;

    public CardsPanel(JPanel cardsPanel){
        this.cardsPanel = cardsPanel;
        add(profilesCardsPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new CardsPanel(null);
    }
}


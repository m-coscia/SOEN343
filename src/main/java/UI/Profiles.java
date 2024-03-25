package UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Profiles extends JFrame {

    private JPanel middle;

    public Profiles() {
        initializeUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeUI() {
        setTitle("Profile Setup");

        // Main panel
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(new Color(-6052186));

        // Subpanels for layout structure, if needed
        JPanel test = new JPanel(new FlowLayout());
        test.setOpaque(false);

        JPanel test1 = new JPanel(new FlowLayout());
        test1.setOpaque(false);

        JPanel test2 = new JPanel(new FlowLayout());
        test2.setOpaque(false);

        JPanel test3 = new JPanel(new FlowLayout());
        test3.setOpaque(false);

        middle = new JPanel(new GridBagLayout());
        middle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null));

        // Add components to middle panel as per your requirements
        setupProfileComponents();

        // Add panels to main panel
        panel1.add(test, BorderLayout.WEST);
        panel1.add(test1, BorderLayout.NORTH);
        panel1.add(test2, BorderLayout.EAST);
        panel1.add(test3, BorderLayout.SOUTH);
        panel1.add(middle, BorderLayout.CENTER);

        add(panel1);
    }

    private void setupProfileComponents() {
        // This is where you'll add components like labels, fields, checkboxes to 'middle'
        middle.setLayout(new GridLayout(0, 11)); // Adjust grid layout as needed

        // Example component
        middle.add(new JLabel("Profile Name"));
        // Continue adding other components similarly...

        // Example adding dynamic components
        addProfileFields();
    }

    private void addProfileFields() {
        middle.add(new JTextField("Profile Name"));
        JComboBox<String> typeField = new JComboBox<>(new String[]{"Parent", "Child", "Guest", "Stranger"});
        middle.add(typeField);

        // Adding listeners or other logic as needed
        typeField.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Selected: " + e.getItem());
                // Add your logic here
            }
        });

        // Re-validate and repaint if dynamically adding components
        middle.revalidate();
        middle.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Profiles::new);
    }
}
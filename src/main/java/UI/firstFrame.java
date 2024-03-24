package UI;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class firstFrame extends JFrame {
    private Controller controller = new Controller();
    private JFrame thisFrame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel layoutFileLabel;
    private JTextField layoutFileName;

    public firstFrame() {
        thisFrame = this;
        initializeComponents();
        setTitle("Testing Swing UI Builder");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(-4931890));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleLabel = new JLabel("Smart Home Simulator");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.insets = new Insets(20, 0, 20, 0); // top padding
        mainPanel.add(titleLabel, gbc);

        layoutFileLabel = new JLabel("Enter the layout file path to start:");
        layoutFileLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        gbc.insets = new Insets(10, 0, 10, 0); // reset to default
        mainPanel.add(layoutFileLabel, gbc);

        layoutFileName = new JTextField();
        layoutFileName.setColumns(20);
        layoutFileName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLayoutFileEntered();
            }
        });
        mainPanel.add(layoutFileName, gbc);
    }

    private void handleLayoutFileEntered() {
        System.out.println("Enter key was pressed");
        try {
            System.out.println(layoutFileName.getText());
            controller.layoutSetUp(layoutFileName.getText());

            LogInOrCreateFrame secondFrame = new LogInOrCreateFrame(thisFrame);
            secondFrame.setVisible(true);
            thisFrame.dispose();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(thisFrame, "File not found: " + layoutFileName.getText(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new firstFrame());
    }
}
//package src.UI;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class DashboardFrame extends JFrame{
//    public JPanel dashboard;
//    private JPanel dashboardPanel;
//    private JPanel userInfoPanel;
//
//    JFrame previousFrame = null;
//
//    public DashboardFrame(JFrame previousFrame)  {
//       this.previousFrame = previousFrame;
//       setLocationRelativeTo(null);
//       setVisible(true);
//       setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    public static void main(String[] args) {
//        new DashboardFrame(null);
//    }
//}
package src.UI;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
        public JPanel dashboard;
    private JPanel dashboardPanel;
    private JPanel userInfoPanel;

    public DashboardFrame(JFrame previousFrame) {
        setTitle("Smart Home Simulator Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel to hold profile icon
        JPanel profilePanel = new JPanel();
        // Add profile icon (you can replace this with your own icon)
        profilePanel.add(new JLabel(new ImageIcon("src/UI/ProfileIcon.png")));

        // Create panel to hold simulation control button
        JPanel controlPanel = new JPanel();
        JButton controlButton = new JButton("Start/Stop Simulation");
        controlPanel.add(controlButton);

        // Create main panel to hold left and right parts
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add profile panel to the left quarter
        mainPanel.add(profilePanel, BorderLayout.WEST);

        // Create panel for the rest three quarters
        JPanel contentPanel = new JPanel();
        // You can add more components to contentPanel here
        contentPanel.setBackground(Color.WHITE);

        // Add content panel to the remaining three quarters
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(mainPanel);

        // Set size
        setSize(800, 600);
        // Center frame on screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame(null);
            frame.setVisible(true);
        });
    }
}


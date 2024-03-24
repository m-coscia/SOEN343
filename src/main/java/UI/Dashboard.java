package UI;

import components.Clock;
import logic.Profile;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dashboard extends JFrame {

    Controller controller = new Controller();
    private JPanel dashboardPanel;
    private JPanel toggleButtonPanel;
    private JPanel userInfoPanel;
    private JSlider timeSlider;
    private JButton editButton;
    private JPanel fullMainPanel;
    private JTabbedPane tabbedPane1;
    private JTextArea outputArea;
    private JSplitPane rightMainPanel;
    private JSplitPane modulesAndLayoutPanel;
    private JTextArea textArea1;
    private JList<String> list1;
    private JPanel houseLayout;
    private JLabel dateLabel;
    private JLabel clockDisplay;
    private JLabel profileIconLabel;
    private JLabel tempLabel;
    private JLabel start_stop_label;
    private JLabel userTypeLabel;
    private JLabel locationLabel;
    private JToggleButton toggleButton;

    private Clock clock = new Clock();
    private Profile currentProfile;

    public Dashboard(Profile loggedIn) {
        this.currentProfile = loggedIn; // Assign the logged-in profile to the currentProfile field
        clock = new Clock(); // Initialize the clock
        initializeUI();
        setupListeners();
    }

    private void initializeUI() {
        // Main frame settings
        setTitle("Smart Home Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);

        // Full Main Panel
        fullMainPanel = new JPanel(new BorderLayout());

        // Output Area
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setEditable(false); // Assuming the console output area should not be editable
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        outputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Edit Button
        editButton = new JButton("Edit");
        // Assuming editButton has an action listener to edit simulation parameters or similar functionality

        // Tabbed Pane setup for SHS, SHC, etc.
        tabbedPane1 = new JTabbedPane();
        JPanel shsPanel = new JPanel();
        tabbedPane1.addTab("SHS", shsPanel);
        JPanel shcPanel = new JPanel();
        tabbedPane1.addTab("SHC", shcPanel);
        // Additional modules as needed...

        // Text Area 1 for Module Data Display
        textArea1 = new JTextArea();
        JScrollPane textArea1ScrollPane = new JScrollPane(textArea1);
        shsPanel.add(textArea1ScrollPane);

        // List1 for Listing Items, such as scenarios or devices
        list1 = new JList<>(new String[]{"Item1", "Item2", "Item3"});
        shcPanel.add(new JScrollPane(list1));

        // House Layout Panel
        houseLayout = new JPanel();
        houseLayout.setBorder(new TitledBorder("House Layout"));

        // Modules and Layout Panel
        modulesAndLayoutPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane1, houseLayout);
        modulesAndLayoutPanel.setDividerLocation(300);

        // Right Main Panel
        JPanel consolePanel = new JPanel(new BorderLayout());
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        consolePanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        rightMainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, modulesAndLayoutPanel, consolePanel);
        rightMainPanel.setDividerLocation(750);

        profileIconLabel = new JLabel(new ImageIcon("path_to_your_icon.png"));
        profileIconLabel.setHorizontalAlignment(JLabel.CENTER);

        // Dashboard panel setup
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.add(fullMainPanel, BorderLayout.CENTER);

        // Adding elements to the Full Main Panel
        fullMainPanel.add(rightMainPanel, BorderLayout.CENTER);
        fullMainPanel.add(editButton, BorderLayout.SOUTH);

        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());
        dashboardPanel.setBorder(new TitledBorder("Smart Home Simulator"));

        // Initialize the toggleButton
        toggleButton = new JToggleButton("Toggle"); // Using JToggleButton for toggle behavior
        toggleButtonPanel.add(toggleButton);

        // Toggle Button Panel
        toggleButtonPanel = new JPanel(new FlowLayout());
        toggleButtonPanel.add(toggleButton);
        dashboardPanel.add(toggleButtonPanel, BorderLayout.NORTH);

        // User Info Panel
        userInfoPanel = new JPanel(new BorderLayout());
        userInfoPanel.setBorder(new TitledBorder("User Info"));
        JLabel userInfoLabel = new JLabel("User: " + (currentProfile != null ? currentProfile.getName() : "John Doe"));
        userInfoPanel.add(userInfoLabel, BorderLayout.NORTH);

        // Slider for simulation parameters
        timeSlider = new JSlider(0, 100, 50);
        userInfoPanel.add(timeSlider, BorderLayout.CENTER);
        dashboardPanel.add(userInfoPanel, BorderLayout.WEST);

        // Lower Panel for Additional Information
        JPanel lowerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dateLabel = new JLabel("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE MMM dd yyyy")));
        clockDisplay = new JLabel("Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        tempLabel = new JLabel("Outside Temp: " + controller.getTemperature() + "ºC");
        start_stop_label = new JLabel("Status: Stopped");
        userTypeLabel = new JLabel("User Type: " + (currentProfile != null ? "Guest" : "Guest"));
        locationLabel = new JLabel("Location: Unknown");
        lowerPanel.add(dateLabel);
        lowerPanel.add(clockDisplay);
        lowerPanel.add(tempLabel);
        lowerPanel.add(start_stop_label);
        lowerPanel.add(userTypeLabel);
        lowerPanel.add(locationLabel);


        // Adding subpanels to dashboardPanel
        dashboardPanel.add(toggleButtonPanel, BorderLayout.NORTH);
        dashboardPanel.add(userInfoPanel, BorderLayout.WEST);
        dashboardPanel.add(rightMainPanel, BorderLayout.CENTER);
        dashboardPanel.add(lowerPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, e -> {
            clockDisplay.setText("Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
        timer.start();

        // Adding dashboardpanel to the JFrame
        add(dashboardPanel);
        //pack(); // Adjusts the frame size to fit the content
        setVisible(true);
    }


    private void setupListeners() {
        editButton.addActionListener(e -> {
            // Example: Changing the user type (this is a placeholder for your actual editing logic)
            String newUserType = JOptionPane.showInputDialog(Dashboard.this, "Enter new user type:");
            if (newUserType != null && !newUserType.trim().isEmpty()) {
                userTypeLabel.setText("User Type: " + newUserType);
                if (currentProfile != null) {
                    //currentProfile.setType(newUserType); // Assuming Profile has a setType method
                }
            }
        });

        toggleButton.addActionListener(e -> {
            if (!clock.isRunning()) {
                clock.start();
                start_stop_label.setText("Running");
            } else {
                clock.pause();
                start_stop_label.setText("Paused");
            }
        });

        timeSlider.addChangeListener(e -> {
            if (!timeSlider.getValueIsAdjusting()) {
                int newSpeed = timeSlider.getValue();
                clock.changeSpeed(newSpeed); // Assuming Clock has a changeSpeed method
                System.out.println("Clock speed adjusted to: " + newSpeed);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard(null));
    }
}
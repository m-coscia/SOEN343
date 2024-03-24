package UI;

import components.Clock;
import logic.Profile;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class Dashboard extends JFrame {

    Controller controller = new Controller();
    private JPanel panel1;
    private JPanel dashboardPanel;
    private JPanel toggleButtonPanel;
    private JPanel userInfoPanel;
    private JPanel lowerPanelofSimParameters;
    private JSlider timeSlider;
    private JPanel fillerPanel;
    private JPanel middleOfSimParam;
    private JButton editButton;
    private JPanel fullMainPanel;
    private JTabbedPane tabbedPane1;
    private JTextArea outputArea;
    private JPanel consolePanel;
    private JLabel consoleLabel;
    private JSplitPane rightMainPanel;
    private JSplitPane modulesAndLayoutPanel;
    private JTextArea textArea1;
    private JList list1;
    private JPanel houseLayout;
    private JPanel filler;
    private JPanel iconPanel;
    private JPanel testing;
    private JLabel dateLabel;
    private JPanel datePanel;
    private JPanel clockLabel;
    private JPanel clockPanel;
    private JLabel clockDisplay;
    private JLabel profileIconLabel;
    private JLabel tempLabel;
    private JLabel start_stop_label;
    private JLabel userTypeLabel;
    private JLabel locationLabel;
    private JTextArea consoleText;

    private Clock clock = new Clock();
    private Profile currentProfile;

    public Dashboard(Profile loggedIn) {
//        setLayout(new BorderLayout());
//        JPanel test = new JPanel();
//        JButton testButton = new JButton();
//        test.add(testButton);
//
//        JPanel test1 = new JPanel();
//        test1.add(testButton);
//
//        JPanel test2 = new JPanel();
//        test2.add(testButton);
//
//        ToggleButton t = new ToggleButton();
//        testButton.add(t);
//        add(test1, BorderLayout.NORTH);
//        add(test2, BorderLayout.EAST);
//        add(test, BorderLayout.WEST);
//        add(t, BorderLayout.SOUTH);
        tempLabel.setText("Oustide Temp. " + controller.getTemperature() + "ºC");
        ToggleButton toggle = new ToggleButton();
        toggle.setSize(10, 10);


        try {
            toggleButtonPanel.add(toggle, BorderLayout.CENTER);

            toggle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    if (!clock.isRunning().get()) {
                        clock.start();
                        start_stop_label.setText("Running");
                    } else {
                        clock.pause();
                        start_stop_label.setText("Paused");
                    }
                }

            });


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            System.out.println(timeSlider.getMinimum() + " " + timeSlider.getMaximum() + " " + timeSlider.getExtent());
            timeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Current value" + timeSlider.getValue());
                    clock.changeSpeed((double) timeSlider.getValue() / 10);
                }
            });
            clock.start();
            clock.pause();
            Timer timer = new Timer(1000, e -> {
                // Update the clock label with the current time
                clockDisplay.setText(clock.getTime().toString());
            });
            timer.start();
            outputArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            textArea1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            list1.setListData(new String[]{"idk", "idk", "idk"});
            add(panel1);
            setSize(1000, 500);
            // Center frame on screen

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
//        add(panel1);
//        setVisible(true);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: should be able to edit the simulation context --> maybe switch labels to JTextArea and then when button is clicked make them editable
            }
        });
    }

    public static void main(String[] args) {
        Dashboard d = new Dashboard(null);
        d.setLocationRelativeTo(null);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new CardLayout(0, 0));
        panel1.setBackground(new Color(-4931890));
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new CardLayout(0, 0));
        dashboardPanel.setAutoscrolls(false);
        dashboardPanel.setBackground(new Color(-2697514));
        dashboardPanel.setMinimumSize(new Dimension(700, 350));
        dashboardPanel.setPreferredSize(new Dimension(1200, 600));
        panel1.add(dashboardPanel, "Card1");
        dashboardPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Smart Home Simulator", TitledBorder.LEFT, TitledBorder.BELOW_TOP, this.$$$getFont$$$(null, Font.BOLD, 14, dashboardPanel.getFont()), new Color(-16777216)));
        fullMainPanel = new JPanel();
        fullMainPanel.setLayout(new BorderLayout(0, 0));
        fullMainPanel.setOpaque(false);
        dashboardPanel.add(fullMainPanel, "Card1");
        userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BorderLayout(0, 0));
        userInfoPanel.setBackground(new Color(-986115));
        fullMainPanel.add(userInfoPanel, BorderLayout.WEST);
        userInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Simulation", TitledBorder.LEFT, TitledBorder.TOP, this.$$$getFont$$$(null, Font.BOLD, 14, userInfoPanel.getFont()), new Color(-16777216)));
        toggleButtonPanel = new JPanel();
        toggleButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        toggleButtonPanel.setOpaque(false);
        userInfoPanel.add(toggleButtonPanel, BorderLayout.NORTH);
        toggleButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        start_stop_label = new JLabel();
        start_stop_label.setAlignmentX(1.0f);
        start_stop_label.setForeground(new Color(-16777216));
        start_stop_label.setHorizontalAlignment(0);
        start_stop_label.setText("Paused");
        start_stop_label.setVisible(true);
        toggleButtonPanel.add(start_stop_label);
        lowerPanelofSimParameters = new JPanel();
        lowerPanelofSimParameters.setLayout(new BorderLayout(0, 0));
        lowerPanelofSimParameters.setOpaque(false);
        userInfoPanel.add(lowerPanelofSimParameters, BorderLayout.SOUTH);
        timeSlider = new JSlider();
        Font timeSliderFont = this.$$$getFont$$$(null, Font.BOLD, 12, timeSlider.getFont());
        if (timeSliderFont != null) timeSlider.setFont(timeSliderFont);
        timeSlider.setInverted(false);
        timeSlider.setMajorTickSpacing(2);
        timeSlider.setMaximum(50);
        timeSlider.setMinorTickSpacing(1);
        timeSlider.setOpaque(false);
        timeSlider.setOrientation(0);
        timeSlider.setPaintLabels(false);
        timeSlider.setPaintTicks(true);
        timeSlider.setSnapToTicks(true);
        timeSlider.setValue(10);
        timeSlider.setValueIsAdjusting(false);
        lowerPanelofSimParameters.add(timeSlider, BorderLayout.CENTER);
        final JLabel label1 = new JLabel();
        label1.setAlignmentX(0.5f);
        Font label1Font = this.$$$getFont$$$("Arial", Font.BOLD, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setHorizontalAlignment(0);
        label1.setText("Time Speed");
        lowerPanelofSimParameters.add(label1, BorderLayout.NORTH);
        fillerPanel = new JPanel();
        fillerPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        fillerPanel.setOpaque(false);
        lowerPanelofSimParameters.add(fillerPanel, BorderLayout.SOUTH);
        final JLabel label2 = new JLabel();
        label2.setText(" ");
        fillerPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText(" ");
        fillerPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        middleOfSimParam = new JPanel();
        middleOfSimParam.setLayout(new BorderLayout(0, 0));
        middleOfSimParam.setOpaque(false);
        userInfoPanel.add(middleOfSimParam, BorderLayout.CENTER);
        middleOfSimParam.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        editButton = new JButton();
        editButton.setBackground(new Color(-986115));
        editButton.setFocusPainted(false);
        editButton.setIcon(new ImageIcon(getClass().getResource("/src/UI/4472997_edit_edit icon_pen edit_pencil_icon.png")));
        editButton.setInheritsPopupMenu(false);
        editButton.setOpaque(false);
        editButton.setText("");
        middleOfSimParam.add(editButton, BorderLayout.NORTH);
        filler = new JPanel();
        filler.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Font fillerFont = this.$$$getFont$$$(null, Font.BOLD, 14, filler.getFont());
        if (fillerFont != null) filler.setFont(fillerFont);
        filler.setOpaque(false);
        middleOfSimParam.add(filler, BorderLayout.WEST);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setOpaque(false);
        middleOfSimParam.add(panel2, BorderLayout.EAST);
        clockPanel = new JPanel();
        clockPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        clockPanel.setOpaque(false);
        middleOfSimParam.add(clockPanel, BorderLayout.SOUTH);
        datePanel = new JPanel();
        datePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        datePanel.setOpaque(false);
        clockPanel.add(datePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dateLabel = new JLabel();
        Font dateLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, dateLabel.getFont());
        if (dateLabelFont != null) dateLabel.setFont(dateLabelFont);
        dateLabel.setText("Wed Oct 21 2024");
        datePanel.add(dateLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clockDisplay = new JLabel();
        Font clockDisplayFont = this.$$$getFont$$$(null, Font.BOLD, 14, clockDisplay.getFont());
        if (clockDisplayFont != null) clockDisplay.setFont(clockDisplayFont);
        clockDisplay.setText("      ");
        datePanel.add(clockDisplay, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText(" ");
        datePanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iconPanel = new JPanel();
        iconPanel.setLayout(new BorderLayout(0, 0));
        iconPanel.setOpaque(false);
        middleOfSimParam.add(iconPanel, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setOpaque(false);
        iconPanel.add(panel3, BorderLayout.NORTH);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setOpaque(false);
        iconPanel.add(panel4, BorderLayout.WEST);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setOpaque(false);
        iconPanel.add(panel5, BorderLayout.EAST);
        testing = new JPanel();
        testing.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        testing.setOpaque(false);
        iconPanel.add(testing, BorderLayout.CENTER);
        profileIconLabel = new JLabel();
        Font profileIconLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, profileIconLabel.getFont());
        if (profileIconLabelFont != null) profileIconLabel.setFont(profileIconLabelFont);
        profileIconLabel.setHorizontalAlignment(0);
        profileIconLabel.setIcon(new ImageIcon(getClass().getResource("/src/UI/smallProfile.png")));
        profileIconLabel.setText("");
        testing.add(profileIconLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tempLabel = new JLabel();
        Font tempLabelFont = this.$$$getFont$$$(null, Font.BOLD, 14, tempLabel.getFont());
        if (tempLabelFont != null) tempLabel.setFont(tempLabelFont);
        tempLabel.setHorizontalAlignment(0);
        tempLabel.setText("Outside Temp: ");
        testing.add(tempLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        locationLabel = new JLabel();
        locationLabel.setAutoscrolls(true);
        Font locationLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, locationLabel.getFont());
        if (locationLabelFont != null) locationLabel.setFont(locationLabelFont);
        locationLabel.setHorizontalAlignment(0);
        locationLabel.setHorizontalTextPosition(0);
        locationLabel.setText("Location:");
        testing.add(locationLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        userTypeLabel = new JLabel();
        Font userTypeLabelFont = this.$$$getFont$$$(null, -1, 14, userTypeLabel.getFont());
        if (userTypeLabelFont != null) userTypeLabel.setFont(userTypeLabelFont);
        userTypeLabel.setText("Parent");
        testing.add(userTypeLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Kitchen");
        testing.add(label5, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightMainPanel = new JSplitPane();
        rightMainPanel.setDividerLocation(285);
        rightMainPanel.setDividerSize(9);
        rightMainPanel.setDoubleBuffered(false);
        rightMainPanel.setOpaque(false);
        rightMainPanel.setOrientation(0);
        fullMainPanel.add(rightMainPanel, BorderLayout.CENTER);
        modulesAndLayoutPanel = new JSplitPane();
        modulesAndLayoutPanel.setDividerLocation(173);
        modulesAndLayoutPanel.setOpaque(true);
        rightMainPanel.setLeftComponent(modulesAndLayoutPanel);
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setOpaque(false);
        modulesAndLayoutPanel.setLeftComponent(tabbedPane1);
        tabbedPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("SHS", panel6);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("SHC", panel7);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel7.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new BorderLayout(0, 0));
        panel7.add(panel8, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, BorderLayout.WEST);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel10, BorderLayout.EAST);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel8.add(scrollPane1, BorderLayout.CENTER);
        textArea1 = new JTextArea();
        textArea1.setOpaque(false);
        textArea1.setText("");
        scrollPane1.setViewportView(textArea1);
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new BorderLayout(0, 0));
        panel7.add(panel11, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.add(panel12, BorderLayout.WEST);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel11.add(panel13, BorderLayout.EAST);
        list1 = new JList();
        panel11.add(list1, BorderLayout.CENTER);
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("SHP", panel14);
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("SHH", panel15);
        houseLayout = new JPanel();
        houseLayout.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        modulesAndLayoutPanel.setRightComponent(houseLayout);
        houseLayout.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        consolePanel = new JPanel();
        consolePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 20, 10, 20), -1, -1));
        consolePanel.setBackground(new Color(-16768062));
        consolePanel.setOpaque(false);
        rightMainPanel.setRightComponent(consolePanel);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setForeground(new Color(-10320921));
        scrollPane2.setHorizontalScrollBarPolicy(31);
        scrollPane2.setOpaque(false);
        scrollPane2.setVerticalScrollBarPolicy(20);
        consolePanel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setOpaque(false);
        outputArea.setWrapStyleWord(true);
        scrollPane2.setViewportView(outputArea);
        consoleLabel = new JLabel();
        consoleLabel.setText("Console");
        consolePanel.add(consoleLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

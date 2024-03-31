package src.UI;

import src.Controller;
import src.components.Room;
import src.components.RoomType;
import src.logic.Parent;
import src.logic.Permissions;
import src.logic.Profile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Dashboard extends JFrame {

    Controller controller = Controller.getController();
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
    public JTextArea outputArea;
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

    private Profile currentProfile;

    public Dashboard(Profile profile) {
        setProfileInfo(profile);

//        Timer timer = new Timer(1000, e -> {
//            // Update the temp label with the current time
//            tempLabel.setText("Oustide Temp. " + controller.getTemperature() + "ºC");
//            //dateLabel.setText((controller.getDate()));
//        });
//        timer.start();


        //dateLabel.setText(controller.getDate());
        setUpClockUI();
        //setUpOutputUI();


        outputArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        textArea1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        list1.setListData(new String[]{"idk", "idk", "idk"});
        add(panel1);

        setSize(1000, 500);
        // Center frame on screen

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

//        add(panel1);
//        setVisible(true);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: should be able to edit the simulation context --> maybe switch labels to JTextArea and then when button is clicked make them editable
            }
        });


        //todo get rooms array form controller
        rightMainPanel.setVisible(true);
        Room r1 = new Room(RoomType.BEDROOM, 2, 3, 1, null);
        Room r2 = new Room(RoomType.LIVINGROOM, 1, 4, 2, null);
        Room r3 = new Room(RoomType.BATHROOM, 0, 1, 1, null);
        Room r4 = new Room(RoomType.KITCHEN, 1, 2, 1, null);
        Room r5 = new Room(RoomType.GARAGE, 2, 5, 3, null);

        Room[] rooms = new Room[]{r1, r2, r3, r4, r5};
        createHouseLayout(rooms);

        //fullMainPanel.add(new SimParameterGUI(), BorderLayout.CENTER);
    }

    private void setUpClockUI() {
        ToggleButton toggle = new ToggleButton();
        toggle.setSize(10, 10);
        try {
            toggleButtonPanel.add(toggle, BorderLayout.CENTER);

            toggle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    if (!controller.getClock().isRunning().get()) {
                        controller.startSimulation();
                        start_stop_label.setText("Running");
                    } else {
                        controller.stopSimulation();
                        start_stop_label.setText("Paused");
                    }
                }

            });

            controller.attachObservers(clockDisplay, dateLabel,tempLabel);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(timeSlider.getMinimum() + " " + timeSlider.getMaximum() + " " + timeSlider.getExtent());
            timeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println("Current value" + timeSlider.getValue());
                    controller.getClock().changeSpeed((double) timeSlider.getValue() / 10);
                }
            });
            controller.startSimulation();
            controller.stopSimulation();
            //controller.attachObservers(clockDisplay);

            Timer timer = new Timer(1000, e -> {
                // Update the clock label with the current time
                clockDisplay.setText(controller.getTime().toString());
                tempLabel.setText("Oustide Temp. " + controller.getTemperature() + "ºC");
                //dateLabel.setText((controller.getDate()));
            });
            timer.start();
        }

    }

    public void createHouseLayout(Room[] rooms) {
        houseLayout.removeAll(); // Clear existing layout
        int gridSize = (int) Math.ceil(Math.sqrt(rooms.length));
        houseLayout.setLayout(new GridLayout(gridSize, gridSize)); // Setting a square grid layout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (Room room : rooms) {
            JPanel roomPanel = new JPanel(new GridBagLayout());
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel roomTypeLabel = new JLabel("Type: " + room.getType(), SwingConstants.CENTER);
            JButton lightsButton = new JButton(room.getLightsStatus());
            JButton doorsButton = new JButton(room.getDoorsStatus());
            JButton windowsButton = new JButton(room.getWindowsStatus());

            if (room.getNumLights() > 0) {
                updateButtonLook(lightsButton, room.getLights().isSwitchedOn());
                lightsButton.addActionListener(e -> {
                    try {
                        room.toggleLights(currentProfile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    updateButtonLook(lightsButton, room.getLights().isSwitchedOn());
                });
            } else {
                lightsButton.setEnabled(false); // Disable the button if there are no lights
                lightsButton.setText("Lights (0): N/A");
            }

            if (room.getNumDoors() > 0) {
                updateButtonLook(doorsButton, room.getDoors().isOpen());
                doorsButton.addActionListener(e -> {
                    try {
                        room.toggleDoors(currentProfile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    updateButtonLook(doorsButton, room.getDoors().isOpen());
                });
            } else {
                doorsButton.setEnabled(false); // Disable the button if there are no doors
                doorsButton.setText("Doors (0): N/A");
            }

            if (room.getNumWindows() > 0) {
                updateButtonLook(windowsButton, room.getWindows().isOpen());
                windowsButton.addActionListener(e -> {
                    try {
                        room.toggleWindows(currentProfile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    updateButtonLook(windowsButton, room.getWindows().isOpen());
                });
            } else {
                windowsButton.setEnabled(false); // Disable the button if there are no windows
                windowsButton.setText("Windows (0): N/A");
            }

            // Add components to the room panel with GridBagConstraints
            roomPanel.add(roomTypeLabel, gbc);
            roomPanel.add(lightsButton, gbc);
            roomPanel.add(doorsButton, gbc);
            roomPanel.add(windowsButton, gbc);

            houseLayout.add(roomPanel); // Adding the room to the house layout
        }

        // Fill remaining grid cells if rooms.length is not a perfect square number
        for (int i = rooms.length; i < gridSize * gridSize; i++) {
            houseLayout.add(new JPanel());
        }

        houseLayout.revalidate();
        houseLayout.repaint();
    }

    // In the Dashboard class
    public void appendToOutputArea(String text) {
        outputArea.append(text + "\n"); // Append the text and a newline to make it readable
    }

    // Helper method to update the appearance of the buttons
    private void updateButtonLook(JButton button, boolean isOn) {
        button.setText(isOn ? button.getText().replace("OFF", "ON") : button.getText().replace("ON", "OFF"));
        button.setText(isOn ? button.getText().replace("CLOSED", "OPEN") : button.getText().replace("OPEN", "CLOSED"));
        button.setForeground(isOn ? Color.GREEN : Color.RED);
    }

    private void setProfileInfo(Profile profile) {
        currentProfile = profile;
        userTypeLabel.setText(controller.getType(profile) + ": " + profile.getName());
    }

    public static void main(String[] args) {
        Profile p = new Profile("Sara", null);
        ArrayList<Profile> pfs = new ArrayList<Profile>();
        pfs.add(p);

        Room r1 = new Room(RoomType.BEDROOM, 2, 3, 1, pfs);
        Room r2 = new Room(RoomType.LIVINGROOM, 1, 4, 2, pfs);
        Room r3 = new Room(RoomType.BATHROOM, 0, 1, 1, pfs);
        Room r4 = new Room(RoomType.KITCHEN, 1, 2, 1, pfs);
        Room r5 = new Room(RoomType.GARAGE, 2, 5, 3, pfs);

        Room[] rooms = new Room[]{r1, r2, r3, r4, r5};

        System.out.println("Test from Dashboard class Main method:");

        Parent p1 = new Parent("Denis", "Denis123", "123", r1);
        Permissions p1Permissions = new Permissions(true, true, true, true, true);
        p1.setPermissions(p1Permissions);

        p.setRoom(r1);

        Dashboard d = new Dashboard(p1);

//         ArrayList<Profile> profiles = new ArrayList<Profile>();
//         profiles.add(p);
//         Room r = new Room(RoomType.BEDROOM,2,3,4,profiles);
//         p.setRoom(r);
//         Dashboard d = new Dashboard(null, p);

        d.setLocationRelativeTo(null);

        d.createHouseLayout(rooms); // Create layout with the array of rooms

    }
}

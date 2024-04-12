package src.UI;

import src.Controller;
import src.components.Room;
import src.components.RoomType;
import src.components.Zone;
import src.logic.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
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
    private JLabel profileLocationLabel;
    private JTextPane consoleOutput;
    private JTable zoneTable;
    private JPanel SHCmainPanel;
    private JScrollPane SHHScrollPanel;
    private JPanel panelForSHH;
    private JPanel SHSmainPanel;
    private JPanel leftFiller;
    private JPanel rightFiller;
    private JPanel SHSPanel;
    private JPanel SHSpanel;
    private JPanel accountPanel;
    private JPanel test;
    private JPanel test2;

    //private JLabel consoleText;

    private Profile currentProfile;
    private JFrame thisFrame;
    private ToggleButton toggle;

    public Dashboard(Profile profile) {
        thisFrame = this;
       // consoleOutput.setText(consoleOutput.getText() + "\n["+ controller.getTime() + "]: Logged in as " + profile.getName() + ".");
        setProfileInfo(profile);
        tempLabel.setVerticalAlignment(SwingConstants.TOP);
        tempLabel.setPreferredSize(new Dimension(300, 200));

        setUpClockUI();
        setUpSHHTab();
        setUpSHCTab();
        setUpSHSTab();
        //setUpOutputUI();
        profileLocationLabel.setText(profile.getLocation().getType().toString());
        add(panel1);

        setSize(1000, 500);
        // Center frame on screen

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo: should be able to edit the simulation context --> maybe switch labels to JTextArea and then when button is clicked make them editable
                //As the user edits --> stop the time + todo: stop simulation?
                toggle.setSelected(false);
                controller.stopSimulation();

                JDialog popup = new JDialog(thisFrame, "Edit Context of Simulation", true);
                popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                popup.setLayout(new BorderLayout());

                ContextPopUp c = new ContextPopUp(profile,thisFrame,consoleOutput);
                JPanel popupPanel = c.getPopupContent();
                popup.add(popupPanel, BorderLayout.CENTER);

                popup.setSize(650, 350);
                popup.setLocationRelativeTo(thisFrame);
                popup.setVisible(true); // Display the popup

                popup.pack();
            }
        });


        rightMainPanel.setVisible(true);

        ArrayList<Room> ar = controller.getRooms();
        Room[] rooms = ar.toArray(new Room[ar.size()]);
        createHouseLayout(rooms);

        setLocationRelativeTo(null);
        //fullMainPanel.add(new SimParameterGUI(), BorderLayout.CENTER);
    }

    public Dashboard(Profile p, String outputMessage){
        this(p);
        consoleOutput.setText(consoleOutput.getText() + outputMessage);
    }
    private void setUpSHSTab() {
        ArrayList<Profile> profiles = controller.getProfiles();
        SHSmainPanel.setLayout(new GridLayout(profiles.size(), 1));

        for (Profile p : profiles) {
            JPanel panel = new JPanel(new FlowLayout());

            TitledBorder titledBorder = BorderFactory.createTitledBorder(p.getName());

            titledBorder.setTitleJustification(TitledBorder.CENTER); // Title alignment
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14)); // Title font
            panel.setBorder(titledBorder);
            panel.setLayout(new GridLayout(8, 2));

            JLabel nameLabel = new JLabel("Name: ");
            JTextField name = new JTextField(p.getName());
            name.setEditable(false);
            panel.add(nameLabel);
            panel.add(name);

            JTextField username = null, password = null;
            if (p instanceof Parent) {
                JLabel usernameLabel = new JLabel("Username");
                username = new JTextField(((Parent) p).getUserName());
                username.setEditable(false);
                panel.add(usernameLabel);
                panel.add(username);

                JLabel passwordLabel = new JLabel("password");
                password = new JTextField(((Parent) p).getPassword());
                password.setEditable(false);
                panel.add(passwordLabel);
                panel.add(password);

            } else if (p instanceof Child) {
                JLabel usernameLabel = new JLabel("Username");
                username = new JTextField(((Child) p).getUserName());
                username.setEditable(false);
                panel.add(usernameLabel);
                panel.add(username);

                JLabel passwordLabel = new JLabel("password");
                password = new JTextField(((Child) p).getPassword());
                password.setEditable(false);
                panel.add(passwordLabel);
                panel.add(password);

            } else if (p instanceof Guest) {
                JLabel usernameLabel = new JLabel("Username");
                username = new JTextField(((Guest) p).getUserName());
                username.setEditable(false);
                panel.add(usernameLabel);
                panel.add(username);

                JLabel passwordLabel = new JLabel("password");
                password = new JTextField(((Guest) p).getPassword());
                password.setEditable(false);
                panel.add(passwordLabel);
                panel.add(password);
            }

            JLabel permissionsLabel = new JLabel("Permissions");
            JButton windowButt = new JButton("windows");
            if(p.getPermissions().getWindowsPermission()){
                windowButt.setForeground(Color.GREEN);
            }else{
                windowButt.setForeground(Color.RED);
            }
            windowButt.setEnabled(false);
            panel.add(permissionsLabel);
            panel.add(windowButt);

            JLabel emptyLabel = new JLabel("Label for format");
            panel.add(emptyLabel);
            emptyLabel.setVisible(false);

            JButton lightsButt = new JButton("Lights");
            if(p.getPermissions().getLightsPermission()){
                lightsButt.setForeground(Color.GREEN);
            }else{
                lightsButt.setForeground(Color.RED);
            }
            lightsButt.setEnabled(false);
            panel.add(lightsButt);

            JLabel emptyLabel1 = new JLabel("Label for format");
            panel.add(emptyLabel1);
            emptyLabel1.setVisible(false);

            JButton doorsButt = new JButton("Doors");
            if(p.getPermissions().getDoorsPermission()){
                doorsButt.setForeground(Color.GREEN);
            }else{
                doorsButt.setForeground(Color.RED);
            }
            doorsButt.setEnabled(false);
            panel.add(doorsButt);

            JLabel emptyLabel2 = new JLabel("Label for format");
            panel.add(emptyLabel2);
            emptyLabel2.setVisible(false);

            JButton garageButt = new JButton("Garage");
            if(p.getPermissions().getGarageDoorPermission()){
                garageButt.setForeground(Color.GREEN);
            }else{
                garageButt.setForeground(Color.RED);
            }
            garageButt.setEnabled(false);
            panel.add(garageButt);


            JButton editButton = new JButton("Edit");
            // JTextField finalUsername = username;
            JTextField finalUsername = username;
            JTextField finalPassword = password;
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    windowButt.setEnabled(true);
                    lightsButt.setEnabled(true);
                    doorsButt.setEnabled(true);
                    garageButt.setEnabled(true);

                    name.setEditable(true);
                    if (finalUsername != null && finalPassword != null) {
                        finalUsername.setEditable(true);
                        finalPassword.setEditable(true);

                        finalPassword.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                controller.changeUserPassword(p,finalPassword.getText());
                                consoleOutput.setText(consoleOutput.getText() + "\n[ " +  controller.getTime() + "] : " +
                                        "Passowrd changed for profile " + p.getName());

                                finalPassword.setEditable(false);
                                name.setEditable(false);
                                finalUsername.setEditable(false);
                            }
                        });

                        finalUsername.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                consoleOutput.setText(consoleOutput.getText() + "\n[ " +  controller.getTime() + "] : " +
                                        "Username name of profile " + p.getName() + " changed to " + finalUsername.getText());
                                finalPassword.setEditable(false);
                                name.setEditable(false);
                                finalUsername.setEditable(false);

                            }
                        });
                    }

                    name.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            consoleOutput.setText(consoleOutput.getText() + "\n[ " +  controller.getTime() + "] : " +
                                    "Profile w/ name \' " + p.getName() + "\' changed to named to \'" + name.getText() + "\'");
                            controller.changeProfileName(p, name.getText());
                            titledBorder.setTitle(p.getName());

                            panel.revalidate();
                            panel.repaint();

                            if(finalPassword !=null && finalUsername != null){
                                finalPassword.setEditable(false);
                                finalUsername.setEditable(false);
                            }

                            name.setEditable(false);
                            windowButt.setEnabled(false);
                            lightsButt.setEnabled(false);
                            doorsButt.setEnabled(false);
                            garageButt.setEnabled(false);
                        }
                    });

                    changePermissions(windowButt,lightsButt,doorsButt,garageButt,p);
                }
            });

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (controller.getCurrentLoggedInUser() == p) {
                        consoleOutput.setText(consoleOutput.getText() + "\n[" + controller.getTime() + "]: Cannot delete" + " profile that is currently logged in. " + "Try again after logging out.");
                    } else {
                        SHSmainPanel.remove(panel);
                        SHSmainPanel.revalidate();
                        SHSmainPanel.repaint();

                        consoleOutput.setText(consoleOutput.getText() + "\n[" + controller.getTime() + "]: Profile" + " for " + p.getName() + " was successfully deleted.");
                        controller.deleteProfile(p);
                    }


                }
            });
            panel.add(editButton);
            panel.add(deleteButton);
            SHSmainPanel.add(panel);
        }

        //accountPanel.add(deleteButton);

    }

    private void changePermissions(JButton windowButt, JButton lightsButt, JButton doorsButt, JButton garageButt, Profile p) {
        windowButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(windowButt.getForeground() == Color.GREEN){
                    controller.changePermission("Window", p, false);
                    windowButt.setForeground(Color.RED);
                }else{
                    controller.changePermission("Window", p, true);
                    windowButt.setForeground(Color.RED);
                }
                windowButt.setEnabled(false);
                lightsButt.setEnabled(false);
                doorsButt.setEnabled(false);
                garageButt.setEnabled(false);

                consoleOutput.setText(consoleOutput.getText() + "\n[" + controller.getTime() + "]: Window permission changed " +
                        "for profile " + p.getName());
            }
        });

        lightsButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(windowButt.getForeground() == Color.GREEN){
                    controller.changePermission("Lights", p, false);
                    windowButt.setForeground(Color.RED);
                }else{
                    controller.changePermission("Lights", p, true);
                    windowButt.setForeground(Color.GREEN);
                }

                windowButt.setEnabled(false);
                lightsButt.setEnabled(false);
                doorsButt.setEnabled(false);
                garageButt.setEnabled(false);

                consoleOutput.setText(consoleOutput.getText() + "\n[" + controller.getTime() + "]: Light permission changed " +
                        "for profile " + p.getName());
            }
        });

        doorsButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(doorsButt.getForeground() == Color.GREEN){
                    controller.changePermission("Doors", p, false);
                    doorsButt.setForeground(Color.RED);
                }else{
                    controller.changePermission("Doors", p, true);
                    doorsButt.setForeground(Color.GREEN);
                }

                windowButt.setEnabled(false);
                lightsButt.setEnabled(false);
                doorsButt.setEnabled(false);
                garageButt.setEnabled(false);

                consoleOutput.setText(consoleOutput.getText() + "\n[" + controller.getTime() + "]: Doors permission changed " +
                        "for profile " + p.getName());
            }

        });

        garageButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(garageButt.getForeground() == Color.GREEN){
                    controller.changePermission("Garage", p, false);
                    garageButt.setForeground(Color.RED);
                }else{
                    controller.changePermission("Garage", p, true);
                    garageButt.setForeground(Color.GREEN);
                }
                windowButt.setEnabled(false);
                lightsButt.setEnabled(false);
                doorsButt.setEnabled(false);
                garageButt.setEnabled(false);

                consoleOutput.setText(consoleOutput.getText() + "\n[" + controller.getTime() + "]: Garage permission changed " +
                        "for profile " + p.getName());
            }
        });
    }

    private void setUpClockUI() {
        toggle = new ToggleButton();
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

            controller.attachObservers(clockDisplay, dateLabel, tempLabel, consoleOutput);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(timeSlider.getMinimum() + " " + timeSlider.getMaximum() + " " + timeSlider.getExtent());
            timeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    //System.out.println("Current value" + timeSlider.getValue());
                    controller.changeSpeed((double) timeSlider.getValue() / 100);
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
//            roomPanel.add(lightsButton, gbc);
//            roomPanel.add(doorsButton, gbc);
//            roomPanel.add(windowsButton, gbc);

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

    private void setUpSHHTab() {
        ArrayList<Zone> zones = controller.getZones();

        zoneTable = new JTable();
        // Create column names (array of strings)
        String[] columnNames = {"Zone", "Zone Type", "Temperature"};

        // Create a table model using DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        zoneTable.setModel(model);

        String[][] data = new String[zones.size()][3];

        for (int i = 0; i < zones.size(); i++) {
            data[i][0] = "Zone " + i;
            data[i][1] = zones.get(i).getType();
            data[i][2] = String.valueOf(zones.get(i).getTemperature());
            model.addRow(data[i]);
        }
        handleTableChanges(model, zones);
        //zoneTable.setE
        //zoneTable.getColumnModel().getColumn(2).setCellEditor(new CellEditor(controller));

        //TODO add cell editor to the table to change the values of temperature and set it in the simulation paramaters
        zoneTable.setRowHeight(25); // Set row height
        zoneTable.getColumnModel().getColumn(1).setPreferredWidth(50);


        panelForSHH.add(zoneTable);
    }

    private void handleTableChanges(DefaultTableModel model, ArrayList<Zone> zones) {
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();

            Zone z = zones.get(row);
            switch (column) {
                case 1: // Type column
                    String newType = (String) model.getValueAt(row, column);
                    System.out.println(newType);
                    controller.setZoneType(z, newType);
                    break;
                case 2: // Temperature column
                    String temperature = (String) model.getValueAt(row, column);
                    Double newTemperature = Double.parseDouble(temperature);
                    System.out.println(newTemperature);
                    controller.setZoneTemperature(newTemperature, z);
                    // obj.setTemperature(newTemperature);
                    break;
            }
        });
    }

    private void setUpSHCTab() {
        ArrayList<Room> rooms = controller.getRooms();
        SHCmainPanel.setLayout(new GridLayout(rooms.size(), 1));
        for (Room r : rooms) {
            JPanel roomPanel = new JPanel();
            TitledBorder titledBorder = BorderFactory.createTitledBorder(r.getType().toString() + " " + r.getId());

            titledBorder.setTitleJustification(TitledBorder.CENTER); // Title alignment
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 14)); // Title font
            roomPanel.setBorder(titledBorder);
            roomPanel.setLayout(new GridLayout(3, 1));

            JButton lightsButton = new JButton(r.getLightsStatus());
            JButton doorsButton = new JButton(r.getDoorsStatus());
            JButton windowsButton = new JButton(r.getWindowsStatus());

            roomPanel.add(lightsButton);
            roomPanel.add(doorsButton);
            roomPanel.add(windowsButton);

            if (r.getNumLights() > 0) {
                updateButtonLook(lightsButton, r.getLights().isSwitchedOn());
                lightsButton.addActionListener(e -> {
                    try {
                        r.toggleLights(currentProfile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    updateButtonLook(lightsButton, r.getLights().isSwitchedOn());
                });
            } else {
                lightsButton.setEnabled(false); // Disable the button if there are no lights
                lightsButton.setText("Lights (0): N/A");
            }

            if (r.getNumDoors() > 0) {
                updateButtonLook(doorsButton, r.getDoors().isOpen());
                doorsButton.addActionListener(e -> {
                    try {
                        r.toggleDoors(currentProfile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    updateButtonLook(doorsButton, r.getDoors().isOpen());
                });
            } else {
                doorsButton.setEnabled(false); // Disable the button if there are no doors
                doorsButton.setText("Doors (0): N/A");
            }

            if (r.getNumWindows() > 0) {
                updateButtonLook(windowsButton, r.getWindows().isOpen());
                windowsButton.addActionListener(e -> {
                    try {
                        r.toggleWindows(currentProfile);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    updateButtonLook(windowsButton, r.getWindows().isOpen());
                });
            } else {
                windowsButton.setEnabled(false); // Disable the button if there are no windows
                windowsButton.setText("Windows (0): N/A");
            }

            SHCmainPanel.add(roomPanel);
        }
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

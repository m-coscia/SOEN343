package src.UI;

import src.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

//todo: if selected type is stranger --> dont let them input a usernmae and password.
//todo: if selected type is parent --> all checkboxes selected;
public class ProfilesCreation extends JFrame {
    private final Controller controller = Controller.getController();
    private JFrame thisFrame;
    private JPanel panel1;
    private JPanel test;
    private JPanel test1;
    private JPanel test2;
    private JPanel test3;
    private JPanel middle;
    private JButton backButton;
    private ArrayList<JTextField> nameFields;
    private ArrayList<JComboBox<String>> typeFields;
    private ArrayList<JTextField> usernameFields;
    private ArrayList<JPasswordField> passwordFields;
    private ArrayList<JCheckBox> windowsCheckboxes;
    private ArrayList<JCheckBox> doorsCheckboxes;
    private ArrayList<JCheckBox> garageCheckboxes;
    private ArrayList<JCheckBox> lightsCheckboxes;

    private ArrayList<JComboBox<String>> locationsFields;

    public ProfilesCreation(JFrame previousFrame) {
        thisFrame = this;
        initializeLists();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                previousFrame.setVisible(true);
            }
        });

        addComponents();

        add(panel1);
        pack();
        setSize(1200, getHeight());
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void addComponents() {
        test.add(new JLabel(""));
        test1.add(new JLabel(""));
        test2.add(new JLabel(""));
        test3.add(new JLabel(""));
        //setLayout(new GridLayout(1,10));
        JLabel nameLabel = new JLabel("Profile Name");
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel typeLabel = new JLabel("Profile Type");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Passwords");
        JLabel windowLabel = new JLabel("Window");
        JLabel doorLabel = new JLabel("Doors");
        JLabel garageLabel = new JLabel("Garage");
        JLabel lightsLabel = new JLabel("Lights");
        JLabel locatioLabel = new JLabel("Location");
        JLabel randomLabel1 = new JLabel("");
        JLabel randomLabel2 = new JLabel("");
        //JLabel randomLabel3 = new JLabel("");


        middle.setLayout(new GridLayout(0, 11));
        middle.add(nameLabel);
        middle.add(typeLabel);
        middle.add(usernameLabel);
        middle.add(passwordLabel);
        middle.add(windowLabel);
        middle.add(doorLabel);
        middle.add(garageLabel);
        middle.add(lightsLabel);
        middle.add(locatioLabel);
        middle.add(randomLabel1); // Empty label for spacing
        middle.add(randomLabel2); // Empty label for spacing

        JTextField nameField = new JTextField();
        middle.add(nameField);
        nameFields.add(nameField);

        JComboBox<String> typeField = new JComboBox<String>(new String[]{"", "Parent", "Child", "Guest", "Stranger"});
        middle.add(typeField);
        typeFields.add(typeField);


        JTextField usernameField = new JTextField();
        middle.add(usernameField);
        usernameFields.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        middle.add(passwordField);
        passwordFields.add(passwordField);

        JCheckBox windowsCheckbox = new JCheckBox();
        middle.add(windowsCheckbox);
        windowsCheckboxes.add(windowsCheckbox);

        JCheckBox doorsCheckbox = new JCheckBox();
        middle.add(doorsCheckbox);
        doorsCheckboxes.add(doorsCheckbox);

        JCheckBox garageCheckbox = new JCheckBox();
        middle.add(garageCheckbox);
        garageCheckboxes.add(garageCheckbox);

        JCheckBox lightsCheckbox = new JCheckBox();
        middle.add(lightsCheckbox);
        lightsCheckboxes.add(lightsCheckbox);


        String[] rooms = controller.getExistingLocations();
        //JComboBox<String> locationField = new JComboBox<String>(new String[]{"" ,"BEDROOM", "LIVINGROOM", "BATHROOM", "KITCHEN", "GARAGE"});
        JComboBox<String> locationField = new JComboBox<>(rooms);
        middle.add(locationField);
        locationsFields.add(locationField);

        typeField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Parent")) {
                    lightsCheckbox.setSelected(true);
                    windowsCheckbox.setSelected(true);
                    doorsCheckbox.setSelected(true);
                    garageCheckbox.setSelected(true);
                } else if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Stranger")) {
                    usernameField.setEnabled(false);
                    usernameField.setBackground(Color.GRAY);
                    passwordField.setEnabled(false);
                    passwordField.setBackground(Color.GRAY);
                }
            }
        });

        JButton addButton = new JButton("Add Profile");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProfileFields();
            }
        });
        middle.add(addButton);

        JButton stopButton = new JButton("Done");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You stopped adding profiles");
                saveProfiles();
//                DashboardFrame dashboard = new DashboardFrame(thisFrame);
//                dashboard.setVisible(true);
                ProfilesLoginFrame loginFrame = new ProfilesLoginFrame(thisFrame);
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setVisible(true);
                dispose();
            }
        });

        middle.add(stopButton);
    }

    private void addProfileFields() {
        JTextField nameField = new JTextField();
        middle.add(nameField);
        nameFields.add(nameField);

        JComboBox<String> typeField = new JComboBox<String>(new String[]{"", "Parent", "Child", "Guest", "Stranger"});
        middle.add(typeField);
        typeFields.add(typeField);

        JTextField usernameField = new JTextField();
        middle.add(usernameField);
        usernameFields.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        middle.add(passwordField);
        passwordFields.add(passwordField);

        JCheckBox windowsCheckbox = new JCheckBox();
        middle.add(windowsCheckbox);
        windowsCheckboxes.add(windowsCheckbox);

        JCheckBox doorsCheckbox = new JCheckBox();
        middle.add(doorsCheckbox);
        doorsCheckboxes.add(doorsCheckbox);

        JCheckBox garageCheckbox = new JCheckBox();
        middle.add(garageCheckbox);
        garageCheckboxes.add(garageCheckbox);

        JCheckBox lightsCheckbox = new JCheckBox();
        middle.add(lightsCheckbox);
        lightsCheckboxes.add(lightsCheckbox);


        String[] rooms = controller.getExistingLocations();
        //JComboBox<String> locationField = new JComboBox<String>(new String[]{"" ,"BEDROOM", "LIVINGROOM", "BATHROOM", "KITCHEN", "GARAGE"});
        JComboBox<String> locationField = new JComboBox<>(rooms);
        locationsFields.add(locationField);
        middle.add(locationField);

        JCheckBox unusable1 = new JCheckBox();
        unusable1.setVisible(false); //so we don't see it, just for formatting
        middle.add(unusable1);

        JCheckBox unusable2 = new JCheckBox();
        unusable2.setVisible(false);
        middle.add(unusable2);//so we don't see it, just for formatting
        //middle.add(unusable2);

        JCheckBox unusable3 = new JCheckBox();
        unusable3.setVisible(false); //so we don't see it, just for formatting
        //middle.add(unusable3);

        typeField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Parent")) {
                    lightsCheckbox.setSelected(true);
                    windowsCheckbox.setSelected(true);
                    doorsCheckbox.setSelected(true);
                    garageCheckbox.setSelected(true);
                } else if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Stranger")) {
                    usernameField.setEnabled(false);
                    usernameField.setBackground(Color.GRAY);
                    passwordField.setEnabled(false);
                    passwordField.setBackground(Color.GRAY);
                }
            }
        });

        //will recheck the placements of the elements in the panel
       // revalidate();
        repaint();
        pack();
        setSize(1200,getHeight());
    }

    private void saveProfiles() {
        controller.saveProfiles(nameFields, usernameFields, passwordFields, typeFields, windowsCheckboxes,
                doorsCheckboxes, garageCheckboxes, lightsCheckboxes, locationsFields);
    }



    private void initializeLists() {
        //content.setBorder(new LineBorder(Color.BLACK));
        nameFields = new ArrayList<>();
        usernameFields = new ArrayList<>();
        passwordFields = new ArrayList<>();
        typeFields = new ArrayList<>();
        windowsCheckboxes = new ArrayList<>();
        doorsCheckboxes = new ArrayList<>();
        garageCheckboxes = new ArrayList<>();
        lightsCheckboxes = new ArrayList<>();
        locationsFields = new ArrayList<JComboBox<String>>();
    }

    public static void main(String[] args) {
        new ProfilesCreation(null);

    }
}

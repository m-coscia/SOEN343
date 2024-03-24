package src.main.java.UI;

import javax.swing.*;
import javax.swing.border.LineBorder;

import src.main.java.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

//todo: if selected type is stranger --> dont let them input a usernmae and password.
//todo: if selected type is parent --> all checkboxes selected;
public class ProfilesCreationFrame extends JFrame {
    JFrame previousFrame = null;
    JFrame thisFrame = this;
    JPanel content = new JPanel(new GridLayout(0, 10));
    private Controller controller = new Controller();
    private ArrayList<JTextField> nameFields;
    private ArrayList<JComboBox<String>> typeFields;
    private ArrayList<JTextField> usernameFields;
    private ArrayList<JPasswordField> passwordFields;
    private ArrayList<JCheckBox> windowsCheckboxes;
    private ArrayList<JCheckBox> doorsCheckboxes;
    private ArrayList<JCheckBox> garageCheckboxes;
    private ArrayList<JCheckBox> lightsCheckboxes;

    private ArrayList<JComboBox<Integer>> locationsFields;

    public ProfilesCreationFrame(JFrame previousFrame) {
        this.previousFrame = previousFrame;
        content.setBorder(new LineBorder(Color.BLACK));
        nameFields = new ArrayList<>();
        usernameFields = new ArrayList<>();
        passwordFields = new ArrayList<>();
        typeFields = new ArrayList<>();
        windowsCheckboxes = new ArrayList<>();
        doorsCheckboxes = new ArrayList<>();
        garageCheckboxes = new ArrayList<>();
        lightsCheckboxes = new ArrayList<>();
        locationsFields = new ArrayList<>();

        //setLayout(new GridLayout(0, 13));
        JLabel nameLabel = new JLabel("Profile Name");
        JLabel typeLabel = new JLabel("Profile Type");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Passwords");
        JLabel windowLabel = new JLabel("Window");
        JLabel doorLabel = new JLabel("Doors");
        JLabel garageLabel = new JLabel("Garage");
        JLabel lightsLabel = new JLabel("Lights");
        JLabel locatioLabel = new JLabel("Location");
        JLabel randomLabel1 = new JLabel("");
        //JLabel randomLabel2 = new JLabel("");
        //JLabel randomLabel3 = new JLabel("");

        content.add(nameLabel);
        content.add(typeLabel);
        content.add(usernameLabel);
        content.add(passwordLabel);
        content.add(windowLabel);
        content.add(doorLabel);
        content.add(garageLabel);
        content.add(lightsLabel);
        content.add(locatioLabel);
        content.add(randomLabel1); // Empty label for spacing
        //content.add(randomLabel2); // Empty label for spacing
        //content.add(randomLabel3); // Empty label for spacing

        JTextField nameField = new JTextField();
        content.add(nameField);
        nameFields.add(nameField);

        JComboBox<String> typeField = new JComboBox<String>(new String[]{"", "Parent", "Child", "Guest", "Stranger"});
        content.add(typeField);
        typeFields.add(typeField);


        JTextField usernameField = new JTextField();
        content.add(usernameField);
        usernameFields.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        content.add(passwordField);
        passwordFields.add(passwordField);

        JCheckBox windowsCheckbox = new JCheckBox();
        content.add(windowsCheckbox);
        windowsCheckboxes.add(windowsCheckbox);

        JCheckBox doorsCheckbox = new JCheckBox();
        content.add(doorsCheckbox);
        doorsCheckboxes.add(doorsCheckbox);

        JCheckBox garageCheckbox = new JCheckBox();
        content.add(garageCheckbox);
        garageCheckboxes.add(garageCheckbox);

        JCheckBox lightsCheckbox = new JCheckBox();
        content.add(lightsCheckbox);
        lightsCheckboxes.add(lightsCheckbox);

        JComboBox<Integer> locationField = new JComboBox<Integer>(new Integer[]{1, 2, 3, 4});
        content.add(locationField);
        locationsFields.add(locationField);

        typeField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Parent")) {
                    lightsCheckbox.setSelected(true);
                    windowsCheckbox.setSelected(true);
                    doorsCheckbox.setSelected(true);
                    garageCheckbox.setSelected(true);
                }else if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Stranger")){
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

        JButton stopButton = new JButton("Done");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You stopped adding profiles");
                saveProfiles();
//                DashboardFrame dashboard = new DashboardFrame(thisFrame);
//                dashboard.setVisible(true);
                ProfilesLoginFrame loginFrame = new ProfilesLoginFrame(thisFrame, controller.getProfiles());
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setVisible(true);
                dispose();
            }
        });
        content.add(addButton);
        content.add(stopButton);

        setLayout(new BorderLayout());
        JPanel filler = new JPanel();
        filler.add(new JLabel("Smart Home Simulator"));
        filler.setBackground(new Color(180, 190, 206));
        JPanel filler2 = new JPanel();

        add(filler, BorderLayout.NORTH);
        add(filler2, BorderLayout.EAST);
        //add(filler, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addProfileFields() {
        JTextField nameField = new JTextField();
        content.add(nameField);
        nameFields.add(nameField);

        JComboBox<String> typeField = new JComboBox<String>(new String[]{"","Parent", "Child", "Guest", "Stranger"});
        content.add(typeField);
        typeFields.add(typeField);

        JTextField usernameField = new JTextField();
        content.add(usernameField);
        usernameFields.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        content.add(passwordField);
        passwordFields.add(passwordField);

        JCheckBox windowsCheckbox = new JCheckBox();
        content.add(windowsCheckbox);
        windowsCheckboxes.add(windowsCheckbox);

        JCheckBox doorsCheckbox = new JCheckBox();
        content.add(doorsCheckbox);
        doorsCheckboxes.add(doorsCheckbox);

        JCheckBox garageCheckbox = new JCheckBox();
        content.add(garageCheckbox);
        garageCheckboxes.add(garageCheckbox);

        JCheckBox lightsCheckbox = new JCheckBox();
        content.add(lightsCheckbox);
        lightsCheckboxes.add(lightsCheckbox);

        JCheckBox unusable1 = new JCheckBox();
        unusable1.setVisible(false); //so we don't see it, just for formatting
        content.add(unusable1);
        JCheckBox unusable2 = new JCheckBox();
        unusable2.setVisible(false); //so we don't see it, just for formatting
        content.add(unusable2);

        JCheckBox unusable3 = new JCheckBox();
        unusable3.setVisible(false); //so we don't see it, just for formatting
        content.add(unusable3);


        typeField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Parent")) {
                    lightsCheckbox.setSelected(true);
                    windowsCheckbox.setSelected(true);
                    doorsCheckbox.setSelected(true);
                    garageCheckbox.setSelected(true);
                }else if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Stranger")){
                    usernameField.setEnabled(false);
                    usernameField.setBackground(Color.GRAY);
                    passwordField.setEnabled(false);
                    passwordField.setBackground(Color.GRAY);
                }
            }
        });

        //will recheck the placements of the elements in the panel
        revalidate();
        repaint();
        pack();
    }

    private void saveProfiles() {
        controller.saveProfiles(nameFields, usernameFields, passwordFields, typeFields, windowsCheckboxes,
                doorsCheckboxes, garageCheckboxes, lightsCheckboxes);
    }

    public static void main(String[] args) {

        ProfilesCreationFrame profilePanel = new ProfilesCreationFrame(null);
        profilePanel.setVisible(true);

    }
}



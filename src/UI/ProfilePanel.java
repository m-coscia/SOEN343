package src.UI;
import src.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
//todo: if selected type is stranger --> dont let them input a usernmae and password.
//todo: if selected type is parent --> all checkboxes selected;
public class ProfilePanel extends JPanel {
    private Controller controller = new Controller();
    private ArrayList<JTextField> nameFields;
    private ArrayList<JComboBox<String>> typeFields;
    private ArrayList<JTextField> usernameFields;
    private ArrayList<JPasswordField> passwordFields;
    private ArrayList<JCheckBox> windowsCheckboxes;

    private ArrayList<JCheckBox> doorsCheckboxes;
    private ArrayList<JCheckBox> garageCheckboxes;
    private ArrayList<JCheckBox> lightsCheckboxes;

    public ProfilePanel() {
        setBorder(new LineBorder(Color.BLACK));
        nameFields = new ArrayList<>();
        usernameFields = new ArrayList<>();
        passwordFields = new ArrayList<>();
        typeFields = new ArrayList<>();
        windowsCheckboxes = new ArrayList<>();
        doorsCheckboxes = new ArrayList<>();
        garageCheckboxes = new ArrayList<>();
        lightsCheckboxes = new ArrayList<>();


        setLayout(new GridLayout(0, 10));
        JLabel nameLabel = new JLabel("Profile Name");
        JLabel typeLabel = new JLabel("Profile Type");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Passwords");
        JLabel windowLabel = new JLabel("Window");
        JLabel doorLabel = new JLabel("Doors");
        JLabel garageLabel = new JLabel("Garage");
        JLabel lightsLabel = new JLabel("Lights");
        JLabel randomLabel1 = new JLabel("");
        JLabel randomLabel2 = new JLabel("");

        add(nameLabel);
        add(typeLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(windowLabel);
        add(doorLabel);
        add(garageLabel);
        add(lightsLabel);
        add(randomLabel1); // Empty label for spacing
        add(randomLabel2); // Empty label for spacing

        JTextField nameField = new JTextField();
        add(nameField);
        nameFields.add(nameField);

        JComboBox<String> typeField= new JComboBox<String>(new String[]{"Parent", "Child", "Guest", "Stranger"});
        add(typeField);
        typeFields.add(typeField);

        JTextField usernameField = new JTextField();
        add(usernameField);
        nameFields.add(nameField);

        JPasswordField passwordField = new JPasswordField();
        add(passwordField);
        passwordFields.add(passwordField);

        JCheckBox windowsCheckbox = new JCheckBox();
        add(windowsCheckbox);
        windowsCheckboxes.add(windowsCheckbox);

        JCheckBox doorsCheckbox = new JCheckBox();
        add(doorsCheckbox);
        doorsCheckboxes.add(doorsCheckbox);

        JCheckBox garageCheckbox = new JCheckBox();
        add(garageCheckbox);
        garageCheckboxes.add(garageCheckbox);

        JCheckBox lightsCheckbox = new JCheckBox();
        add(lightsCheckbox);
        lightsCheckboxes.add(lightsCheckbox);

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
            }
        });
        add(addButton);
        add(stopButton);
    }

    private void addProfileFields() {
        JTextField nameField = new JTextField();
        add(nameField);
        nameFields.add(nameField);

        JComboBox<String> typeField= new JComboBox<String>(new String[]{"Parent", "Child", "Guest", "Stranger"});
        add(typeField);
        typeFields.add(typeField);

        JTextField usernameField = new JTextField();
        add(usernameField);
        usernameFields.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        add(passwordField);
        passwordFields.add(passwordField);

        JCheckBox windowsCheckbox = new JCheckBox();
        add(windowsCheckbox);
        windowsCheckboxes.add(windowsCheckbox);

        JCheckBox doorsCheckbox = new JCheckBox();
        add(doorsCheckbox);
        doorsCheckboxes.add(doorsCheckbox);

        JCheckBox garageCheckbox = new JCheckBox();
        add(garageCheckbox);
        garageCheckboxes.add(garageCheckbox);

        JCheckBox lightsCheckbox = new JCheckBox();
        add(lightsCheckbox);
        lightsCheckboxes.add(lightsCheckbox);

        JCheckBox unusable1 = new JCheckBox();
        unusable1.setVisible(false); //so we don't see it, just for formatting
        add(unusable1);
        JCheckBox unusable2 = new JCheckBox();
        unusable2.setVisible(false); //so we don't see it, just for formatting
        add(unusable2);

        //will recheck the placements of the elements in the panel
        revalidate();
        repaint();
    }

    private void saveProfiles(){
        controller.saveProfiles(nameFields, usernameFields, passwordFields, typeFields, windowsCheckboxes,
                doorsCheckboxes, garageCheckboxes, lightsCheckboxes);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Profile Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        ProfilePanel profilePanel = new ProfilePanel();
        frame.add(profilePanel);
        frame.setVisible(true);
    }
}




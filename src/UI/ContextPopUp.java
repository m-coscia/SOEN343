package src.UI;

import src.Controller;
import src.components.Room;
import src.components.RoomType;
import src.logic.Parent;
import src.logic.Permissions;
import src.logic.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * TODO:
 *  1. display current users and their location in the home
 *  2. allow the user to move profiles to separate rooms
 *  3. Log into another profile
 *  4. turn on/off auto mode;
 *
 */

public class ContextPopUp{

    private JPanel popUpPanel;
    private final Controller controller = Controller.getController();
    private JPanel titlePanel;
    private JButton saveChangesButton;
    private JPanel profilesPanel;
    private JPanel bottomPanel;
    private JComboBox comboBox1;
    private JSpinner tempSpinner;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private JPanel profilesMainPanel;
    private JPanel profileMainPanel;
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private String[] updatedLocations;
    private ArrayList<Profile> profiles;
    private JFrame dashboard;

    public ContextPopUp(Profile currentProfile, JFrame dashboard){
        this.dashboard = dashboard;
        profiles = controller.getProfiles();
        popUpPanel.add(titlePanel);
        setProfilePanel();
        setUpCheckboxes();
        setUpTempSpinner();
       // setUpTimeEdit();
//        setUpDateEdit();
        setUpSave();

    }

    public JPanel getPopupContent(){
        return popUpPanel;
    }

    private void setProfilePanel(){
        updatedLocations = new String[profiles.size()];

        profilesMainPanel = new JPanel(new GridLayout(profiles.size() + 1, 3));

        JLabel profileLabel = new JLabel("Profile");

        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilesMainPanel.add(profileLabel);

        JLabel locationLabel = new JLabel("Location");
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilesMainPanel.add(locationLabel);

        JLabel loggedLabel = new JLabel("Logged In");
        loggedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilesMainPanel.add(loggedLabel);

        int count =0;
        for (Profile p : profiles){
            updatedLocations[count] = "-1";
            System.out.println("setting up profile " + p.getName());
            JLabel name = new JLabel(p.getName());
            name.setHorizontalAlignment(SwingConstants.CENTER);
            profilesMainPanel.add(name);


            JComboBox<String> location = setComboBoxLocations();
            for (int i = 0; i <location.getItemCount(); i++) {
                StringTokenizer st = new StringTokenizer(location.getItemAt(i), " ");
                System.out.print(st.nextToken() + " ");
                int loc = Integer.parseInt(st.nextToken());

                //display the room the user is in based on room id's instead of just names of rooms
                if (p.getLocation().getId() == loc) {
                    System.out.println(loc);
                    location.setSelectedItem(location.getItemAt(i));
                    break;
                }
            }
            setUpLocationChange(location, count);
            profilesMainPanel.add(location);

            JCheckBox loggedInCheck = new JCheckBox();
            checkBoxes.add(loggedInCheck);
            loggedInCheck.setHorizontalAlignment(SwingConstants.CENTER);
            if(controller.getCurrentLoggedInUser() == p){
                loggedInCheck.setSelected(true);
            }
            profilesMainPanel.add(loggedInCheck);
            count++;
        }

        popUpPanel.add(titlePanel,BorderLayout.CENTER);
        //popUpPanel.add(profilesMainPanel);
        profilesPanel.add(profilesMainPanel);
        profilesPanel.add(bottomPanel, BorderLayout.SOUTH);
        popUpPanel.add(profilesPanel);
    }

    private JComboBox<String> setComboBoxLocations(){
        ArrayList<Room> rooms = controller.getRooms();

        String[] roomArray = new String[rooms.size()];

        for(int i = 0; i < rooms.size(); i++ ){
            roomArray[i] = rooms.get(i).getType().toString() + " " + rooms.get(i).getId();
        }

        return new JComboBox<>(roomArray);
    }

    private void setUpCheckboxes(){
        //for every checkbox in the list
        for(JCheckBox c: checkBoxes) {
            //add action listener
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //uncheck any checkbox that was previously check bcz only one must be logged in at a time
                    for (JCheckBox c2: checkBoxes){
                        if(c2 != c){
                            c2.setSelected(false);
                        }
                    }
                }
            });
        }

    }

    private void setUpLocationChange(JComboBox locationBox, int index){
        locationBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatedLocations[index] = String.valueOf(locationBox.getSelectedIndex());
                System.out.println(locationBox.getModel().getSelectedItem());
            }
        });
    }

    private void setUpSave(){
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeProfileLocations();
                Profile p = changeLoggedInUser();
                changeTemperature();

                dashboard.dispose();
                dashboard = new Dashboard(p);
                dashboard.setVisible(true);
            }
        });
    }

    private void changeProfileLocations(){

        //check the locations to see which were updated
        for(int i = 0; i < updatedLocations.length; i++){
            if(profiles.get(i).getLocation().getId() != Integer.parseInt(updatedLocations[i])){
                controller.changeUserLocation(profiles.get(i),Integer.parseInt(updatedLocations[i]));

                if(Integer.parseInt(updatedLocations[i] )!= -1) {
                    System.out.println("location of profile " + profiles.get(i).getName() + " was changed from " +
                            profiles.get(i).getLocation().toString() + "to room with id " + updatedLocations[i]);
                }
            }
        }
    }

    private Profile changeLoggedInUser(){

        for(int i = 0; i < checkBoxes.size(); i++){
            if(checkBoxes.get(i).isSelected()){
                controller.login(profiles.get(i));
                return profiles.get(i);
            }
        }

        return null;
    }

    private void changeTemperature(){
        if(!String.valueOf(controller.getTemperature()).equals(tempSpinner.getModel().getValue())){
            int temp = (int) tempSpinner.getModel().getValue();
            controller.setWeather(Double.valueOf(temp));
        }
    }

    private void setUpTempSpinner(){
        tempSpinner.getModel().setValue(controller.getTemperature());
    }
    public static void main(String[] args) {

        ArrayList<Profile> pfs = new ArrayList<Profile>();

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


        Profile naika = new Parent("NAIKA", "N", "N", null);
        Profile n2= new Parent("asmae", "N", "N", null);
        Profile n3 = new Parent("asmae", "N", "N", null);

        naika.setRoom(r1);
        n2.setRoom(r3);
        n3.setRoom(r4);


        pfs.add(naika);
        pfs.add(n2);
        pfs.add(n3);


        JFrame testFrame = new JFrame();
        JLabel popupLabel = new JLabel("This is a popup");

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.add(popupLabel);

        Popup p = new PopupFactory().getPopup(testFrame,panel,180,100);
        JButton b = new JButton();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.show();
            }
        });

        JPanel TEST = new JPanel();
        TEST.add(b);
        testFrame.add(TEST);
        testFrame.setSize(400,400);
        testFrame.setVisible(true);
        testFrame.setLocationRelativeTo(null);

    }
}

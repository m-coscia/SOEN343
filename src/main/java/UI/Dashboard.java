package UI;

import controller.Controller;
import components.Clock;
import components.Room;
import components.RoomType;
import logic.Profile;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    private Clock  clock = new Clock();
    private Profile currentProfile;

    public Dashboard(Profile loggedIn, Profile profile){

        setProfileInfo(profile);

        tempLabel.setText("Oustide Temp. " + controller.getTemperature() + "ºC");
        ToggleButton toggle = new ToggleButton();
        toggle.setSize(10,10);


        try{
            toggleButtonPanel.add(toggle, BorderLayout.CENTER);

            toggle.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    if(!clock.isRunning().get()){
                        clock.start();
                        start_stop_label.setText("Running");
                    }else {
                        clock.pause();
                        start_stop_label.setText("Paused");
                    }
                }

            });


        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {

            System.out.println(timeSlider.getMinimum()+" " + timeSlider.getMaximum() +" " + timeSlider.getExtent());
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
            list1.setListData(new String[] {"idk", "idk", "idk"});
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

    private void setProfileInfo(Profile profile){
        currentProfile = profile;
        userTypeLabel.setText(controller.getType(profile) + ": " + profile.getName());
    }
    public static void main(String[] args) {
        Profile p = new Profile("Sara", null);
        ArrayList<Profile> profiles = new ArrayList<Profile>();
        profiles.add(p);
        Room r = new Room(RoomType.BEDROOM,2,3,4,profiles);
        p.setRoom(r);
        Dashboard d = new Dashboard(null, p);
        d.setLocationRelativeTo(null);
    }
}

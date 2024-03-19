package src.UI;

import src.logic.Profile;

import javax.swing.*;
import java.util.ArrayList;

public class ProfilesLoginFrame extends JFrame {
    private JFrame previousFrame;
    private JPanel panel1;
    private JButton backButton;
    private JButton previousProfile;
    private JButton selectButton;
    private JButton nextButton;

    public ProfilesLoginFrame(JFrame previousFrame, ArrayList<Profile> profiles) {
        this.previousFrame = previousFrame;
        setUpFrame();
        add(panel1);

    }

    private void setUpFrame(){
        setTitle("Pick a Profile to log in to");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
    }


}

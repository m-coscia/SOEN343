package src.UI;

import src.Controller;
import src.logic.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogInOrCreateFrame extends JFrame{

    JFrame thisFrame = null;
    protected JPanel accountCreateForm;
    private JButton newAccountButton;
    private JButton logInButton;
    private JLabel loginLabel;
    private JLabel newAccountLabel;
    private JButton backButton;
    private Controller controller = new Controller();
    private ArrayList<Profile> profiles = controller.getProfiles();
    public LogInOrCreateFrame(JFrame previousFrame){

        setTitle("Log in to Existing Account or Create Accounts");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //to go back to the previous frame
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                previousFrame.setVisible(true);
            }
        });

        //goes to login page
        //TODO complete the login panel (its gonna be selecting a profile an choosing its location
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.getProfiles().isEmpty()){
                    NoProfilesLoginFrame nextFrame = new NoProfilesLoginFrame(thisFrame);
                    nextFrame.setVisible(true);
                    dispose();
                }else{
                    ProfilesLoginFrame nextFrame = new ProfilesLoginFrame(thisFrame, controller.getProfiles());
                    nextFrame.setVisible(true);
                    nextFrame.setLocationRelativeTo(null);
                    dispose();
                }

            }
        });

        //goes to account creation page
        newAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfilesCreation nextFrame = new ProfilesCreation(thisFrame);
                nextFrame.setVisible(true);
                dispose();
            }
        });

        thisFrame = this;
        add(accountCreateForm);
        setSize(600,600);
        setLocationRelativeTo(null);
        //setVisible(true);
    }

}

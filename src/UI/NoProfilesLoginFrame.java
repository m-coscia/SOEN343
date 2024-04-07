package src.UI;

import src.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoProfilesLoginFrame extends JFrame{

    private Controller controller =Controller.getController();
    private JFrame previousFrame;
    private JPanel framePanel;
    private JButton backButton;
    private JPanel leftSidePanel;
    private JLabel noProfileLabel;
    private JPanel cardsPanel;
//    private ArrayList<Profile> profiles;

    public NoProfilesLoginFrame(JFrame previousFrame){
        this.previousFrame = previousFrame;
 //       getProfiles();
//f(profiles.isEmpty()){
            System.out.println("There are no profiles to display");
            setContentPane(framePanel);
//        }else{
//            System.out.println("There are profiles to display. Change the panels or wtv");
//            framePanel.remove(noProfileLabel);
//        }
        setUpBackButton();
        setLocationRelativeTo(null);
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setUpBackButton() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                previousFrame.setVisible(true);

            }
        });
    }

//    private void getProfiles(){
//        profiles = controller.getProfiles();
//        for(Profile p: profiles){
//            System.out.println(p.toString());
//        }
//    }
    public static void main(String[] args) {
        new NoProfilesLoginFrame(null);
    }
}

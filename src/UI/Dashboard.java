package src.UI;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel panel1;
    private JPanel dashboardPanel;
    private JPanel userInfoPanel;
    private JPanel toggleButtonPanel;
    private JPanel lowerPanelofSimParameters;
    private JSlider timeSlider;
    private JPanel fillerPanel;
    private JPanel middleOfSimParam;
    private JButton eButton;
    private JTabbedPane tabbedPane1;

    Dashboard(){
//        setLayout(new BorderLayout());
//        JPanel test = new JPanel();
//        JButton testButton = new JButton();
//        test.add(testButton);
//
//        JPanel test1 = new JPanel();
//        test1.add(testButton);
//
//        JPanel test2 = new JPanel();
//        test2.add(testButton);
//
//        ToggleButton t = new ToggleButton();
//        testButton.add(t);
//        add(test1, BorderLayout.NORTH);
//        add(test2, BorderLayout.EAST);
//        add(test, BorderLayout.WEST);
//        add(t, BorderLayout.SOUTH);
        ToggleButton toggle = new ToggleButton();
        toggle.setSize(10,10);
        try{
            toggleButtonPanel.add(toggle, BorderLayout.CENTER);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            add(panel1);
            setSize(800, 500);
            // Center frame on screen
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }
//        add(panel1);
//        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}

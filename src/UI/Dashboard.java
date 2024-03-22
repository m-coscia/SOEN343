package src.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JPanel panel1;
    private JPanel dashboardPanel;
    private JPanel toggleButtonPanel;
    private JPanel userInfoPanel;
    private JPanel lowerPanelofSimParameters;
    private JSlider timeSlider;
    private JPanel fillerPanel;
    private JPanel middleOfSimParam;
    private JButton eButton;
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
    private JLabel profileIconLabel;
    private JTextArea consoleText;

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
            //consolePanel.add
//            testing.
//            iconPanel.add(new JLabel(new ImageIcon("src/ProfileIcon.png")));
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
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Dashboard d = new Dashboard();
        d.setLocationRelativeTo(null);

    }
}
